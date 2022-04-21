package com.wj.springcloud.childhandler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author ：actor
 * @date ：Created in 2022/3/9 10:54
 * @description：Outbound表示服务器发送的handler
 * @modified By：
 * @version: $
 */
@Configuration
@Order(3)
public class ServerOutboundHandler extends ChannelHandlerAdapter {


    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        //将传递过来的内容转换为ByteBuf对象
        ByteBuf buf = (ByteBuf) msg;
        //和文件IO一样，用一个字节数组读数据
        byte[] reg = new byte[buf.readableBytes()];
        buf.readBytes(reg);
        String body=new String(reg,"UTF-8");
        System.out.println("服务端发送的内容:\n"+body);
        String respMsg = body+"\n请问你需要操作什么任务";
        ByteBuf respByteBuf = Unpooled.copiedBuffer(respMsg.getBytes());
        ctx.write(respByteBuf);
        ctx.flush(); //ctx.write()方法执行后，需要调用flush()方法才能令它立即执行
    }
}
