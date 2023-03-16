package pl.kancelaria.AHG.user.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.entityModel.users.roles.repository.RolesRepository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;


@Service
public class DeleteUserService {
    public static final String DELETED = "deleted";

    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(DeleteUserService.class);

    public DeleteUserService(UserRepository userRepository, RolesRepository rolesRepository, EventLogService eventLogService) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.eventLogService = eventLogService;
    }

    public ResponseEntity<HttpStatus> deleteUser(Long id) {
        if (id != null) {
            UserOB userOB = userRepository.getOne(id);
            String userName = userOB.getUsername();
            userOB.setUserName(DELETED);
            userOB.setEmail(null);
            userOB.setImie(DELETED);
            userOB.setNazwisko(DELETED);
            userOB.setTelefon(DELETED);
            userOB.setPassword(DELETED);
            userOB.setStan(null);
            userOB.setDateAdded(null);
            logger.info("Uzytkowinik o id: " + id + " zostal usuniety.");
            eventLogService.createLog(EventLogConstants.USUNIECIE_UZYTKOWNIKA, userName);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            logger.info("Uzytkowinik o id: " + id + " nie zostal poprawnie usuniety.");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

}
