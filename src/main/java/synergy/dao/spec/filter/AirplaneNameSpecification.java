package synergy.dao.spec.filter;

import javax.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import synergy.dao.spec.SpecificationProvider;
import synergy.entity.Airplane;

@Component
public class AirplaneNameSpecification implements SpecificationProvider<Airplane> {
    private static final String FILTER_KEY = "nameIn";
    private static final String FIELD_NAME = "name";

    @Override
    public Specification<Airplane> getSpecification(String[] params) {
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
