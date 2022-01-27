package synergy.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import synergy.dao.AirCompanyDao;
import synergy.dao.AirplaneDao;
import synergy.entity.AirCompany;
import synergy.entity.Airplane;

@ExtendWith(MockitoExtension.class)
class AirplaneServiceImplTest {
    @InjectMocks
    private AirplaneServiceImpl airplaneService;

    @Mock
    private AirplaneDao airplaneDao;
    @Mock
    private AirCompanyDao airCompanyDao;

    @Test
    void updateCompany() {
        Airplane actualAirplane = Airplane.builder()
                .airCompany(AirCompany.builder()
                        .name("Ryanair")
                        .build())
                .build();
        AirCompany actualAirCompany = AirCompany.builder().name("asd").build();
        Mockito.when(airplaneDao.getById(1L)).thenReturn(actualAirplane);
        Mockito.when(airplaneDao.save(actualAirplane)).thenReturn(actualAirplane);
        Mockito.when(airCompanyDao.getById(2L)).thenReturn(actualAirCompany);
        Airplane actual = airplaneService.updateCompany(1L, 2L);
        Assertions.assertEquals("asd", actual.getAirCompany().getName());
    }
}