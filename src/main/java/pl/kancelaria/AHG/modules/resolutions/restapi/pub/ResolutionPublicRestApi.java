package pl.kancelaria.AHG.modules.resolutions.restapi.pub;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResolutionListDTO getResolutionListByDescription(String opis) {
        return resolutionService.getResolutionListByDescription(opis);
    }

    @Override
    public ResolutionListDTO getResolutionListByDescriptionAndPages(String description, Integer pageNumber, Integer pageSize) {
        return resolutionService.getResolutionListByDescriptionAndPages(description, pageNumber, pageSize);
    }
}
