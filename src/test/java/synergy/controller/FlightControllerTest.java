package synergy.controller;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import synergy.dto.mapper.FlightMapper;

class FlightControllerTest {
    @InjectMocks
    private FlightController flightController;

    @Mock
    private FlightMapper flightMapper;

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