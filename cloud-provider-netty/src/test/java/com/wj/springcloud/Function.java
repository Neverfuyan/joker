package com.wj.springcloud;

import org.apache.tomcat.util.buf.ByteBufferUtils;
import org.junit.Test;
import rx.Single;
import sun.misc.Signal;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import static com.wj.springcloud.utils.ByteBufferUtil.debugAll;

/**
 * @author ：actor
 * @date ：Created in 2022/3/10 15:03
 * @description：测试
 * @modified By：
 * @version: $
 */
public class Function {

    @Test
    public void test1(){
        String system = System.getProperties().getProperty("os.name").toLowerCase().startsWith("win") ? "INT" : "TERM";
        Signal signal = new Signal(system);
        Signal.handle(signal,(s)->{
            System.out.println("信号开始执行。。。。。。");
            try {
                TimeUnit.SECONDS.sleep(Integer.MAX_VALUE);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Runtime.getRuntime().addShutdownHook(new Thread(()->{
                System.out.println("jvm 接收到关闭指令，开始执行");
                System.out.println("netty NioEventLoopGroup shutdownGracefully");
                try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("jvm 关闭执行结束");
            },""));
        });
        //Signal.handle()
        System.out.println(signal);

    }

    /**
     * ByteBuffer 的应用
     */
    @Test
    public void test2(){
        try (FileChannel channel = new FileInputStream("data.txt").getChannel()) {
            //准备缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(10);
            //从channel中读取数据,向buffer写入
            while (true) {
                int read = channel.read(buffer);
                if(read ==-1){
                    break;
                }
                //切换成都模式
                buffer.flip();
                //是否还有剩余数据
                while (buffer.hasRemaining()){
                    byte b = buffer.get();
                    System.out.println((char)b);
                }
            }
            //切换成写模式
            buffer.clear();
        } catch (IOException e) {
        }
    }

    /**
     * ByteBuffer与字符串的转换
     */
    @Test
    public void test3(){
        //1、字符串转为ByteBuffer 仍是写模式
        ByteBuffer byteBuffer = ByteBuffer.allocate(10);
        byteBuffer.put("hello".getBytes());
        debugAll(byteBuffer);

        //2、Charset 已经切换到读模式
        ByteBuffer byteBuffer2 = StandardCharsets.UTF_8.encode("hello");
        debugAll(byteBuffer2);

        //3、wrap 已经切换到读模式
        ByteBuffer byteBuffer3 = ByteBuffer.wrap("hello".getBytes());
        debugAll(byteBuffer3);

        byteBuffer.flip();
        String string = StandardCharsets.UTF_8.decode(byteBuffer).toString();
        System.out.println(string);


    }

}
