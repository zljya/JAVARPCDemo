package com.cxq.protocol;

import org.apache.catalina.*;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.core.StandardEngine;
import org.apache.catalina.core.StandardHost;
import org.apache.catalina.startup.Tomcat;

public class HttpServer {
    /**
     * 启动服务器端口号接收请求
     * @param hostname
     * @param port
     */
    public void start(String hostname, Integer port){
        //读取用户的配置 server.name=tomcat/netty 可以从配置项或者nacos
        Tomcat tomcat = new Tomcat();

        Server server = tomcat.getServer();
        Service service  =server.findService("Tomcat");

        Connector connector = new Connector();
        connector.setPort(port);

        Engine engine = new StandardEngine();
        engine.setDefaultHost(hostname);

        Host host = new StandardHost();
        host.setName(hostname);

        String contextPath = "";
        Context context = new StandardContext();
        context.setPath(contextPath);
        context.addLifecycleListener(new Tomcat.FixContextListener());

        host.addChild(context);
        engine.addChild(host);

        service.setContainer(engine);
        service.addConnector(connector);

        // tomcat是一个servlet容器，所以向容器中添加一个servlet
        tomcat.addServlet(contextPath,"dispatcher", new DispatcherServlet());
        context.addServletMappingDecoded("/*","dispatcher");
        try{
            tomcat.start();
            tomcat.getServer().await();
        }catch (LifecycleException e){
            e.printStackTrace();
        }

    }
}
