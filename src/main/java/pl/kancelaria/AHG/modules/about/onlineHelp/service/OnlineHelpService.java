package pl.kancelaria.AHG.modules.about.onlineHelp.service;

import lombok.RequiredArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.service.MailSenderService;
import pl.kancelaria.AHG.modules.about.onlineHelp.dto.OnlineHelpRequestDto;
import pl.kancelaria.AHG.user.services.UserService;

import javax.mail.MessagingException;

@Service
@RequiredArgsConstructor
public class OnlineHelpService {

    @Value("${onlineHelp.email}")
    String emailAdmin;

    private final MailSenderService mailSenderService;
    private final EventLogService eventLogService;
    Logger logger = LoggerFactory.getLogger(UserService.class);

    public ResponseEntity<HttpStatus> sendEmailNotification(OnlineHelpRequestDto request) {
        boolean resultValidation = validateMessage(request);
        if (resultValidation) {
            String message = request.getMessage()
                    .concat("\n" + request.getName())
                    .concat("\n" + "email: " + request.getEmail())
                    .concat("\n" + "telefon: " + request.getPhoneNumber());
            try {
                mailSenderService.sendMail(request.getEmail(), OnlineHelpConstant.TEMAT_WIADOMOSCI_DO_PETENTA, message, false);
                logger.info("Wysłano powiadomienie email od użytkownika: " + request.getEmail());
                mailSenderService.sendMail(emailAdmin, OnlineHelpConstant.TEMAT_WIADOMOSCI_DO_ADMINA, message, false);
                logger.info("Wysłano powiadomienie emial do admina: " + emailAdmin);
                eventLogService.createLog(EventLogConstants.ZAPYTANIE_EMAIL_OD_PETENTA, "petent");
                return new ResponseEntity<>(HttpStatus.ACCEPTED);
            } catch (MessagingException messagingException) {
                logger.info("Błąd podczas wysłania powiadomienia " + messagingException);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }
        logger.info("Wystąpił błąd podczas walidacji powiadomienia");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private boolean validateMessage(OnlineHelpRequestDto request) {
        if (!validateEmail(request.getEmail())) {
            return false;
        } else if (request.getName() == null) {
            return false;
        } else if (request.getPhoneNumber() == null) {
            return false;
        } else if (request.getMessage() == null) {
            return false;
        } else {
            return true;
        }
    }

    private boolean validateEmail(String email) {
        EmailValidator emailValidator = EmailValidator.getInstance();
        return emailValidator.isValid(email);
    }
}
