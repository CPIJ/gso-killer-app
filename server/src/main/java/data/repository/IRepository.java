package data.repository;

import java.util.List;
import java.util.function.Predicate;

public interface IRepository<T> {

    List<T> getAll();
    List<T> findBy(Predicate<T> predicate);
    boolean add(T entity);
    boolean addRange(List<T> entities);
    boolean update(T entity);
    boolean remove(T entity);
    void save();

}
