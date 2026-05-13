package com.imooc.SmartChargeStation.mqtt.client.factory;

import com.imooc.SmartChargeStation.mqtt.client.conf.MqttProps;
import com.imooc.SmartChargeStation.mqtt.client.model.MqttConstants;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

/**
 * author: Imooc
 * description: MQTT 工厂类
 * date: 2024
 */


@Configuration
@Slf4j
public class FactoryBuilder {

    @Resource
    private MqttProps props;


    /**
     * author: Imooc
     * description: 获取Mqtt客户端
     * @param :
     * @return org.springframework.integration.mqtt.core.MqttPahoClientFactory
     */
    @Bean
    public MqttPahoClientFactory getFactory() {

        /* **********************
         *
         * 为什么要使用工厂模式创建对象
         *
         * 1. 对象创建和使用分开
         * 2. 方便维护
         *
         *
         * *********************/

        DefaultMqttPahoClientFactory client =
                new DefaultMqttPahoClientFactory();

        MqttConnectOptions options = props.getOptions();
        //配置连接地址
        options.setServerURIs(new String[]{props.getHost()});

        client.setConnectionOptions(options);

        return client;

    }

    /* **********************
     *
     *
     * Spring Integration的角色
     *
     * 1. 消息的生产者()
     * 2. 消息处理器(MqttPahoMessagingHandler)
     * 3. 消息通道(MessageChannel)
     *
     * Spring Integration发送以及接收信息
     * 都需要通过消息通道
     *
     *注意：发送和接收的消息通道并不是同一个通道
     *
     *
     * *********************/


}
