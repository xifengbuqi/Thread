package com.atguigu.juc;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Exchanger;

/**
 * 严格控制，不管执行多少次，一定要前面线程结束，才能激活总结线程（main 线程）；
 * 如果不写辅助类去控制，只用 flag 和 number 去控制，真的很难；
 */
public class CountDownLatchDemo
{
    public static void main(String[] args) throws InterruptedException
    {
        // public CountDownLatch(int count){}
        CountDownLatch countDownLatch = new CountDownLatch(6);
        for (int i = 1; i <= 6; i++)
        {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
                countDownLatch.countDown();
            },String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println(Thread.currentThread().getName() + "\t班长关门走人");
    }

    // 此方法没有控制线程容易报异常
    private static void closeDoor()
    {
        for (int i = 1; i <= 6; i++)
        {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + "\t离开教室");
            },String.valueOf(i)).start();
        }

        System.out.println(Thread.currentThread().getName() + "\t班长关门走人");
    }
}
