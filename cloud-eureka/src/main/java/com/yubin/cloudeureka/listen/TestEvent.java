package com.yubin.cloudeureka.listen;

import org.springframework.cloud.netflix.eureka.server.event.EurekaInstanceCanceledEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * 测试取消服务事件
 *
 * @author YUBIN
 * @create 2021-02-01
 */
@Component
public class TestEvent {

    @EventListener
    public void listen(EurekaInstanceCanceledEvent event){
        // 发邮件 短信
        System.out.println("下线:"+event.getServerId());
    }
}
