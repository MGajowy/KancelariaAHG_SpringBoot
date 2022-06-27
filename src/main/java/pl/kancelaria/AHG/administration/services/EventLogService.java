package pl.kancelaria.AHG.administration.services;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.administration.dto.EventLogDTO;
import pl.kancelaria.AHG.administration.dto.EventLogListDTO;
import pl.kancelaria.AHG.comon.model.administration.eventLog.EventLogOB;
import pl.kancelaria.AHG.comon.model.administration.eventLog.repository.EventLogRepository;
import pl.kancelaria.AHG.comon.model.users.user.UserOB;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
}
