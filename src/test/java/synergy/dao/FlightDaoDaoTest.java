package synergy.dao;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import synergy.entity.AirCompany;
import synergy.entity.Flight;

@DataJpaTest
@Testcontainers
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FlightDaoDaoTest {
    @Container
    static MySQLContainer<?> database = new MySQLContainer<>("mysql:8")
            .withDatabaseName("root")
            .withPassword("root")
            .withUsername("root");

    @DynamicPropertySource
    static void setDataSourceProperties(DynamicPropertyRegistry propertyRegistry) {
        propertyRegistry.add("spring.datasource.url",database::getJdbcUrl);
        propertyRegistry.add("spring.datasource.password",database::getPassword);
        propertyRegistry.add("spring.datasource.username",database::getUsername);
    }

    @Autowired
    private AirplaneDao airplaneDao;
    @Autowired
    private AirCompanyDao airCompanyDao;
    @Autowired
    private FlightDao flightDao;

    @BeforeEach
    void before() {
        AirCompany company = airCompanyDao.save(AirCompany.builder()
                .name("Ryanair")
                .build());
        for (int i = 0; i < 3; i++) {
            flightDao.save(Flight.builder()
                    .flightStatus(Flight.FlightStatus.PENDING)
                    .airCompany(company)
                    .departureCountry("Ukraine")
                    .distance(9153)
                    .estimatedFlightTime(LocalTime.of(10, 0))
                    .startedAt(LocalDateTime.now().minusHours(10))
                    .endedAt(LocalDateTime.now().minusHours(0))
                    .build());
        }
    }

    @Test
    void shouldReturnAllByAirCompanyNameRyanairAndFlightStatusPending() {
        List<Flight> actual = flightDao.findAllByAirCompanyNameAndFlightStatus("Ryanair",
                Flight.FlightStatus.PENDING);
        Assertions.assertEquals(3, actual.size());
        Assertions.assertEquals("Ukraine", actual.get(0).getDepartureCountry());
        Assertions.assertEquals("Ryanair", actual.get(0).getAirCompany().getName());
        Assertions.assertEquals("Ryanair", actual.get(1).getAirCompany().getName());
        Assertions.assertEquals("Ryanair", actual.get(2).getAirCompany().getName());
        Assertions.assertEquals("PENDING", actual.get(0).getFlightStatus().name());
        Assertions.assertEquals("PENDING", actual.get(1).getFlightStatus().name());
        Assertions.assertEquals("PENDING", actual.get(2).getFlightStatus().name());
    }

    @Test
    void findAllByFlightStatus_pending_andStartedAtBefore_9_hours() {
        List<Flight> actual = flightDao.findAllByFlightStatusAndStartedAtBefore(Flight.FlightStatus.PENDING,
                LocalDateTime.now().minusHours(9));
        Assertions.assertEquals(3, actual.size());
        Assertions.assertEquals("Ukraine", actual.get(0).getDepartureCountry());
        Assertions.assertTrue(LocalDateTime.now().minusHours(9)
                .isAfter(actual.get(0).getStartedAt()));
        Assertions.assertEquals("PENDING", actual.get(0).getFlightStatus().name());
        Assertions.assertEquals("PENDING", actual.get(1).getFlightStatus().name());
        Assertions.assertEquals("PENDING", actual.get(2).getFlightStatus().name());
    }

    @Test
    void findAllByFlightStatusEquals_pending_ok() {
        List<Flight> actual = flightDao.findAllByFlightStatusEquals(
                Flight.FlightStatus.PENDING);
        Assertions.assertEquals(3, actual.size());
        Assertions.assertTrue(LocalDateTime.now().minusHours(8)
                .isAfter(actual.get(0).getStartedAt()));
        Assertions.assertTrue(LocalDateTime.now().minusHours(12)
                .isBefore(actual.get(0).getStartedAt()));
        Assertions.assertEquals("PENDING", actual.get(0).getFlightStatus().name());
        Assertions.assertEquals("PENDING", actual.get(1).getFlightStatus().name());
        Assertions.assertEquals("PENDING", actual.get(2).getFlightStatus().name());
    }

    @Test
    void findAllByFlightStatusEquals_active_notOk() {
        List<Flight> actual = flightDao.findAllByFlightStatusEquals(
                Flight.FlightStatus.ACTIVE);
        Assertions.assertEquals(List.of(), actual);
    }
}