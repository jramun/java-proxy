package me.jramun.proxy.pattern;

interface UserProviderM {
    String getUser(String username);
}

class UserProviderImplM implements UserProviderM {

    @Override
    public String getUser(String username) {
        return "Mojtaba (Ramun) Sharif";
    }
}

class UserServiceM {
    private final LoggingUserProviderProxyM proxy;

    public UserServiceM(LoggingUserProviderProxyM proxy) {
        this.proxy = proxy;
    }

    public String getUser(String username) {
        return this.proxy.getUser(username);
    }
}

class LoggingUserProviderProxyM implements UserProviderM {
    private final UserProviderM userProvider;

    public LoggingUserProviderProxyM(UserProviderM userProvider) {
        this.userProvider = userProvider;
    }

    @Override
    public String getUser(String username) {
        System.out.println("Log method get user is calling " + username);
        return userProvider.getUser(username);
    }
}

public class Manually {

    public static void main(String[] args) {
        UserProviderM userProvider = new UserProviderImplM();
        LoggingUserProviderProxyM proxy = new LoggingUserProviderProxyM(userProvider);
        UserServiceM userServiceM = new UserServiceM(proxy);
        System.out.println(userServiceM.getUser(":("));
    }
}