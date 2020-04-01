package com.tlp.mrhill.Test;

import com.tlp.mrhill.vo.UserVO;

import java.lang.reflect.Constructor;

/**
 * @ClassName TestReflect
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/4/1 16:09
 * @Version 1.0
 **/
public class TestReflect {
    public static void main(String[] args) {
        UserVO userVO = new UserVO();
        Class userVOClass = userVO.getClass();//获取Class对象
        System.out.println(userVOClass);

        Class userVoClass2 = userVO.getClass();//获取Class对象
        System.out.println(userVoClass2);

        try {
            Class userVoClass3 = Class.forName("com.tlp.mrhill.vo.UserVO");
            System.out.println(userVoClass3);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("------------------所有构造函数-----------------");
        Constructor[] constructors = userVoClass2.getDeclaredConstructors();
        for (Constructor c : constructors){
            System.out.println(c);
        }


    }
}
