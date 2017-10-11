package by.vitali.domain.services.interfaces;



import java.util.List;

public interface IService<TYPE> {

    void add(TYPE type) throws Exception;

    void update(TYPE type) throws Exception;

    TYPE getById(int id) throws Exception;

    List<TYPE> getAll() throws Exception;
}
