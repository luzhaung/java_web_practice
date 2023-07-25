package com.shiqidu.bank.dao;

import com.shiqidu.bank.pojo.Account;

public interface AccountDao {

    /**
     * 根据账号查询账户信息
     * @param actNo 账号
     * @return 账户信息
     */
    Account selectByActNo(String actNo);

    /**
     * 更新账户
     * @param act 账户信息
     * @return 1：成功；其他失败
     */
    int updateByActNo(Account act);
}
