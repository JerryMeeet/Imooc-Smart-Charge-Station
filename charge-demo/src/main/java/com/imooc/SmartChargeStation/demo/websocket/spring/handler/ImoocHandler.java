package com.imooc.SmartChargeStation.demo.websocket.spring.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * author: Imooc
 * description: 自定义处理器
 * date: 2024
 */

@Component
@Slf4j
public class ImoocHandler implements WebSocketHandler {

    /**
     * author: Imooc
     * description: 握手之后触发
     * @param session:
     * @return void
     */
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {

        log.info(">>>>>基于Spring注解 连接建立成功 <<<<<");
    }

    /**
     * author: Imooc
     * description: 消息业务逻辑处理
     * @param session:
     * @param message:
     * @return void
     */
    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

        //取出消息内容
        String payload = message.getPayload().toString();
        Object token = session.getAttributes().get("Token");


    }

    /**
     * author: Imooc
     * description: 发送错误触发
     * @param session:
     * @param exception:
     * @return void
     */
    @Override
    public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

    }

    /**
     * author: Imooc
     * description: 连接断开触发
     * @param session:
     * @param closeStatus:
     * @return void
     */
    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {

    }

    /**
     * author: Imooc
     * description: 是否支持内容切片处理
     * @param :
     * @return boolean
     */
    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
