package com.cc;

import cn.hutool.core.io.FileTypeUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.junit.Test;

import java.io.File;
import java.util.concurrent.*;

/**
 * @author ：actor
 * @date ：Created in 2022/1/19 14:25
 * @description：方法测试
 * @modified By：
 * @version: $
 */
public class Function {


    @Test
    public void test1() throws Exception {
        File file = FileUtil.file("E:\\360MoveData\\Users\\actor\\Desktop\\1.xls");
        String type = FileTypeUtil.getType(file);
        String name = FileUtil.getPrefix(file);
        System.out.println(type+":"+name);
    }

    @Test
    public void test2() throws Exception {
        CountDownLatch countDownLatch = ThreadUtil.newCountDownLatch(3);
        ExecutorService executorService = ThreadUtil.newExecutor(3);
        for (int i = 0; i < 10 ; i++) {
            FutureTask futureTask = new FutureTask<>(new ThreadTest(countDownLatch));
            executorService.submit(futureTask);
        }
        System.out.println("主线程"+Thread.currentThread().getName()+"等待子线程执行完成...");
        countDownLatch.await();
        System.out.println("主线程"+Thread.currentThread().getName()+"开始执行...");
    }


    @Test
    public void test3(){
        ExecutorService service = Executors.newFixedThreadPool(3);
        final CountDownLatch latch = new CountDownLatch(3);
        for (int i = 0; i < 3; i++) {
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
                        Thread.sleep((long) (Math.random() * 10000));
                        System.out.println("子线程"+Thread.currentThread().getName()+"执行完成");
                        latch.countDown();//当前线程调用此方法，则计数减一
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            service.execute(runnable);
        }

        try {
            System.out.println("主线程"+Thread.currentThread().getName()+"等待子线程执行完成...");
            latch.await();//阻塞当前线程，直到计数器的值为0
            System.out.println("主线程"+Thread.currentThread().getName()+"开始执行...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test4(){
        boolean a = NumberUtil.isNumber("a");
        System.out.println(a);
    }



    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    class ThreadTest implements Callable{

        private volatile CountDownLatch latch;

        @Override
        public Object call() throws Exception {
            System.out.println("子线程" + Thread.currentThread().getName() + "开始执行");
            Thread.sleep((long) (Math.random() * 10000));
            latch.countDown();
            System.out.println("子线程"+Thread.currentThread().getName()+"执行完成");
            return "你好啊";
        }
    }

}
