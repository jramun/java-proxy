package me.jramun.proxy.examples;

import java.lang.reflect.Proxy;
import java.util.Map;

public class WithLambdaHandler {

    private static Map proxyInstance = (Map) Proxy.newProxyInstance(WithLambdaHandler.class.getClassLoader(), new Class[]{Map.class}, (proxy, method, methodArgs) -> {
        System.out.println("Method call and proxy method run ...");
        return null;
    });


    public static void main(String[] args) {
        proxyInstance.put("Hi", "Poppy");
    }

}
