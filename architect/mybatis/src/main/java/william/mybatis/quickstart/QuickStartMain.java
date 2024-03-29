package william.mybatis.quickstart;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import william.mybatis.quickstart.entity.UserEntity;
import william.mybatis.quickstart.mapper.UserMapper;

/**
 * @Auther: ZhangShenao
 * @Date: 2018/12/7 11:35
 * @Description:
 *
 * SqlSessionFactoryBuilder: 基于建造者模式,读取配置信息,创建SqlSessionFactory。方法级别生命周期
 * SqlSessionFactory: 基于工厂单例模式,创建SqlSession,存在于程序的整个生命周期
 * SqlSession: 代表一次数据库连接。一般通过调用Mapper访问数据库,也可以直接发送SQL执行。线程不安全,要保证线程独享(方法级别生命周期)
 * Mapper: 由一个Java接口和XML文件组成,包含了要执行的SQL语句和结果集映射规则。方法级别生命周期
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

    //测试一级缓存,SqlSession级别,默认开启。
    //MyBatis将Mapper的方法名和参数,通用一定算法,生成缓存的Key
    //任何的insert、update和delete操作都会清空一级缓存
    @Test
    public void testLevel1Cache() {
        //第一次查询,走DB
        SqlSession sqlSession = sqlSessionFactory.openSession(true);
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity = userMapper.selectById(1L);
        System.err.println(userEntity);

        //由于一级缓存是SqlSession级别,因此关闭SqlSession后会清空缓存
        /*sqlSession.close();
        sqlSession = sqlSessionFactory.openSession(true);*/


        //Insert、Update和Delete操作都会更新缓存
       /* userEntity.setNote("New Note");
        userMapper.update(userEntity);*/

        //第二次查询,走缓存
        UserMapper userMapper1 = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity1 = userMapper1.selectById(1L);
        System.err.println(userEntity1);
    }

    //二级缓存存在于SqlSessionFactory的生命周期中,可以理解为跨SqlSession的缓存
    //二级缓存是以namespace为单位的,不同namespace下的操作互不影响。
    //生产环境下建议关闭二级缓存,因为二级缓存容易造成多namespace之间共享一份缓存,从而出现脏读的情况
    @Test
    public void testLevel2Cache() {
        //第一次查询,走DB
        SqlSession sqlSession = sqlSessionFactory.openSession();
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
        UserEntity userEntity = userMapper.selectById(1L);
        System.err.println(userEntity);
        sqlSession.commit();        //提交SqlSession,此时会更新缓存

        //第二次查询,走二级缓存
        UserEntity userEntity1 = userMapper.selectById(1L);
        System.err.println(userEntity1);

        //由于二级缓存是SqlSessionFactory级别的,因此关闭SqlSession不会清空二级缓存
        sqlSession.close();

        //执行更新操作,会清空二级缓存
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        UserMapper userMapper2 = sqlSession2.getMapper(UserMapper.class);
        userEntity.setNote("New Note1111");
        userMapper2.update(userEntity);
        sqlSession2.commit();

        //第三次查询,尽管使用了新创建的SqlSession,但是由于二级缓存是SqlSessionFactory级别的,因此仍然会走二级缓存
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
        UserMapper userMapper3 = sqlSession3.getMapper(UserMapper.class);
        UserEntity userEntity3 = userMapper3.selectById(1L);
        System.err.println(userEntity3);
    }

    //MyBatis反射工具箱的使用
    @Test
    public void testReflect(){
        //创建ObjectFactory
        ObjectFactory objectFactory = new DefaultObjectFactory();

        //通过ObjectFactory实例化对象
        UserEntity user = objectFactory.create(UserEntity.class);

        //创建ObjectWrapperFactory
        ObjectWrapperFactory objectWrapperFactory = new DefaultObjectWrapperFactory();

        //创建ReflectorFactory
        ReflectorFactory reflectorFactory = new DefaultReflectorFactory();

        //通过原始对象,ObjectFactory、ObjectWrapperFactory和ReflectorFactory,创建MetaObject
        MetaObject metaObject = MetaObject.forObject(user, objectFactory, objectWrapperFactory, reflectorFactory);

        //通过MetaObject可以方便地对对象属性进行处理
        Map<String,Object> props = new HashMap<>();
        props.put("id",1L);
        props.put("userName","James");
        props.put("realName","James");
        props.put("sex",1);
        props.put("mobile","12345678901");
        props.put("email","James@qq.com");
        props.put("note","小皇帝");
        props.forEach(metaObject::setValue);

        System.err.println("getterNames: " + Arrays.toString(metaObject.getGetterNames()));

        System.err.println(metaObject.getOriginalObject());
    }

}
