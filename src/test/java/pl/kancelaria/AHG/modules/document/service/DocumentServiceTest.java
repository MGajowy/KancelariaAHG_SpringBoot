package pl.kancelaria.AHG.modules.document.service;

import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.LoggerFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.entityModel.document.DocumentOB;
import pl.kancelaria.AHG.common.entityModel.document.StatusFile;
import pl.kancelaria.AHG.common.entityModel.document.repository.DocumentRepository;
import pl.kancelaria.AHG.common.entityModel.users.roles.RolesOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserSexEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.common.service.MailSenderService;
import pl.kancelaria.AHG.modules.document.dto.DocumentListDTO;
import pl.kancelaria.AHG.user.role.RolesName;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
class DocumentServiceTest {
    @Mock
    private DocumentRepository documentRepository;
    @Mock
    private UserRepository userRepository;
    @Mock
    private EventLogService eventLogService;
    @Mock
    private MailSenderService mailSenderService;
    @InjectMocks
    DocumentService documentService;

    @Test
    void shouldGetDocumentListForUser() {

        //given
        Optional<UserOB> userOB = createUserOB();
        DocumentOB documentOB = getDocumentOB(userOB);

        when(userRepository.findById(any())).thenReturn(userOB);
        when(documentRepository.searchByUserIdAndTerm(any(), any(), any(), any())).thenReturn(List.of(documentOB));

        //when
        DocumentListDTO actual = documentService.getDocumentListForUser("", StatusFile.PUBLIC.toString(), 1L, 0, 5);

        //then
        assertThat(actual.getDocumentList().size()).isEqualTo(1);
        assertThat(actual.getDocumentList().get(0).getDocName()).isEqualTo("nowy.txt");
    }

    @Test
    void sholudSaveDocument() throws IOException {

        //given
        Optional<UserOB> userOB = createUserOB();
        MultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

        when(userRepository.findById(any())).thenReturn(userOB);
        when(eventLogService.createLog(EventLogConstants.UPLOAD_NEW_FILE, userOB.get().getUsername())).thenReturn(true);

        //when
        Long actual = documentService.saveFile(file, 1L);

        //then
        assertThat(actual).isEqualTo(0L);
        assertNotNull(actual);
    }

    @Test
    void sholudUserIsNotNullSaveDocument() throws IOException {

        //given
        Logger docLogger = (Logger) LoggerFactory.getLogger(DocumentService.class);
        ListAppender<ILoggingEvent> listAppender = new ListAppender<>();
        listAppender.start();
        docLogger.addAppender(listAppender);

        when(userRepository.findById(any())).thenReturn(null);
        MultipartFile file = new MockMultipartFile("data", "filename.txt", "text/plain", "some xml".getBytes());

        //when
        documentService.saveFile(file, 1L);

        //then
        List<ILoggingEvent> logsList = listAppender.list;
        assertEquals("Zapis dokumentu: Nie znaleziono użytkownika o podanym id", logsList.get(0).getMessage());

    }

    private Optional<UserOB> createUserOB() {
        UserOB userOB = new UserOB();
        RolesOB rolesOB = new RolesOB();
        rolesOB.setId(1L);
        rolesOB.setRolesName(RolesName.USER);
        List<RolesOB> roles = new ArrayList<>();
        roles.add(rolesOB);
        userOB.setId(1L);
        userOB.setName("Adam");
        userOB.setSurname("Adamowicz");
        userOB.setUserName("adam");
        userOB.setPhoneNumber("1111111111");
        userOB.setActivationState(UserStateEnum.AKTYWNY);
        userOB.setRolesOBSet(roles);
        userOB.setEmail("m@hhh.pl");
        userOB.setSex(UserSexEnum.MEZCZYZNA);
        return Optional.of(userOB);
    }

    @NotNull
    private static DocumentOB getDocumentOB(Optional<UserOB> userOB) {
        return new DocumentOB(1L, "nowy.txt", "txt", new byte[0], LocalDate.now(), null, StatusFile.PUBLIC.toString(), userOB.get());
    }
}