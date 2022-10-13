package pl.kancelaria.AHG.administration.auth.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import pl.kancelaria.AHG.common.entityModel.users.token.repository.TokenRepository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;
import pl.kancelaria.AHG.user.dto.UserPasswordDTO;

@Service
public class AuthServices {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final JwtUserDetailsService userDetailsService;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    Logger logger = LoggerFactory.getLogger(AuthServices.class);

    public AuthServices(AuthenticationManager authenticationManager, JwtTokenUtil jwtTokenUtil, JwtUserDetailsService userDetailsService, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenUtil = jwtTokenUtil;
        this.userDetailsService = userDetailsService;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public String createAuthenticationToken(JwtRequest authenticationRequest) throws Exception {
        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());
        final String token = jwtTokenUtil.generateToken(userDetails);
        return token;

    }

    public String saveNewUser(RegistrationDTO user) {
        userDetailsService.save(user);
        return user.getUsername();
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            logger.info("Uzytkownik o loginie: " + username + " zostal zalogowany do aplikacji.");
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }

    public Boolean verifyTokenAndSetPassword(UserPasswordDTO dto) {
        UserOB userOB = tokenRepository.findByToken(dto.getToken()).getFk_uzytkownik();
        if (userOB != null) {
            userOB.setStan(UserStateEnum.AKTYWNY);
            userOB.setPassword(passwordEncoder.encode(dto.getPassword()));
            userRepository.save(userOB);
            return true;
        } else {
            return false;
        }
    }

    public Boolean resetPassword(UserPasswordDTO dto) {
        UserOB userOB = tokenRepository.findByToken(dto.getToken()).getFk_uzytkownik();
        return userOB != null && extracted(dto, userOB);
    }

    private boolean extracted(UserPasswordDTO dto, UserOB userOB) {
        userOB.setPassword(passwordEncoder.encode(dto.getPassword()));
        userRepository.save(userOB);
        return true;
    }
}
