package synergy.service.impl;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import synergy.dao.AirCompanyDao;
import synergy.entity.AirCompany;
import synergy.service.AirCompanyService;

@Service
@AllArgsConstructor
public class AirCompanyServiceImpl implements AirCompanyService {
    private final AirCompanyDao dao;

    @Override
    public AirCompany save(AirCompany airCompany) {
        return dao.save(airCompany);
    }

    @Override
    public AirCompany getById(Long id) {
        return dao.getById(id);
    }

    @Override
    public List<AirCompany> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteById(Long id) {
        dao.deleteById(id);
    }

    @Override
    public void delete(AirCompany airCompany) {
        dao.delete(airCompany);
    }

    @Override
    public AirCompany update(Long id, AirCompany airCompany) {
        airCompany.setId(id);
        return save(airCompany);
    }
}
