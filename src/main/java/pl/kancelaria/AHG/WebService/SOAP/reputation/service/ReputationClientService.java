package pl.kancelaria.AHG.WebService.SOAP.reputation.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;
import java.util.Objects;

@Service
public class ReputationClientService implements ReputationServiceConnect {

    @Value("${reputationClientService.wsdl}")
    private String endpointValueConfiguration;

    public Reputation getReputationById(GetReputation rep) throws MalformedURLException {
        GetResponse response = getReputationService().getReputation(rep);
        return response.getReputation();
    }

    public List<Reputation> getAllReputation() throws MalformedURLException {
        GetAllReputation getAllReputation = new GetAllReputation();
        GetAllReputationResponse response = getReputationService().getAllReputation(getAllReputation);
        return response.getReputation();
    }

    public ResponseEntity<HttpStatus> addReputation(AddReputation reputation) throws MalformedURLException {
        AddReputationResponse response = getReputationService().addReputation(reputation);
        return response == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CREATED);
    }

    public Long addLikeReputation(Long request) throws MalformedURLException {
        return getReputationService().addLikeReputation(request);
    }

    public Long addNotLikeReputation(Long request) throws MalformedURLException {
        return getReputationService().addNotLikeReputation(request);
    }

    public ResponseEntity<HttpStatus> deleteReputation(Long request) throws MalformedURLException {
        String result = getReputationService().deleteReputation(request);
        return Objects.equals(result, "ok") ? new ResponseEntity<>(HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ReputationInformationService getReputationService() throws MalformedURLException {
        final String endpoint = endpointValueConfiguration;
        final URL url = URI.create(endpoint).toURL();
        final ReputationService service = new ReputationService(url);
        return service.getPort(ReputationInformationService.class);
    }
}
