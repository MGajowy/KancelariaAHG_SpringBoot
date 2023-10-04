package pl.kancelaria.AHG.modules.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.entityModel.document.DocumentOB;
import pl.kancelaria.AHG.common.entityModel.document.StatusFile;
import pl.kancelaria.AHG.common.entityModel.document.repository.DocumentRepository;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.common.service.MailSenderService;
import pl.kancelaria.AHG.modules.document.DocumentConstant;
import pl.kancelaria.AHG.modules.document.dto.DocumentDTO;
import pl.kancelaria.AHG.modules.document.dto.DocumentListDTO;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final UserRepository userRepository;
    private final EventLogService eventLogService;
    private final MailSenderService mailSenderService;
    public final EntityManager entityManager;


    public Boolean saveFile(MultipartFile[] files, Long userId) throws Exception {
        Optional<UserOB> user;
        user = userRepository.findById(userId);

        if (user.isEmpty()) {
            throw new Exception("Nie znaleziono użytkownika o podanym ID");
        }

        for (MultipartFile file : files) {
            try {
                saveDocument(user, file);
            } catch (Exception exception) {
                log.error("Wystąpił błąd podczas zapisu pliku: {}", file.getOriginalFilename());
                return false;
            }
        }

        try {
            mailSenderService.sendMail(user.get().getEmail(), DocumentConstant.TOPIC_EMAIL_FILE, DocumentConstant.MESSAGE_EMAIL_FILE, false);
        } catch (Exception ex) {
            log.error("Pliki - Wystąpił błąd podczas wysyłki powiadomienia email dla użytkownika: {} {}", user.get().getEmail(), ex);
        }

        return true;
    }

    @Transactional
    private void saveDocument(Optional<UserOB> user, MultipartFile file) throws IOException {
        DocumentOB documentOB = new DocumentOB();
        documentOB.setDocName(file.getOriginalFilename());
        documentOB.setDocType(file.getContentType());
        documentOB.setData(file.getBytes());
        documentOB.setCreateDate(LocalDate.now());
        documentOB.setDeleteDate(null);
        documentOB.setStatus(StatusFile.PUBLIC.toString());
        documentOB.setUserid(user.get());
        documentRepository.save(documentOB);
        Boolean logServiceLog = eventLogService.createLog(EventLogConstants.UPLOAD_NEW_FILE, user.get().getUsername());
        if (logServiceLog)
            log.info("Nowy plik został dodany do DB: {} ", file.getOriginalFilename());
    }

    public DocumentDTO downloadFile(Long idFile) {
        try {
            Optional<DocumentOB> doc = documentRepository.findById(idFile);
            if (doc.isPresent()) {
                DocumentDTO documentDTO = mapToDTO(doc);
                eventLogService.createLog(EventLogConstants.DOWNLOAD_FILE + doc.get().getDocName(),
                        userRepository.findById(doc.get().getUserid().getId()).get().getUsername());
                return mapToDTO(doc);
            }
        } catch (Exception ex) {
            log.error("Nie znaleziono dokumentu o ID : {} {} ", idFile, ex.getStackTrace());
        }
        return null;
    }

    private DocumentDTO mapToDTO(Optional<DocumentOB> doc) {
        DocumentOB documentOB = doc.get();
        return DocumentDTO.builder()
                .docName(documentOB.getDocName())
                .docType(documentOB.getDocType())
                .statusFile(checkStatus(documentOB.getStatus()))
                .createDate(documentOB.getCreateDate())
                .data(documentOB.getData())
                .build();
    }

    private StatusFile checkStatus(String status) {
        StatusFile statusFile = null;

        switch (status) {
            case "PUBLIC":
                statusFile = StatusFile.PUBLIC;
                break;
            case "NOT_PUBLIC":
                statusFile = StatusFile.NOT_PUBLIC;
                break;
            case "DELETE":
                statusFile = StatusFile.DELETE;
                break;
            default:
                statusFile = StatusFile.PUBLIC;
        }
        return statusFile;
    }

    public DocumentListDTO getDocumentList(String term, Integer pageNumber, Integer pageSize, HttpServletRequest request) {
        final Pageable documentPageable = PageRequest.of(pageNumber, pageSize, Sort.by("createDate").descending());
        UserOB user = userRepository.searchUserName(request.getRemoteUser());
        if (user != null) {
            List<DocumentOB> documents = documentRepository.searchByUserIdAndTerm(user.getId(), term, StatusFile.PUBLIC.toString(), documentPageable);
            if (!CollectionUtils.isEmpty(documents)) {
                long totalRecords = documentRepository.totalRecordsSize(user.getId());
                if (validateDocumentOB(documents)) {
                    List<DocumentDTO> collect = documents.stream()
                            .map(documentOB -> DocumentDTO.builder()
                                    .id(documentOB.getId())
                                    .createDate(documentOB.getCreateDate())
                                    .statusFile(StatusFile.valueOf(documentOB.getStatus()))
                                    .docName(documentOB.getDocName())
                                    .docType(documentOB.getDocType())
                                    .data(documentOB.getData())
                                    .build())
                            .sorted(Comparator.comparing(DocumentDTO::getCreateDate).reversed())
                            .collect(Collectors.toList());
                    return DocumentListDTO.builder()
                            .totalRecords(totalRecords)
                            .documentList(collect)
                            .build();
                }
            }
        }
        return DocumentListDTO.builder()
                .documentList(List.of())
                .totalRecords(0L)
                .build();
    }

    public ResponseEntity<HttpEntity> deleteDocument(Long id) {
        Optional<DocumentOB> document = documentRepository.findById(id);
        if (document.isPresent()) {
            document.get().setStatus(StatusFile.DELETE.toString());
            document.get().setDeleteDate(LocalDate.now());
            documentRepository.save(document.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    public DocumentListDTO getDocumentListForUser(String term, String status, Long id, Integer pageNumber, Integer pageSize) {
        final Pageable documentPageable = PageRequest.of(pageNumber, pageSize, Sort.by("createDate").descending());
        Optional<UserOB> user = userRepository.findById(id);

        if (user.isPresent()) {
            UserOB userOB = user.get();
            List<DocumentOB> documents = documentRepository.searchByUserIdAndTerm(id, term, status, documentPageable);
            if (!CollectionUtils.isEmpty(documents)) {
                long totalRecords = documentRepository.totalRecordsSize(userOB.getId());
                if (validateDocumentOB(documents)) {
                    List<DocumentDTO> collect = documents.stream()
                            .map(documentOB -> DocumentDTO.builder()
                                    .id(documentOB.getId())
                                    .statusFile(StatusFile.valueOf(documentOB.getStatus()))
                                    .docName(documentOB.getDocName())
                                    .docType(documentOB.getDocType())
                                    .createDate(documentOB.getCreateDate())
                                    .data(documentOB.getData())
                                    .build())
                            .sorted(Comparator.comparing(DocumentDTO::getCreateDate).reversed())
                            .collect(Collectors.toList());
                    return DocumentListDTO.builder()
                            .totalRecords(totalRecords)
                            .documentList(collect)
                            .build();
                }
            }
        }
        return DocumentListDTO.builder()
                .documentList(List.of())
                .totalRecords(0L)
                .build();
    }

    private boolean validateDocumentOB(List<DocumentOB> documents) {
        for (DocumentOB dok : documents) {
            if (dok.getData() != null && dok.getDocName() != null)
                continue;
            else {
                log.error("Plik nie ma podanej nazwy lub nie posiada blob: {}", dok.getId());
                return false;
            }
        }
        return true;
    }
}