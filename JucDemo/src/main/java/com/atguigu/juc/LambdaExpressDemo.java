package com.atguigu.juc;

// 接口只有方法声明，没有方法实现
// 接口可以 new
// 一个接口，有且仅有一个方法声明，称为函复式接口
//@FunctionalInterface
//interface Foo
//{
//    public void sayHello();
//}

@FunctionalInterface // 显示定义，必须有一个方法声明
interface Foo
{
    public int add(int x, int y);
    // public int add2(int x, int y);

    // default() 不算在内，用default开头的Lambda Express，知道这是新语法规定允许有方法声明加实现的；
    default int div(int x, int y)
    {
        System.out.println("***hello java02");
        return x/y;
    }

    default int div2(int x, int y)
    {
        System.out.println("***hello java02");
        return x/y;
    }

    public static  int mv(int x, int y)
    {
        return x * y;
    }

    public static  int mv2(int x, int y)
    {
        return x * y;
    }
}

/**
 *
 * 2 Lambda Express
 *   2.1 口诀
 *     拷贝小括号，写死右箭头，落地大括号；
 *     Lambda Express 不关心方法名，接口有且仅有一个方法
 *     （自古华山一条道         不会找错）
 *
 *   2.2 FunctionalInterface
 *     ava8新特性函复式接口，如果自己不写，当接口有且仅有一个方法底层自动加上（类似 extends Object）；
 *     显示定义，必须有一个方法声明。
 *
 *   2.3 default 方法的默认实现
 *     java8 之前规定，接口内只能有声明，不能有实现；
 *     可以有多个 default 方法；
 *     java 面向接口编程的语言，java8以后接口既有定义又有实现，功能被无限扩大。
 *
 *   2.4 静态方法实现
 *     java5 静态方法导入inputstatic（都不用）;
 *     一个接口可以有多个静态方法实现。
 */
public class LambdaExpressDemo
{
    public static void main(String[] args)
    {
        /*// 接口可以 new
        Foo foo = new Foo()
        {
            @Override
            public void sayHello()
            {
                System.out.println("******hello 02");
            }
        };
        foo.sayHello();*/

        /*Foo foo = () -> {System.out.println("******hello 02 Lambda Express");};
        foo.sayHello();*/

        // Lambda Express 强调极简模式的链式化编程，参数类型可以省略
        Foo foo = (x, y) -> {
            System.out.println("******come in here");
            return x + y;
        };
        System.out.println(foo.add(3 ,5));

        System.out.println(foo.add(10 ,5));

        Foo.mv(3, 5);
    }
}
