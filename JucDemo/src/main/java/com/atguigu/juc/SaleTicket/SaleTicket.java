//package com.qifeng.juc.SaleTicket;
//
//class Ticket{//资源类
//    //票
//    private int number = 30;
//
//    public synchronized void saleTicket()
//    {
//        if (number > 0){
//            System.out.println(Thread.currentThread().getName()+"\t卖出第："+(number--)+"\t还剩下："+number);
//        }
//    }
//}
///**
// *题目：三个售票员   卖出   30张票
// * 多线程编程的企业级套路+模板
// * 1.在高内聚低耦合的前提下，线程    操作(对外暴露的调用方法)     资源类
// */
//public class SaleTicket {
//    public static void main(String[] args)
//    {
//        Ticket ticket = new Ticket();
//
//        // thead(Rannable target, String name) Allocates a new Thread object.
//
//        // new接口（实现接口）这个现象，就叫匿名内部类
//        new Thread(new Runnable()
//        {
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
//            public void run() {
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
//    }
//}
//
////// 通过实现Runnable接口，传统写法
////class T implements Runnable
// {
////    @Override
////    public void run()
//      {
////
////    }
////}