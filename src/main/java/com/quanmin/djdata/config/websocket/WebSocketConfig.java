package com.quanmin.djdata.config.websocket;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.server.standard.ServerEndpointExporter;

/**
 * @Author: ate
 * @Description: websocket端口开启
 * @CreateDate: 2019-11-25 12:34
 * @ClassName: com.quanmin.djdata.config.websocket.WebSocketConfig
 */
@Configuration
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Bean
    public ServerEndpointExporter serverEndpointExporter(){
        return new ServerEndpointExporter();
    }

    //注册STOMP协议节点，同时指定使用SockJS协议。
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/djdata/ws").setAllowedOrigins("*").withSockJS();
    }

    //由于我们是实现推送功能，这里的消息代理是/topic
    /*@Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        //启动STOMP 代理中继功能，并将其代理目的地前缀设置为 "/queue"
        config.enableSimpleBroker("/topic");
        //应用程序开头
        config.setApplicationDestinationPrefixes("/app");
    }*/

}
