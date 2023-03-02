package com.cxq.protocol;

import com.cxq.common.Invocation;
import com.cxq.register.LocalRegister;
import org.apache.commons.io.IOUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 专门处理请求的
 */
public class HttpServerHandler {
    public void handler(HttpServletRequest req, HttpServletResponse resp){
        // 处理请求 --->调用哪个接口的哪个方法，传的什么参数（接口、方法、方法参数，可以把这三者抽象成一个对象）
        //首先要判断序列化的方式然后进行反序列化，这里直接使用了JDK的反序列化
        try {
            Invocation invocation = (Invocation) new ObjectInputStream(req.getInputStream()).readObject();
            //获取到接口名字
            String interfaceName = invocation.getInterfaceName();
            //从注册中心找到对应的接口实现类
            Class classImpl = LocalRegister.get(interfaceName);
            // 根据实现类、方法名字、参数类型找到对应的方法
            Method method = classImpl.getMethod(invocation.getMethodName(), invocation.getParameterTypes());
            //执行方法
            Object result = method.invoke(classImpl.newInstance(), invocation.getParameters());
            // 把结果写入Response中
            IOUtils.write((String)result,resp.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
