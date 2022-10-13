package pl.kancelaria.AHG.shared.restapi.auth.restApi.pub;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.kancelaria.AHG.administration.configuration.jwt.model.JwtRequest;
import pl.kancelaria.AHG.shared.restapi.RestApiUrlStale;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;
import pl.kancelaria.AHG.user.dto.UserPasswordDTO;

import javax.ws.rs.POST;
import javax.ws.rs.Path;


@Path(value = RestApiUrlStale.REST_PATH)
@RequestMapping(value = RestApiUrlStale.REST_PATH )
public interface IAuthPublicRestApi {

    @POST
    @PostMapping(RestApiUrlStale.LOGIN)
    @Path(RestApiUrlStale.LOGIN)
    ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception;

    @POST
    @PostMapping(RestApiUrlStale.REJESTRACJA)
    @Path(RestApiUrlStale.REJESTRACJA)
    ResponseEntity<?> saveUser(@RequestBody RegistrationDTO user);

    @POST
    @PostMapping (RestApiUrlStale.USTAW_HASLO)
    @Path(RestApiUrlStale.USTAW_HASLO)
    Boolean verifyTokenAndSetPassword(@RequestBody UserPasswordDTO dto);

    @POST
    @PostMapping (RestApiUrlStale.RESET_HASLA)
    @Path(RestApiUrlStale.RESET_HASLA)
    Boolean resetPassword(@RequestBody UserPasswordDTO dto);


}
