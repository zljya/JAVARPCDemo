package com.cxq.common;


import java.io.Serializable;

// 因为是网络请求的参数，所以要支持序列化,这里用的是JDK的序列化，也要实现其他的比如用JSON的序列化方式等
public class Invocation implements Serializable {
    //调用的接口名
    private String interfaceName;
    //调用的方法名
    private String methodName;
    //调用的参数类型，判断同名方法的重载
    private Class[] parameterTypes;
    //调用的参数值
    private Object[] parameters;

    public Invocation(String interfaceName, String methodName, Class[] parameterTypes, Object[] parameters) {
        this.interfaceName = interfaceName;
        this.methodName = methodName;
        this.parameterTypes = parameterTypes;
        this.parameters = parameters;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Class[] getParameterTypes() {
        return parameterTypes;
    }

    public void setParameterTypes(Class[] parameterTypes) {
        this.parameterTypes = parameterTypes;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public void setParameters(Object[] parameters) {
        this.parameters = parameters;
    }
}
