package pl.kancelaria.AHG.user.services;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.dto.UserListDTO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


@Service
public class UserListService {

    public final EntityManager entityManager;
    public final UserRepository userRepository;

    public UserListService(EntityManager entityManager, UserRepository userRepository) {
        this.entityManager = entityManager;
        this.userRepository = userRepository;
    }

    public UserListDTO getUserList(String term) {
        UserListDTO response = new UserListDTO();
        List<UserOB> userOBList = provideCriteriaList(term);
        if (!CollectionUtils.isEmpty(userOBList)) {
            List<UserDTO> users = new ArrayList<>();
            userOBList.forEach(user -> {
                UserDTO daneDTO = new UserDTO();
                BeanUtils.copyProperties(user, daneDTO);
                users.add(daneDTO);
            });
            response.setListaUzytkownikow(users);
        } else {
            response.setListaUzytkownikow(new ArrayList<>());
        }
        return response;
    }

    private List<UserOB> provideCriteriaList(String term) {
        TypedQuery<UserOB> query = entityManager.createQuery(prepareSearchInquiry(term), UserOB.class);
        if (!term.isEmpty()) {
            query.setParameter("term", "%" + term.toLowerCase() + "%");
        }
        return query.getResultList();
    }

    public String prepareSearchInquiry(String term) {
        StringBuilder query = new StringBuilder("SELECT u FROM UserOB u");
        query.append(" WHERE u.userName != 'deleted'");
        if (!term.isEmpty()) {
            query.append(" AND LOWER(u.nazwisko) like :term");
        }
        query.append(" ORDER BY u.nazwisko DESC");
        return query.toString();
    }

    public UserListDTO getUserListOfStatus(String stan) {
        UserListDTO response = new UserListDTO();
        List<UserOB> listOB = userRepository.findUserobsByStan(checkStatus(stan));
        if (!CollectionUtils.isEmpty(listOB)) {
            List<UserDTO> dtoList = new ArrayList<>();
            listOB.forEach(userOB -> {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(userOB, userDTO);
                dtoList.add(userDTO);
            });
            response.setListaUzytkownikow(dtoList);
        } else {
            response.setListaUzytkownikow(new ArrayList<>());
        }
        return response;
    }

    private UserStateEnum checkStatus(String stan) {
        UserStateEnum userStateEnum = null;

        switch (stan) {
            case "AKTYWNY":
                userStateEnum = UserStateEnum.AKTYWNY;
                break;
            case "NIEAKTYWNY":
                userStateEnum = UserStateEnum.NIEAKTYWNY;
                break;
            case "ZABLOKOWANY":
                userStateEnum = UserStateEnum.ZABLOKOWANY;
                break;
            case "USUNIETY":
                userStateEnum = UserStateEnum.USUNIETY;
                break;
            default:
                userStateEnum = UserStateEnum.AKTYWNY;
        }
        return userStateEnum;
    }
}
