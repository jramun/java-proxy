package me.jramun.proxy.pattern;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

interface UserProvider {
    String getUser(String username);
}

class UserProviderImpl implements UserProvider {

    @Override
    public String getUser(String username) {
        return "Mojtaba (Ramun) Sharif";
    }
}

class UserService {
    private final UserProvider userProvider;

    public UserService(UserProvider userProvider) {
        this.userProvider = userProvider;
    }

    public String getUser(String username) {
        return this.userProvider.getUser(username);
    }
}

class LoggingUserProviderProxy implements InvocationHandler {

    private final Object invocationTarget;

    LoggingUserProviderProxy(Object invocationTarget) {
        this.invocationTarget = invocationTarget;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Log method get user is calling " + Arrays.toString(args));
        return method.invoke(invocationTarget, args);
    }
}

public class DynamicWithJDK {

    public static void main(String[] args) {
        UserProvider userProvider = new UserProviderImpl();
        userProvider = (UserProvider) Proxy.newProxyInstance(DynamicWithJDK.class.getClassLoader(), new Class[]{UserProvider.class}, new LoggingUserProviderProxy(userProvider));
        UserService userService = new UserService(userProvider);
        userService.getUser(";)");

    }
}
