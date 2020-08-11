package com.custom_rpc.proxy;

import com.custom_rpc.RpcRequest;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 16:10
 * @Version 1.0
 * @TODO 服务端socket与目标方法的中间代理层
 **/
@Slf4j
public class Mediator {

    // 用来存储发布服务的实例（服务调用的路由）
    public static Map<String, BeanMethod> ROUTING = new ConcurrentHashMap<>();

    private volatile static Mediator instance;

    private Mediator() {}

    public static Mediator getInstance() {
        if (instance == null) {
            synchronized (Mediator.class) {
                if (instance == null) {
                    instance = new Mediator();
                }
            }
        }
        return instance;
    }

    public Object processor(RpcRequest rpcRequest) {
        // 路由key
        String routingKey = rpcRequest.getCalssName() + "." + rpcRequest.getMethodName();
        BeanMethod beanMethod = ROUTING.get(routingKey);
        if (beanMethod == null) {
            return null;
        }

        // 执行目标方法
        Object bean = beanMethod.getBean();
        Method method = beanMethod.getMethod();

        try {
            return method.invoke(bean,rpcRequest.getArgs());
        } catch (IllegalAccessException e) {
            log.error(e.getMessage(),e);
        } catch (InvocationTargetException e) {
            log.error(e.getMessage(),e);
        }
        return null;

    }




}
