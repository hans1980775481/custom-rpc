package com.custom_rpc.service;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 16:20
 * @Version 1.0
 **/
public interface IOrderService {

    String queryOrderList();

    String orderById(String id);
}
