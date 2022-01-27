package synergy.service;

import java.util.List;
import java.util.Map;
import synergy.entity.Airplane;

public interface AirplaneService extends GenericService<Airplane> {
    Airplane updateCompany(Long id, Long airCompanyId);

    List<Airplane> findAll(Map<String, String> params);
}
