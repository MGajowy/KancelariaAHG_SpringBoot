package pl.kancelaria.AHG.user.services;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.configuration.jwt.config.JwtTokenUtil;
import pl.kancelaria.AHG.comon.model.users.token.TokenOB;
import pl.kancelaria.AHG.comon.model.users.token.repository.TokenRepository;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.comon.model.users.user.UserSexEnum;
import pl.kancelaria.AHG.comon.model.users.user.UserStateEnum;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.comon.service.MailSenderService;
import pl.kancelaria.AHG.user.dto.AddUserDTO;
import pl.kancelaria.AHG.user.dto.LocationDTO;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.dto.UserPasswordDTO;

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
    private final JwtTokenUtil jwtTokenUtil;


    public UserService(UserRepository userRepository, TokenRepository tokenRepository, PasswordEncoder passwordEncoder, MailSenderService mailSenderService, PasswordEncoder bcryptEncoder, JwtTokenUtil jwtTokenUtil) {
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
        this.jwtTokenUtil = jwtTokenUtil;

        UserOB userOB = new UserOB();
        userOB.setImie("admin");
        userOB.setEmail("admin@wp.pl");
        userOB.setPassword(passwordEncoder.encode("adminadmin"));
        userOB.setUserName("admin");
        userOB.setNazwisko("administrator");
        userOB.setStan(UserStateEnum.AKTYWNY);
        userOB.setPlec(UserSexEnum.MEZCZYZNA);
        userOB.setTelefon("543434343");
        userRepository.save(userOB);
    }

    //nowa metoda do tworzenie tokena
    public String utworzToken(UserDetails userDetails){
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }
//    public String utworzToken(){
//        String token = "";
//        token = UUID.randomUUID().toString();
//        return token;
//    }
    private void wyslijEmailAktywacyjny (UserOB user, LocationDTO locationDTO){
        String token = utworzToken(user);
        TokenOB tokenOB = new TokenOB(user, token);
        tokenRepository.save(tokenOB);

        String url = locationDTO.getAppUrl()
                + "/set-password?token=" + token;
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

    //nowa metoda weryfikacji tokena
//             public void weryfikujToken(String token, UserDetails userDetails) {
//                jwtTokenUtil.validateToken(token, userDetails);
//                 UserOB userOB = tokenRepository.findByToken(token).getFk_uzytkownik();
//                 userOB.setStan(UserStateEnum.AKTYWNY);
//                 userRepository.save(userOB);
//    }

    public void aktywujUzytkownika (LocationDTO locationDTO){
         UserOB userOB = userRepository.getOne(locationDTO.getId());
        wyslijEmailAktywacyjny(userOB, locationDTO);
    }

    public void dezaktuwujUzytkownika(long id) {
        UserOB userOB = userRepository.getOne(id);
        userOB.setStan(UserStateEnum.NIEAKTYWNY);
        userRepository.save(userOB);
    }

    public Boolean checkToken(String token) {
        if (token == null){
            return false;
        }else {
            UserOB userOB = tokenRepository.findByToken(token).getFk_uzytkownik();
            userOB.setStan(UserStateEnum.AKTYWNY);
            return true;
        }
    }
}


