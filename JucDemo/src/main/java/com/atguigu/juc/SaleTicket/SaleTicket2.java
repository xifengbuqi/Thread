package com.atguigu.juc.SaleTicket;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{//资源类
    //票
    private int number = 30;
    private Lock lock = new ReentrantLock();

    public void saleTicket()
    {

        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t还剩下："+number);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
/**
 *题目：三个售票员   卖出   30张票
 * 多线程编程的企业级套路+模板
 * 1.在高内聚低耦合的前提下，线程    操作(对外暴露的调用方法)     资源类
 */
public class SaleTicket2 {
    public static void main(String[] args)
    {
        Ticket ticket = new Ticket();

        // 匿名内部类 ，代码太长，太烂
        new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.saleTicket();}, "A").start();
        new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.saleTicket();}, "B").start();
        new Thread(() -> {for (int i = 1; i <= 40; i++) ticket.saleTicket();}, "C").start();

//        // new接口（实现接口）这个现象，就叫匿名内部类
//        // thead(Rannable target, String name) Allocates a new Thread object.
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                for (int i = 1; i <= 40; i++)
//                {
//                    ticket.saleTicket();
//                }
//            }
//        },"A").start();
//
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run()
//            {
//                for (int i = 1; i <= 40; i++)
//                {
//                    ticket.saleTicket();
//                }
//            }
//        },"B").start();
//
//        new Thread(new Runnable()
//        {
//            @Override
//            public void run() {
//                for (int i = 1; i <= 40; i++)
//                {
//                    ticket.saleTicket();
//                }
//            }
//        },"C").start();
    }
}

//// 通过实现Runnable接口，传统写法
//class T implements Runnable
// {
//    @Override
//    public void run() {
//
//    }
//}