package pl.kancelaria.AHG.administration.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.administration.dto.EventLogDTO;
import pl.kancelaria.AHG.administration.dto.EventLogListDTO;
import pl.kancelaria.AHG.administration.dto.EventLogPDFExport;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogOB;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.repository.EventLogRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


@Service
public class EventLogService {

    private final EventLogRepository eventLogRepository;

    @Autowired
    public EventLogService(EventLogRepository eventLogRepository) {
        this.eventLogRepository = eventLogRepository;
    }


    public Boolean createLog(String czynnosc, String user) {
        EventLogOB logOB = new EventLogOB(czynnosc, user);
        logOB.setCzynnosc(czynnosc);
        logOB.setUzytkownik(user);
        Calendar dateAdded = Calendar.getInstance();
        dateAdded.getTime();
        logOB.setData_czynnosci(dateAdded);
        this.eventLogRepository.save(logOB);
        return true;
    }

    public EventLogListDTO downloadEventLog() {
        EventLogListDTO listDTO = new EventLogListDTO();
        List<EventLogOB> eventLogListOB = this.eventLogRepository.findAll();
        if (!CollectionUtils.isEmpty(eventLogListOB)) {
            List<EventLogDTO> eventLogList = new ArrayList<>();
            eventLogListOB.forEach(eventLogOB -> {
                EventLogDTO logDTO = new EventLogDTO();
                BeanUtils.copyProperties(eventLogOB, logDTO);
                if (eventLogOB.getUzytkownik().isEmpty() || eventLogOB.getUzytkownik() == null) {
                    logDTO.setUzytkownik("Administrator");
                } else {
                    logDTO.setUzytkownik(eventLogOB.getUzytkownik());
                }
                eventLogList.add(logDTO);
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
        for (EventLogOB event : eventLogListOB) {
            EventLogDTO logDTO = new EventLogDTO();
            BeanUtils.copyProperties(event, logDTO);
            eventLogList.add(logDTO);
        }
        return eventLogList;
    }
}
