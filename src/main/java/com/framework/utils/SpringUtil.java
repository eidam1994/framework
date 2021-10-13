package com.framework.utils;

import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringUtil.applicationContext == null) {
            SpringUtil.applicationContext = applicationContext;
        }
    }

    /**
     * 获取Spring容器
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /***
     * 根据Bean name获取容器中指定Bean组件
     */
    public static Object getBean(String name) {
        return getApplicationContext().getBean(name);
    }

    /***
     * 根据Bean class获取容器中指定Bean组件
     */
    public static <T> T getBean(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }

    /***
     * 根据Bean name+class获取容器中指定Bean组件
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        return getApplicationContext().getBean(name, clazz);
    }

    /**
     * 获取MVC Bean组件，将代理类转为原生目标bean（如果目标类被切面增强则会失效）
     * 此方法获取到的组件内部autowired注入的组件不为null
     */
    public static Object getAopBean(String name) {
        Object object = getBean(name);
        if (AopUtils.isAopProxy(object)) {
            try {
                object = ((Advised) object).getTargetSource().getTarget();
            } catch (Exception e) {
                throw new RuntimeException("get target AOP bean failed", e);
            }
        }
        return object;
    }


}