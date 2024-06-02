package com.shiqidu.myspring.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author luzhuang
 * @version 1.0
 * @className null.java
 * @since 1.0
 */
@Retention(RetentionPolicy.RUNTIME)
public @interface Component {

    String value();
}
