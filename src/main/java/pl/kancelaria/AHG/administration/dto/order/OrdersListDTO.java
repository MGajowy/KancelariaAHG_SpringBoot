package pl.kancelaria.AHG.administration.dto.order;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class OrdersListDTO {
    private List<OrderDTO> orderDTOList;
    private Long totalRecord;
}