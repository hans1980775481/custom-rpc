package com.custom_rpc.proxy;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 12:52
 * @Version 1.0
 * @TODO 初始化中间层代理对象 一定要加上Component注解，不然在加载bean的时候这类会被忽视，导致里面的方法没有被运行
 **/
@Component
public class InitialMerdiator implements BeanPostProcessor {

    /**
     * 发布服务，【就是将可以远程调用的类(加了RpcRemoteService注解的类)，加入到Mediator.ROUTING中】
     *
     * @param bean
     * @param beanName
     * @return
     * @throws BeansException
     */
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        // 加了服务发布标记的bean进行远程发布发布所有的方法[服务]

        if (bean.getClass().isAnnotationPresent(RpcRemoteService.class)) {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method method : methods) {
                String routingKey = bean.getClass().getInterfaces()[0].getName() + "." + method.getName();

                BeanMethod beanMethod = new BeanMethod();
                beanMethod.setBean(bean);
                beanMethod.setMethod(method);

                Mediator.ROUTING.put(routingKey, beanMethod);
            }
        }

        return bean;
    }
}
