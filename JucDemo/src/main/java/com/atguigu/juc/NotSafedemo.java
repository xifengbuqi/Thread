package com.atguigu.juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 题目：举例说明集合类是不安全的
 *
 * 1、故障现象
 *  java.util.ConcurrentModificationException（并发修改异常）
 * 2、导致原因
 *  ArrayList 线程不安全，有没有加加
 *
 * 3、解决方案
 *      自己加锁（不可以考虑，sun 公司肯定会考虑到，JDK8了）
 *  3.1 vector
 *      换一种,ArrayList 前身 vector,vector 线程安全，底层 add 方法加锁
 *      (public synchronized void addElement(E obj))
 *      线程安全可以保证数据一致性，但是读取效率（访问性能）下降；vector 线程安全，但是同一时间段只能有一个人操作
 *  3.2 Collections.synchronizedList(new ArrayList<>());
 *      ArrayList 数组的工具类，Arrays是辅助类；ArrayList 上面是 List 接口，List 接口的父接口  collection接口。
 *      Collections 工具类，Collections.synchronizedList 可以把一个线程不安全的 ArrayList 转化为线程安全的。
 *      小数据量的时候完全可以，扛不住高并发。
 *  3.3 CopyOnWriteArrayList<>   写时复制/拷贝技术
 *      java.util.concurrent类下的 CopyOnWriteArrayList；
 *
 * 4、优化建议（同样的错误，不出现第二次）
 */
public class NotSafedemo {
    public static void main(String[] args)
    {
        Map<String, String> map = new ConcurrentHashMap<>();//Collections.synchronizedMap();//new HashMap<>();

        for (int i = 1; i <= 30; i++)
        {
            new Thread(() -> {
                // map 在多线程第一个参数 String 就是线程的名字，线程名字唯一；
                map.put(Thread.currentThread().getName(), UUID.randomUUID().toString().substring(0, 8));
                System.out.println(map);
            },String.valueOf(i)).start();
        }

        new HashMap<>();
    }

    private static void setNotSafe()
    {
        Set<String> set = new CopyOnWriteArraySet<>();// Collections.synchronizedSet();// new HashSet<>();

        new HashSet<>();

        for (int i = 1; i <= 30; i++)
        {
            new Thread(() -> {
                set.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(set);
            },String.valueOf(i)).start();
        }
    }

    private static void listNotSafe()
    {
        List<String> list = new CopyOnWriteArrayList<>();
        // Collections.synchronizedList(new ArrayList<>());// new Vector<>()// new ArrayList<>();

        for (int i = 1; i <= 30; i++)
        {
            new Thread(() -> {
                list.add(UUID.randomUUID().toString().substring(0, 8));
                System.out.println(list);
            },String.valueOf(i)).start();
        }
    }

//    private static void listNotSafe()
//    {
//        /*// 新的写法，方法引用
//        List<String> list = Arrays.asList("a", "b", "c");
//        list.forEach(System.out::println);*/
//
//        /*// 变成多线程环境，以往所用的知识就不对了
//        // 面试：印象深刻的故障。ArrayList此线程不安全，写一个
//        // 只有一个线程，没有争抢，不会出错
//        List<String> list = new ArrayList<>();
//
//        list.add("a");
//        list.add("b");
//        list.add("c");
//
//        for(String element : list)
//        {
//            System.out.println(element);
//        }*/
//
//        // List<String> list = new ArrayList<>();
//        // List<String> list = new Vector<>();
//        // List<String> list = Collections.synchronizedList(new ArrayList<>());
//        List<String> list = new CopyOnWriteArrayList<>();
//        // Collections.synchronizedList(new ArrayList<>());// new Vector<>()// new ArrayList<>();
//
//        for (int i = 1; i <= 30; i++)
//        {
//            new Thread(() -> {
//                // 工作中生成随机数，常用的两个工具类，UUID 和  currentTimeMillis（当前时间）
//                // 随机数 + UUID + 时间串
//                list.add(UUID.randomUUID().toString().substring(0, 8));
//                // System.currentTimeMillis();
//
//                // list 默认复写了toString()，一边写（add）一边读（println）
//                // 多次运行结果不一样，但是程序没有报错（违背数据一致性原则）
//                // 线程增多，java.util.ConcurrentModificationException 异常
//                System.out.println(list);
//            },String.valueOf(i)).start();
//        }
//    }
}