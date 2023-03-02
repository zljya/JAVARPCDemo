package com.cxq.protocol;

import com.cxq.common.Invocation;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpClient {
    public String send(String hostname, Integer port, Invocation invocation) {
        // 读取用户的配置（用户是用原始的方式发送http请求还是框架等）
        try {
            URL url = new URL("http", hostname, port, "/");
            // 创建连接
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            System.out.println(httpURLConnection.getURL());

            httpURLConnection.setRequestMethod("POST");
            httpURLConnection.setDoOutput(true);

            //配置
            OutputStream outputStream = httpURLConnection.getOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);

            oos.writeObject(invocation);
            oos.flush();
            oos.close();

            //阻塞式的获取结果，一定要等到代码返回
            InputStream inputStream = httpURLConnection.getInputStream();
            String result = IOUtils.toString(inputStream);
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
