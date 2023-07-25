package com.shiqidu.bank.dao.impl;

import com.shiqidu.bank.dao.AccountDao;
import com.shiqidu.bank.pojo.Account;
import com.shiqidu.bank.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountDaoImpl implements AccountDao {
    public Account selectByActNo(String actNo) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Account account = (Account) sqlSession.selectOne("account.selectByActNo", actNo);
        sqlSession.close();
        return account;
    }

    public int updateByActNo(Account act) {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        int count = sqlSession.selectOne("account.updateByActNo", act);
        sqlSession.commit();
        sqlSession.close();
        return count;
    }
}
