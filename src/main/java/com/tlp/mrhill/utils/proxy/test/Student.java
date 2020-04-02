package com.tlp.mrhill.utils.proxy.test;

/**
 * @ClassName Student
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/4/1 17:29
 * @Version 1.0
 **/
public class Student implements Person {
    private String name;
    public  Student(String name){
        this.name = name;
    }
    @Override
    public void giveMoney() {
        System.out.println(name+"上交班费50");
    }
}
