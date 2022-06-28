package pl.kancelaria.AHG.user.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.dto.UserListDTO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserListService {

    public final EntityManager entityManager;

    public UserListService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public UserListDTO pobierzListeUzytkownikow(String term) {
        UserListDTO response = new UserListDTO();
        List<UserOB> userOBList = podajListeWedlugKryteriow(term);
        if (!CollectionUtils.isEmpty(userOBList)) {
            List<UserDTO> uzytkownik = new ArrayList<>();
            userOBList.forEach(u -> {
                UserDTO daneDTO = new UserDTO();
                BeanUtils.copyProperties(u, daneDTO);
                uzytkownik.add(daneDTO);
            });
            response.setListaUzytkownikow(uzytkownik);
        } else {
            response.setListaUzytkownikow(new ArrayList<>());
        }
        return response;
    }

    private List<UserOB> podajListeWedlugKryteriow(String term) {
        TypedQuery<UserOB> query = entityManager.createQuery(przygotujZapytanieWyszukiwania(term),UserOB.class);
        if (!term.isEmpty()){
            query.setParameter("term","%" + term.toLowerCase() + "%");
        }
        return query.getResultList();
    }

    public String przygotujZapytanieWyszukiwania(String term) {
        StringBuilder query = new StringBuilder("SELECT u FROM UserOB u");
        if (!term.isEmpty()){
            query.append(" WHERE LOWER(u.nazwisko) like :term");
        }
        query.append(" ORDER BY u.nazwisko DESC");
        return query.toString();
    }
}
