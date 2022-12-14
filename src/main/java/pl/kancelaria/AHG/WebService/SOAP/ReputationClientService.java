package pl.kancelaria.AHG.WebService.SOAP;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.List;

@Service
public class ReputationClientService implements ReputationServiceConnect{

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
        return response.getReputation() == null ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ReputationInformationService getReputationService() throws MalformedURLException {
        final String endpoint = "http://localhost:8040/ws/reputation.wsdl";
        final URL url = URI.create(endpoint).toURL();
        final ReputationService service = new ReputationService(url);
        return service.getPort(ReputationInformationService.class);
    }
}
