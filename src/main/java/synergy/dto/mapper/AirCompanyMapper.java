package synergy.dto.mapper;

import org.springframework.stereotype.Component;
import synergy.dto.RequestDtoMapper;
import synergy.dto.ResponseDtoMapper;
import synergy.dto.request.AirCompanyRequestDto;
import synergy.dto.response.AirCompanyResponseDto;
import synergy.entity.AirCompany;

@Component
public class AirCompanyMapper implements RequestDtoMapper<AirCompanyRequestDto, AirCompany>,
        ResponseDtoMapper<AirCompanyResponseDto, AirCompany> {
    @Override
    public AirCompany mapToModel(AirCompanyRequestDto dto) {
        return AirCompany.builder()
                .name(dto.getName())
                .companyType(dto.getCompanyType())
                .foundedAt(dto.getFoundedAt())
                .build();
    }

    @Override
    public AirCompanyResponseDto toResponseDto(AirCompany airCompany) {
        return AirCompanyResponseDto.builder()
                .id(airCompany.getId())
                .name(airCompany.getName())
                .companyType(airCompany.getCompanyType())
                .foundedAt(airCompany.getFoundedAt())
                .build();
    }
}
