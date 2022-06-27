package pl.kancelaria.AHG.administration.restApi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.administration.dto.EventLogListDTO;
import pl.kancelaria.AHG.administration.services.EventLogService;


@RestController
public class IAdministrationSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.administration.restapi.secured.IAdministrationSecuredRestApi {

    private final EventLogService eventLogService;

    @Autowired
    public IAdministrationSecuredRestApi(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }

    @Override
    public EventLogListDTO pobierzDziennikZdarzenDto() {
       return this.eventLogService.pobierzDziennikZdarzen();
    }
}
