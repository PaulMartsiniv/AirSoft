package synergy.controller;

import java.util.List;
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
import org.springframework.web.bind.annotation.RestController;
import synergy.dto.request.AirCompanyRequestDto;
import synergy.dto.response.AirCompanyResponseDto;
import synergy.service.AirCompanyService;
import synergy.service.mapper.AirCompanyMapper;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/company")
public class AirCompanyController {
    private static final Logger logger = LogManager.getLogger(AirCompanyController.class);
    private final AirCompanyService companyService;
    private final AirCompanyMapper companyMapper;

    @PostMapping
    public AirCompanyResponseDto add(@RequestBody AirCompanyRequestDto requestDto) {
        return companyMapper.toResponseDto(companyService
                .save(companyMapper.mapToModel(requestDto)));
    }

    @GetMapping
    public List<AirCompanyResponseDto> findAll() {
        logger.info("findAll method in class AirCompanyController was called. ");
        return companyService.findAll().stream()
                .map(companyMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public AirCompanyResponseDto getById(@PathVariable Long id) {
        logger.info("getById method in class AirCompanyController was called. "
                + "Params: id = {}", id);
        return companyMapper.toResponseDto(companyService.getById(id));
    }

    @PutMapping(value = "/{id}")
    public AirCompanyResponseDto updateCompany(@RequestBody AirCompanyRequestDto dto,
                                               @PathVariable Long id) {
        return companyMapper.toResponseDto(companyService.update(id,
                companyMapper.mapToModel(dto)));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("deleteById method in class AirCompanyController was called. "
                + "Params: id = {}", id);
        companyService.deleteById(id);
    }

}
