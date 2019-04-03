package william.mybatis.quickstart;

import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.test.annotation.Rollback;
import org.springframework.util.ResourceUtils;
import william.mybatis.quickstart.entity.UserEntity;
import william.mybatis.quickstart.mapper.UserMapper;

import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/7 11:35
 * @Description:
 */
public class QuickStartMain {
    private SqlSessionFactory sqlSessionFactory;

    @Before
    public void init() throws Exception {
        InputStream inputStream = ResourceUtils.getURL("classpath:mybatis-config.xml").openStream();
        sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
    }

    @Test
    public void testGetUserById() {
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity = userMapper.selectById(1L);
        System.err.println(userEntity);
    }

    @Test
    public void testInsert() {
        //自动提交事务,默认为false
        SqlSession sqlSession = sqlSessionFactory.openSession(true);

        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity = new UserEntity();
        userEntity.setUserName("James");
        userEntity.setRealName("James");
        userEntity.setMobile("13812345678");
        userEntity.setSex(1);
        userEntity.setEmail("james@qq.com");
        userEntity.setNote("小皇帝");
        userMapper.insert(userEntity);
        System.err.println(userEntity.getId());
    }

    @Test
    public void testBatchInsert() {
        //设置Batch模式的SqlSession,批量执行SQL
        //ExecutorType默认为SIMPLE
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity1 = new UserEntity();
        userEntity1.setUserName("James");
        userEntity1.setRealName("James");
        userEntity1.setMobile("13812345678");
        userEntity1.setSex(1);
        userEntity1.setEmail("james@qq.com");
        userEntity1.setNote("小皇帝");

        //调用Mapper的方法,此时并不会真正执行SQL语句
        userMapper.insert(userEntity1);
        System.err.println("Insert UserEntity1");
        UserEntity userEntity2 = new UserEntity();
        userEntity2.setUserName("Kobe");
        userEntity2.setRealName("Kobe");
        userEntity2.setMobile("12911112222");
        userEntity2.setSex(1);
        userEntity2.setEmail("kobe@qq.com");
        userEntity2.setNote("黑曼巴");

        //调用Mapper的方法,此时并不会真正执行SQL语句
        userMapper.insert(userEntity2);


        //提交事务,此时批量语句一起执行
        sqlSession.commit();
        System.err.println("Insert UserEntity2");
    }

    @Test
    public void testLevel1Cache() {
        //测试一级缓存，SqlSession级别，默认开启。MyBatis将Mapper的方法名和参数，通用一定算法，生成缓存的Key
        //任何的insert、update和delete操作都会清空一级缓存
        //第一次查询，走DB
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity = userMapper.selectById(1L);
        System.err.println(userEntity);

        //update操作会更新缓存
        userEntity.setNote("New Note");
        userMapper.update(userEntity);


        //第二次查询，走缓存
        UserMapper userMapper1 = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity1 = userMapper1.selectById(1L);
        System.err.println(userEntity1);
    }

    @Test
    public void testLevel2Cache() {
        //二级缓存存在于 SqlSessionFactory 的生命周期中，可以理解为跨sqlSession；
        //缓存是以namespace为单位的，不同namespace下的操作互不影响。

        //第一次查询，走DB
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity = userMapper.selectById(1L);
        System.err.println(userEntity);
        sqlSession.commit();

        //执行更新操作，会清空二级缓存
       /* userEntity.setNote("New Note1111");
        userMapper.update(userEntity);
        sqlSession.commit();*/

        //第二次查询，走二级缓存
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        UserEntity userEntity2 = userMapper2.selectById(1L);
        System.err.println(userEntity2);
    }

}
