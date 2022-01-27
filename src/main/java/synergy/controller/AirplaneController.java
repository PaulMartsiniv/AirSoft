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
import synergy.dto.mapper.AirCompanyMapper;
import synergy.dto.mapper.AirplaneMapper;
import synergy.dto.request.AirCompanyRequestDto;
import synergy.dto.request.AirplaneRequestDto;
import synergy.dto.response.AirplaneResponseDto;
import synergy.service.AirplaneService;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/airplane")
public class AirplaneController {
    private static final Logger logger = LogManager.getLogger(AirplaneController.class);
    private final AirplaneService airplaneService;
    private final AirplaneMapper airplaneMapper;

    @GetMapping
    public List<AirplaneResponseDto> findAll(@RequestParam Map<String, String> params) {
        logger.info("findAll method in class AirplaneController was called."
                + " Params: params = {}", params);
        return airplaneService.findAll(params).stream()
                .map(airplaneMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @GetMapping(value = "/{id}")
    public AirplaneResponseDto getById(@PathVariable Long id) {
        logger.info("getById method in class AirplaneController was called. "
                + "Params: id = {}", id);
        return airplaneMapper.toResponseDto(airplaneService.getById(id));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteById(@PathVariable Long id) {
        logger.info("deleteById method in class AirplaneController was called. "
                + "Params: id = {}", id);
        airplaneService.deleteById(id);
    }

    @PutMapping(value = "/update")
    public AirplaneResponseDto updateCompany(@RequestParam("airplaneId") Long airplaneId,
                                             @RequestParam("companyId") Long companyId) {
        logger.info("updateCompany method in class AirplaneController was called. "
                + "Params: airplaneId = {}, companyId = {}", airplaneId, companyId);
        return airplaneMapper.toResponseDto(
                airplaneService.updateCompany(airplaneId, companyId));
    }

    @PostMapping
    public AirplaneResponseDto add(@RequestBody AirplaneRequestDto requestDto) {
        logger.info("add method in class AirplaneController was called. "
                + "Params: requestDto = {}", requestDto);
        logger.info("add method in class AirplaneController was called. ");
        return airplaneMapper.toResponseDto(airplaneService
                .save(airplaneMapper.mapToModel(requestDto)));
    }
}
