package synergy.dao.spec;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import synergy.entity.AirCompany;

@Component
public class AirCompanySpecificationManager implements SpecificationManager<AirCompany> {
    private final Map<String, SpecificationProvider<AirCompany>> providersMap;

    public AirCompanySpecificationManager(List<SpecificationProvider<AirCompany>> list) {
        this.providersMap = list.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<AirCompany> get(String filterKey, String[] params) {
        if (!providersMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is no supported for data filtering");
        }
        return providersMap.get(filterKey).getSpecification(params);
    }
}
