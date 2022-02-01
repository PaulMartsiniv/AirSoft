package synergy.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import synergy.dao.FlightDao;
import synergy.dao.spec.SpecificationManager;
import synergy.entity.Flight;
import synergy.service.FlightService;
import synergy.util.TimeUtils;

@Service
@AllArgsConstructor
public class FlightServiceImpl implements FlightService {
    private static final ZoneOffset ZONE_OFFSET_UKRAINE = ZoneOffset.of("+02:00");
    private final SpecificationManager<Flight> manager;
    private final FlightDao dao;

    @Override
    public Flight save(Flight flight) {
        if (flight.getFlightStatus().equals(Flight.FlightStatus.ACTIVE)) {
            flight.setStartedAt(LocalDateTime.now());
        } else if (flight.getFlightStatus().equals(Flight.FlightStatus.COMPLETED)) {
            flight.setEndedAt(LocalDateTime.now());
        } else if (flight.getFlightStatus().equals(Flight.FlightStatus.DELAYED)) {
            flight.setDelayStartedAt(LocalDateTime.now());
        } else {
            flight.setFlightStatus(Flight.FlightStatus.PENDING);
        }
        return dao.save(flight);
    }

    @Override
    public Flight getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<Flight> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Flight> findAll(Map<String, String> params) {
        Specification<Flight> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Flight> spec = manager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(spec) : specification.and(spec);
        }
        return dao.findAll(specification);
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(Flight flight) {
        dao.delete(flight);
    }

    @Override
    public List<Flight> findAllByAirCompany_nameAndFlightStatus(String airCompanyName,
                                                                String flightStatus) {
        return dao.findAllByAirCompanyNameAndFlightStatus(airCompanyName,
                getFlightStatus(flightStatus));
    }

    @Override
    public List<Flight> findAllByFlightStatusAndStartedAtBefore() {
        return dao.findAllByFlightStatusAndStartedAtBefore(Flight.FlightStatus.ACTIVE,
                LocalDateTime.now().minusDays(1).withNano(0));
    }

    @Override
    public List<Flight> findAllByDifferences(String status) {
        TimeUtils timeUtils = new TimeUtils();
        return dao.findAllByFlightStatusEquals(
                        getFlightStatus(status)).stream()
                .filter(e -> timeUtils.getDifferences(e.getStartedAt(),
                                e.getEndedAt(), ZONE_OFFSET_UKRAINE)
                        .isAfter(e.getEstimatedFlightTime()))
                .collect(Collectors.toList());
    }

    @Override
    public Flight updateFlightStatus(Long id, String status) {
        Flight flight = getById(id);
        flight.setFlightStatus(getFlightStatus(status));
        return save(flight);
    }

    private Flight.FlightStatus getFlightStatus(String status) {
        status = status.toLowerCase();
        Flight.FlightStatus flightStatus = Flight.FlightStatus.PENDING;
        if (status.startsWith("a")) {
            flightStatus = Flight.FlightStatus.ACTIVE;
        } else if (status.startsWith("c")) {
            flightStatus = Flight.FlightStatus.COMPLETED;
        } else if (status.startsWith("d")) {
            flightStatus = Flight.FlightStatus.DELAYED;
        }
        return flightStatus;
    }
}
