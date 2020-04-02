package com.tlp.mrhill.Test;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @ClassName TestAtomic
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/3/31 17:45
 * @Version 1.0
 **/
public class TestAtomic {
    private AtomicInteger data = new AtomicInteger();
    static {
        System.out.println("初始化");
    }

    public static void main(String[] args) {

    }
}
