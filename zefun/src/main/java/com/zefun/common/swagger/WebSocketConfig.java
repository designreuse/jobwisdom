package com.zefun.common.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * spring java ws
* @author 高国藩
* @date 2016年5月23日 下午4:32:17
 */
@Configuration
@EnableWebSocket
public class WebSocketConfig extends WebMvcConfigurerAdapter implements WebSocketConfigurer {
    
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(systemWebSocketHandler(), "/websocket").addInterceptors(new WebSocketHandshakeInterceptor());
//        registry.addHandler(systemWebSocketHandler(), "/sockjs/websocket").addInterceptors(new WebSocketHandshakeInterceptor());
    }

    /**
     * ws系统集成模块
    * @author 高国藩
    * @date 2016年5月23日 下午4:32:31
    * @return  为spring创建实例
     */
    @Bean
    public WebSocketHandler systemWebSocketHandler() {
        return new SystemWebSocketHandler();
    }
    
}