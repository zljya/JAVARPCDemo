package com.cxq;

import com.cxq.protocol.HttpServer;
import com.cxq.register.LocalRegister;

public class Provider {
    public static void main(String[] args) {
        // 注册
        LocalRegister.regist(HelloService.class.getName(), HelloServiceImpl.class);
        // Netty, Tomcat
        HttpServer httpServer  =new HttpServer();
        httpServer.start("localhost", 8080);
    }
}
