package com.imooc.SmartChargeStation.demo.netty.handlers;

import com.imooc.SmartChargeStation.demo.protobuf.UserProtobuf;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * author: Imooc
 * description: Protobuf (客户端) 处理器
 * date: 2024
 */

@Slf4j
public class ImoocClientProtobufHandler extends SimpleChannelInboundHandler<UserProtobuf.User> {
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, UserProtobuf.User byteBuf) throws Exception {

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {

        //计数器
        int count = 0;
        for(int i=0;i<100;i++) {

            //数据发送
            UserProtobuf.User user = UserProtobuf.User.newBuilder().setName("this is from Client Protobuf").build();

            ctx.writeAndFlush(user);
            ++count;
            log.info("粘包半包>>>>>第"+count+"次的发送数据包:"+user.getName());

        }

    }
}
