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


    public Boolean dodajLog (String czynnosc, String user){
        EventLogOB logOB = new EventLogOB(czynnosc, user);
        logOB.setCzynnosc(czynnosc);
        logOB.setUzytkownik(user);
        Calendar data_dodania = Calendar.getInstance();
        data_dodania.getTime();
        logOB.setData_czynnosci(data_dodania);
        this.eventLogRepository.save(logOB);
        return true;
    }

    public EventLogListDTO pobierzDziennikZdarzen(){
        EventLogListDTO listDTO = new EventLogListDTO();
        List<EventLogOB> listaZdarzenOB = this.eventLogRepository.findAll();
        if (!CollectionUtils.isEmpty(listaZdarzenOB)){
            List<EventLogDTO> dziennikZdarzen = new ArrayList<>();
            listaZdarzenOB.forEach(eventLogOB ->{
                EventLogDTO logDTO = new EventLogDTO();
                BeanUtils.copyProperties(eventLogOB, logDTO);
                if(eventLogOB.getUzytkownik().isEmpty() && eventLogOB.getUzytkownik() == null ){
                    logDTO.setUzytkownik("nie odnaleziono");
                }else{
                    logDTO.setUzytkownik(eventLogOB.getUzytkownik());
                }
                dziennikZdarzen.add(logDTO);
            } );
            listDTO.setListaLogow(dziennikZdarzen);
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
        List<EventLogDTO> list = pobierzDziennikZdzrzenDTO();
        EventLogPDFExport eventLogPDFExport = new EventLogPDFExport(list);
        eventLogPDFExport.export(response);
    }

    private List<EventLogDTO> pobierzDziennikZdzrzenDTO() {
        List<EventLogDTO> listDTO = new ArrayList<>();
        List<EventLogOB> allOB = eventLogRepository.findAll();
        for ( EventLogOB event : allOB ) {
            EventLogDTO logDTO =  new EventLogDTO();
            BeanUtils.copyProperties(event, logDTO);
            listDTO.add(logDTO);
        }
        return listDTO;
    }
}
