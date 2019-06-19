package DatabaseAssist.mybatis;

import java.util.List;
import java.util.Map;

public interface BaseMapper<E> {

    int insert(E obj);

    void inserts(List<E> objs);

    int delete(int id);

    int update(E obj);

    E select(int id);

    List<E> selects(Map<String,Object> condition);
}
