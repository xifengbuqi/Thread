package com.atguigu.juc;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
class User
{
    private Integer id;
    private String userName;
    private int age;
}

/**
 * 题目：
 * 请按照给出数据，找出同时满足以下条件的用户，也即以下条件全部满足；
 * 偶数ID且年龄大于24且用户名转为大写且用户名字母倒叙排序
 * 只输出一个用户名字。
 */
public class StreamDemo
{
    public static void main(String[] args) {
        User u1 = new User(11, "a", 23);
        User u2 = new User(12, "b", 24);
        User u3 = new User(13, "c", 22);
        User u4 = new User(14, "d", 28);
        User u5 = new User(16, "e", 26);

        // java 之前，list和数组互换，list可以变成数组，数组可以变成 list；
        // java8 以后，list 可以变成 stream，stream 可以变成 list。
        List<User> list = Arrays.asList(u1,  u2, u3, u4, u5);

        list.stream().filter(u -> {
            return u.getId() % 2 == 0;
        }).filter(t -> {
            return t.getAge() > 24;
        }).map(m -> {
            return m.getUserName().toUpperCase();
        }).sorted((o1, o2) -> {
            return o2.compareTo(o1);
        }).limit(1).forEach(System.out::println);
        // map(Function<? super T,? extends R> mapper)//map 映射


        /*// 比较精确 ，有return就用返回值，极简模式只有一行
        // return 灰色的，可以删除，但是方法体括号和结尾分号需要删除；
        // JDK 官方文档风格
        list.stream()
                .filter(u -> u.getId() % 2 == 0)
                .filter(t -> t.getAge() > 24)
                .map(m -> m.getUserName().toUpperCase())
                .sorted((o1, o2) -> o2.compareTo(o1))
                .limit(1)
                .forEach(System.out::println);*/






       /*List<Integer> list2 = Arrays.asList(1, 2, 3);
       list2.stream().map(x -> { return x * 2;}).collect(Collectors.toList());//collect(Collectors.toList()转换成list

       for(Integer element : list2) {
           System.out.println(element);
       }*/


        // ===========================================================================
        /*Function<String, Integer> function = new Function<String, Integer>()
        {
            @Override
            public Integer apply(String s) {
                return 1024;
            }
        };
        System.out.println(function.apply("abc"));*/

        // Lambda Express 集合结合引用
        /*Function<String, Integer> function = s -> { return  s.length();};
        System.out.println(function.apply("abc"));*/

        /*Predicate<String> predicate = new Predicate<String>()
        {
            @Override
            public boolean test(String s) {
                return false;
            }
        };*/
        /*Predicate<String> predicate = s -> { return s.isEmpty();};
        System.out.println(predicate.test("xiass"));*/

        /*Consumer<String> consumer = new Consumer<String>()
        {
            @Override
            public void accept(String s) {

            };
        };*/
        /*Consumer<String> consumer = s -> { System.out.println(s);};
        consumer.accept("xiass");*/

        /*Supplier<String> supplier = new Supplier<String>() {
            @Override
            public String get() {
                return null;
            }
        };*/
        /*Supplier<String> supplier = () -> {return  "java0222";};
        System.out.println(supplier.get());*/

        // 利用流式计算处理一下，Java 就是SQL，SQL 就是Java
        //select userName from User where id = and age > 24 and
    }
}

interface MyInterface
{
    public int myInt(int x);
    public String myPrint(String str);
    public boolean isOk(String str);
}