package com.imooc.SmartChargeStation.netty.server.handlers;

import com.imooc.SmartChargeStation.protocol.protobuf.ChargingCmdProtobuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * author: Imooc
 * description: 专门处理Protobuf反序列之后的数据
 * date: 2025
 */

@Slf4j
public class ProtobufHandler extends SimpleChannelInboundHandler<ChargingCmdProtobuf.ChargingCmd> {
    @Override
    protected void channelRead0(
            ChannelHandlerContext channelHandlerContext,
            ChargingCmdProtobuf.ChargingCmd chargingCmd) throws Exception {

        log.info(">>>>>微信小程序发送的充电指令："+chargingCmd.getCmd());
    }
}
