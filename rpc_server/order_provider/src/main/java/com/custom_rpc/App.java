package com.custom_rpc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Hello world!
 * @TODO 服务端启动类,要加载Bean
 */
@Configuration
@ComponentScan("com.custom_rpc")
public class App {
    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(App.class);
    }
}
