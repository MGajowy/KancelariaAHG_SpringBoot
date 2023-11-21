package pl.kancelaria.AHG.shared.restapi.administration.restapi.secured;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.kancelaria.AHG.administration.dto.EventLogListDTO;
import pl.kancelaria.AHG.administration.dto.order.OrderDTO;
import pl.kancelaria.AHG.administration.dto.order.OrderFinishDTO;
import pl.kancelaria.AHG.administration.dto.order.OrdersListDTO;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import java.io.IOException;


@Path(AdministrationSecuredRestApiUrl.SCIEZKA_ADMINISTRACJA)
@RequestMapping(value = AdministrationSecuredRestApiUrl.SCIEZKA_ADMINISTRACJA)
public interface IAdministrationSecuredRestApi {

    @GET
    @GetMapping(AdministrationSecuredRestApiUrl.DZIENNIK_ZDARZEN)
    @Path(AdministrationSecuredRestApiUrl.DZIENNIK_ZDARZEN)
    EventLogListDTO getEventLogsDTO();

    @GetMapping(AdministrationSecuredRestApiUrl.DZIENNIK_ZDARZEN + "/{pageNumber}" + "/{pageSize}")
    EventLogListDTO getEventLogListByNameAndPage(@QueryParam("name") String description,
                                                 @PathVariable("pageNumber") final Integer pageNumber,
                                                 @PathVariable("pageSize") final Integer pageSize);

    @GetMapping(AdministrationSecuredRestApiUrl.EXPORT_TO_PDF)
    void exportToPDF(HttpServletResponse response) throws IOException;

    @GET
    @GetMapping(AdministrationSecuredRestApiUrl.ORDER + "/{pageNumber}" + "/{pageSize}")
    @Path(AdministrationSecuredRestApiUrl.ORDER)
    OrdersListDTO getOrders(@QueryParam("term") String term,
                            @PathVariable("pageNumber") final Integer pageNumber,
                            @PathVariable("pageSize") final Integer pageSize);

    @POST
    @PostMapping(AdministrationSecuredRestApiUrl.ORDER)
    @Path(AdministrationSecuredRestApiUrl.ORDER)
    ResponseEntity<HttpStatus> addOrder(@RequestBody OrderDTO orderDTO);

    @PUT
    @PutMapping(AdministrationSecuredRestApiUrl.ORDER + "/{id}")
    @Path(AdministrationSecuredRestApiUrl.ORDER)
    ResponseEntity<HttpStatus> modifyOrder(@PathVariable(value = "id") long id, @RequestBody OrderDTO orderDTO);

    @DELETE
    @DeleteMapping(AdministrationSecuredRestApiUrl.ORDER + "/{id}")
    @Path(AdministrationSecuredRestApiUrl.ORDER)
    ResponseEntity<HttpStatus> deleteOrder(@PathVariable(value = "id") long id);

    @POST
    @PostMapping(AdministrationSecuredRestApiUrl.ORDER_DATE_END)
    @Path(AdministrationSecuredRestApiUrl.ORDER_DATE_END)
    ResponseEntity<HttpStatus> finishOrder(@RequestBody OrderFinishDTO orderFinishDTO);

    @GetMapping(AdministrationSecuredRestApiUrl.EXPORT_ORDERS_TO_PDF)
    void exportOrdersToPDF(HttpServletResponse response) throws IOException;

}
