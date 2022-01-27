package synergy.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import synergy.dao.FlightDao;
import synergy.entity.AirCompany;
import synergy.entity.Flight;
import synergy.util.TimeUtils;

@ExtendWith(MockitoExtension.class)
class FlightServiceImplTest {
    @InjectMocks
    private FlightServiceImpl flightService;
    private static Flight flight;

    @Mock
    private FlightDao flightDao;

    @BeforeAll
    public static void setup() {
        flight = Flight.builder()
                .flightStatus(Flight.FlightStatus.PENDING)
                .airCompany(AirCompany.builder()
                        .name("Ryanair")
                        .build())
                .airplane(null)
                .departureCountry("Ukraine")
                .destinationCountry("USA")
                .distance(9153)
                .estimatedFlightTime(null)
                .startedAt(null)
                .endedAt(null)
                .delayStartedAt(null)
                .createdAt(LocalDate.now().minusYears(10))
                .build();
    }

    @Test
    void save() {
        flight.setFlightStatus(Flight.FlightStatus.PENDING);
        Mockito.when(flightDao.save(flight)).thenReturn(flight);
        Flight actual = flightService.save(flight);
        Assertions.assertEquals("PENDING", actual.getFlightStatus().name());

        flight.setFlightStatus(Flight.FlightStatus.COMPLETED);
        actual = flightService.save(flight);
        Assertions.assertEquals("COMPLETED", actual.getFlightStatus().name());
        Assertions.assertEquals(LocalDateTime.now().format(TimeUtils.DATE_TIME_FORMATTER),
                actual.getEndedAt().format(TimeUtils.DATE_TIME_FORMATTER));

        flight.setFlightStatus(Flight.FlightStatus.ACTIVE);
        actual = flightService.save(flight);
        Assertions.assertEquals("ACTIVE", actual.getFlightStatus().name());
        Assertions.assertEquals(LocalDateTime.now().format(TimeUtils.DATE_TIME_FORMATTER),
                actual.getStartedAt().format(TimeUtils.DATE_TIME_FORMATTER));

        flight.setFlightStatus(Flight.FlightStatus.DELAYED);
        actual = flightService.save(flight);
        Assertions.assertEquals("DELAYED", actual.getFlightStatus().name());
        Assertions.assertEquals(LocalDateTime.now().format(TimeUtils.DATE_TIME_FORMATTER),
                actual.getDelayStartedAt().format(TimeUtils.DATE_TIME_FORMATTER));
    }

    @Test
    void findAllByAirCompany_NameAndFlightStatus_pending() {
        flight.setFlightStatus(Flight.FlightStatus.PENDING);
        Mockito.when(flightDao.findAllByAirCompanyNameAndFlightStatus(
                "Ryanair", Flight.FlightStatus.PENDING))
                .thenReturn(List.of(flight));
        List<Flight> actual = flightService.findAllByAirCompany_nameAndFlightStatus(
                "Ryanair", "p");
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("Ryanair", actual.get(0).getAirCompany().getName());
        actual = flightService.findAllByAirCompany_nameAndFlightStatus("Ryanair", "a");
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void findAllByAirCompany_NameAndFlightStatus_completed() {
        flight.setFlightStatus(Flight.FlightStatus.COMPLETED);
        Mockito.when(flightDao.findAllByAirCompanyNameAndFlightStatus(
                        "Ryanair", Flight.FlightStatus.COMPLETED))
                .thenReturn(List.of(flight));
        List<Flight> actual = flightService.findAllByAirCompany_nameAndFlightStatus(
                "Ryanair", "c");
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("Ryanair", actual.get(0).getAirCompany().getName());
        actual = flightService.findAllByAirCompany_nameAndFlightStatus("Ryanair", "d");
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void findAllByAirCompany_NameAndFlightStatus_delayed() {
        flight.setFlightStatus(Flight.FlightStatus.DELAYED);
        Mockito.when(flightDao.findAllByAirCompanyNameAndFlightStatus("Ryanair",
                        Flight.FlightStatus.DELAYED)).thenReturn(List.of(flight));
        List<Flight> actual = flightService.findAllByAirCompany_nameAndFlightStatus(
                "Ryanair", "d");
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("Ryanair", actual.get(0).getAirCompany().getName());
        actual = flightService.findAllByAirCompany_nameAndFlightStatus("Ryanair", "a");
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void findAllByAirCompany_NameAndFlightStatus_active() {
        flight.setFlightStatus(Flight.FlightStatus.ACTIVE);
        Mockito.when(flightDao.findAllByAirCompanyNameAndFlightStatus("Ryanair",
                        Flight.FlightStatus.ACTIVE)).thenReturn(List.of(flight));
        List<Flight> actual = flightService.findAllByAirCompany_nameAndFlightStatus(
                "Ryanair", "a");
        Assertions.assertEquals(1, actual.size());
        Assertions.assertEquals("Ryanair", actual.get(0).getAirCompany().getName());
        actual = flightService.findAllByAirCompany_nameAndFlightStatus("Ryanair", "c");
        Assertions.assertEquals(0, actual.size());
    }

    @Test
    void findAllByFlightStatusAndStartedAtBefore_ok() {
        flight.setFlightStatus(Flight.FlightStatus.ACTIVE);
        flight.setStartedAt(LocalDateTime.now().minusDays(2));
        Mockito.when(flightDao.findAllByFlightStatusAndStartedAtBefore(
                Flight.FlightStatus.ACTIVE, LocalDateTime.now().minusHours(24)
                                .withNano(0))).thenReturn(List.of(flight));
        List<Flight> actual = flightService.findAllByFlightStatusAndStartedAtBefore();
        Assertions.assertEquals(1, actual.size());
    }

    @Test
    void findAllByDifferences() {
    }

    @Test
    void updateFlightStatus() {
    }
}