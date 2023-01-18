package pl.kancelaria.AHG.shared.restapi.reputations.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.ws.rs.DELETE;
import javax.ws.rs.Path;

@Path(value = ReputationSecuredRestApiUrl.PATH_REPUTATION)
@RequestMapping(value = ReputationSecuredRestApiUrl.PATH_REPUTATION)
public interface ReputationSecuredRestApi {

    @DELETE
    @Path(ReputationSecuredRestApiUrl.REPUTATION_DELETE)
    @DeleteMapping(ReputationSecuredRestApiUrl.REPUTATION_DELETE + "/{id}")
    ResponseEntity<HttpStatus> deleteReputation(@PathVariable(value = "id") long id);
}
