package com.shiqidu.bank.dao.impl;

import com.shiqidu.bank.dao.AccountDao;
import com.shiqidu.bank.pojo.Account;
import com.shiqidu.bank.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl implements AccountDao {
    public Account selectByActNo(String actNo) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        return (Account) sqlSession.selectOne("account.selectByActNo", actNo);
    }

    public int updateByActNo(Account act) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        return sqlSession.update("account.updateByActNo", act);
    }
}
