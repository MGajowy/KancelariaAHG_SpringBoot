package pl.kancelaria.AHG.user.services;

import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.configuration.jwt.config.JwtTokenUtil;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.comon.model.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.comon.model.users.roles.RolesOB;
import pl.kancelaria.AHG.comon.model.users.roles.repository.RolesRepository;
import pl.kancelaria.AHG.comon.model.users.token.TokenOB;
import pl.kancelaria.AHG.comon.model.users.token.repository.TokenRepository;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;
import pl.kancelaria.AHG.comon.model.users.user.UserSexEnum;
import pl.kancelaria.AHG.comon.model.users.user.UserStateEnum;
import pl.kancelaria.AHG.comon.model.users.user.repository.UserRepository;
import pl.kancelaria.AHG.comon.service.MailSenderService;
import pl.kancelaria.AHG.user.dto.*;
import pl.kancelaria.AHG.user.role.RolesName;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Michal
 * @created 20/08/2020
 */
@Service

public class UserService {
    private final RolesRepository rolesRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final MailSenderService mailSenderService;
    private final JwtTokenUtil jwtTokenUtil;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(UserService.class);


    public UserService(RolesRepository rolesRepository,
                       UserRepository userRepository,
                       TokenRepository tokenRepository,
                       PasswordEncoder passwordEncoder,
                       MailSenderService mailSenderService,
                       JwtTokenUtil jwtTokenUtil,
                       EventLogService eventLogService) {
        this.rolesRepository = rolesRepository;
        this.userRepository = userRepository;
        this.tokenRepository = tokenRepository;
        this.passwordEncoder = passwordEncoder;
        this.mailSenderService = mailSenderService;
        this.jwtTokenUtil = jwtTokenUtil;
        this.eventLogService = eventLogService;

        RolesOB roles_1 = new RolesOB();
        roles_1.setNazwa(RolesName.ADMIN);
        RolesOB roles_2 = new RolesOB();
        roles_2.setNazwa(RolesName.USER);

        List<RolesOB> list = new ArrayList<>();
        list.add(roles_1);
        list.add(roles_2);
        UserOB userOB = new UserOB();
        userOB.setImie("admin");
        userOB.setEmail("micgaj3@wp.pl");
        userOB.setPassword(passwordEncoder.encode("adminadmin"));
        userOB.setUserName("admin");
        userOB.setNazwisko("administrator");
        userOB.setStan(UserStateEnum.AKTYWNY);
        userOB.setPlec(UserSexEnum.MEZCZYZNA);
        userOB.setTelefon("543434343");
        userOB.setRolesOBSet(list);
        List<UserOB> userOBS = new ArrayList<>();
        userOBS.add(userOB);
        roles_1.setUserOBSet(userOBS);
//        roles_2.setUserOBSet(userOBS);
        userRepository.save(userOB);
        rolesRepository.save(roles_1);
        rolesRepository.save(roles_2);
        logger.info("Dodano uzytkownika administracyjnego");

    }


    public String utworzToken(UserDetails userDetails) {
        String token = jwtTokenUtil.generateToken(userDetails);
        return token;
    }

    //    public String utworzToken(){
//        String token = "";
//        token = UUID.randomUUID().toString();
//        return token;
//    }
    private void wyslijEmailAktywacyjny(UserOB user, LocationDTO locationDTO) {
        String token = utworzToken(user);
        TokenOB tokenOB = new TokenOB(user, token);
        tokenRepository.save(tokenOB);
        String url = locationDTO.getAppUrl()
                + "/set-password?token=" + token;
        try {
            mailSenderService.sendMail(user.getEmail(), "Weryfikacja tokena", url, false);
            logger.info("Wyslano email aktywacyjny do uzytkownika o loginie: " + user.getUsername());
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public boolean utworzNowegoUzytkownika(AddUserDTO user) {
        RolesOB role = rolesRepository.findAllByNazwa(user.getRola());
        List<RolesOB> list = new ArrayList<>();
        list.add(role);
        UserOB userOB = new UserOB();
        userOB.setImie(user.getImie());
        userOB.setNazwisko(user.getNazwisko());
        userOB.setUserName(user.getUsername());
        userOB.setEmail(user.getEmail());
        userOB.setTelefon(user.getTelefon());
        userOB.setPlec(user.getPlec());
        userOB.setStan(UserStateEnum.NIEAKTYWNY);
        userOB.setRolesOBSet(list);
        List<UserOB> userRoles = new ArrayList<>();
        userRoles.add(userOB);
        role.setUserOBSet(userRoles);
        this.userRepository.save(userOB);
        logger.info("Uzytkownik " + userOB.getUsername() + " zostal poprawnie dodany do bazy danych.");
        eventLogService.dodajLog(EventLogConstants.DODANO_NOWEGO_UZYTKOWNIKA, userOB.getUsername());
        return true;
    }

    //nowa metoda weryfikacji tokena
//             public void weryfikujToken(String token, UserDetails userDetails) {
//                jwtTokenUtil.validateToken(token, userDetails);
//                 UserOB userOB = tokenRepository.findByToken(token).getFk_uzytkownik();
//                 userOB.setStan(UserStateEnum.AKTYWNY);
//                 userRepository.save(userOB);
//    }
    public boolean aktywujUzytkownika(LocationDTO locationDTO) {
        UserOB userOB = userRepository.getOne(locationDTO.getId());
        if (userOB.getEmail().isEmpty() || userOB.getEmail() == null) {
            return false;
        } else {
            wyslijEmailAktywacyjny(userOB, locationDTO);
            eventLogService.dodajLog(EventLogConstants.AKTYWACJA_UZYTKOWNIKA, userOB.getUsername());
            return true;
        }
    }

    public boolean dezaktuwujUzytkownika(long id) {
        UserOB userOB = userRepository.getOne(id);
        if (userOB.getStan() == UserStateEnum.AKTYWNY) {
            userOB.setStan(UserStateEnum.NIEAKTYWNY);
            userRepository.save(userOB);
            logger.info("Uzytkownik o id: " + id + " zostal zdezaktywowany");
            eventLogService.dodajLog(EventLogConstants.DEZAKTYWACJA_UZYTKOWNIKA, userOB.getUsername());
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkToken(String token) {
        if (token == null) {
            return false;
        } else {
            UserOB userOB = tokenRepository.findByToken(token).getFk_uzytkownik();
            userOB.setStan(UserStateEnum.AKTYWNY);
            return true;
        }
    }


    public Boolean ResetHasla(ResetPasswordDTO dto) {
        UserOB userOB = userRepository.findAllByUserName(dto.getUsername());
        return wyslijEmailResetHasla(userOB, dto);
    }

    private Boolean wyslijEmailResetHasla(UserOB userOB, ResetPasswordDTO dto) {
        String token = utworzToken(userOB);
        TokenOB tokenOB = new TokenOB(userOB, token);
        tokenRepository.save(tokenOB);
        String url = dto.getAppUrl()
                + "/reset-password?token=" + token;
        try {
            mailSenderService.sendMail(userOB.getEmail(), "Reset hasła użytkownika", url, false);
            logger.info("Wyslano email resetu hasla dla uzytkownika o loginie: " + dto.getUsername());
            eventLogService.dodajLog(EventLogConstants.WYSLANO_EMAIL_RESETU_HASLA, userOB.getUsername());
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }

    }

}


