package synergy.dao.spec;

import org.springframework.data.jpa.domain.Specification;

public interface SpecificationManager<T> {
    Specification<T> get(String filterKey, String[] params);
}
