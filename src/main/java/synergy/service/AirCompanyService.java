package synergy.service;

import synergy.entity.AirCompany;

public interface AirCompanyService extends GenericService<AirCompany> {
    AirCompany update(Long id, AirCompany airCompany);
}
