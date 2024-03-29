package pl.kancelaria.AHG.modules.regulations.restApi.pub;

import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.modules.regulations.dto.RegulationListDTO;
import pl.kancelaria.AHG.modules.regulations.service.RegulationService;

@RestController
public class RegulationPublicRestApi implements pl.kancelaria.AHG.shared.restapi.modules.regulations.restApi.pub.RegulationPublicRestApi {

    private final RegulationService regulationService;

    public RegulationPublicRestApi(RegulationService regulationService) {
        this.regulationService = regulationService;
    }

    @Override
    public RegulationListDTO getRegulationsListByName(String name) {
        return regulationService.getRegulationsListByName(name);
    }

    @Override
    public RegulationListDTO getRegulationsListByNameAndPage(String name, Integer pageNumber, Integer pageSize) {
        return regulationService.getRegulationsListByNameAndPage(name, pageNumber, pageSize);
    }
}
