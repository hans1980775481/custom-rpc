package com.custom_rpc.proxy;

import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 12:49
 * @Version 1.0
 * @TODO  服务端服务发现注解
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface RpcRemoteService {

}
