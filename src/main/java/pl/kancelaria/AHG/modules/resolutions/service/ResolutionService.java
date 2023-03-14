package pl.kancelaria.AHG.modules.resolutions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.ResolutionsOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.repository.ResolutionRepositoryPage;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.repository.ResolutionsRepository;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListOfCategoryDTO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ResolutionService {

    private final ResolutionsRepository resolutionsRepository;
    private final CategoriesRepository categoriesRepository;
    private final EntityManager entityManager;
    private final ResolutionRepositoryPage resolutionRepositoryPage;


    public ResolutionListDTO getResolutionList() {
        ResolutionListDTO response = new ResolutionListDTO();
        List<ResolutionsOB> resolutionsOBS = this.resolutionsRepository.findAll();
        if (!CollectionUtils.isEmpty(resolutionsOBS)) {
            List<ResolutionDTO> resolutionList= new ArrayList<>();
            resolutionsOBS.forEach(e -> {
                ResolutionDTO daneDTO = new ResolutionDTO();
                BeanUtils.copyProperties(e, daneDTO);
                CategoriesOB categoriesOB = this.categoriesRepository.getOne(e.getKategoria().getId());
                daneDTO.setNazwaKategorii(categoriesOB.getRodzajKategorii());
                resolutionList.add(daneDTO);
            });
            response.setListaUchwal(resolutionList);
        } else {
            response.setListaUchwal(new ArrayList<>());
        }
        return response;
    }

    public ResolutionListOfCategoryDTO getResolutionListByCategories(Long idKategorii) {
        List<ResolutionsOB> resolutionsOBS = getListByCategory(idKategorii);
        CategoriesOB categoriesOB = categoriesRepository.getOne(idKategorii);

        return ResolutionListOfCategoryDTO.builder()
                .nazwaKategorii(categoriesOB.getRodzajKategorii())
                .listaUchwal(resolutionsOBS.stream().map(ResolutionsOB::getResolutionDTO).collect(Collectors.toList()))
                .build();
    }

    private List<ResolutionsOB> getListByCategory(Long idKategorii) {
        TypedQuery<ResolutionsOB> query = entityManager.createQuery(prepareResolutionListInquiry(idKategorii), ResolutionsOB.class);
        query.setParameter("idKategoria", idKategorii);
        return query.getResultList();
    }

    private String prepareResolutionListInquiry(Long idKategorii) {
        StringBuilder query = new StringBuilder("SELECT r FROM ResolutionsOB r");
        query.append(" WHERE r.kategoria.id = :idKategoria");
        query.append(" ORDER BY r.opis DESC");
        return query.toString();
    }

    public ResolutionListDTO getResolutionListCB() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResolutionsOB> cq = cb.createQuery(ResolutionsOB.class);

        Root<ResolutionsOB> resolutionsOBRoot = cq.from(ResolutionsOB.class);
        cq.select(resolutionsOBRoot.get("tresc"));
        cq.distinct(true);
        cq.orderBy(cb.asc(resolutionsOBRoot.get("tresc")));
        CriteriaQuery<ResolutionsOB> select = cq.select(resolutionsOBRoot);

        TypedQuery<ResolutionsOB> query = entityManager.createQuery(select);
        List<ResolutionsOB> resultList = query.getResultList();

        List<ResolutionDTO> resolutionList = new ArrayList<>();
        resultList.forEach(r -> {
                    ResolutionDTO dto = new ResolutionDTO();
                    BeanUtils.copyProperties(r, dto);
            resolutionList.add(dto);
                }
        );
        ResolutionListDTO response = new ResolutionListDTO();
        response.setListaUchwal(resolutionList);
        return response;
    }

    public ResolutionListDTO getResolutionListByDescription(String opis) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResolutionsOB> cq = cb.createQuery(ResolutionsOB.class);

        Root<ResolutionsOB> resolutionsOBRoot = cq.from(ResolutionsOB.class);
        ParameterExpression<String> parameter = cb.parameter(String.class);
        cq.where(cb.like(cb.lower(resolutionsOBRoot.get("opis")), parameter));

        TypedQuery<ResolutionsOB> query = entityManager.createQuery(cq);
        query.setParameter(parameter, "%" + opis.toLowerCase() + "%");

        List<ResolutionsOB> resultList = query.getResultList();
        List<ResolutionDTO> resolutionList = new ArrayList<>();
        resultList.forEach(r -> {
                    ResolutionDTO dto = new ResolutionDTO();
                    BeanUtils.copyProperties(r, dto);
                    dto.setNazwaKategorii(r.getKategoria().getRodzajKategorii());
            resolutionList.add(dto);
                }
        );
        ResolutionListDTO response = new ResolutionListDTO();
        response.setListaUchwal(resolutionList);
        return response;
    }

    public ResolutionListDTO getResolutionListByDescriptionAndPages(String description, Integer pageNumber, Integer pageSize) {
        final Pageable resolutionPageable = PageRequest.of(pageNumber, pageSize);
        List<ResolutionsOB> allByDescription = resolutionRepositoryPage.findByOpisLike("%" + description + "%", resolutionPageable);
        List<ResolutionDTO> resolutionDTOList = createResponseDTO(allByDescription);
        ResolutionListDTO listDTO = new ResolutionListDTO();
        listDTO.setListaUchwal(resolutionDTOList);
        listDTO.setTotalRecords(resolutionRepositoryPage.countByOpisLike("%" + description + "%"));
        return listDTO;
    }

    private List<ResolutionDTO> createResponseDTO(List<ResolutionsOB> allByDescription) {
        List<ResolutionDTO> resolutionList = new ArrayList<>();
        allByDescription.forEach(element -> {
            ResolutionDTO dto = new ResolutionDTO();
            BeanUtils.copyProperties(element, dto);
            dto.setNazwaKategorii(element.getKategoria().getRodzajKategorii());
            resolutionList.add(dto);
        });
        return resolutionList;
    }
}
