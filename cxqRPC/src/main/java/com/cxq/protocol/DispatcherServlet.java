package com.cxq.protocol;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DispatcherServlet相当于一个中转站
 */
public class DispatcherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 拿到请求之后，交给相应的处理器去处理，（在一些框架里，handler通常是过滤器和责任链的模式)
        new HttpServerHandler().handler(req, resp);
    }
}
