package com.custom_rpc.proxy;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 16:11
 * @Version 1.0
 **/
@Data
public class BeanMethod {
    private Object bean;

    private Method method;
}
