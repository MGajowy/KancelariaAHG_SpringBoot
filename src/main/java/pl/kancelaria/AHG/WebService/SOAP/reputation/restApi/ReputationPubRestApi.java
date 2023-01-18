package pl.kancelaria.AHG.WebService.SOAP.reputation.restApi;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.WebService.SOAP.reputation.service.ReputationClientService;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.AddReputation;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.GetReputation;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.Reputation;
import pl.kancelaria.AHG.shared.restapi.reputations.pub.ReputationPublicRestApi;

import java.net.MalformedURLException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ReputationPubRestApi implements ReputationPublicRestApi {

    private final ReputationClientService reputationClientService;

    @Override
    public Reputation getReputationById(GetReputation request) throws MalformedURLException {
        return reputationClientService.getReputationById(request);
    }

    @Override
    public List<Reputation> getAllReputation() throws MalformedURLException {
        return reputationClientService.getAllReputation();
    }

    @Override
    public ResponseEntity<HttpStatus> addReputation(AddReputation request) throws MalformedURLException {
        return reputationClientService.addReputation(request);
    }

    @Override
    public String addLikeReputation(long id) throws MalformedURLException {
        return reputationClientService.addLikeReputation(id).toString();
    }

    @Override
    public String addNotLikeReputation(long id) throws MalformedURLException {
        return reputationClientService.addNotLikeReputation(id).toString();
    }
}
