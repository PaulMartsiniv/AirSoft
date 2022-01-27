package synergy.dao.spec.filter;

import javax.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import synergy.dao.spec.SpecificationProvider;
import synergy.entity.Flight;

@Component
public class FlightAirCompanySpecification implements SpecificationProvider<Flight> {
    private static final String FILTER_KEY = "airCompanyIn";
    private static final String FIELD_NAME = "airCompany";

    @Override
    public Specification<Flight> getSpecification(String[] params) {
        return (root, query, cb) -> {
            CriteriaBuilder.In<String> predicate = cb.in(root.get(FIELD_NAME));
            for (String value : params) {
                predicate.value(value);
            }
            return cb.and(predicate, predicate);
        };
    }

    @Override
    public String getFilterKey() {
        return FILTER_KEY;
    }
}
