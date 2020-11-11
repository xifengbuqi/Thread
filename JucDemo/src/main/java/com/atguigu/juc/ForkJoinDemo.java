package com.atguigu.juc;


import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

// 抽象类不能被实例化
class MyTask extends RecursiveTask<Integer>
{
    private static final Integer ADJUST_VALUE =10;

    private int begin;
    private int end;
    private int result;

    public MyTask(int begin, int end)
    {
        this.begin = begin;
        this.end = end;
    }

    @Override
    protected Integer compute()
    {
        // 如果任务足够小就计算任务
        if(end - begin <= ADJUST_VALUE)
        {
            for (int i = begin; i < end; i++)
            {
                result = result + i;
            }
        } else {
            // 如果任务大于阈值，就分裂为两个子任务机选
            int middle = (end + begin) / 2;
            MyTask task01 = new MyTask(begin, middle);
            MyTask task02 = new MyTask(middle+1, end);
            // 执行子任务
            task01.fork();
            task02.fork();
            // 等待子任务执行完成，合并其结果
            result = task01.join() + task02.join();
        }
        return result;
    }
}

/**
 * 分支合并框架
 *
 * ForkJoinPool
 * ForkJoinTask
 * RecursiveTask
 */
public class ForkJoinDemo
{
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ForkJoinPool threadPool = new ForkJoinPool();
        // 生成一个计算任务 ，，负责计算1+2+3+···+100
        MyTask myTask = new MyTask(1, 100);
        // 执行任务
        ForkJoinTask<Integer> forkJoinTask = threadPool.submit(myTask);

        System.out.println(forkJoinTask.get());
        threadPool.shutdown();
    }
}
