package pl.kancelaria.AHG.administration.dto.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderDTO {
    private long id;
    private String name;
    private String surname;
    private Double sum;
    private Boolean orInstallments;
    private Long numberOfInstallments;
    private String email;
    private String phoneNumber;
    private String dateOfAdmission;
    private String endDate;
    private String caseType;
}
