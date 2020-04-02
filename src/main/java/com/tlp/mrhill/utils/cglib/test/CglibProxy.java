package com.tlp.mrhill.utils.cglib.test;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @ClassName CglibProxy
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/4/2 15:43
 * @Version 1.0
 **/
public class CglibProxy implements MethodInterceptor {
//    private Object object;
    public Object newInstall(Object object){
        return Enhancer.create(object.getClass(),this);
    }
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("登陆魔兽世界！");
        methodProxy.invokeSuper(o,objects);
        System.out.println("退出魔兽世界！");
        return null;
    }
}
