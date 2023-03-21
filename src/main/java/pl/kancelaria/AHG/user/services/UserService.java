package pl.kancelaria.AHG.user.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.configuration.jwt.config.JwtTokenUtil;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.entityModel.users.roles.RolesOB;
import pl.kancelaria.AHG.common.entityModel.users.roles.repository.RolesRepository;
import pl.kancelaria.AHG.common.entityModel.users.token.TokenOB;
import pl.kancelaria.AHG.common.entityModel.users.token.repository.TokenRepository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.common.service.MailSenderService;
import pl.kancelaria.AHG.user.dto.*;
import pl.kancelaria.AHG.user.role.RolesName;

import javax.mail.MessagingException;
import java.util.*;


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

        Date date = new Date();
        date.getTime();
        RolesOB roles_1 = new RolesOB();
        roles_1.setRolesName(RolesName.ADMIN);
        RolesOB roles_2 = new RolesOB();
        roles_2.setRolesName(RolesName.USER);

        List<RolesOB> list = new ArrayList<>();
        list.add(roles_1);
        list.add(roles_2);
        UserOB userOB = new UserOB();
        userOB.setName("admin");
        userOB.setEmail("micgaj3@wp.pl");
        userOB.setPassword(passwordEncoder.encode("adminadmin"));
        userOB.setUserName("admin");
        userOB.setSurname("administrator");
        userOB.setActivationState(UserStateEnum.AKTYWNY);
        userOB.setSex(UserSexEnum.MEZCZYZNA);
        userOB.setPhoneNumber("543434343");
        userOB.setDateAdded(date);
        userOB.setRolesOBSet(list);
        List<UserOB> userOBS = new ArrayList<>();
        userOBS.add(userOB);
        roles_1.setUserOBSet(userOBS);
//        roles_2.setUserOBSet(userOBS);
        userRepository.save(userOB);
        rolesRepository.save(roles_1);
        rolesRepository.save(roles_2);
        logger.info("Dodano uzytkownika administracyjnego");
        eventLogService.createLog(EventLogConstants.DODANO_NOWEGO_UZYTKOWNIKA, userOB.getUsername());

    }

    public String createToken(UserDetails userDetails) {
        return jwtTokenUtil.generateToken(userDetails);
    }

    public void sendActivationEmail(UserOB user, LocationDTO locationDTO) {
        String token = createToken(user);
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

    public ResponseEntity<HttpStatus> createNewUser(AddUserDTO user) {
        try {
            Date date = new Date();
            date.getTime();
            RolesOB role = rolesRepository.findAllByRolesName(user.getRolesName());
            List<RolesOB> list = new ArrayList<>();
            list.add(role);
            UserOB userOB = new UserOB();
            userOB.setName(user.getName());
            userOB.setSurname(user.getSurname());
            userOB.setUserName(user.getUsername());
            userOB.setEmail(user.getEmail());
            userOB.setPhoneNumber(user.getPhoneNumber());
            userOB.setSex(user.getSex());
            userOB.setActivationState(UserStateEnum.NIEAKTYWNY);
            userOB.setDateAdded(date);
            userOB.setRolesOBSet(list);
            List<UserOB> userRoles = new ArrayList<>();
            userRoles.add(userOB);
            role.setUserOBSet(userRoles);
            this.userRepository.save(userOB);
            logger.info("Uzytkownik " + userOB.getUsername() + " zostal poprawnie dodany do bazy danych.");
            eventLogService.createLog(EventLogConstants.DODANO_NOWEGO_UZYTKOWNIKA, userOB.getUsername());
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    //nowa metoda weryfikacji tokena
//             public void weryfikujToken(String token, UserDetails userDetails) {
//                jwtTokenUtil.validateToken(token, userDetails);
//                 UserOB userOB = tokenRepository.findByToken(token).getFk_uzytkownik();
//                 userOB.setStan(UserStateEnum.AKTYWNY);
//                 userRepository.save(userOB);
//    }

    public boolean userActivation(LocationDTO locationDTO) {
        UserOB userOB = userRepository.getOne(locationDTO.getId());
        if (userOB.getEmail().isEmpty() || userOB.getEmail() == null) {
            return false;
        } else {
            sendActivationEmail(userOB, locationDTO);
            eventLogService.createLog(EventLogConstants.AKTYWACJA_UZYTKOWNIKA, userOB.getUsername());
            return true;
        }
    }

    public boolean userDeactivation(long id) {
        UserOB userOB = userRepository.getOne(id);
        if (userOB.getActivationState() == UserStateEnum.AKTYWNY) {
            userOB.setActivationState(UserStateEnum.NIEAKTYWNY);
            userRepository.save(userOB);
            logger.info("Uzytkownik o id: " + id + " zostal zdezaktywowany");
            eventLogService.createLog(EventLogConstants.DEZAKTYWACJA_UZYTKOWNIKA, userOB.getUsername());
            return true;
        } else {
            return false;
        }
    }

    public Boolean checkToken(String token) {
        if (token == null) {
            return false;
        } else {
            TokenOB tokenOB = tokenRepository.findByToken(token);
            UserOB userOB = tokenOB.getUser();
            userOB.setActivationState(UserStateEnum.AKTYWNY);
            return true;
        }
    }

    public Boolean passwordReset(ResetPasswordDTO dto) {
        UserOB userOB = userRepository.findAllByUserName(dto.getUsername());
        return sendEmailPasswordReset(userOB, dto);
    }

    private Boolean sendEmailPasswordReset(UserOB userOB, ResetPasswordDTO dto) {
        String token = createToken(userOB);
        TokenOB tokenOB = new TokenOB(userOB, token);
        tokenRepository.save(tokenOB);
        String url = dto.getAppUrl()
                + "/reset-password?token=" + token;
        try {
            mailSenderService.sendMail(userOB.getEmail(), "Reset hasła użytkownika", url, false);
            logger.info("Wyslano email resetu hasla dla uzytkownika o loginie: " + dto.getUsername());
            eventLogService.createLog(EventLogConstants.WYSLANO_EMAIL_RESETU_HASLA, userOB.getUsername());
            return true;
        } catch (MessagingException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<RolesName> getRoles(String remoteUser) {
        UserOB userOB = userRepository.findAllByUserName(remoteUser);
        List<RolesOB> rolesOBSet = userOB.getRolesOBSet();
        List<RolesName> rolesNames = new ArrayList<>();
        for (RolesOB role : rolesOBSet) {
            rolesNames.add(role.getRolesName());
        }
        return rolesNames;
    }
}


