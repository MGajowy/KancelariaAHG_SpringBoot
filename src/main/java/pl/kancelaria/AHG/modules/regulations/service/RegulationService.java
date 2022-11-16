package pl.kancelaria.AHG.modules.regulations.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.RegulationOB;
import pl.kancelaria.AHG.common.entityModel.regulations.regulation.repository.RegulationRepository;
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
public class RegulationService {
    private final EntityManager entityManager;
    private final RegulationRepository regulationRepository;

    public RegulationService(EntityManager entityManager, RegulationRepository regulationRepository) {
        this.entityManager = entityManager;
        this.regulationRepository = regulationRepository;
    }

    public RegulationDTO detailsRegulation(long id) {
        RegulationDTO regulationDTO = new RegulationDTO();
        BeanUtils.copyProperties(regulationRepository.getOne(id), regulationDTO);
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
        resultList.forEach(r -> {
                    RegulationDTO dto = new RegulationDTO();
                    BeanUtils.copyProperties(r, dto);
                    dto.setNazwaKategorii(r.getKategoria().getRodzajKategorii());
                    regulationList.add(dto);
                }
        );
        RegulationListDTO regulationListDTO = new RegulationListDTO();
        regulationListDTO.setListaRozporzadzen(regulationList);
        return regulationListDTO;
    }
}
