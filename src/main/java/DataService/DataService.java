package DataService;

import java.io.IOException;
import java.util.List;

public interface DataService<E> {

    void init() throws IOException;

    void closed();

    //crud

    int add(E obj);

    void adds(List<E> obj) throws ClassNotFoundException;

    E get(int id);

    void update(E obj);

    void delete(int id);
}
