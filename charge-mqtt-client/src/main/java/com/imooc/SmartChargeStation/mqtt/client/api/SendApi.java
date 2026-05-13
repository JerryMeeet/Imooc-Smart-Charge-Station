package com.imooc.SmartChargeStation.mqtt.client.api;

import com.imooc.SmartChargeStation.mqtt.client.conf.MqttProps;
import com.imooc.SmartChargeStation.mqtt.client.service.MqttService;
import com.imooc.SmartChargeStation.protocol.mqtt.message.ChargePayload;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;

/**
 * author: Imooc
 * description: 发送消息API
 * date: 2024
 */

@Slf4j
public class SendApi {

    @Resource
    private MqttProps props;

    @Resource
    private MqttService gateWay;

    /**
     * author: Imooc
     * description: 发送MQTT消息(payload)
     * @param message:
     * @return void
     */
    void doSend(ChargePayload message) {
        gateWay.send(props.getTopic(),message);
    }
}
