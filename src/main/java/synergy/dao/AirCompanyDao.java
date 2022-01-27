package synergy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import synergy.entity.AirCompany;

@Repository
public interface AirCompanyDao extends JpaRepository<AirCompany, Long>,
        JpaSpecificationExecutor<AirCompany> {
}
