package com.wj.springcloud.childhandler;

import cn.hutool.core.date.DateUtil;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import java.util.Date;


/**
 * @author ：actor
 * @date ：Created in 2022/3/9 10:34
 * @description：时间戳处理hander
 * @modified By：
 * @version: $
 */
@Configuration
@Order(2)
public class ServerInboundGetTimeHandler extends ChannelHandlerAdapter {

    /**
     * 获取客户端内容类
     * @param ctx
     * @param msg
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //将传递过来的内容转换为ByteBuf对象
        ByteBuf buf = (ByteBuf) msg;
        //和文件IO一样，用一个字节数组读数据
        byte[] reg = new byte[buf.readableBytes()];
        buf.readBytes(reg);
        //将读取的数据转换为字符串
        String body = new String(reg, "UTF-8");
        //给客户端传递的内容，同样也要转换成ByteBuf对象
        String respMsg = DateUtil.format(new Date(),"yyyy-MM-dd HH:mm:ss");
        ByteBuf respByteBuf = Unpooled.copiedBuffer(respMsg.getBytes());
        //调用write方法，通知并将数据传给outboundHand
        ctx.write(respByteBuf);
    }

    /**
     * 刷新后才将数据发出到SocketChannel
     * @param ctx
     * @throws Exception
     */
    @Override
    public void flush(ChannelHandlerContext ctx) throws Exception {
        super.flush(ctx);
    }

    /**
     * 关闭
     * @param ctx
     * @param promise
     * @throws Exception
     */
    @Override
    public void close(ChannelHandlerContext ctx, ChannelPromise promise) throws Exception {
        super.close(ctx, promise);
    }
}
