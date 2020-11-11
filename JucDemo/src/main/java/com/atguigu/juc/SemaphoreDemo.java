package com.atguigu.juc;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class SemaphoreDemo
{
    public static void main(String[] args)
    {
        // 换成1，等价于 synchronized；一个资源类上只有一个资源，加一个锁；
        Semaphore semaphore = new Semaphore(3);  // 模拟资源类，有3个空车位

        for (int i = 1; i <= 6; i++)
        {
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    System.out.println(Thread.currentThread().getName() + "\t抢占到了车位");
                    // 暂停一会线程
                    try { TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e){ e.printStackTrace(); }
                    System.out.println(Thread.currentThread().getName() + "\t离开 了车位");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                }
            },String.valueOf(i)).start();
        }
    }
}
