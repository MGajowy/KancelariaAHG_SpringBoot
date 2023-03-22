package pl.kancelaria.AHG.administration.services;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.administration.dto.EventLogDTO;
import pl.kancelaria.AHG.administration.dto.EventLogListDTO;
import pl.kancelaria.AHG.administration.dto.EventLogPDFExport;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogOB;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.repository.EventLogRepository;
import pl.kancelaria.AHG.common.service.DateConvertService;


import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class EventLogService {

    private final EventLogRepository eventLogRepository;
    private final DateConvertService dateConvertService;

    public Boolean createLog(String action, String user) {
        EventLogOB logOB = new EventLogOB(action, user);
        logOB.setAction(action);
        logOB.setUserName(user);
        Date dateAdded = new Date();
        dateAdded.getTime();
        logOB.setDateAction(dateAdded);
        this.eventLogRepository.save(logOB);
        return true;
    }

    public EventLogListDTO getEventLogList() {
        EventLogListDTO listDTO = new EventLogListDTO();
        List<EventLogOB> eventLogListOB = this.eventLogRepository.findAll();
        if (!CollectionUtils.isEmpty(eventLogListOB)) {
            List<EventLogDTO> eventLogList = new ArrayList<>();
            eventLogListOB.forEach(eventLogOB -> {
                EventLogDTO eventLog = EventLogDTO.builder()
                        .id(eventLogOB.getId())
                        .action(eventLogOB.getAction())
                        .dateAction(dateConvertService.convertDateToString(eventLogOB.getDateAction()))
                        .userName(eventLogOB.getUserName().isEmpty() || eventLogOB.getUserName() == null ? "Administrator" : eventLogOB.getUserName())
                        .build();
                eventLogList.add(eventLog);
            });
            listDTO.setLogList(eventLogList);
        } else {
            listDTO.setLogList(new ArrayList<>());
        }
        return listDTO;
    }

    public void exportToPDF(HttpServletResponse response) throws IOException {
        response.setContentType("application/pdf");
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=eventLog.pdf";

        response.setHeader(headerKey, headerValue);
        List<EventLogDTO> eventLogList = getEventLogDTO();
        EventLogPDFExport eventLogPDFExport = new EventLogPDFExport(eventLogList);
        eventLogPDFExport.export(response);
    }

    private List<EventLogDTO> getEventLogDTO() {
        List<EventLogDTO> eventLogList = new ArrayList<>();
        List<EventLogOB> eventLogListOB = eventLogRepository.findAll();
        if (!CollectionUtils.isEmpty(eventLogListOB)) {
            eventLogListOB.forEach(eventLogOB -> {
                EventLogDTO eventLog = EventLogDTO.builder()
                        .id(eventLogOB.getId())
                        .action(eventLogOB.getAction())
                        .userName(eventLogOB.getUserName())
                        .dateAction(dateConvertService.convertDateToString(eventLogOB.getDateAction()))
                        .build();
                eventLogList.add(eventLog);
            });
        }
        return eventLogList;
    }

    public EventLogListDTO getEventLogListByNameAndPage(String name, Integer pageNumber, Integer pageSize) {
        final Pageable eventLogPageable = PageRequest.of(pageNumber, pageSize, Sort.by("dateAction").descending().and(Sort.by("action")));
        List<EventLogOB> logOBList = eventLogRepository.findByActionLike("%" + name + "%", eventLogPageable);
        List<EventLogDTO> dtoList = createResponseDTO(logOBList);
        EventLogListDTO eventLogListDTO = new EventLogListDTO();
        eventLogListDTO.setLogList(dtoList);
        eventLogListDTO.setTotalRecord(eventLogRepository.countByActionLike("%" + name + "%"));
        return eventLogListDTO;
    }

    private List<EventLogDTO> createResponseDTO(List<EventLogOB> logOBList) {
        List<EventLogDTO> list = new ArrayList<>();
        logOBList.forEach(element -> {
            list.add(
                    EventLogDTO.builder()
                            .userName(element.getUserName())
                            .action(element.getAction())
                            .dateAction(dateConvertService.convertDateToString(element.getDateAction()))
                            .build()
            );
        });
        return list;
    }
}
