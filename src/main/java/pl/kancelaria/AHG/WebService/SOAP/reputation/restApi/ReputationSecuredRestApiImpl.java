package pl.kancelaria.AHG.WebService.SOAP.reputation.restApi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.WebService.SOAP.reputation.service.ReputationClientService;
import pl.kancelaria.AHG.shared.restapi.reputations.secured.ReputationSecuredRestApi;

import java.net.MalformedURLException;

@RestController
@RequiredArgsConstructor
public class ReputationSecuredRestApiImpl  implements ReputationSecuredRestApi {

    private final ReputationClientService reputationClientService;

    @Override
    public ResponseEntity<HttpStatus> deleteReputation(long id) throws MalformedURLException {
        return reputationClientService.deleteReputation(id);
    }
}
