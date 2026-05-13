package com.imooc.SmartChargeStation.demo.netty.util;

import com.imooc.SmartChargeStation.demo.netty.handlers.ImoocClientProtobufHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * author: Imooc
 * description: 连接工具类
 * date: 2024
 */


@Slf4j
public class ConnectUtil {
    private final EventLoopGroup eventLoop = new NioEventLoopGroup();
    private Bootstrap bootstrap;
    private Channel channel;

    public void connect(String host, int port) {

        bootstrap = new Bootstrap();
        bootstrap
                .group(eventLoop)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) {
                        ChannelPipeline pipeline = ch.pipeline();

                        pipeline
                                .addLast(new ProtobufVarint32LengthFieldPrepender())
                                .addLast(new ProtobufEncoder())
                                .addLast(new ImoocClientProtobufHandler());
                    }
                });

        doConnect(host, port);
    }

    private void doConnect(String host, int port) {

        log.info(">>>>> Netty 客户端尝试连接 {}:{}", host, port);

        ChannelFuture future = bootstrap.connect(host, port);

        future.addListener((ChannelFutureListener) f -> {
            if (f.isSuccess()) {
                channel = f.channel();
                log.info(">>>>> Netty 客户端连接成功");
            } else {
                log.warn(">>>>> 连接失败，10 秒后重试...");
                f.channel().eventLoop().schedule(
                        () -> doConnect(host, port),
                        10,
                        TimeUnit.SECONDS
                );
            }
        });
    }

    public void close() {
        if (channel != null) {
            channel.close();
        }
        eventLoop.shutdownGracefully();
    }
}
