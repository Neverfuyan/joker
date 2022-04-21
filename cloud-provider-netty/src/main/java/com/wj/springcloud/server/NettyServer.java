package com.wj.springcloud.server;

import com.wj.springcloud.childhandler.ServerInboundGetTimeHandler;
import com.wj.springcloud.childhandler.ServerInboundHandler;
import com.wj.springcloud.childhandler.ServerLastOutboundHandler;
import com.wj.springcloud.childhandler.ServerOutboundHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：actor
 * @date ：Created in 2022/3/9 9:51
 * @description：netty 服务端
 * @modified By：
 * @version: $
 */
@Component
public class NettyServer {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(NettyServer.class);

    /**
     * channelHandler就是我们处理数据逻辑的地方，
     * 它一共分为两大类：InboundHandler和呕Outboundhandler。
     * InboundHandler用于处理输入的数据和改变channel状态类型，
     * OutboundHandler用于回写给外界的数据。
     * InboundHandler：顺序执行
     * OutboundHandler：逆序执行
     * 在channelHandler的执行过程中，InboundHandler会覆盖后面的OutboundHandler，所以在开发中应该先执行OutboundHandler再执行InboundHandler
     */
    @Autowired
    ServerInboundHandler serverInboundHandler;

    @Autowired
    ServerInboundGetTimeHandler serverInboundGetTimeHandler;

    @Autowired
    ServerLastOutboundHandler serverLastOutboundHandler;

    @Autowired
    ServerOutboundHandler serverOutboundHandler;


    public void nettyServerStart()  {
        logger.info("netty服务端开始启动。。。。。。");
        //1、创建两个线程组，用于接收客户端的请求任务,创建两个线程组是因为netty采用的是反应器设计模式
        //该线程主要是接收客户端连接
            //EventLoop 好比一个线程，1个EventLoop 可以服务多个channel，而一个channel只会有一个EventLoop 。EventLoop 在netty中就是负责整个IO操作，
            //包括从消息的读取、编码以及后续 ChannelHandler 的执行，这样做的好处就是避免了线程中的上下文切换时，大量浪费资源情况。
            //EventLoopGroup 是负责分配EventLoop到新创建的channel，EventLoopGroup 就好比线程池，它里面包含多个EventLoop。
            NioEventLoopGroup bossGroup = new NioEventLoopGroup();
            //改线程主要是处理bossGroup安排的工作
            NioEventLoopGroup workerGroup = new NioEventLoopGroup();
            //2、创建netty的启动类
        //BootStrap 是netty中的引导启动类也就是一个工厂配置类，可以通过它来完成 Netty 的客户端或服务器端的 Netty 初始化，
            ServerBootstrap bootstrap = new ServerBootstrap();
            //3、创建一个通道
            ChannelFuture channelFuture = null;

        try {
            //4、设置线程组
            //gruop()方法用于配置netty中的线程组，也就是我们的EventLoopGroup ，在服务端中需要配置两个线程组，
            // 这是因为netty中采用的是反应器设计模式（reactor ），我们知道反应器设计模式中是需要两个线程组，一个用于接收用户的请求，另一个用于处理请求的内容。
            bootstrap.group(bossGroup,workerGroup)
                    //channel()方法用于配置通道的IO类型，IO类型有两个：阻塞IO（BIO）OioServerSocketChannel；非阻塞IO（NIO）NioServerSocketChannel
                    //设置通道 非阻塞io
                    .channel(NioServerSocketChannel.class)
                    //给每条parent channel 连接设置一些TCP底层相关的属性。
                    //设置日志
                    .option(ChannelOption.SO_BACKLOG,128)
                    //设置缓冲去大小
                    .option(ChannelOption.SO_RCVBUF,32*1024)
                    //给每条child channel连接设置一些TCP底层相关的属性，比如上面，我们设置了两种TCP属性，其中 ChannelOption.SO_KEEPALIVE表示是否开启TCP底层心跳机制，true为开
                    //是否保持连接
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
                    //设置处理逻辑的逻辑处理类
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            //ChannelPipeline是handler的任务组，里面有多个handler
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(serverLastOutboundHandler);
                            pipeline.addLast(serverOutboundHandler);
                            pipeline.addLast(serverInboundHandler);
                            pipeline.addLast(serverInboundGetTimeHandler);
                        }
                    });
            //阻塞端口号，以及同步策略
            channelFuture =  bootstrap.bind(19231).sync().addListener(listen->{
                if (listen.cause() != null) {
                    ExceptionUtils.printRootCauseStackTrace(listen.cause());
                }
                if(listen.isSuccess()){
                    logger.info("服务启动成功。。。。。。");
                }else{
                    logger.info("服务启动失败。。。。。。");
                }
            });
            //第一种方式 通道不阻塞的情况下，在finally里优雅的释放
            //channelFuture.channel().closeFuture().sync();
            //第二种方式
            channelFuture.channel().closeFuture().addListener(new ChannelFutureListener() {
                @Override
                public void operationComplete(ChannelFuture future) throws Exception {
                    bossGroup.shutdownGracefully();
                    workerGroup.shutdownGracefully();
                }
            });
        } catch (InterruptedException e) {
            logger.info("服务端阻塞异常:{}",e.getMessage());
        }
//        finally{
//           // 线程退出工作状态
//            logger.info("线程关闭。。。。。。");
//            bossGroup.shutdownGracefully();
//            workerGroup.shutdownGracefully();
//        }
    }
}
