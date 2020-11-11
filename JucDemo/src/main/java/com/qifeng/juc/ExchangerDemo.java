package com.qifeng.juc;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerDemo
{
    private static final Exchanger<String> exgr = new Exchanger<>();
    // 固定数线程池,一般使用自定义线程池
    private static final ExecutorService threadPool = Executors.newFixedThreadPool(2);

    public static void main(String[] args)
    {
        threadPool.execute(() -> {
            try {
                String A = "银行流水A";// A 录入的银行流水
                exgr.exchange(A);
                System.out.println(A);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.execute(() -> {
            try {
                String B = "银行流水B";// B 录入的银行流水
                System.out.println(B);
                String A = exgr.exchange("B");
                System.out.println("A 和 B 数据是否一致:" + A.equals(B) + "A 录入的时：" + A + "B 录入的是：" + B);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        threadPool.shutdown();
    }
}
