package com.tlp.mrhill.Test;

import java.util.Timer;
import java.util.concurrent.*;

public class TestThreadPool {
    public static void main(String[] args) throws InterruptedException {
//        TestNewFixedThreadPool();
//        TestNewCachedThreadPool();
//        TestNewScheduledThreadPool();
        TestNesSingleThreadPool();
    }

    static ExecutorService fixedThreadPoll = Executors.newFixedThreadPool(3);
    public static void TestNewFixedThreadPool(){
        for (int i = 0; i < 6; i++){
            final int index = i;
            fixedThreadPoll.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+",index:"+index);
                }
            });
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("4秒以后。。。");
        fixedThreadPoll.shutdown();
    }

    static ExecutorService cachedThreadPoll = Executors.newCachedThreadPool();
    public static  void TestNewCachedThreadPool() throws InterruptedException {
        for (int i = 0; i < 6; i++){
            final int index = i;
            cachedThreadPoll.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+",index:"+index);
                }
            });
        }
        Thread.sleep(4000);
        System.out.println("4秒之后。。。");
        cachedThreadPoll.shutdown();
    }

    static ScheduledExecutorService scheduledThreadpool = Executors.newScheduledThreadPool(3);
    public static void TestNewScheduledThreadPool(){
        for (int i = 0; i < 3; i++){
            final int index = i;
            scheduledThreadpool.scheduleWithFixedDelay(new Runnable() {
                @Override
                public void run() {
                    System.out.println(Thread.currentThread().getName()+",index:"+index);
                }
            },0,3,TimeUnit.SECONDS);
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("4millis....");
        scheduledThreadpool.shutdown();
    }

    static ExecutorService singleThreadPool = Executors.newSingleThreadExecutor();
    public static void TestNesSingleThreadPool(){
        for (int i = 0; i < 3; i++){
            final int index = i;
            singleThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName()+",index:"+index);
                }
            });
        }
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("4millis....");
        singleThreadPool.shutdown();
    }
}
