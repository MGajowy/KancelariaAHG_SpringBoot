package pl.kancelaria.AHG.user.dto;

import lombok.Data;

import java.util.List;

/**
 * @author Michal
 * @created 09/08/2020
 */
@Data
public class UserListDTO {
    List<UserDTO> listaUzytkownikow;
}
