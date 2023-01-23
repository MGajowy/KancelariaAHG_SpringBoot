package pl.kancelaria.AHG.shared.restapi.reputations.pub;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.AddReputation;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.GetReputation;
import pl.kancelaria.AHG.WebService.SOAP.wsdlReputation.Reputation;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import java.net.MalformedURLException;
import java.util.List;

@Path(value = ReputationPublicRestApiUrl.PATH_REPUTATION)
@RequestMapping(value = ReputationPublicRestApiUrl.PATH_REPUTATION)
public interface ReputationPublicRestApi {

    @GET
    @GetMapping (ReputationPublicRestApiUrl.REPUTATION_GET_ONE)
    @Path(ReputationPublicRestApiUrl.REPUTATION_GET_ONE)
    Reputation getReputationById(@RequestBody GetReputation request) throws MalformedURLException;

    @GET
    @GetMapping(ReputationPublicRestApiUrl.REPUTATION_GET_ALL)
    @Path(ReputationPublicRestApiUrl.REPUTATION_GET_ALL)
    List<Reputation> getAllReputation() throws MalformedURLException;

    @POST
    @PostMapping(ReputationPublicRestApiUrl.REPUTATION_ADD)
    @Path(ReputationPublicRestApiUrl.REPUTATION_ADD)
    ResponseEntity<HttpStatus> addReputation(@RequestBody AddReputation request) throws MalformedURLException;

    @GET
    @GetMapping(ReputationPublicRestApiUrl.REPUTATION_ADD_LIKE + "/{id}")
    @Path(ReputationPublicRestApiUrl.REPUTATION_ADD_LIKE)
    String addLikeReputation(@PathVariable(value = "id") long id) throws MalformedURLException;

    @GET
    @GetMapping(ReputationPublicRestApiUrl.REPUTATION_ADD_NOT_LIKE + "/{id}")
    @Path(ReputationPublicRestApiUrl.REPUTATION_ADD_NOT_LIKE)
    String addNotLikeReputation(@PathVariable(value = "id") long id) throws MalformedURLException;
}
