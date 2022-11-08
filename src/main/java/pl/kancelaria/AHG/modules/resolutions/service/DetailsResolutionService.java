package pl.kancelaria.AHG.modules.resolutions.service;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.common.entityModel.resolutions.resolutions.repository.ResolutionsRepository;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;


@Service
public class DetailsResolutionService {

    private final ResolutionsRepository resolutionsRepository;

    public DetailsResolutionService(ResolutionsRepository resolutionsRepository) {
        this.resolutionsRepository = resolutionsRepository;
    }

    public ResolutionDTO detailsResolution(long id) {
        ResolutionDTO resolutionDTO = new ResolutionDTO();
        BeanUtils.copyProperties(resolutionsRepository.getOne(id), resolutionDTO);
        return resolutionDTO;
    }
}
