package synergy.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Entity
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "air_companies")
public class AirCompany {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "company_type")
    @Enumerated(EnumType.STRING)
    private CompanyType companyType;
    @Column(name = "founded_at")
    private LocalDate foundedAt;

    public enum CompanyType { INTERNATIONAL_AIRLINES, NATIONAL_AIRLINES, REGIONAL_AIRLINES }
}
