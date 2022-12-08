package pl.kancelaria.AHG.modules.about.onlineHelp.restApi;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.WebService.SOAP.ReputationClientService;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.GetReputation;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.Reputation;
import pl.kancelaria.AHG.modules.about.onlineHelp.dto.OnlineHelpRequestDto;
import pl.kancelaria.AHG.modules.about.onlineHelp.service.OnlineHelpService;
import pl.kancelaria.AHG.shared.restapi.modules.about.onlineHelp.pub.IOnlineHelpPublicRestApi;

import java.net.MalformedURLException;
import java.util.List;

@RestController
public class OnlineHelpPublicRestApi implements IOnlineHelpPublicRestApi {

    private final OnlineHelpService onlineHelpService;
    private final ReputationClientService reputationClientService;

    public OnlineHelpPublicRestApi(OnlineHelpService onlineHelpService, ReputationClientService reputationClientService) {
        this.onlineHelpService = onlineHelpService;
        this.reputationClientService = reputationClientService;
    }

    @Override
    public ResponseEntity<HttpStatus> sendEmailNotification(OnlineHelpRequestDto request) {
        return onlineHelpService.sendEmailNotification(request);
    }

    @Override
    public Reputation getReputationById(GetReputation reputation) throws MalformedURLException {
        return reputationClientService.getReputationById(reputation);
    }

    @Override
    public List<Reputation> getAllReputation() throws MalformedURLException {
        return reputationClientService.getAllReputation();
    }
}
