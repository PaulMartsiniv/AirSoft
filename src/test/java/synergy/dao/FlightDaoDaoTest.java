package synergy.dao;

import java.time.LocalDate;
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
import synergy.entity.Airplane;
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
                .companyType(AirCompany.CompanyType.INTERNATIONAL_AIRLINES)
                .foundedAt(LocalDate.of(1984, 11, 28))
                .build());
        Airplane airplane = airplaneDao.save(Airplane.builder()
                .name("Boeing 737-800")
                .factorySerialNumber("AN1324546")
                .airCompany(company)
                .flight_distance(1000)
                .fuelCapacity(500)
                .type(Airplane.TypeOfAirlines.AIRLINER)
                .createdAt(LocalDate.of(2022, 1, 26))
                .build());
        for (int i = 0; i < 3; i++) {
            flightDao.save(Flight.builder()
                    .flightStatus(Flight.FlightStatus.PENDING)
                    .airCompany(company)
                    .airplane(List.of(airplane))
                    .departure_country("Ukraine")
                    .destinationCountry("USA")
                    .distance(9153)
                    .estimatedFlightTime(LocalTime.of(10, 10))
                    .startedAt(LocalDateTime.of(2022, 1, 26, 7, 0))
                    .endedAt(LocalDateTime.of(2022, 1, 26, 18, 18))
                    .delayStartedAt(null)
                    .createdAt(LocalDate.now().minusYears(10))
                    .build());
        }
    }

    @Test
    void shouldReturnADSADAD() {
        List<Flight> actual = flightDao.findAllByAirCompany_NameAndFlightStatus("Ryanair",
                Flight.FlightStatus.PENDING);
        Assertions.assertEquals(3, actual.size());
        Assertions.assertEquals("Ukraine", actual.get(0).getDeparture_country());
    }
}