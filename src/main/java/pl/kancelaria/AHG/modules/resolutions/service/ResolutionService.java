package pl.kancelaria.AHG.modules.resolutions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.CategoriesOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.categories.repository.CategoriesRepository;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.ResolutionsOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.repository.ResolutionsRepository;
import pl.kancelaria.AHG.common.service.DateConvertService;
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
    private final DateConvertService dateConvertService;


    public ResolutionListDTO getResolutionList() {
        ResolutionListDTO response = new ResolutionListDTO();
        List<ResolutionsOB> resolutionsOBS = this.resolutionsRepository.findAll();
        if (!CollectionUtils.isEmpty(resolutionsOBS)) {
            List<ResolutionDTO> resolutionList= new ArrayList<>();
            resolutionsOBS.forEach(e -> {
                ResolutionDTO resolutionDTO = new ResolutionDTO();
                BeanUtils.copyProperties(e, resolutionDTO);
                CategoriesOB categoriesOB = this.categoriesRepository.getOne(e.getCategory().getId());
                resolutionDTO.setCategoryName(categoriesOB.getCategoryName());
                resolutionDTO.setDateAdded(dateConvertService.convertDateToString(e.getDateAdded()));
                resolutionList.add(resolutionDTO);
            });
            response.setResolutionsList(resolutionList);
        } else {
            response.setResolutionsList(new ArrayList<>());
        }
        return response;
    }

    public ResolutionListOfCategoryDTO getResolutionListByCategories(Long categoryId) {
        List<ResolutionsOB> resolutionsOBS = getListByCategory(categoryId);
        CategoriesOB categoriesOB = categoriesRepository.getOne(categoryId);

        return ResolutionListOfCategoryDTO.builder()
                .categoryName(categoriesOB.getCategoryName())
                .resolutionsList(resolutionsOBS.stream().map(ResolutionsOB::getResolutionDTO).collect(Collectors.toList()))
                .build();
    }

    private List<ResolutionsOB> getListByCategory(Long categoryId) {
        TypedQuery<ResolutionsOB> query = entityManager.createQuery(prepareResolutionListInquiry(categoryId), ResolutionsOB.class);
        query.setParameter("categoryId", categoryId);
        return query.getResultList();
    }

    private String prepareResolutionListInquiry(Long categoryId) {
        StringBuilder query = new StringBuilder("SELECT r FROM ResolutionsOB r");
        query.append(" WHERE r.category.id = :categoryId");
        query.append(" ORDER BY r.resolutionName DESC");
        return query.toString();
    }

    public ResolutionListDTO getResolutionListCB() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResolutionsOB> cq = cb.createQuery(ResolutionsOB.class);

        Root<ResolutionsOB> resolutionsOBRoot = cq.from(ResolutionsOB.class);
        cq.select(resolutionsOBRoot.get("contents"));
        cq.distinct(true);
        cq.orderBy(cb.asc(resolutionsOBRoot.get("contents")));
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
        response.setResolutionsList(resolutionList);
        return response;
    }

    public ResolutionListDTO getResolutionListByDescription(String resolutionName) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ResolutionsOB> cq = cb.createQuery(ResolutionsOB.class);

        Root<ResolutionsOB> resolutionsOBRoot = cq.from(ResolutionsOB.class);
        ParameterExpression<String> parameter = cb.parameter(String.class);
        cq.where(cb.like(cb.lower(resolutionsOBRoot.get("resolutionName")), parameter));

        TypedQuery<ResolutionsOB> query = entityManager.createQuery(cq);
        query.setParameter(parameter, "%" + resolutionName.toLowerCase() + "%");

        List<ResolutionsOB> resultList = query.getResultList();
        List<ResolutionDTO> resolutionList = new ArrayList<>();
        resultList.forEach(ob -> {
                    ResolutionDTO dto = new ResolutionDTO();
                    BeanUtils.copyProperties(ob, dto);
                    dto.setCategoryName(ob.getCategory().getCategoryName());
                    dto.setDateAdded(dateConvertService.convertDateToString(ob.getDateAdded()));
            resolutionList.add(dto);
                }
        );
        ResolutionListDTO response = new ResolutionListDTO();
        response.setResolutionsList(resolutionList);
        return response;
    }

    public ResolutionListDTO getResolutionListByDescriptionAndPages(String term, Integer pageNumber, Integer pageSize) {
        final Pageable resolutionPageable = PageRequest.of(pageNumber, pageSize, Sort.by("dateAdded").descending().and(Sort.by("resolutionName")));
        List<ResolutionsOB> listOB = resolutionsRepository.findByResolutionNameLike("%" + term + "%", resolutionPageable);
        List<ResolutionDTO> resolutionDTOList = createResponseDTO(listOB);
        ResolutionListDTO response = new ResolutionListDTO();
        response.setResolutionsList(resolutionDTOList);
        response.setTotalRecords(resolutionsRepository.countByResolutionNameLike("%" + term + "%"));
        return response;
    }

    private List<ResolutionDTO> createResponseDTO(List<ResolutionsOB> listOB) {
        List<ResolutionDTO> resolutionList = new ArrayList<>();
        listOB.forEach(element -> {
            ResolutionDTO dto = new ResolutionDTO();
            BeanUtils.copyProperties(element, dto);
            dto.setCategoryName(element.getCategory().getCategoryName());
            dto.setDateAdded(dateConvertService.convertDateToString(element.getDateAdded()));
            resolutionList.add(dto);
        });
        return resolutionList;
    }
}
