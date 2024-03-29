package synergy.dto.response;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import synergy.entity.Flight;

@Getter
@Setter
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class FlightResponseDto {
    private Long id;
    private Flight.FlightStatus flightStatus;
    private AirCompanyResponseDto airCompany;
    private List<AirplaneResponseDto> airplane;
    private String departureCountry;
    private String destinationCountry;
    private int distance;
    private LocalTime estimatedFlightTime;
    private LocalDateTime startedAt;
    private LocalDateTime endedAt;
    private LocalDateTime delayStartedAt;
    private LocalDate createdAt;
}
