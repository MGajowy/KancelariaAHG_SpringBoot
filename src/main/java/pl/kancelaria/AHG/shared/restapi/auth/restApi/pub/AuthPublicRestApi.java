package pl.kancelaria.AHG.shared.restapi.auth.restApi.pub;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.administration.configuration.jwt.model.JwtRequest;
import pl.kancelaria.AHG.administration.configuration.jwt.model.JwtResponse;
import pl.kancelaria.AHG.shared.restapi.RestApiUrlStale;
import pl.kancelaria.AHG.shared.restapi.users.restapi.secured.UserSecuredRestApiUrl;
import pl.kancelaria.AHG.user.dto.LoginDTO;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;

import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Michal
 * @created 01/09/2020
 */
@Path(value = RestApiUrlStale.REST_PATH)
@RequestMapping(value = RestApiUrlStale.REST_PATH )
public interface AuthPublicRestApi {

    @POST
    @PostMapping(RestApiUrlStale.LOGIN)
    @Path(RestApiUrlStale.LOGIN)
    ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception;

    @POST
    @PostMapping(RestApiUrlStale.REJESTRACJA)
    @Path(RestApiUrlStale.REJESTRACJA)
    ResponseEntity<?> saveUser(@RequestBody RegistrationDTO user);

}
