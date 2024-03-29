package com.shiqidu.mybatis.test;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
import com.shiqidu.mybatis.pojo.Car;
import org.apache.ibatis.session.SqlSession;
import com.shiqidu.mybatis.util.SqlSessionUtil;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class CarMapperTest {

    @Test
    public void testDataSource() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));

        for (int i = 0; i < 11; i++) {
            SqlSession sqlSession = sqlSessionFactory.openSession();
            Car car = new Car(null, "33333", "比亚迪F9", 12.0, "2022-11-11", "插混");
            sqlSession.insert("car.insertCar", car);
            /*sqlSession.commit();
            sqlSession.close();*/
        }
    }

    @Test
    public void testEnvironment() throws IOException {
        SqlSessionFactoryBuilder sqlSessionFactoryBuilder = new SqlSessionFactoryBuilder();
        SqlSessionFactory sqlSessionFactory = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"));
        SqlSession sqlSession = sqlSessionFactory.openSession();

        Car car = new Car(null, "33333", "比亚迪F9", 12.0, "2022-11-11", "插混");
        sqlSession.insert("car.insertCar", car);

        sqlSession.commit();
        sqlSession.close();


        SqlSessionFactory sqlSessionFactory1 = sqlSessionFactoryBuilder.build(Resources.getResourceAsStream("mybatis-config.xml"), "test");
        SqlSession sqlSession1 = sqlSessionFactory1.openSession();

        Car car1 = new Car(null, "55555", "比亚迪F10", 15.0, "2023-06-11", "插混");
        sqlSession1.insert("car.insertCar", car1);

        sqlSession1.commit();
        sqlSession1.close();
    }

    @Test
    public void testNamespace() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        List<Object> cars = sqlSession.selectList("userMapper.selectAll");
        cars.forEach(System.out::println);
        sqlSession.close();
    }

    @Test
    public void selectCarByCarNo() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        List<Car> cars = sqlSession.selectList("car.selectCarByCarNo", "1003");
        if (cars != null) {
            cars.forEach(System.out::println);
        }
        if (cars != null) {
            System.out.println("查询到" + cars.size() + "条数据");
        } else {
            System.out.println("未查到数据");
        }
        sqlSession.close();
    }

    @Test
    public void selectCarById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = sqlSession.selectOne("selectCarById", 1);
        System.out.println(car);
        sqlSession.close();
    }

    @Test
    public void updateCarById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = new Car(14, "2222", "比亚迪F1", 13.0, "2023-11-11", "插混1");
        int count = sqlSession.update("updateCarById", car);
        System.out.println("更新" + count + "条数据");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void deleteCarById() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.delete("deleteCarById", 15);
        System.out.println("删除" + count + "条数据");
        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void insertCarByPojo() {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Car car = new Car(null, "3333", "比亚迪F1", 12.0, "2022-11-11", "插混");
        int count = sqlSession.insert("insertCarByPojo", car);
        System.out.println("插入" + count + "条数据");

        sqlSession.commit();
        sqlSession.close();
    }

    @Test
    public void insertCar() {
        SqlSession sqlSession = SqlSessionUtil.openSession();

        HashMap<Object, Object> map = new HashMap<>();
        map.put("car_no", "1111");
        map.put("brand", "比亚迪");
        map.put("guide_price", 10.0);
        map.put("produce_time", "2022-10-11");
        map.put("car_type", "插混");

        int count = sqlSession.insert("insertCar", map);
        System.out.println("插入" + count + "条数据");
        sqlSession.commit();
        sqlSession.close();
    }
}
