package com.atguigu.juc;

import java.util.concurrent.CompletableFuture;

public class CompletableFutureDemo
{
    public static void main(String[] args) throws Exception
    {
        // 异步调用
        CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "没有返回，update mysql ok");
        });
        completableFuture.get();

        // 异步回调
        CompletableFuture<Integer> completableFuture2 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "有返回，insert mysql ok");
            // int age = 10 / 0;
            return 1024;
        });
        completableFuture2.whenComplete((t, u) -> {
            System.out.println("****t" + t);
            System.out.println("****u" + u);
        }).exceptionally(f -> {
            System.out.println("****Exception:" + f.getMessage());
            return 444;
        }).get();
    }
}
