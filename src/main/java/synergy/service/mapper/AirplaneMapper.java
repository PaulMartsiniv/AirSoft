package synergy.service.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import synergy.dto.request.AirplaneRequestDto;
import synergy.dto.response.AirplaneResponseDto;
import synergy.entity.Airplane;

@Component
@AllArgsConstructor
public class AirplaneMapper implements RequestDtoMapper<AirplaneRequestDto, Airplane>,
        ResponseDtoMapper<AirplaneResponseDto, Airplane> {
    private final AirCompanyMapper companyMapper;

    @Override
    public Airplane mapToModel(AirplaneRequestDto dto) {
        return Airplane.builder()
                .name(dto.getName())
                .factorySerialNumber(dto.getFactorySerialNumber())
                .airCompany(dto.getAirCompany())
                .numberOfFlights(dto.getNumberOfFlights())
                .flightDistance(dto.getFlightDistance())
                .fuelCapacity(dto.getFuelCapacity())
                .type(dto.getType())
                .createdAt(dto.getCreatedAt())
                .build();
    }

    @Override
    public AirplaneResponseDto toResponseDto(Airplane airplane) {
        return AirplaneResponseDto.builder()
                .id(airplane.getId())
                .name(airplane.getName())
                .factorySerialNumber(airplane.getFactorySerialNumber())
                .airCompany(companyMapper.toResponseDto(airplane.getAirCompany()))
                .numberOfFlights(airplane.getNumberOfFlights())
                .flightDistance(airplane.getFlightDistance())
                .fuelCapacity(airplane.getFuelCapacity())
                .type(airplane.getType())
                .createdAt(airplane.getCreatedAt())
                .build();
    }
}
