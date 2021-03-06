package com.quanmin.djdata.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Author: ate
 * @Description: Application上下文对象工具类
 * @CreateDate: 2019-11-15 11:50
 * @ClassName: com.quanmin.djdata.util.ApplicationContextUtil
 */
@Component
public class ApplicationContextUtil implements ApplicationContextAware {

    /**
     * 上下文对象实例
     */
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * @Titel 获取applicationContext
     * @param
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     *
     * @Titel 通过name获取 Bean.
     * @param name Bean名称
     * @return Object
     */
    public static Object getBean(String name){
        return getApplicationContext().getBean(name);
    }

    /**
     *
     * @Titel 通过class获取Bean
     * @param clazz 类
     * @return Bean
     */
    public static <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     *
     * @Titel 通过name,以及Clazz返回指定的Bean
     * @param name Bean名称 clazz 类
     * @return
     */
    public static <T> T getBean(String name,Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }

    public static <T> T getBeanByClass(String name,Class<T> clazz){
        String className = clazz.getSimpleName();
        return getApplicationContext().getBean(className.concat(".").concat(name), clazz);
    }

}
