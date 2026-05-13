package com.imooc.SmartChargeStation.demo.mqtt.PahoMode.service.impl;

import com.imooc.SmartChargeStation.demo.mqtt.PahoMode.service.ClientService;
import com.imooc.SmartChargeStation.demo.mqtt.PahoMode.utils.MqttClientUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

/**
 * author: Imooc
 * description: 业务实现类
 * date: 2024
 */

@Service
public class ClientServiceImpl implements ClientService {

    @Resource
    private MqttClientUtils utils;


    @Override
    public void publish(String topic, String message) {
        utils.publish(topic,message);
    }

    @Override
    public void subScribe(String topic) {
        utils.subScribe(topic);
    }

    @Override
    public void disconnct() {
        utils.disconnct();
    }
}
