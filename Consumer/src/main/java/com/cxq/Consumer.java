package com.cxq;

import com.cxq.common.Invocation;
import com.cxq.protocol.HttpClient;

public class Consumer {
    public static void main(String[] args) {
        Invocation invocation = new Invocation(HelloService.class.getName(),"sayHello",
                new Class[]{String.class}, new Object[]{"程小青"});

        HttpClient httpClient = new HttpClient();
        String result = httpClient.send("localhost",8080, invocation);
        System.out.println(result);
    }
}
