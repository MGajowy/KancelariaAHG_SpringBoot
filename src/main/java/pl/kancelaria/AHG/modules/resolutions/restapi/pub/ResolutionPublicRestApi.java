package pl.kancelaria.AHG.modules.resolutions.restapi.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListOfCategoryDTO;
import pl.kancelaria.AHG.modules.resolutions.service.ResolutionService;


@RestController
@RequiredArgsConstructor
public class ResolutionPublicRestApi implements pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.pub.ResolutionPublicRestApi {

    private final ResolutionService resolutionService;

    @Override
    public ResolutionListDTO getResolutionList() {
        return resolutionService.getResolutionList();
    }

    @Override
    public ResolutionListOfCategoryDTO getResolutionListByCategories(long id) {
        return resolutionService.getResolutionListByCategories(id);
    }

    @Override
    public ResolutionListDTO getResolutionListCB() {
        return resolutionService.getResolutionListCB();
    }

    @Override
    public ResolutionListDTO getResolutionListByDescription(String resolutionName) {
        return resolutionService.getResolutionListByDescription(resolutionName);
    }

    @Override
    public ResolutionListDTO getResolutionListByDescriptionAndPages(String term, Integer pageNumber, Integer pageSize) {
        return resolutionService.getResolutionListByDescriptionAndPages(term, pageNumber, pageSize);
    }
}
