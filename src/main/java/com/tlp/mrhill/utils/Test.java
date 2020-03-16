package com.tlp.mrhill.utils;

import com.tlp.mrhill.model.User;

import java.util.*;
import java.util.concurrent.*;

public class Test {


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        FutureTask<String> ft = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "yangzhao";
            }
        });
        new Thread(ft).start();
        System.out.println(ft.get());

        ExecutorService ssss = Executors.newSingleThreadExecutor();
        ssss.submit(new Runnable() {
            @Override
            public void run() {
                System.out.println("------------");
            }
        });
        ssss.shutdown();
    }

}
