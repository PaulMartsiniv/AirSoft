package synergy.dto.mapper;

import org.springframework.stereotype.Component;
import synergy.dto.RequestDtoMapper;
import synergy.dto.ResponseDtoMapper;
import synergy.dto.request.FlightRequestDto;
import synergy.dto.response.FlightResponseDto;
import synergy.entity.Flight;

@Component
public class FlightMapper implements RequestDtoMapper<FlightRequestDto, Flight>,
        ResponseDtoMapper<FlightResponseDto, Flight> {
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
                .airCompany(flight.getAirCompany())
                .airplane(flight.getAirplane())
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
