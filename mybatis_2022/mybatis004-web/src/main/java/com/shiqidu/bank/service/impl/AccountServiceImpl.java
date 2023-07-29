package com.shiqidu.bank.service.impl;

import com.shiqidu.bank.dao.AccountDao;
import com.shiqidu.bank.dao.impl.AccountDaoImpl;
import com.shiqidu.bank.exception.AccountNotEnoughException;
import com.shiqidu.bank.exception.TransferException;
import com.shiqidu.bank.pojo.Account;
import com.shiqidu.bank.service.AccountService;
import com.shiqidu.bank.util.GenerateDaoProxy;
import com.shiqidu.bank.util.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;

public class AccountServiceImpl implements AccountService {

    // 自己写的dao实现类
    // private final AccountDao accountDao = new AccountDaoImpl();
    // 自己封装的动态代理
    // private final AccountDao accountDao = (AccountDao) GenerateDaoProxy.generate(SqlSessionUtil.openSession(), AccountDao.class);
    // mybatis的动态代理
    private final AccountDao accountDao = SqlSessionUtil.openSession().getMapper(AccountDao.class);

    /**
     * 转账方法
     *
     * @param fromActNo 转出账号
     * @param toActNo   转入账号
     * @param money     转账金额
     * @throws AccountNotEnoughException 账户不足异常
     * @throws TransferException         其他异常
     */
    public void transfer(String fromActNo, String toActNo, Double money) throws AccountNotEnoughException, TransferException {
        SqlSession sqlSession = SqlSessionUtil.openSession();
        Account fromAccount = accountDao.selectByActNo(fromActNo);
        if (fromAccount == null) {
            throw new TransferException("转出账户不存在");
        }
        if (fromAccount.getBalance() < money) {
            throw new AccountNotEnoughException("对不起，余额不足");
        }

        Account toAccount = accountDao.selectByActNo(toActNo);
        if (toAccount == null) {
            throw new TransferException("转入账户不存在");
        }
        fromAccount.setBalance(fromAccount.getBalance() - money);
        toAccount.setBalance(toAccount.getBalance() + money);

        int affectCount = accountDao.updateByActNo(fromAccount);

        affectCount += accountDao.updateByActNo(toAccount);

        sqlSession.commit();
        SqlSessionUtil.close(sqlSession);

        if (affectCount < 2) {
            throw new TransferException("转账失败");
        }
    }
}
