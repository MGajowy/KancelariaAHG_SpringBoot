package pl.kancelaria.AHG.administration.auth.services;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.configuration.jwt.config.JwtTokenUtil;
import pl.kancelaria.AHG.administration.configuration.jwt.model.JwtRequest;
import pl.kancelaria.AHG.administration.configuration.jwt.model.JwtResponse;
import pl.kancelaria.AHG.administration.configuration.jwt.service.JwtUserDetailsService;
import pl.kancelaria.AHG.user.dto.LoginDTO;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;

/**
 * @author Michal
 * @created 01/09/2020
 */
@Service
public class AuthServices {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;

    public AuthServices(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
    }
    public String utworzTokenAutentykacji(JwtRequest authenticationRequest) throws Exception{
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;

    }
    public String zapiszNowegoUzytkownika(RegistrationDTO user) {
        userDetailsService.save(user);
        return user.getUsername();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
