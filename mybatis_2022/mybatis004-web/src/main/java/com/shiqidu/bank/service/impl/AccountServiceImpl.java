package com.shiqidu.bank.service.impl;

import com.shiqidu.bank.dao.AccountDao;
import com.shiqidu.bank.dao.impl.AccountDaoImpl;
import com.shiqidu.bank.exception.AccountNotEnoughException;
import com.shiqidu.bank.exception.TransferException;
import com.shiqidu.bank.pojo.Account;
import com.shiqidu.bank.service.AccountService;

public class AccountServiceImpl implements AccountService {
    private AccountDao accountDao = new AccountDaoImpl();

    public void transfer(String fromActNo, String toActNo, Double money) throws AccountNotEnoughException, TransferException {
        Account fromAccount = accountDao.selectByActNo(fromActNo);
        if (fromAccount.getBalance() < money) {
            throw new AccountNotEnoughException("对不起，余额不足");
        }

        Account toAccount = accountDao.selectByActNo(toActNo);

        fromAccount.setBalance(fromAccount.getBalance() - money);
        toAccount.setBalance(toAccount.getBalance() + money);

        int affectCount = accountDao.updateByActNo(fromAccount);
        affectCount += accountDao.updateByActNo(toAccount);

        if (affectCount < 2) {
            throw new TransferException("转账失败");
        }


    }
}
