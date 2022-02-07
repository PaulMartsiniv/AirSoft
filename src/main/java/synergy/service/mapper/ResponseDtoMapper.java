package synergy.service.mapper;

public interface ResponseDtoMapper<D, T> {
    D toResponseDto(T t);
}
