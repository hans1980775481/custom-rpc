package com.custom_rpc.rpc_client.controller;

import com.custom_rpc.rpc_client.proxy.RpcReference;
import com.custom_rpc.service.IOrderService;
import com.custom_rpc.service.ITestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 17:03
 * @Version 1.0
 **/
@RestController
public class TestController {

    @RpcReference
    private IOrderService orderService;

    @RpcReference
    private ITestService testService;

    @GetMapping("/test")
    public String test() {
        return orderService.queryOrderList();
    }

    @GetMapping("/get")
    public String get() {
        return testService.sayHello();
    }

    @GetMapping("/param/{name}")
    public String test2(@PathVariable("name") String name) {
        String result = orderService.orderById(name);
        return result;
    }



}
