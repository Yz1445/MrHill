package com.tlp.mrhill.Test;

public class TestRunAndStart {
    static class RunnableImpl implements Runnable{
        @Override
        public void run() {
            System.out.println("运行RunnableImpl方法！");
        }
    }

    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableImpl());
//        thread.start();
        thread.run();
    }
}
