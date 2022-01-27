package synergy.dto.request;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import synergy.entity.AirCompany;
import synergy.entity.Airplane;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class AirplaneRequestDto {
    private String name;
    private String factorySerialNumber;
    private AirCompany airCompany;
    private int numberOfFlights;
    private int flightDistance;
    private int fuelCapacity;
    private Airplane.TypeOfAirlines type;
    private LocalDate createdAt;
}
