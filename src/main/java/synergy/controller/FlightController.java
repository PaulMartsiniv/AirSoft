package synergy.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import synergy.dto.request.FlightRequestDto;
import synergy.dto.response.FlightResponseDto;
import synergy.entity.Flight;
import synergy.service.FlightService;
import synergy.service.mapper.FlightMapper;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/flight")
public class FlightController {
    private static final Logger logger = LogManager.getLogger(FlightController.class);
    private final FlightService flightService;
    private final FlightMapper flightMapper;

    @GetMapping
    public List<FlightResponseDto> findAll(@RequestParam Map<String, String> params) {
        logger.info("findAll method in class FlightController was called."
                + " Params: params = {}", params);
        return flightService.findAll(params).stream()
                .map(flightMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public FlightResponseDto getById(@PathVariable Long id) {
        logger.info("getById method in class FlightController was called. "
                + "Params: id = {}", id);
        return flightMapper.toResponseDto(flightService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("deleteById method in class FlightController was called. "
                + "Params: id = {}", id);
        flightService.deleteById(id);
    }

    @GetMapping("/findAllByAirCompany_NameAndFlightStatus")
    public List<FlightResponseDto> findAllByAirCompanyNameAndFlightStatus(
            @RequestParam("airCompanyName") String airCompanyName,
            @RequestParam("flightStatus") String flightStatus) {
        logger.info("findAllByAirCompany_NameAndFlightStatus method in class "
                        + "FlightController was called. Params: airCompany_name = {},"
                        + " flightStatus = {}",
                airCompanyName, flightStatus);
        return flightService.findAllByAirCompany_nameAndFlightStatus(airCompanyName,
                        flightStatus).stream()
                .map(flightMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/active24")
    public List<FlightResponseDto> findAllByFlightStatus_ActiveAndStartedAtBefore24Hours() {
        logger.info("findAllByFlightStatus_ActiveAndStartedAtBefore24Hours method in class "
                + "FlightController was called. ");
        return flightService.findAllByFlightStatusAndStartedAtBefore().stream()
                .map(flightMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/differences")
    public List<FlightResponseDto> findAllByFlightStatusEqualsAndEstimatedFlightTimeLessThan(
            @RequestParam("status") String status) {
        logger.info("findAllByFlightStatusEqualsAndEstimatedFlightTimeLessThan method "
                + "in class FlightController was called. Params: status = {}", status);
        return flightService.findAllByDifferences(status).stream()
                .map(flightMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public FlightResponseDto add(@RequestBody FlightRequestDto requestDto) {
        Flight add = flightService.save(flightMapper.mapToModel(requestDto));
        logger.info("add method in class FlightController was called. "
                + "Params: requestDto = {}", requestDto);
        return flightMapper.toResponseDto(add);
    }

    @PutMapping
    public FlightResponseDto updateFlightStatus(@RequestParam("id") Long id,
                                                @RequestParam("status") String status) {
        logger.info("updateFlightStatus method in class FlightController was called. "
                + "Params: id = {}, FlightStatus = {}", id, status);
        return flightMapper.toResponseDto(flightService.updateFlightStatus(id, status));
    }
}
