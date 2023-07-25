package com.shiqidu.bank.service;

import com.shiqidu.bank.exception.AccountNotEnoughException;
import com.shiqidu.bank.exception.TransferException;

/**
 * 账户业务类
 *
 * @author luzhuang
 * @version 1.0
 * @since 1.0
 */
public interface AccountService {

    /**
     * 完成转账业务
     * @param fromActNo 转出账号
     * @param toActNo 转入账号
     * @param money 转账金额
     */
    void transfer(String fromActNo, String toActNo, Double money) throws AccountNotEnoughException, TransferException;
}
