package com.tlp.mrhill.Test;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * @ClassName TestThreadMXBean
 * @Description TODO
 * @Author yangzhao
 * @Date 2020/3/18 15:13
 * @Version 1.0
 **/
public class TestThreadMXBean {
    public static void main(String[] args) {
        testDeadlock();
    }
    public static void testDeadlock(){
        Object object_A = new Object();
        Object object_B = new Object();
        new Thread(new DeadLockThread(object_A,object_B)).start();
        new Thread(new DeadLockThread(object_B,object_A)).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(new MonitorThread()).start();

    }
}
/**
 * @author yangzhao
 * @Description 检测死锁的内部类
 * @Date 15:23 2020/3/18
 * @Param 
 * @return  
 **/
class MonitorThread implements Runnable{
    @Override
    public void run() {
        ThreadMXBean tmx = ManagementFactory.getThreadMXBean();
        long [] ids = tmx.findDeadlockedThreads();
        if (ids != null) {
            ThreadInfo[] infos = tmx.getThreadInfo(ids,true,true);
            System.out.println("the following threads are deadlocked");
            for (ThreadInfo ti : infos){
                System.out.println(ti);
            }
        }
    }
}
/**
 * @author yangzhao
 * @Description 会产生死锁的内部类
 * @Date 15:22 2020/3/18
 * @Param 
 * @return  
 **/
 class DeadLockThread implements Runnable{
    private Object object1;
    private Object object2;
    public DeadLockThread(Object o1,Object o2){
        this.object1 = o1;
        this.object2 = o2;
    }
     @Override
     public void run() {
        synchronized (object1){
            try {
                Thread.sleep(1000);
                synchronized (object2){}
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
     }
 }
