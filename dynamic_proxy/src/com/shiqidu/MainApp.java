package com.shiqidu;

import com.shiqidu.factory.UsbKingstonFactory;
import com.shiqidu.handler.MySellHandler;
import com.shiqidu.service.UsbDiskSell;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class MainApp {
    public static void main(String[] args) {
        UsbDiskSell factory = new UsbKingstonFactory();
        InvocationHandler handler = new MySellHandler(factory);
        UsbDiskSell proxy = (UsbDiskSell) Proxy.newProxyInstance(factory.getClass().getClassLoader(), factory.getClass().getInterfaces(), handler);
        float sell = proxy.sell(1);
        System.out.println("动态代理对象调用方法: " + sell);
    }
}
