package pl.kancelaria.AHG.modules.regulations.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.RegulationOB;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.repository.RegulationRepository;
import pl.kancelaria.AHG.common.service.DateConvertService;
import pl.kancelaria.AHG.modules.regulations.dto.RegulationDTO;
import pl.kancelaria.AHG.modules.regulations.dto.RegulationListDTO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RegulationService {
    private final EntityManager entityManager;
    private final RegulationRepository regulationRepository;
    private final DateConvertService dateConvertService;

    public RegulationDTO detailsRegulation(long id) {
        RegulationDTO regulationDTO = new RegulationDTO();
        RegulationOB regulationOB = regulationRepository.getOne(id);
        BeanUtils.copyProperties(regulationOB, regulationDTO);
        regulationDTO.setDateAdded(dateConvertService.convertDateToString(regulationOB.getDateAdded()));
        return regulationDTO;
    }

    public RegulationListDTO getRegulationsListByName(String nazwa) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<RegulationOB> cq = cb.createQuery(RegulationOB.class);

        Root<RegulationOB> regulationOBRoot = cq.from(RegulationOB.class);
        ParameterExpression<String> parameter = cb.parameter(String.class);
        cq.where(cb.like(cb.lower(regulationOBRoot.get("nazwa")), parameter));

        TypedQuery<RegulationOB> query = entityManager.createQuery(cq);
        query.setParameter(parameter, "%" + nazwa.toLowerCase() + "%");

        List<RegulationOB> resultList = query.getResultList();
        List<RegulationDTO> regulationList = new ArrayList<>();
        resultList.forEach(ob -> {
                    RegulationDTO dto = new RegulationDTO();
                    BeanUtils.copyProperties(ob, dto);
                    dto.setCategoryName(ob.getCategory().getRodzajKategorii());
                    dto.setDateAdded(dateConvertService.convertDateToString(ob.getDateAdded()));
                    regulationList.add(dto);
                }
        );
        RegulationListDTO regulationListDTO = new RegulationListDTO();
        regulationListDTO.setRegulationList(regulationList);
        regulationListDTO.setTotalRecords(regulationRepository.countByRegulationNameLike("%" + nazwa + "%"));
        return regulationListDTO;
    }

    public RegulationListDTO getRegulationsListByNameAndPage(String term, Integer pageNumber, Integer pageSize) {
        final Pageable regulationPageable = PageRequest.of(pageNumber, pageSize, Sort.by("dateAdded").descending().and(Sort.by("regulationName")));
        List<RegulationOB> allByNazwa = regulationRepository.findByRegulationNameLike("%" + term + "%", regulationPageable);
        List<RegulationDTO> regulationDTOList = createResponseDTO(allByNazwa);
        RegulationListDTO regulationListDTO = new RegulationListDTO();
        regulationListDTO.setRegulationList(regulationDTOList);
        regulationListDTO.setTotalRecords(regulationRepository.countByRegulationNameLike("%" + term + "%"));
        return regulationListDTO;
    }

    private List<RegulationDTO> createResponseDTO(List<RegulationOB> allByNazwa) {
        List<RegulationDTO> regulationList = new ArrayList<>();
        allByNazwa.forEach(ob -> {
            RegulationDTO dto = new RegulationDTO();
            BeanUtils.copyProperties(ob, dto);
            dto.setCategoryName(ob.getCategory().getRodzajKategorii());
            dto.setDateAdded(dateConvertService.convertDateToString(ob.getDateAdded()));
            regulationList.add(dto);
        });
        return regulationList;
    }

}
