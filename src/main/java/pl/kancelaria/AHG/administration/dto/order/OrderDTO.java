package pl.kancelaria.AHG.administration.dto.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

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
    private LocalDate dateOfAdmission;
    private LocalDate endDate;
    private String caseType;
}
