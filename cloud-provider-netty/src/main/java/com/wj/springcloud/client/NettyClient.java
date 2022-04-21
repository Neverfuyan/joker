package com.wj.springcloud.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;

/**
 * @author ：actor
 * @date ：Created in 2022/3/9 11:17
 * @description：netty客户端
 * @modified By：
 * @version: $
 */
@Component
public class NettyClient {

    /** logger */
    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);

    public static void main(String[] args) {
        //客户端只需要创建一个线程就足够了
        EventLoopGroup group = new NioEventLoopGroup();
        //客户端启动类
        try{
            Bootstrap bootstrap = new Bootstrap();
            //设置线程组
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    //设置IP和端口
                    .remoteAddress(new InetSocketAddress("127.0.0.1", 19231))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            socketChannel.pipeline().addLast(new ClientHandler());
                        }
                    });
            //阻塞通道
            ChannelFuture channelFuture = bootstrap.connect().sync().addListener(listen ->{
                if (listen.cause() != null) {
                    ExceptionUtils.printRootCauseStackTrace(listen.cause());
                }
                if (listen.isSuccess()){
                    logger.info("netty客户端启动成功。。。。。。");
                }else{
                    logger.info("netty客户端启动失败。。。。。。");
                }
            });
            channelFuture.channel().closeFuture().sync();
        }catch (Exception e) {
            e.printStackTrace();
            logger.info("客户端异常:"+e.getMessage());
        } finally {
             group.shutdownGracefully();
        }
    }
}
