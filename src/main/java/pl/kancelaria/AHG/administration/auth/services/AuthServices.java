package pl.kancelaria.AHG.administration.auth.services;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.configuration.jwt.config.JwtTokenUtil;
import pl.kancelaria.AHG.administration.configuration.jwt.model.JwtRequest;
import pl.kancelaria.AHG.administration.configuration.jwt.service.JwtUserDetailsService;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.entityModel.users.token.repository.TokenRepository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;
import pl.kancelaria.AHG.user.dto.UserPasswordDTO;

import java.util.Optional;

@Service
@AllArgsConstructor
@Log4j2
public class AuthServices {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final EventLogService eventLogService;

    public String createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    public ResponseEntity<HttpStatus> saveNewUser(RegistrationDTO user) {
        try {
            userDetailsService.save(user);
            log.info("Rejestracja użytkownika o adresie email: " + user.getEmail());
            eventLogService.createLog(EventLogConstants.REJESTARCJA_NOWEGO_UZYTKOWNIKA, user.getUsername());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Bład podczas rejestarcji. " + e.getMessage());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
//        return user.getUsername();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            log.info("Uzytkownik o loginie: " + username + " zostal zalogowany do aplikacji.");
            eventLogService.createLog(EventLogConstants.LOG_IN, username);
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public Boolean verifyTokenAndSetPassword(UserPasswordDTO dto) {
        UserOB userOB = tokenRepository.findByToken(dto.getToken()).getUser();
        if (userOB != null) {
            userOB.setActivationState(UserStateEnum.AKTYWNY);
            userOB.setPassword(passwordEncoder.encode(dto.getPassword()));
            userRepository.save(userOB);
            return true;
        } else {
            return false;
        }
    }

    public Boolean resetPassword(UserPasswordDTO dto) {
        UserOB userOB = tokenRepository.findByToken(dto.getToken()).getUser();
        return userOB != null && extracted(dto, userOB);
    }

    private boolean extracted(UserPasswordDTO dto, UserOB userOB) {
        userOB.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(userOB);
        return true;
    }

    public Boolean checkLogin(String login) {
        if (verifyLogin(login))
            return false;

        Optional<UserOB> user = userRepository.findByUserName(login);
        return !user.isPresent();
    }

    private boolean verifyLogin(String login) {
        return login.equalsIgnoreCase("admin") || login.contains("admin") ||
                login.equalsIgnoreCase("undefind") || login.equalsIgnoreCase("null");
    }
}
