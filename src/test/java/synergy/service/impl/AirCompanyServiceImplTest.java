package synergy.service.impl;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import synergy.dao.AirCompanyDao;
import synergy.entity.AirCompany;

@ExtendWith(MockitoExtension.class)
class AirCompanyServiceImplTest {
    @InjectMocks
    private AirCompanyServiceImpl companyService;
    @Mock
    private AirCompanyDao airCompanyDao;

    @Test
    void update() {
        AirCompany airCompany = AirCompany.builder().id(1L).name("asd").build();
        Mockito.when(airCompanyDao.save(airCompany)).thenReturn(airCompany);
        AirCompany actual = companyService.update(1L, airCompany);
        Assertions.assertEquals(1L, actual.getId());
        Assertions.assertEquals(airCompany.getName(), actual.getName());
    }
}