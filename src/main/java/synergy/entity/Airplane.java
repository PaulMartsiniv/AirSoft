package synergy.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "airplanes")
public class Airplane {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "factory_serial_number")
    private String factorySerialNumber;
    @ManyToOne
    @JoinColumn(name = "air_company_id")
    private AirCompany airCompany;
    @Column(name = "number_of_flights")
    private int numberOfFlights;
    @Column(name = "flight_distance")
    private int flightDistance;
    @Column(name = "fuel_capacity")
    private int fuelCapacity;
    @Enumerated(EnumType.STRING)
    private TypeOfAirlines type;
    @Column(name = "created_at")
    private LocalDate createdAt;

    public enum TypeOfAirlines {
        AIRLINER, MAIL_PLANE, AERIAL_FIREFIGHTING,
        AGRICULTURAL_PLANE, CARGO_AIRCRAFT
    }
}
