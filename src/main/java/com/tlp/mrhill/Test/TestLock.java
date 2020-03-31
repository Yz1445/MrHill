package com.tlp.mrhill.Test;

import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @ClassName TestLock
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/3/31 15:58
 * @Version 1.0
 **/
public class TestLock {
    static class MyService{
        private Lock lock = new ReentrantLock();
        public void serviceA(){
            lock.lock();
            for (int i = 0; i < 3; i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+",Ai="+i);
            }
            lock.unlock();
        }
        public void serviceB(){
            lock.lock();
            for (int i = 0; i < 3; i++){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+",Bi="+i);
            }
            lock.unlock();
        }
    }
    /**
     * @author yangzhao
     * @Description 对内部类Myservice进行测试，证明lock的加锁解锁
     * @Date 16:41 2020/3/31
     * @Param []
     * @return void
     **/
    public  static void TestMyService() {
        final MyService myService = new MyService();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService.serviceA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService.serviceB();
            }
        }).start();
    }

    static class MyService2{
        private Lock lock = new ReentrantLock();
        private Condition condition = lock.newCondition();
        private Condition condition2 = lock.newCondition();
        public void ServiceA(){
            try {
                lock.lock();
                condition.await();//线程挂起，进入等待状态
                System.out.println(Thread.currentThread().getName()+",Ai=");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        public void ServiceB(){
            try {
                lock.lock();

                System.out.println(Thread.currentThread().getName()+",Bi=");
                condition.signal();//唤醒线程
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
        public void ServiceC(){
            try {
                lock.lock();
                condition2.await();//线程挂起，进入等待状态
                System.out.println(Thread.currentThread().getName()+",Ci=");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
        public void ServiceD(){
            try {
                lock.lock();
                System.out.println(Thread.currentThread().getName()+",Di=");
                condition2.signal();//唤醒线程
            } catch (Exception e){
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
    /**
     * @author yangzhao
     * @Description 测试TestMyService2 进行线程的挂起与唤醒操作
     * @Date 16:49 2020/3/31
     * @Param []
     * @return void
     **/
    public static void TestMyService2(){
        final MyService2 myService2 = new MyService2();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService2.ServiceA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
//                myService2.ServiceB(); //方法B是通过condition唤醒，可以唤醒被condition.await()的线程
                myService2.ServiceD();//方法是通过condition2唤醒，不可以唤醒被condition.await()的线程
            }
        }).start();
    }

    static class MyService3{
        private Lock lock = new ReentrantLock();
        public void ServiceA(){
            try {
                lock.lockInterruptibly();
                Thread.sleep(500);
                System.out.println("lock了。。。"+Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
                System.out.println("中断了"+ Thread.currentThread().getName());
            }finally {
                lock.unlock();
            }
        }
    }
    /**
     * @author yangzhao
     * @Description lock的中断特性
     * @Date 17:16 2020/3/31
     * @Param []
     * @return void
     **/
    public static void TestMyService3(){
        final MyService3  myService3 = new MyService3();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadA:"+Thread.currentThread().getName());
                myService3.ServiceA();
            }
        });
        t1.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("ThreadB:"+Thread.currentThread().getName());
                myService3.ServiceA();
            }
        });
        t2.start();
        t2.interrupt();
    }

    static class MyService4{
        private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
        private int i = 0;
        public void serviceA(){
            try {
                lock.readLock().lock();
                i++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+"读写锁测试A：i="+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.readLock().unlock();
            }
        }
        public void serviceB(){
            try {
//                lock.readLock().lock();//读锁
                lock.writeLock().lock();//写锁
                i++;
                Thread.sleep(100);
                System.out.println(Thread.currentThread().getName()+"读写锁测试B：i="+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
//                lock.readLock().unlock();
                lock.writeLock().unlock();
            }
        }
    }
    /**
     * @author yangzhao
     * @Description 对lock的读读、读写锁进行测试

     * @Date 17:25 2020/3/31
     * @Param []
     * @return void
     **/
    public static void TestMyService4(){
        final MyService4 myService4 = new MyService4();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService4.serviceA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService4.serviceB();
            }
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("线程0将i加1后，开始休眠100毫秒，线程1这个时候将i加1后变为2；接着两个同时输出i=2；\n" +
                "使用线程1和线程2都使用的是读锁，所以不会加锁.");
    }

    public static void TestMyService4_1(){
        final MyService4 myService4 = new MyService4();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService4.serviceA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService4.serviceA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService4.serviceB();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService4.serviceA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService4.serviceA();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService4.serviceB();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                myService4.serviceB();
            }
        }).start();
    }



    public static void main(String[] args) {
        TestMyService4_1();
    }

}
