package pl.kancelaria.AHG.administration.auth.restApi.pub;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.administration.auth.services.AuthServices;
import pl.kancelaria.AHG.administration.configuration.jwt.model.JwtRequest;
import pl.kancelaria.AHG.administration.configuration.jwt.model.JwtResponse;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;
import pl.kancelaria.AHG.user.dto.UserPasswordDTO;

@RestController
@CrossOrigin
public class IAuthPublicRestApi implements pl.kancelaria.AHG.shared.restapi.auth.restApi.pub.IAuthPublicRestApi {

    private final AuthServices authServices;

    public IAuthPublicRestApi(AuthServices authServices) {
        this.authServices = authServices;
    }

    @Override
    public ResponseEntity<?> createAuthenticationToken(JwtRequest authenticationRequest) throws Exception  {
        String token = authServices.utworzTokenAutentykacji(authenticationRequest);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    @Override
    public ResponseEntity<?> saveUser(RegistrationDTO user)  {
       String userNew = authServices.zapiszNowegoUzytkownika(user);
        return ResponseEntity.ok(userNew);
    }

    @Override
    public Boolean aktywacjaHasla(UserPasswordDTO dto) {
       Boolean wynik =  authServices.weryfikujTokeniUstawHaslo(dto);
        return wynik;
    }

    @Override
    public Boolean resetHasla(UserPasswordDTO dto) {
        Boolean wynik = authServices.resetujHaslo(dto);
        return wynik;
    }
}
