package synergy.service;

import java.util.List;
import java.util.Map;
import synergy.entity.Flight;

public interface FlightService extends GenericService<Flight> {
    List<Flight> findAllByAirCompany_nameAndFlightStatus(String airCompanyName,
                                                         String flightStatus);

    List<Flight> findAllByFlightStatusAndStartedAtBefore();

    List<Flight> findAllByDifferences(Long id);

    List<Flight> findAll(Map<String, String> params);

    Flight updateFlightStatus(Long id, String status);
}
