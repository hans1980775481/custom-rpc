package com.custom_rpc.socket;

import com.custom_rpc.RpcRequest;
import com.custom_rpc.proxy.Mediator;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 16:21
 * @Version 1.0
 **/
@Slf4j
public class ProcessorHandler implements Runnable {
    private Socket socket;

    public ProcessorHandler(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void run() {
        ObjectInputStream inputStream = null;
        ObjectOutputStream outputStream = null;
        try {
            inputStream = new ObjectInputStream(socket.getInputStream());

            // 反序列化
            RpcRequest rpsRequest = (RpcRequest) inputStream.readObject();

            // 中间代理执行目标方法
            Mediator instance = Mediator.getInstance();
            Object response = instance.processor(rpsRequest);
            System.out.println("服务端的执行结果：" + response);

            // 将执行结果返回给客户端
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(response);
            outputStream.flush();


        } catch (IOException e) {
            log.error(e.getMessage(), e);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(), e);
        } finally {
            closeStream(inputStream, outputStream);
        }
    }

    /**
     * 关闭输入输出流
     *
     * @param inputStream
     * @param outputStream
     */
    public void closeStream(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
    }
}
