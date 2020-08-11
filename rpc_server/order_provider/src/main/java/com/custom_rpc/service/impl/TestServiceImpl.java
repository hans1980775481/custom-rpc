package com.custom_rpc.service.impl;

import com.custom_rpc.proxy.RpcRemoteService;
import com.custom_rpc.service.ITestService;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 16:21
 * @Version 1.0
 **/
@RpcRemoteService
public class TestServiceImpl implements ITestService {
    @Override
    public String sayHello() {
        return "Hello world";
    }
}
