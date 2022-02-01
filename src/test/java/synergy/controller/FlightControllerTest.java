package synergy.controller;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import synergy.service.mapper.FlightMapper;
import synergy.dto.response.AirCompanyResponseDto;
import synergy.dto.response.AirplaneResponseDto;
import synergy.dto.response.FlightResponseDto;
import synergy.entity.AirCompany;
import synergy.entity.Airplane;
import synergy.entity.Flight;
import synergy.service.FlightService;


@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
class FlightControllerTest {
    private static final List<Flight> flights = new ArrayList<>();
    private static final List<FlightResponseDto> flightResponseDtos= new ArrayList<>();

    @MockBean
    private FlightService flightService;

    @Autowired
    private MockMvc mockMvc;
    @Mock
    private FlightMapper flightMapper;

    @BeforeAll
    static void beforeAll() {
        flights.add(Flight.builder()
                .id(1L)
                .flightStatus(Flight.FlightStatus.PENDING)
                .airCompany(AirCompany.builder()
                        .id(1L)
                        .name("das")
                        .build())
                .airplanes(List.of(Airplane.builder()
                        .id(1L)
                        .name("asd")
                        .build()))
                .departureCountry("Ukraine")
                .distance(123)
                .build());
        flights.add(Flight.builder()
                .id(2L)
                .flightStatus(Flight.FlightStatus.ACTIVE)
                .airCompany(AirCompany.builder()
                        .id(1L)
                        .name("das")
                        .build())
                .airplanes(List.of(Airplane.builder()
                        .id(1L)
                        .name("asd")
                        .build()))
                .departureCountry("USA")
                .distance(456)
                .build());
        flights.add(Flight.builder()
                .id(3L)
                .flightStatus(Flight.FlightStatus.DELAYED)
                .airCompany(AirCompany.builder()
                        .id(1L)
                        .name("das")
                        .build())
                .airplanes(List.of(Airplane.builder()
                        .id(1L)
                        .name("asd")
                        .build()))
                .departureCountry("England")
                .distance(789)
                .build());
        flightResponseDtos.add(
                FlightResponseDto.builder()
                        .id(1L)
                        .flightStatus(Flight.FlightStatus.PENDING)
                        .airCompany(AirCompanyResponseDto.builder()
                                .id(1L)
                                .name("das")
                                .build())
                        .airplane(List.of(AirplaneResponseDto.builder()
                                .id(1L)
                                .name("asd")
                                .build()))
                        .departureCountry("Ukraine")
                        .distance(123)
                        .build());
        flightResponseDtos.add(FlightResponseDto.builder()
                        .id(2L)
                        .flightStatus(Flight.FlightStatus.ACTIVE)
                        .airCompany(AirCompanyResponseDto.builder()
                                .id(1L)
                                .name("das")
                                .build())
                        .airplane(List.of(AirplaneResponseDto.builder()
                                .id(1L)
                                .name("asd")
                                .build()))
                        .departureCountry("USA")
                        .distance(456)
                        .build());
        flightResponseDtos.add(FlightResponseDto.builder()
                        .id(3L)
                        .flightStatus(Flight.FlightStatus.DELAYED)
                        .airCompany(AirCompanyResponseDto.builder()
                                .id(1L)
                                .name("das")
                                .build())
                        .airplane(List.of(AirplaneResponseDto.builder()
                                .id(1L)
                                .name("asd")
                                .build()))
                        .departureCountry("England")
                        .distance(789)
                        .build());
    }

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void findAll() {

    }

    @Test
    void getById() {

    }

    @Test
    void deleteById() {
    }

    @Test
    void findAllByAirCompanyNameAndFlightStatus() {
    }

    @Test
    void findAllByFlightStatus_ActiveAndStartedAtBefore24Hours() {
    }

    @Test
    void findAllByFlightStatusEqualsAndEstimatedFlightTimeLessThan() {
    }

    @Test
    void add() {

    }

    @Test
    void updateFlightStatus() {

    }
}
