package pl.kancelaria.AHG.modules.resolutions.service;

import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.comon.model.resolutions.resolutions.repository.ResolutionsRepository;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionDTO;

/**
 * @author Michal
 * @created 10/01/2021
 */
@Service
public class DetailsResolutionService {

    private final ResolutionsRepository resolutionsRepository;

    public DetailsResolutionService(ResolutionsRepository resolutionsRepository) {
        this.resolutionsRepository = resolutionsRepository;
    }

    public ResolutionDTO szczegolyUchwaly(long id) {
        return null;
    }
}
