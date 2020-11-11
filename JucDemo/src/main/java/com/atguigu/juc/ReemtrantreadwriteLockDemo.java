package com.atguigu.juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache
{
    private volatile Map<String, Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    public void put(String key, Object value)
    {
        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 写入数据" + key);
            // 暂停一会线程
            try { TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e){ e.printStackTrace(); }
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + "\t 写入完成");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.writeLock().unlock();
        }
    }

    public void get(String key)
    {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t 读取数据");
            // 暂停一会线程
            try { TimeUnit.SECONDS.sleep(3); }catch (InterruptedException e){ e.printStackTrace(); }
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t 读取完成" + result);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            readWriteLock.readLock().unlock();
        }
    }
}

/** 多个线程同时读一个资源类没有任何问题，所以为了满足并发量，读取共享资源应该可以同时进行。
 * 但是
 * 如果有一个线程想去写共享资源类，就不应该再有其他线程可以对该资源类读或写
 * 总结：
 *  读-读可共存，
 *  写读不可共存，
 *  写写不可共存，
 */
public class ReemtrantreadwriteLockDemo
{
    public static void main(String[] args) {
        MyCache myCache = new MyCache();

        for(int i = 1; i <= 5; i++)
        {
            final int tempInt = i;
            new Thread(() -> {
                myCache.put(tempInt + "", tempInt + "");
            },String.valueOf(i)).start();
        }

        for(int i = 1; i <= 5; i++)
        {
            final int tempInt = i;
            new Thread(() -> {
                myCache.get(tempInt + "");
            },String.valueOf(i)).start();
        }
    }
}
