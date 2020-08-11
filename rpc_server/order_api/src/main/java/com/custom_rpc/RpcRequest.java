package com.custom_rpc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 16:20
 * @Version 1.0
 * @TODO rpc通信参数传递类
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RpcRequest implements Serializable {
    private static final long serialVersionUID = 5602161762057659482L;

    /**
     * 类名
     */
    private String calssName;

    /**
     * 方法名
     */
    private String methodName;
    /**
     * 方法参数
     */
    private Object[] args;
    /**
     * 参数类型
     */
    private Class[] type;



}
