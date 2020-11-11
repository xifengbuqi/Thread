package com.atguigu.juc;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class BlockingQueueDemo
{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        // List list = new ArrayList();
        BlockingQueue<String> blockingQueue = new ArrayBlockingQueue<>(3);

        /*// 第一组，抛出异常
        System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.add("c"));

        // System.out.println(blockingQueue.add("x"));

        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());
        System.out.println(blockingQueue.remove());*/

        /*System.out.println(blockingQueue.add("a"));
        System.out.println(blockingQueue.add("b"));
        System.out.println(blockingQueue.element());*/


        /*// 第二组，特殊值
        System.out.println(blockingQueue.offer("a"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("c"));

        //System.out.println(blockingQueue.offer("x"));

        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());
        System.out.println(blockingQueue.poll());*/


        /*// 第三组，阻塞
        // put 方法堵塞队列已满，会一直阻塞，知道有空位置；
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");
        blockingQueue.put("a");

        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());
        System.out.println(blockingQueue.take());*/

        // 第四章，超时
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("b"));
        System.out.println(blockingQueue.offer("a", 3L, TimeUnit.SECONDS));

    }
}