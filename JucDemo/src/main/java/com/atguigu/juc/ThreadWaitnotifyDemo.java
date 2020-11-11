package com.atguigu.juc;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class AirConditioner // 资源类
{
    private int number =0;
    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    public  void increment() throws InterruptedException
    {
        lock.lock();
        try {
            // 1、判断
            while(number != 0)
            {
                condition.await(); // this.wait();
            }

            // 2、干活
            number++;
            System.out.println(Thread.currentThread().getName()+"\t"+number);

            // 3、通知（唤醒）
            condition.signalAll();// this.notifyAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void decrement() throws InterruptedException
    {
        lock.lock();
        try {
            // 1、判断
            while(number == 0)
            {
                condition.await(); // this.wait();
            }

            // 2、干活
            number--;
            System.out.println(Thread.currentThread().getName()+"\t"+number);

            // 3、通知（唤醒）
            condition.signalAll();// this.notifyAll();

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    /*public synchronized void increment() throws InterruptedException
    {
        // 1、判断
        while(number != 0)
        {
            this.wait();
        }
        // 2、干活
        number++;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        // 3、通知（唤醒）
        this.notifyAll();
    }

    public synchronized void decrement() throws InterruptedException
    {
        // 1、判断
        while(number == 0)
        {
            this.wait();
        }

        // 2、干活
        number--;
        System.out.println(Thread.currentThread().getName()+"\t"+number);

        // 3、通知（唤醒）
        this.notifyAll();
    }*/
}

/**
 * 题目（面试题）：现在有两个线程，可以操作初始值为零的一个变量，
 * 实现一个线程对该变量加1，一个线程对该变量减1，
 * 实现交替，来10轮，变量初始值为零。
 *
 * 1、 高内聚低耦合前提下，线程操作资源类
 * 2、判断/干活/通知
 * 3、多线程交互中，必须要防止多线程的虚假唤醒，也即（判断只能用while，不能用if，）
 *
 */
public class ThreadWaitnotifyDemo {
    public static void main(String[] args) throws Exception {
        AirConditioner airConditioner = new AirConditioner();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++)
            {
                try {
                    Thread.sleep(200);
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++)
            {
                try {
                    Thread.sleep(300);
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++)
            {
                try {
                    Thread.sleep(400);
                    airConditioner.increment();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"C").start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++)
            {
                try {
                    Thread.sleep(500);
                    airConditioner.decrement();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"D").start();
    }
}