package com.atguigu.juc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;

class MyThread implements Callable<Integer>
{
    @Override
    public Integer call() throws Exception
    {
        System.out.println("******come in here");
        // 暂停一会线程
        try { TimeUnit.SECONDS.sleep(4); }catch (InterruptedException e){ e.printStackTrace(); }
        return 1024;
    }
}

/**
 * java 多线程中，第3种获得多线程的方式。
 *
 */
public class CallableDemo
{
    public static void main(String[] args) throws InterruptedException, ExecutionException
    {
        // 为什么叫 FutureTask
        FutureTask futureTask = new FutureTask(new MyThread());

        new Thread(futureTask,"A").start();
        new Thread(futureTask,"B").start();

        // 思想
        // 之前程序有且只有一个线程 main 主线程，冰糖葫芦串模型，从上到下执行；
        // System.out.println(futureTask.get());

        System.out.println(Thread.currentThread().getName() + "******计算完成");

         System.out.println(futureTask.get());
    }
}
