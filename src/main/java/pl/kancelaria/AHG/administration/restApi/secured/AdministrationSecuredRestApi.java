package pl.kancelaria.AHG.administration.restApi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.administration.dto.EventLogListDTO;
import pl.kancelaria.AHG.administration.services.EventLogService;

/**
 * @author Michal
 * @created 29/07/2020
 */
@RestController
public class AdministrationSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.administration.restapi.secured.AdministrationSecuredRestApi {

    private final EventLogService eventLogService;

    @Autowired
    public AdministrationSecuredRestApi(EventLogService eventLogService) {
        this.eventLogService = eventLogService;
    }

    @Override
    public EventLogListDTO pobierzDziennikZdarzenDto() {
       return this.eventLogService.pobierzDziennikZdarzen();
    }
}
