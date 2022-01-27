package synergy.dto.response;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import synergy.entity.AirCompany;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AirCompanyResponseDto {
    private Long id;
    private String name;
    private AirCompany.CompanyType companyType;
    private LocalDate foundedAt;
}
