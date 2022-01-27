package synergy.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import synergy.entity.Airplane;

@Repository
public interface AirplaneDao extends JpaRepository<Airplane, Long>,
        JpaSpecificationExecutor<Airplane> {

    void getByIdAndAirCompany_Id(Long id, Long airCompany_id);
}
