package com.example.demo.config;

import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;

@Configuration
public class SpringConfig{

    @EventListener
    @Order(1)
    public void eventListener(ApplicationStartedEvent event) {
        System.out.println("f");
        System.out.println(event.getApplicationContext().isActive());
//        System.out.println(event.getApplicationContext().getId());
    }
}
