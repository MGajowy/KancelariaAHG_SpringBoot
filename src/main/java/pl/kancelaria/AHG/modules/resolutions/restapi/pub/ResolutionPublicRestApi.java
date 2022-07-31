package pl.kancelaria.AHG.modules.resolutions.restapi.pub;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListDTO;
import pl.kancelaria.AHG.modules.resolutions.dto.ResolutionListOfCategoryDTO;
import pl.kancelaria.AHG.modules.resolutions.service.ResolutionService;


@RestController
public class ResolutionPublicRestApi implements pl.kancelaria.AHG.shared.restapi.modules.resolutions.restapi.pub.ResolutionPublicRestApi {

    private final ResolutionService resolutionService;

    @Autowired
    public ResolutionPublicRestApi(ResolutionService resolutionService) {
        this.resolutionService = resolutionService;
    }

    @Override
    public ResolutionListDTO pobierzListeUchwalDto() {
        return resolutionService.pobierzListeUchwal();
    }

    @Override
    public ResolutionListOfCategoryDTO pobierzListeUchwalPoKategorii(long id) {
        return resolutionService.pobierzListeUchwalPoKategorii(id);
    }

    @Override
    public ResolutionListDTO pobierzListeUchwalCB() {
        return resolutionService.pobierzListeUchwalCB();
    }

    @Override
    public ResolutionListDTO pobierzListeUchwalPoOpisie(String opis) {
        return resolutionService.pobierzListeUchwalPoOpisieCB(opis);
    }
}
