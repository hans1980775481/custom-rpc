package com.custom_rpc.rpc_client.proxy;

import com.custom_rpc.RpcRequest;
import com.custom_rpc.rpc_client.socket.RpcNetTransport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 17:04
 * @Version 1.0
 **/
@Component
public class RemoteInvocationHandler implements InvocationHandler {

    @Autowired
    private RpcNetTransport rpcNetTransport;

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setCalssName(method.getDeclaringClass().getName());
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setType(method.getParameterTypes());
        rpcRequest.setArgs(args);

        return rpcNetTransport.send(rpcRequest);
    }
}
