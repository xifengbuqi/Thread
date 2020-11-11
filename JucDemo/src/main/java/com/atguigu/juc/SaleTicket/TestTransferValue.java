package com.atguigu.juc.SaleTicket;

import com.atguigu.juc.Person;

/**
 * Java 内存模型和指针
 * 程序跑通了，但底层原理不明白；深透明细
 *
 * 注意：
 *  1、传入的是值是基本类型还是引用；类型是String还是非String；是系统自带，还是自定义。
 *      传入的值是基本类型，值不会改变；传入的值是引用类型，值会被修改。
 *  2、方法的作用域
 */
public class TestTransferValue
{
    public void changeValue1(int age)
    {
        age =30;
    }
    public void changeValue2(Person person)
    {
        person.setPersonName("xxx");
    }
    public void changeValue3(String str)
    {
        str = "xxx";
    }

    public static void main(String[] args)
    {
        TestTransferValue test = new TestTransferValue();
        // int 基本类型，age局部变量；
        // 传入的是基本类型的值；
        // main 在栈里面，每个方法独立运行，各自一份；
        // 在一个方法里，基本类型传入的是复制内容；
        int age = 20;
        test.changeValue1(age);
        System.out.println("age----" + age);// 20

        // Person 引用类型，自己创建；
        // 引用放在栈，实例对象放在堆； 引用（在等号左边） = 实例对象（在等号右边）；
        // Java 中的引用，实质底层就是指针
        // main 里面一个叫person的引用（person为局部变量），指向堆里面Person类型的内存地址，值为abc；
        // 传入的是内存地址或者说是引用；changeValue2的引用指向同一个内存地址，值被修改为 xxx；
        // 打印的是main的person，内存地址没有改变，但是值被u改了；
        Person person = new Person("abc");
        test.changeValue2(person);
        System.out.println("personName----" + person.getPersonName());// xxx

        // String 引用类型，JDK 自带
        // String str = "abc";，这种写法，JDK8理解，是在方法区，也就是常量池（字符串常量池）；
        // main 的 str 指向 abc，changeValue3传入str也指向abc；
        // 在字符串常量池，以 String str = "abc"; 方式构造字符串，先在字符串常量池查找；
        // 如果值不存在没有就先建，找一个位置，生成新的变量；如值已经存在，直接复用；
        // changeValue3 修改str的值，就重新建立了变量；main 的str没有改变
        String str = "abc";
        test.changeValue3(str);
        System.out.println("String----" + str);// abc
    }
}
