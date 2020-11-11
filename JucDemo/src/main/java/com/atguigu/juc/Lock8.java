package com.atguigu.juc;

import java.util.concurrent.TimeUnit;

class Phone
{
    public static synchronized void sendEmail() throws Exception
    {
        // 暂停一会线程
        // Thread.sleep(4000);
        try { TimeUnit.SECONDS.sleep(4); }catch (Exception e){ e.printStackTrace(); }
        System.out.println("------sendEmail");
    }

    public synchronized void sendSMS() throws Exception
    {
        System.out.println("------sendSMS");
    }

    public void hello()
    {
        System.out.println("------hello");
    }
}

/**
 * 题目：多线程8锁
 * 1、标准访问（两个普通同步方法，同一部手机），请问先打印邮件还是短信？    邮件
 * 2、邮件方法暂停4秒钟，请问先打印邮件还是短信？    邮件
 * 3、新增一个普通方法hello()；请问先打印邮件还是hello？    hello
 * 4、两部手机，请问先打印邮件还是短信？    短信
 * 5、两个静态同步方法，同一部手机，请问先打印邮件还是短信？    邮件
 * 6、两个静态同步方法，两部手机，请问先打印邮件还是短信？    邮件
 * 7、一个普通同步方法，一个静态同步方法，同一部手机，请问先打印邮件还是短信？    短信
 * 8、一个普通同步方法，一个静态同步方法，两部手机，请问先打印邮件还是短信？    短信
 *
 *
 *解释：
 * 1、2锁解释（根本不可能同步进去）
 * 一个对象里面如果有多个 synchronized 方法，某一个时刻内，只要一个线程去调用其中的一个 synchronized 方法了，
 * 其它的线程都只能等待，换句话说，某一个时刻内，只能有唯一一个线程去访问这些 synchronized 方法。
 * 锁的是当前对象this, 被锁定后，其它的线程都不能进入到当前对家的其它的 synchronized方法。
 *
 * 3锁解释（普通方法没加 synchronized，线程不会争抢）
 * 加个普通方法后发现和同步锁无关；
 * 4锁解释（phong锁的时邮件，phone2锁的时短信，各用各的）
 * 换成两个对象后，不是同一把锁了，情况立刻变化。
 *
 * 5、6锁的解释（static用在变量、方法、类前面，static属于全体对象，属于这个类模板phone.class的
 * new  this，具体的一部手机
 * 静态 class，唯一的模板
 * ）
 * 都换成静态同步方法后，情况又变化；
 * 所有的非静态同步方法用的都是同一把锁实例对象本身，
 *
 * 7、8锁的解释
 * synchronized 实现同步的基础: Java 中的每一个对象都可以作为锁。
 * 具体表现为以3种形式。
 * 对于普通同步方法，锁是当前实例对象。
 * 对尹静态同步方法，锁足当前类的 Class 对象。
 * 对于同步方法块，锁是 Synchonized 括号里配置的对象。
 *
 * 当一个线程试图访问同步代码块时，它首先必须得到锁，退出或抛出异前时必须释放锁。
 *
 *
 * 总结：
 * 也就是说如果一个实例对象的普通同步方法获取锁后，该实例对象的其他普通同步方法必须等待获取锁的方法释放锁后才能获取锁，
 * 可是别的实例对象的普通同步方法因为跟该实例对象的非静态同步方法用的是不同的锁，
 * 所以毋须等待该实例对象已获取锁的非静态同步方法释放锁就可以获取他们自己的锁。
 *
 * 所有的静态同步方法用的也是同把锁类对象本身，
 * 这两把锁是两个不同的对象，所以静态同步方法与非静态同步方法之间是不会有竞态条件的。
 * 但是旦一个静态同步 方法获取锁后，其他的静态同步方法都必须等待该方法释放锁后才能获取锁，
 * 而不管是同个实例对象的静态同步方法之间，
 * 还是不同的实例对象的静态同步方法之间，只要它们同一个类的实例对象!
 */
public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            try {
                phone.sendEmail();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"A").start();

        Thread.sleep(100);

        new Thread(() -> {
            try {
                // phone.sendSMS();
                // phone.hello();
                phone2.sendSMS();
            }catch (Exception e){
                e.printStackTrace();
            }
        },"A").start();
    }
}