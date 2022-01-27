package synergy.dao;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import synergy.entity.Flight;

@Repository
public interface FlightDao extends JpaRepository<Flight, Long>,
        JpaSpecificationExecutor<Flight> {
    List<Flight> findAllByAirCompany_NameAndFlightStatus(String airCompany_name,
                                                         Flight.FlightStatus flightStatus);

    List<Flight> findAllByFlightStatusAndStartedAtBefore(Flight.FlightStatus flightStatus,
                                                         LocalDateTime startedAt);

    List<Flight> findAllByFlightStatusEqualsAndEstimatedFlightTimeLessThan
            (Flight.FlightStatus flightStatus, LocalTime estimatedFlightTime);
}
