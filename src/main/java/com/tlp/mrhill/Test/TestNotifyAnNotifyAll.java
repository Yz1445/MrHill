
package com.tlp.mrhill.Test;

public class TestNotifyAnNotifyAll {
    private static Object obj = new Object();

    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunnableImplA(obj));
        Thread thread2 = new Thread(new RunnableImplA(obj));
        thread1.start();
        thread2.start();
//        Thread thread3 = new Thread(new RunnableImplB(obj));
//        thread3.start();
        Thread thread4 = new Thread(new RunnableImplC(obj));
        thread4.start();
    }
    static class RunnableImplA implements Runnable{
        private Object obj;
        public RunnableImplA (Object obj) {
            this.obj = obj;
        }
        @Override
        public void run() {
            System.out.println("运行RunnableImplA");
            synchronized (obj){
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("obj 在RunnableImplA上继续执行！");
            }

        }
    }
    static class RunnableImplB implements Runnable{
        private Object obj;
        public RunnableImplB(Object obj){
            this.obj= obj;
        }
        @Override
        public void run() {
            System.out.println("运行RunnableImplB");
            System.out.println("睡眠3秒");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj){
                System.out.println("通知B开始运行");
                obj.notify();
            }
        }
    }
    static class RunnableImplC implements Runnable{
        private Object obj;
        public RunnableImplC(Object obj){
            this.obj = obj;
        }
        @Override
        public void run() {
            System.out.println("运行RunnableImplC");
            System.out.println("RunnableImplC睡眠3秒");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (obj){
                System.out.println("唤醒所有等待线程");
                obj.notifyAll();
            }
        }
    }

}
