package pl.kancelaria.AHG.modules.regulations.dto;

import lombok.Data;

import java.util.List;

@Data
public class RegulationListDTO {
    List<RegulationDTO> listaRozporzadzen;
    Long totalRecords;
}
