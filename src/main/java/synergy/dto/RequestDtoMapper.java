package synergy.dto;

public interface RequestDtoMapper<D, T> {
    T mapToModel(D dto);
}
