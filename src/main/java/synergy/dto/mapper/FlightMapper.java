package synergy.dto.mapper;

import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import synergy.dto.RequestDtoMapper;
import synergy.dto.ResponseDtoMapper;
import synergy.dto.request.FlightRequestDto;
import synergy.dto.response.FlightResponseDto;
import synergy.entity.Flight;

@Component
@AllArgsConstructor
public class FlightMapper implements RequestDtoMapper<FlightRequestDto, Flight>,
        ResponseDtoMapper<FlightResponseDto, Flight> {
    AirCompanyMapper companyMapper;
    AirplaneMapper airplaneMapper;

    @Override
    public Flight mapToModel(FlightRequestDto dto) {
        return Flight.builder()
                .flightStatus(dto.getFlightStatus())
                .airCompany(dto.getAirCompany())
                .airplane(dto.getAirplane())
                .departure_country(dto.getDeparture_country())
                .destinationCountry(dto.getDestinationCountry())
                .distance(dto.getDistance())
                .startedAt(dto.getStartedAt())
                .endedAt(dto.getEndedAt())
                .delayStartedAt(dto.getDelayStartedAt())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    @Override
    public FlightResponseDto toResponseDto(Flight flight) {
        return FlightResponseDto.builder()
                .id(flight.getId())
                .flightStatus(flight.getFlightStatus())
                .airCompany(companyMapper.toResponseDto(flight.getAirCompany()))
                .airplane(flight.getAirplane().stream()
                        .map(airplaneMapper::toResponseDto)
                        .collect(Collectors.toList()))
                .departure_country(flight.getDeparture_country())
                .destinationCountry(flight.getDestinationCountry())
                .distance(flight.getDistance())
                .startedAt(flight.getStartedAt())
                .endedAt(flight.getEndedAt())
                .delayStartedAt(flight.getDelayStartedAt())
                .createdAt(flight.getCreatedAt())
                .build();
    }
}
