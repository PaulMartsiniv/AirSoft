package synergy.service;

import java.util.List;

public interface GenericService<T> {
    T save(T t);

    T getById(Long id);

    List<T> findAll();

    void deleteById(Long id);

    void delete(T t);
}
