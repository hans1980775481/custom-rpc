package com.custom_rpc.service.impl;

import com.custom_rpc.proxy.RpcRemoteService;
import com.custom_rpc.service.IOrderService;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 16:21
 * @Version 1.0
 **/
@RpcRemoteService
public class OrderServiceImpl implements IOrderService {
    @Override
    public String queryOrderList() {
        return "查询 queryOrderList";
    }

    @Override
    public String orderById(String id) {
        return "查询 orderById id = " + id;
    }
}
