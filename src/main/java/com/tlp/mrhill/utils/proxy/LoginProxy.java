package com.tlp.mrhill.utils.proxy;

import com.tlp.mrhill.model.User;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
@Component
public class LoginProxy implements InvocationHandler {
    private Object object;
    public Object bind(Object o){
        this.object = o;
        return Proxy.newProxyInstance(o.getClass().getClassLoader(),o.getClass().getInterfaces(),this);
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = null;
        System.out.println("事物开始"+method.getName());
        for (Object o : args){
            System.out.println("arg:"+o.getClass());
            User u = (User) o;
            System.out.println(">>>>>>>>>"+u.getUserName());
        }
        result = method.invoke(object,args);
        System.out.println("事物结束!");
        return result;
    }
}
