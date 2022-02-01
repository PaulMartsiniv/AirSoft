package synergy.service.impl;

import java.util.List;
import java.util.Map;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import synergy.dao.AirCompanyDao;
import synergy.dao.AirplaneDao;
import synergy.dao.spec.SpecificationManager;
import synergy.entity.AirCompany;
import synergy.entity.Airplane;
import synergy.service.AirplaneService;

@Service
@AllArgsConstructor
public class AirplaneServiceImpl implements AirplaneService {
    private final SpecificationManager<Airplane> manager;
    private final AirCompanyDao airCompanyDao;
    private final AirplaneDao airplaneDao;

    @Override
    public Airplane save(Airplane airplane) {
        return airplaneDao.save(airplane);
    }

    @Override
    public Airplane getById(Long id) {
        return airplaneDao.getById(id);
    }

    @Override
    public List<Airplane> findAll() {
        return airplaneDao.findAll();
    }

    @Override
    public List<Airplane> findAll(Map<String, String> params) {
        Specification<Airplane> specification = null;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            Specification<Airplane> spec = manager.get(entry.getKey(),
                    entry.getValue().split(","));
            specification = specification == null
                    ? Specification.where(spec) : specification.and(spec);
        }
        return airplaneDao.findAll(specification);
    }

    @Override
    public void deleteById(Long id) {
        airplaneDao.deleteById(id);
    }

    @Override
    public void delete(Airplane airplane) {
        airplaneDao.delete(airplane);
    }

    @Override
    public Airplane updateCompany(Long id, Long airCompanyId) {
        Airplane airplane = getById(id);
        AirCompany airCompany = airCompanyDao.getById(airCompanyId);
        airplane.setAirCompany(airCompany);
        return save(airplane);
    }
}
