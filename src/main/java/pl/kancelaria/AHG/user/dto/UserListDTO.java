package pl.kancelaria.AHG.user.dto;

import lombok.Data;

import java.util.List;

@Data
public class UserListDTO {
    List<UserDTO> listaUzytkownikow;
    Long totalRecord;
}
