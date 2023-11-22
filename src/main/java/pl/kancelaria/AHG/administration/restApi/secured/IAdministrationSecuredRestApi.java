package pl.kancelaria.AHG.administration.restApi.secured;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import pl.kancelaria.AHG.administration.dto.EventLogListDTO;
import pl.kancelaria.AHG.administration.dto.order.OrderDTO;
import pl.kancelaria.AHG.administration.dto.order.OrderFinishDTO;
import pl.kancelaria.AHG.administration.dto.order.OrdersListDTO;
import pl.kancelaria.AHG.administration.services.EventLogService;
import pl.kancelaria.AHG.administration.services.OrderService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@RestController
public class IAdministrationSecuredRestApi implements pl.kancelaria.AHG.shared.restapi.administration.restapi.secured.IAdministrationSecuredRestApi {

    private final EventLogService eventLogService;
    private final OrderService orderService;

    @Autowired
    public IAdministrationSecuredRestApi(EventLogService eventLogService, OrderService orderService) {
        this.eventLogService = eventLogService;
        this.orderService = orderService;
    }

    @Override
    public EventLogListDTO getEventLogsDTO() {
       return this.eventLogService.getEventLogList();
    }

    @Override
    public EventLogListDTO getEventLogListByNameAndPage(String name, Integer pageNumber, Integer pageSize) {
        return eventLogService.getEventLogListByNameAndPage(name, pageNumber, pageSize);
    }

    @Override
    public void exportToPDF(HttpServletResponse response) throws IOException {
         eventLogService.exportToPDF(response);
    }

    @Override
    public OrdersListDTO getOrders(String term, Integer pageNumber, Integer pageSize) {
        return orderService.getOrders(term, pageNumber, pageSize);
    }

    @Override
    public OrderDTO detailsOrder(long id) {
        return orderService.detailsOrder(id);
    }

    @Override
    public ResponseEntity<HttpStatus> addOrder(OrderDTO orderDTO) {
        return orderService.addOrder(orderDTO);
    }

    @Override
    public ResponseEntity<HttpStatus> modifyOrder(long id, OrderDTO orderDTO) {
        return orderService.modifyOrder(id, orderDTO);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteOrder(long id) {
        return orderService.deleteOrder(id);
    }

    @Override
    public ResponseEntity<HttpStatus> finishOrder(OrderFinishDTO orderFinishDTO) {
        return orderService.finishOrder(orderFinishDTO);
    }

    @Override
    public void exportOrdersToPDF(HttpServletResponse response) throws IOException {
        orderService.exportOrdersToPDF(response);
    }
}
