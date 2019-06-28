package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringApplicationRegister implements ApplicationContextAware {

    private   static  ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringApplicationRegister.applicationContext = applicationContext;
    }

    public  static  ApplicationContext getApplicationContext(){
        return  applicationContext;
    }

    /**
     * 获取容器对象
     * @param type
     * @param <T>
     * @return
     */
    public static <T> T getBean(Class<T> type) {
        return applicationContext.getBean(type);
    }

    public static Object getBean(String name) {
        return applicationContext.getBean(name);
    }


}
