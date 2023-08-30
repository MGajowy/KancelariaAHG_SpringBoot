package pl.kancelaria.AHG.user.services;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.common.entityModel.users.user.UserOB;
import pl.kancelaria.AHG.common.entityModel.users.user.UserStateEnum;
import pl.kancelaria.AHG.common.entityModel.users.user.repository.UserRepository;
import pl.kancelaria.AHG.common.service.DateConvertService;
import pl.kancelaria.AHG.shared.restapi.modules.document.restApi.user.dto.UserDocumentDTO;
import pl.kancelaria.AHG.shared.restapi.modules.document.restApi.user.dto.UserListDocumentDTO;
import pl.kancelaria.AHG.user.dto.UserDTO;
import pl.kancelaria.AHG.user.dto.UserListDTO;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;


@Service
@RequiredArgsConstructor
public class UserListService {

    public final EntityManager entityManager;
    public final UserRepository userRepository;
    private final DateConvertService dateConvertService;

    public UserListDTO getUserList(String term) {
        UserListDTO response = new UserListDTO();
        List<UserOB> userOBList = provideCriteriaList(term);
        if (!CollectionUtils.isEmpty(userOBList)) {
            List<UserDTO> users = new ArrayList<>();
            userOBList.forEach(user -> {
                UserDTO daneDTO = new UserDTO();
                BeanUtils.copyProperties(user, daneDTO);
                daneDTO.setDateAdded(dateConvertService.convertDateToString(user.getDateAdded()));
                users.add(daneDTO);
            });
            response.setUsersList(users);
        } else {
            response.setUsersList(new ArrayList<>());
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

    public UserListDTO getUserListOfStatus(String activationState) {
        UserListDTO response = new UserListDTO();
        List<UserOB> listOB = userRepository.findUserobsByActivationState(checkStatus(activationState));
        if (!CollectionUtils.isEmpty(listOB)) {
            List<UserDTO> dtoList = new ArrayList<>();
            listOB.forEach(userOB -> {
                UserDTO userDTO = new UserDTO();
                BeanUtils.copyProperties(userOB, userDTO);
                userDTO.setDateAdded(dateConvertService.convertDateToString(userOB.getDateAdded()));
                dtoList.add(userDTO);
            });
            response.setUsersList(dtoList);
        } else {
            response.setUsersList(new ArrayList<>());
        }
        return response;
    }

    private UserStateEnum checkStatus(String activationState) {
        UserStateEnum userStateEnum = null;

        switch (activationState) {
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

    public UserListDTO getUserListByNameAndPage(String term, Integer pageNumber, Integer pageSize) {
        final Pageable userPageable = PageRequest.of(pageNumber, pageSize, Sort.by("dateAdded").descending().and(Sort.by("surname")));
        List<UserOB> userOBList = userRepository.searchByUserNameLike(term.toLowerCase(), userPageable);
        List<UserDTO> userDTOList = createResponseDTO(userOBList);
        UserListDTO response = new UserListDTO();
        response.setUsersList(userDTOList);
        response.setTotalRecord(userRepository.countByUserNameLike("%" + term + "%"));
        return response;
    }

    private List<UserDTO> createResponseDTO(List<UserOB> userOBList) {
        List<UserDTO> list = new ArrayList<>();
        userOBList.forEach(element -> {
            UserDTO dto = new UserDTO();
            BeanUtils.copyProperties(element, dto);
            dto.setDateAdded(dateConvertService.convertDateToString(element.getDateAdded()));
            list.add(dto);
        });
        return list;
    }

    public UserListDocumentDTO getAllUsers() {
        UserListDocumentDTO response = new UserListDocumentDTO();
        List<UserOB> userOBList = userRepository.searchAllUsers();
        if (!CollectionUtils.isEmpty(userOBList)) {
            List<UserDocumentDTO> users = new ArrayList<>();
            userOBList.forEach(user -> {
                users.add(UserDocumentDTO.builder()
                        .id(user.getId())
                        .name(user.getSurname() + " " + user.getName())
                        .build());
            });
            response.setUserDocument(users);
        } else {
            response.setUserDocument(new ArrayList<>());
        }
        return response;
    }
}
