package synergy.dto;

public interface ResponseDtoMapper<D, T> {
    D toResponseDto(T t);
}
