package DataService.mybatis;

import DataObject.YouthNews;
import DataService.DataService;
import DatabaseAssist.mybatis.mapper.YouthNewsMapper;
import Util.MyLogger;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 *  YouthNews的服务类,使用mybatis功能
 *
 *  当使用多线程启动时，由于SqlSession是一个非线程安全的类，所以为了保证不会发生线程安全问题，我的做法是
 *      直接在method scope内启动和关闭一个sqlsession，这样线程之间的各自的sqlsession是不同的，也就不会互相影响
 *  更推荐的方式是使用ThreadLocal类去包装Sqlsession
 */
public class YouthNewsService implements DataService<YouthNews> {

    private static SqlSessionFactory sqlSessionFactory;

    @Override
    public void init() throws IOException {
        String resource = "properties/mybatis-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        MyLogger.log("YouthNewsService init over");
    }

    @Override
    public void closed() {

    }

    @Override
    public int add(YouthNews obj) {
        SqlSession session = sqlSessionFactory.openSession(true);
        YouthNewsMapper youthNewsMapper = session.getMapper(YouthNewsMapper.class);
        youthNewsMapper.insert(obj);
        session.close();
        return obj.getId();
    }

    @Override
    public void adds(List<YouthNews> obj) {
        SqlSession session = sqlSessionFactory.openSession(true);
        YouthNewsMapper youthNewsMapper = session.getMapper(YouthNewsMapper.class);
        youthNewsMapper.inserts(obj);
        session.close();
    }

    @Override
    public YouthNews get(int id) {
        return null;
    }

    @Override
    public void update(YouthNews obj) {

    }

    @Override
    public void delete(int id) {

    }
}
