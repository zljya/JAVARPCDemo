package com.cxq.register;

import java.util.HashMap;
import java.util.Map;

/**
 * 本地注册，当然还可以扩展为注册中心注册
 */
public class LocalRegister {
    // 接口名字对应的类
    private static Map<String, Class> map = new HashMap<>();

    /**
     * 注册接口，接口名字对应哪个实现类
     * @param interfaceName
     * @param implClass
     */
    public static void regist(String interfaceName, Class implClass) {
        map.put(interfaceName, implClass);
    }

    /**
     * 根据接口名字返回接口
     * @param interfaceName
     * @return
     */
    public static Class get(String interfaceName){
        return map.get(interfaceName);
    }
}
