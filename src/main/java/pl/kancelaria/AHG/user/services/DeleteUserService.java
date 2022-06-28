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
    private final UserRepository userRepository;
    private final RolesRepository rolesRepository;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(DeleteUserService.class);

    public DeleteUserService(UserRepository userRepository, RolesRepository rolesRepository, EventLogService eventLogService) {
        this.userRepository = userRepository;
        this.rolesRepository = rolesRepository;
        this.eventLogService = eventLogService;
    }

    public ResponseEntity<HttpStatus> usunUzytkownika(long id) {
        try {
            UserOB userOB = userRepository.getOne(id);
            userRepository.deleteById(id);
            logger.info("Uzytkowinik o id: " + id + " zostal usuniety.");
            eventLogService.dodajLog(EventLogConstants.USUNIECIE_UZYTKOWNIKA, userOB.getUsername());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
