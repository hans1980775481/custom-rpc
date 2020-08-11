package com.custom_rpc.rpc_client.socket;

import com.custom_rpc.RpcRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 17:03
 * @Version 1.0
 * @TODO rpc socket 网络传输
 **/
@Slf4j
@Component
public class RpcNetTransport {

    @Value("${rpc.host}")
    private String host;
    @Value("${rpc.port}")
    private int port;


    public Object send(RpcRequest rpcRequest) {
        ObjectOutputStream outputStream = null;
        ObjectInputStream inputStream = null;

        try {
            Socket socket = new Socket(host, port);

            // 发送目标方法信息
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(rpcRequest);
            outputStream.flush();

            // 接收返回值
            inputStream = new ObjectInputStream(socket.getInputStream());
            return inputStream.readObject();

        } catch (UnknownHostException e) {
            log.error(e.getMessage(),e);
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        } catch (ClassNotFoundException e) {
            log.error(e.getMessage(),e);
        } finally {
            closeStream(inputStream,outputStream);
        }
        return null;
    }

    public void closeStream(ObjectInputStream inputStream, ObjectOutputStream outputStream) {
        // 关闭输入输出流
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                log.error(e.getMessage(),e);
            }
        }
    }
}
