package com.shiqidu.bank.exception;

public class AccountNotEnoughException extends Exception {
    public AccountNotEnoughException() {
    }

    public AccountNotEnoughException(String msg) {
        super(msg);
    }
}
