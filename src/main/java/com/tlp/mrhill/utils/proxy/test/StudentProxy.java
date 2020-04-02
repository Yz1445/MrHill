package com.tlp.mrhill.utils.proxy.test;

import java.io.ObjectInputStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @ClassName StudentProxy
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/4/1 17:32
 * @Version 1.0
 **/
public class StudentProxy implements InvocationHandler {
    private Object o;
    public StudentProxy(Object ob){
        this.o = ob;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object obb = null;
        obb = method.invoke(o,args);
        return null;
    }
}
