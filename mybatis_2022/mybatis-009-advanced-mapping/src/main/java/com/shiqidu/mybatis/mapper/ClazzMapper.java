package com.shiqidu.mybatis.mapper;

import com.shiqidu.mybatis.pojo.Clazz;

public interface ClazzMapper {

    /**
     * 分步查询第一步
     * @param cid cid
     * @return Clazz
     */
    Clazz selectByStep1(Integer cid);

    /**
     * 根据班级编号查询班级
     * @param cid cid
     * @return Clazz
     */
    Clazz selectByCollection(Integer cid);

    /**
     * 分步查询第二步
     * @param cid cid
     * @return Clazz
     */
    Clazz selectByIdStep2(Integer cid);
}
