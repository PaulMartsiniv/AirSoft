package synergy.dao.spec;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import synergy.entity.Flight;

@Component
public class FlightSpecificationManager implements SpecificationManager<Flight> {
    private final Map<String, SpecificationProvider<Flight>> providersMap;

    public FlightSpecificationManager(List<SpecificationProvider<Flight>> list) {
        this.providersMap = list.stream()
                .collect(Collectors.toMap(SpecificationProvider::getFilterKey,
                        Function.identity()));
    }

    @Override
    public Specification<Flight> get(String filterKey, String[] params) {
        if (!providersMap.containsKey(filterKey)) {
            throw new RuntimeException("Key " + filterKey + " is no supported for data filtering");
        }
        return providersMap.get(filterKey).getSpecification(params);
    }
}
