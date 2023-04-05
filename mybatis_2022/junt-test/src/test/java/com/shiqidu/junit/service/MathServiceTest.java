package com.shiqidu.junit.service;

import org.junit.Assert;
import org.junit.Test;

/**
 * 单元测试类
 */
public class MathServiceTest {
    @Test
    public void testSum() {
        MathService mathService = new MathService();
        int actual = mathService.sum(1, 2);
        int expected = 3;
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSub() {
        MathService mathService = new MathService();
        int actual = mathService.sub(3, 1);
        int expected = 2;
        Assert.assertEquals(expected, actual);
    }
}
