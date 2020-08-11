package com.custom_rpc.rpc_client.proxy;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Proxy;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 17:03
 * @Version 1.0
 * @TODO 远程动态调用service代理
 **/
@Slf4j
@Component
public class ReferenceInvokeProxy implements BeanPostProcessor {

    @Autowired
    private RemoteInvocationHandler invocationHandler;

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        Field[] fields = bean.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(RpcReference.class)) {
                field.setAccessible(true);

                // 针对这个加了RpcReference注解的字段，设置一个代理的值
                Object proxy = Proxy.newProxyInstance(field.getType().getClassLoader(), new Class<?>[]{field.getType()}, invocationHandler);

                // 相当于针对加了RpcReference的注解，设置了一个代理，这个代理的是现实invocationHandler
                try {
                    field.set(bean,proxy);
                } catch (IllegalAccessException e) {
                    log.error(e.getMessage(),e);
                }

            }
        }
        return bean;
    }
}
