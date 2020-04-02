package com.tlp.mrhill.utils.proxy.test;

import java.lang.reflect.Proxy;

/**
 * @ClassName ProxyTest
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/4/1 17:31
 * @Version 1.0
 **/
public class ProxyTest {
    public static void main(String[] args) {
        Student z = new Student("张三");
        z.giveMoney();
        StudentProxy sp = new StudentProxy(z);
        Person ps = (Person) Proxy.newProxyInstance(z.getClass().getClassLoader(),z.getClass().getInterfaces(),sp);

        ps.giveMoney();
    }
}
