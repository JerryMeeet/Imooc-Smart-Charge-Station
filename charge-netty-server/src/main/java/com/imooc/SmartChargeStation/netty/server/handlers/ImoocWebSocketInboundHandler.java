package com.imooc.SmartChargeStation.netty.server.handlers;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.websocketx.BinaryWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ConcurrentHashMap;

/**
 * author: Imooc
 * description: 自定义WebSocket处理器
 * date: 2024
 */

/* **********************
 *
 * WebSocketFrame
 * 是WebSocket数据帧的格式
 *
 * *********************/

@Slf4j
public class ImoocWebSocketInboundHandler extends SimpleChannelInboundHandler<WebSocketFrame> {

    //保存连接进来的Channel
    //用于发信息给指定的用户
    private static ConcurrentHashMap<String, Channel> channelMap = new ConcurrentHashMap<String, Channel>();

    /**
     * author: Imooc
     * description: 有新的客户端连接进来触发
     * @param ctx:
     * @return void
     */
    @Override
    public void handlerAdded (ChannelHandlerContext ctx) throws Exception {

        /* **********************
         *
         * ChannelHandhandlerAddedlerContext保存了
         * Channel以及ChannelPipeline的上下文信息
         *
         * *********************/

        //通过Context对象获取当前连接进来的Channel
        Channel channel =  ctx.channel();
        log.info(">>>>>有新的客户端连接进来: "+channel.id());
        //保存当前连接进来的Channel
        channelMap.put(channel.id().asLongText(), channel);
    }

    /**
     * author: Imooc
     * description: 连接断开触发
     * @param ctx:
     * @return void
     */
    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {

        Channel channel =  ctx.channel();
        log.info(">>>>>有客户端断开连接: "+channel.id());
        //删除保存的Channel
        channelMap.remove(channel.id().asLongText());
    }

    /**
     * author: Imooc
     * description: 接收到消息触发
     * @param channelHandlerContext:
     * @param msg:
     * @return void
     */
    @Override
    protected void channelRead0(
            ChannelHandlerContext channelHandlerContext,
            WebSocketFrame msg) throws Exception {

        /* **********************
         *
         * WebSocketFrame包含
         * 1. TextWebSocketFrame 数据帧的内容是文本类型
         * 2.  BinaryWebSocketFrame 数据帧的内容是二进制类型
         *
         * *********************/

        //判断数据帧的内容是否文本类型
        if( msg instanceof TextWebSocketFrame) {
            //文本类型的数据的业务逻辑会在这里处理
            String message = ((TextWebSocketFrame) msg).text();
            log.info(">>>>>收到channel id为"+channelHandlerContext.channel().id()+"的消息: "+message);
        }else if( msg instanceof BinaryWebSocketFrame) {
            //(Protobuf序列数据)二进制类型的数据业务逻辑会在这里处理


        }
    }
}
