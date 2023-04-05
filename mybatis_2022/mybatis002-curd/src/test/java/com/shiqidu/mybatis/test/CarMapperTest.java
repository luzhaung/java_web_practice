package com.shiqidu.mybatis.test;

import com.shiqidu.mybatis.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.HashMap;

public class CarMapperTest {
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
