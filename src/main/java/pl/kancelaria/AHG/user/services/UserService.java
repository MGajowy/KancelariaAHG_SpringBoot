package pl.kancelaria.AHG.user.services;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.comon.model.users.token.TokenOB;
import pl.kancelaria.AHG.comon.model.users.token.repository.TokenRepository;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.comon.model.users.user.UserStateEnum;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.comon.service.MailSenderService;
import pl.kancelaria.AHG.user.dto.AddUserDTO;
import pl.kancelaria.AHG.user.dto.RegistrationDTO;
import pl.kancelaria.AHG.user.dto.UserDTO;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * @author Michal
 * @created 20/08/2020
 */
@Service
public class UserService {
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;


    public UserService(UserRepository userRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, MailSenderService mailSenderService, PasswordEncoder bcryptEncoder) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
    }

    public String utworzToken(){
        String token = "";
        token = UUID.randomUUID().toString();
        return token;
    }
    private void wyslijEmailAktywacyjny (UserOB user, HttpServletRequest request){
        String token = utworzToken();
        TokenOB tokenOB = new TokenOB(user, token);
        tokenRepository.save(tokenOB);

        String url = "http://" + request.getServerName()
                + ":"
                + request.getServerPort()
                + request.getContextPath()
                + "/rest/uzytkownicy/secured/weryfikuj-token?token=" + token;

        try {
            mailSenderService.sendMail(user.getEmail(), "Weryfikacja tokena", url, false);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
    public boolean utworzNowegoUzytkownika(AddUserDTO user, HttpServletRequest request) {
        UserOB userOB = new UserOB();
        userOB.setImie(user.getImie());
        userOB.setNazwisko(user.getNazwisko());
        userOB.setUserName(user.getUsername());
        userOB.setEmail(user.getEmail());
        userOB.setTelefon(user.getTelefon());
        userOB.setPlec(user.getPlec());
        userOB.setStan(UserStateEnum.NIEAKTYWNY);
    this.userRepository.save(userOB);
    //this.aktywujUzytkownika(user, request);
    return true;
    }
        public void weryfikujToken(String token) {

//        UserOB userOB = tokenRepository.findByValue(token).getFk_uzytkownik();
//            userOB.setStan(UserStateEnum.AKTYWNY);
//            userRepository.save(userOB);
        }
    //    public void aktywujUzytkownika (UserDTO user, HttpServletRequest request){
//        UserOB userOB = new UserOB();
//        BeanUtils.copyProperties(user, userOB);
//        wyslijEmailAktywacyjny(userOB, request);
//    }
    public void aktywujUzytkownika (long id, HttpServletRequest request){
         UserOB userOB = userRepository.getOne(id);
        wyslijEmailAktywacyjny(userOB, request);
    }

    public void dezaktuwujUzytkownika(long id) {
        UserOB userOB = userRepository.getOne(id);
        userOB.setStan(UserStateEnum.NIEAKTYWNY);
        userRepository.save(userOB);
    }
}


