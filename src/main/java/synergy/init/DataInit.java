package synergy.init;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import synergy.entity.AirCompany;
import synergy.entity.Airplane;
import synergy.entity.Flight;
import synergy.service.AirCompanyService;
import synergy.service.AirplaneService;
import synergy.service.FlightService;
import synergy.util.TimeUtils;

@Component
@AllArgsConstructor
public class DataInit implements ApplicationRunner {
    private final AirCompanyService airCompanyService;
    private final AirplaneService airplaneService;
    private final FlightService flightService;

    @Override
    public void run(ApplicationArguments args) {
        saveAll();
    }

    private void saveAll() {
        AirCompany airCompany = airCompanyService.save(saveAirCompany());
        Airplane airplane = airplaneService.save(saveAirplane(airCompany));
        flightService.save(saveFlight(airCompany, airplane));
    }

    private AirCompany saveAirCompany() {
        return AirCompany.builder()
                .name("Ryanair")
                .companyType(AirCompany.CompanyType.INTERNATIONAL_AIRLINES)
                .foundedAt(LocalDate.of(1984, 11, 28))
                .build();
    }

    private Airplane saveAirplane(AirCompany airCompany) {
        return Airplane.builder()
                .name("Boeing 737-800")
                .factorySerialNumber("AN1324546")
                .airCompany(airCompany)
                .flightDistance(1000)
                .fuelCapacity(500)
                .type(Airplane.TypeOfAirlines.AIRLINER)
                .createdAt(LocalDate.of(2022, 1, 26))
                .build();
    }

    private Flight saveFlight(AirCompany airCompany, Airplane airplane) {
        TimeUtils timeUtils = new TimeUtils();
        LocalDateTime startedAt = LocalDateTime.of(2022, 1, 26, 7, 0);
        LocalDateTime endedAt = LocalDateTime.of(2022, 1, 26, 18, 18);
        return Flight.builder()
                .flightStatus(Flight.FlightStatus.PENDING)
                .airCompany(airCompany)
                .airplane(List.of(airplane))
                .departureCountry("Ukraine")
                .destinationCountry("USA")
                .distance(9153)
                .estimatedFlightTime(timeUtils
                        .getDifferences(startedAt, endedAt, ZoneOffset.of("+02:00")))
                .startedAt(startedAt)
                .endedAt(endedAt)
                .delayStartedAt(null)
                .createdAt(LocalDate.now().minusYears(10))
                .build();
    }
}
