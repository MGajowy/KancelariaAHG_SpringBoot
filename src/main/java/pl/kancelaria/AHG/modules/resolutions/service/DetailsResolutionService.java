package pl.kancelaria.AHG.modules.resolutions.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.ResolutionsOB;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.repository.ResolutionsRepository;
import pl.kancelaria.AHG.common.service.DateConvertService;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;


@Service
@RequiredArgsConstructor
public class DetailsResolutionService {

    private final ResolutionsRepository resolutionsRepository;
    private final DateConvertService dateConvertService;

    public ResolutionDTO detailsResolution(long id) {
        ResolutionDTO resolutionDTO = new ResolutionDTO();
        ResolutionsOB resolutionsOB = resolutionsRepository.getOne(id);
        BeanUtils.copyProperties(resolutionsOB, resolutionDTO);
        resolutionDTO.setDateAdded(dateConvertService.convertDateToString(resolutionsOB.getDateAdded()));
        return resolutionDTO;
    }
}
