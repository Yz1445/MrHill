package com.tlp.mrhill.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestRunnableAndCallable {
    public static void main(String[] args) {
        TestRunnable();
        testCallable();
        testImplementsCallable();
    }
    //测试实现Runnable接口和启动线程
    public static void TestRunnable(){
        Thread thread = new Thread(new CustomRunnable());
        thread.setName("testRunnable");
        thread.start();
    }
    public static void testCallable(){
        Callable<String> callable = new CustomCallable();
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        Thread thread = new Thread(futureTask);
        thread.setName("testcallable");
        thread.start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    public static void testImplementsCallable(){
        Callable<String> callable = new CustomCallable2();
        FutureTask<String> futureTask = new FutureTask<String>(callable);
        Thread thread = new Thread(futureTask);
        thread.setName("testImplementsCallable");
        thread.start();

        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    //通过实现Runnable接口重写run方法
    static class CustomRunnable implements  Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
    //
    static class CustomCallable implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            return "Callable Result";
        }
    }
    static class CustomCallable2 implements Callable<String>{
        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName());
            throw  new Exception("I can compute result");
        }
    }
}
