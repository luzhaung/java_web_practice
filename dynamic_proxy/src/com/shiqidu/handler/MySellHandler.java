package com.shiqidu.handler;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MySellHandler implements InvocationHandler {
    private Object target = null;

    public MySellHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object res;
        res = method.invoke(target, args);
        if (res != null) {
            float price = (float) res;
            price += 20;
            res = price;
        }
        return res;
    }
}
