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

    public Boolean createLog(String czynnosc, String user) {
        EventLogOB logOB = new EventLogOB(czynnosc, user);
        logOB.setCzynnosc(czynnosc);
        logOB.setUzytkownik(user);
        Date dateAdded = new Date();
        dateAdded.getTime();
        logOB.setDataCzynnosci(dateAdded);
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
                        .czynnosc(eventLogOB.getCzynnosc())
                        .dataCzynnosci(dateConvertService.convertDateToString(eventLogOB.getDataCzynnosci()))
                        .uzytkownik(eventLogOB.getUzytkownik().isEmpty() || eventLogOB.getUzytkownik() == null ? "Administrator" : eventLogOB.getUzytkownik())
                        .build();
                eventLogList.add(eventLog);
            });
            listDTO.setListaLogow(eventLogList);
        } else {
            listDTO.setListaLogow(new ArrayList<>());
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
                        .czynnosc(eventLogOB.getCzynnosc())
                        .uzytkownik(eventLogOB.getUzytkownik())
                        .dataCzynnosci(dateConvertService.convertDateToString(eventLogOB.getDataCzynnosci()))
                        .build();
                eventLogList.add(eventLog);
            });
        }
        return eventLogList;
    }

    public EventLogListDTO getEventLogListByNameAndPage(String name, Integer pageNumber, Integer pageSize) {
        final Pageable eventLogPageable = PageRequest.of(pageNumber, pageSize, Sort.by("dataCzynnosci").descending().and(Sort.by("czynnosc")));
        List<EventLogOB> logOBList = eventLogRepository.findByCzynnoscLike("%" + name + "%", eventLogPageable);
        List<EventLogDTO> dtoList = createResponseDTO(logOBList);
        EventLogListDTO eventLogListDTO = new EventLogListDTO();
        eventLogListDTO.setListaLogow(dtoList);
        eventLogListDTO.setTotalRecord(eventLogRepository.countByCzynnoscLike("%" + name + "%"));
        return eventLogListDTO;
    }

    private List<EventLogDTO> createResponseDTO(List<EventLogOB> logOBList) {
        List<EventLogDTO> list = new ArrayList<>();
        logOBList.forEach(element -> {
            list.add(
                    EventLogDTO.builder()
                            .uzytkownik(element.getUzytkownik())
                            .czynnosc(element.getCzynnosc())
                            .dataCzynnosci(dateConvertService.convertDateToString(element.getDataCzynnosci()))
                            .build()
            );
        });
        return list;
    }
}
