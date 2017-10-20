package by.vitali.domain.services.interfaces;



import java.util.List;

public interface GeneralManagement<T> {

    void save(T type) throws Exception;

    void update(T type) throws Exception;

    T read(int id) throws Exception;

    List<T> getAll() throws Exception;
}
