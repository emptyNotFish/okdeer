package base;

import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.List;

/**
 * Created by Administrator on 2018/4/21 0021.
 */
public interface IBaseMapper {

    //public List<V> findList(T t);

    void add();

    void update();

    void delete(String id);

    Object findById(String id);
}
