package pl.kancelaria.AHG.administration.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Michal
 * @created 07/01/2021
 */
@Data
public class EventLogListDTO {
    List<EventLogDTO> listaLogow;
}
