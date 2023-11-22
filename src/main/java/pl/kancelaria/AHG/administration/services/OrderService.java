package pl.kancelaria.AHG.administration.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import pl.kancelaria.AHG.administration.dto.order.OrderDTO;
import pl.kancelaria.AHG.administration.dto.order.OrderFinishDTO;
import pl.kancelaria.AHG.administration.dto.order.OrdersListDTO;
import pl.kancelaria.AHG.common.entityModel.administration.eventLog.EventLogConstants;
import pl.kancelaria.AHG.common.entityModel.administration.orders.OrderOB;
import pl.kancelaria.AHG.common.entityModel.administration.orders.repository.OrderRepository;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class OrderService {

    private final OrderRepository orderRepository;
    private final EventLogService eventLogService;

    public OrdersListDTO getOrders(String term, Integer pageNumber, Integer pageSize) {
        final Pageable orderPageable = PageRequest.of(pageNumber, pageSize);
        List<OrderOB> obList = orderRepository.findByNameAndSurname(term.toLowerCase(), orderPageable);
        if (!CollectionUtils.isEmpty(obList)) {
            long totalRecords = orderRepository.totalRecordSize(term);
            List<OrderDTO> dtoList = createResponseDTO(obList);
            return OrdersListDTO.builder()
                    .orderDTOList(dtoList)
                    .totalRecord(totalRecords)
                    .build();
        }
        return OrdersListDTO.builder()
                .orderDTOList(List.of())
                .totalRecord(0L)
                .build();
    }

    private List<OrderDTO> createResponseDTO(List<OrderOB> obList) {

        List<OrderDTO> collect = obList.stream()
                .map(orderOB -> OrderDTO.builder()
                        .id(orderOB.getId())
                        .name(orderOB.getName())
                        .surname(orderOB.getSurname())
                        .email(orderOB.getEmail())
                        .phoneNumber(orderOB.getPhoneNumber())
                        .sum(orderOB.getSum())
                        .numberOfInstallments(orderOB.getNumberOfInstallments())
                        .orInstallments(orderOB.getOrInstallments())
                        .dateOfAdmission(orderOB.getDateOfAdmission())
                        .endDate(orderOB.getEndDate() != null ? orderOB.getEndDate() : null)
                        .caseType(orderOB.getCaseType())
                        .build())
                .sorted(Comparator.comparing(OrderDTO::getDateOfAdmission).reversed())
                .collect(Collectors.toList());
        return collect;
    }

    @Transactional
    public ResponseEntity<HttpStatus> addOrder(OrderDTO orderDTO) {
        Boolean validation = validateOrder(orderDTO);
        if (!validation) {
            OrderOB orderOB = new OrderOB();
            BeanUtils.copyProperties(orderDTO, orderOB);
            orderOB.setDateOfAdmission(LocalDate.now());
            orderRepository.save(orderOB);
            eventLogService.createLog(EventLogConstants.CREATE_ORDER, "");
            log.info("Utworzono nowe zlecenie dla użytkownika z adresem email: {}", orderDTO.getEmail());
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        log.error("Nie udało się dodać nowego zlecenia");
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Boolean validateOrder(OrderDTO orderDTO) {
        return orderDTO.getName().isBlank() || orderDTO.getSurname().isBlank() || orderDTO.getEmail().isBlank()
                || orderDTO.getPhoneNumber().isBlank() || orderDTO.getCaseType().isBlank();
    }

    @Transactional
    public ResponseEntity<HttpStatus> modifyOrder(long id, OrderDTO orderDTO) {
        Optional<OrderOB> orderOB = orderRepository.findById(id);
        if (orderOB.isPresent()) {
            OrderOB order = orderOB.get();
            order.setName(orderDTO.getName());
            order.setSurname(orderDTO.getSurname());
            order.setEmail(orderDTO.getEmail());
            order.setPhoneNumber(orderDTO.getPhoneNumber());
            order.setDateOfAdmission(orderDTO.getDateOfAdmission());
            order.setEndDate(orderDTO.getEndDate());
            order.setOrInstallments(orderDTO.getOrInstallments());
            order.setNumberOfInstallments(orderDTO.getNumberOfInstallments());
            order.setSum(orderDTO.getSum());
            order.setCaseType(orderDTO.getCaseType());
            orderRepository.save(order);
            log.info("Zmodyfikowano przyjęte zlecenie o id: {} ", id);
            eventLogService.createLog(EventLogConstants.MODIFY_ORDER, "");
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.warn("Wystąpił błąd podczas modyfikacji zlecenia ");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public ResponseEntity<HttpStatus> deleteOrder(long id) {
        Optional<OrderOB> orderOB = orderRepository.findById(id);
        if (orderOB.isPresent()) {
            orderRepository.deleteById(id);
            log.info("Zlecenie o id: {} zostało usunięte", id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
    }

    public void exportOrdersToPDF(HttpServletResponse response) {
        //todo do zaimplementowania eksport do pliku z danymi zleceń
    }

    @Transactional
    public ResponseEntity<HttpStatus> finishOrder(OrderFinishDTO orderFinishDTO) {

        Optional<OrderOB> order = orderRepository.findById(orderFinishDTO.getId());
        if (order.isPresent()) {
            order.get().setEndDate(orderFinishDTO.getDateToClose());
            orderRepository.save(order.get());
            log.info("Zlecenie o id {} zostało zakończone", orderFinishDTO.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            log.error("Wystąpił błąd podczas zakończania zlecenia o id: {}", orderFinishDTO.getId());
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    public OrderDTO detailsOrder(long id) {
        Optional<OrderOB> order = orderRepository.findById(id);
        if (order.isPresent()) {
            OrderOB orderOB = order.get();
            return OrderDTO.builder()
                    .id(orderOB.getId())
                    .name(orderOB.getName())
                    .surname(orderOB.getSurname())
                    .email(orderOB.getEmail())
                    .phoneNumber(orderOB.getPhoneNumber())
                    .caseType(orderOB.getCaseType())
                    .numberOfInstallments(orderOB.getNumberOfInstallments())
                    .dateOfAdmission(orderOB.getDateOfAdmission())
                    .endDate(orderOB.getEndDate())
                    .sum(orderOB.getSum())
                    .build();
        }
        return OrderDTO.builder().build();
    }
}