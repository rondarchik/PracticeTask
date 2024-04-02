package repositories;

import java.util.List;
import java.util.UUID;

public interface IBaseRepository<T> {
    List<T> readTable();
    void create(T t);
    void update(T t);
    void delete(UUID id);
}
