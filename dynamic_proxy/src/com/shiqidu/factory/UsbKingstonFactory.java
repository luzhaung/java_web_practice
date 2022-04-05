package com.shiqidu.factory;

import com.shiqidu.service.UsbDiskSell;

public class UsbKingstonFactory implements UsbDiskSell {
    @Override
    public float sell(int amount) {
        System.out.println("目标类中执行了sell目标方法");
        return 58;
    }
}
