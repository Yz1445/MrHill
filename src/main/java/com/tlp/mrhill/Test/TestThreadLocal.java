package com.tlp.mrhill.Test;

import java.util.concurrent.CountDownLatch;

/**
 * @ClassName TestThreadLocal
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/3/18 15:54
 * @Version 1.0
 **/
public class TestThreadLocal {

    private static ThreadLocal<Integer> numbers = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue(){
            return 100;
        }
    };
    public static  Integer aa = 100;
    public static void main(String[] args) {
        doNotUseThreadLocal();
//        useThreadLocal();
    }
    public static void doNotUseThreadLocal() {
        int threads = 9;
        System.out.println(aa);
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++){
            new Thread(() -> {
                aa+=1;
                System.out.println(aa);
                countDownLatch.countDown();
            }).start();
        }
    }
    public static void useThreadLocal(){
        int threads = 9;
        CountDownLatch countDownLatch = new CountDownLatch(threads);
        for (int i = 0; i < threads; i++){
            Thread thread = new Thread(() -> {
                numbers.set(numbers.get()+1);
                System.out.println(numbers.get());
                countDownLatch.countDown();
            });
            thread.start();
        }
    }
}
