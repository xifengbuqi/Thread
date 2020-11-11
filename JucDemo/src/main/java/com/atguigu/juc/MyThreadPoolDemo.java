package com.atguigu.juc;

import java.util.concurrent.*;

public class MyThreadPoolDemo
{
    public static void main(String[] args)
    {
        System.out.println(Runtime.getRuntime().availableProcessors());

        ExecutorService threadPool = new ThreadPoolExecutor(
                2,
                5,
                2L,
                TimeUnit.SECONDS,
                new LinkedBlockingQueue<>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());

        try {
            // 模拟 10个顾客办理业务，目前池子有 5个工作线程提高服务
            for (int i = 1; i <= 6; i++) {
                try { TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                // try { TimeUnit.SECONDS.sleep(4); }catch (InterruptedException e){ e.printStackTrace(); }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }

    private static void initPool()
    {
        // ExecutorService threadPool = Executors.newFixedThreadPool(5);// 一池 5 个线程
        // ExecutorService threadPool = Executors.newSingleThreadExecutor();// 一池 1 个线程
        // cache 表示可以不去查数据库，数据可以复用；
        ExecutorService threadPool = Executors.newCachedThreadPool();// 一池 n 个线程

        // 池化技术使用后需要释放
        try {
            // 模拟 10个顾客办理业务，目前池子有 5个工作线程提高服务
            for (int i = 1; i <= 10; i++) {
                try { TimeUnit.SECONDS.sleep(1); }catch (InterruptedException e){ e.printStackTrace(); }
                threadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + "\t 办理业务");
                });
                // try { TimeUnit.SECONDS.sleep(4); }catch (InterruptedException e){ e.printStackTrace(); }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            threadPool.shutdown();
        }
    }
}
