package com.custom_rpc.socket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author xiongmin
 * @Description //TODO
 * @Date 2020/8/9 16:22
 * @Version 1.0
 * @TODO spring 容器启动后，会发布毅哥ContextRefreshedEvent,容器启动后启动socket监听
 **/
@Component
@Slf4j
public class SocketServer implements ApplicationListener<ContextRefreshedEvent> {

    /**
     * 创建线程池
     */
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        ServerSocket serverSocket = null;

        try {
            serverSocket = new ServerSocket(8888);
            while (true) {
                Socket accept = serverSocket.accept();

                // 线程池异步处理socket
                executorService.execute(new ProcessorHandler(accept));
            }
        } catch (IOException e) {
            log.error(e.getMessage(),e);
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    log.error(e.getMessage(), e);
                }
            }
        }

    }
}
