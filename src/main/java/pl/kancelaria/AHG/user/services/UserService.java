package pl.kancelaria.AHG.user.services;

import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.comon.model.users.token.TokenOB;
import pl.kancelaria.AHG.comon.model.users.token.repository.TokenRepository;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.comon.model.users.user.UserSexEnum;
import pl.kancelaria.AHG.comon.model.users.user.UserStateEnum;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.comon.service.MailSenderService;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author Michal
 * @created 20/08/2020
 */
@Service
public class UserService {
    private final  UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;

    public UserService(UserRepository userRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, MailSenderService mailSenderService) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;

        UserOB userOB = new UserOB();
        userOB.setImie("Adam");
        userOB.setEmail("mich@wp.pl");
        userOB.setHaslo(passwordEncoder.encode("Adam123"));
        userOB.setLogin("GAJOS");
        userOB.setNazwisko("Kowalski");
        userOB.setStan(UserStateEnum.NIEAKTYWNY);
        userOB.setPlec(UserSexEnum.MEZCZYZNA);
        userOB.setTelefon("543434343");
        userRepository.save(userOB);
    }

    public String utworzToken(String token){
       token = UUID.randomUUID().toString();
        return token;
    }

    public void utworzNowegoUzytkownika(UserOB user, HttpServletRequest request) {
        user.setHaslo(passwordEncoder.encode(user.getHaslo()));
        userRepository.save(user);
        String token = "";
        utworzToken(token);
        TokenOB tokenOB = new TokenOB(user, token);
        tokenRepository.save(tokenOB);

        String url = "http://" + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath()
                + "/verify-token?token=" + token;

        try {
            mailSenderService.sendMail(user.getEmail(), "Weryfikacja tokena", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
        public void veryfikacjaTokena (String token) {
            //UserOB userOB = tokenRepository.findByValue(token).getFk_uzytkownik();
//            userOB.setStan(UserStateEnum.AKTYWNY);
//            userRepository.save(userOB);
        }

    public void rejestracjaNowegoUzytkownika(RegistrationDTO registrationDTO) {
        RegistrationDTO response = new RegistrationDTO();
        UserOB userOB = new UserOB();
        userOB.setHaslo(passwordEncoder.encode(userOB.getHaslo()));
        BeanUtils.copyProperties(response, userOB);
        userRepository.save(userOB);
        //return response;
    }
}


