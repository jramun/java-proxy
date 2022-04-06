package me.jramun.proxy.examples;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

public class SimpleHandler {

    public static void main(String[] args) {
        Map proxyInstance = (Map) Proxy.newProxyInstance(SimpleHandler.class.getClassLoader(), new Class[]{Map.class}, new DynamicInvocationHandler());
        proxyInstance.put("Hello", "World");
    }
}

class DynamicInvocationHandler implements InvocationHandler {


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("invoke method " + method.getName());
        return 42;
    }
}
