package com.sky.hiwise.study.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器：初始化前后进行处理工作
 * 将后置处理器加入到容器中
 * @author lfy
 */
@Component
public class MyBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		if (bean instanceof BaseService) {
			CustomerType customerType = bean.getClass().getAnnotation(CustomerType.class);
			System.out.println("postProcessBeforeInitialization customerType... " + customerType.value());
		} else {
			System.out.println("MyBeanPostProcessor postProcessBeforeInitialization... "+beanName+"=>"+bean);
		}
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		// TODO Auto-generated method stub
        if (bean instanceof BaseService) {
            CustomerType customerType = bean.getClass().getAnnotation(CustomerType.class);
            System.out.println("postProcessAfterInitialization customerType... " + customerType.value());
        } else {
            System.out.println("MyBeanPostProcessor postProcessAfterInitialization..."+beanName+"=>"+bean);
        }
		return bean;
	}

}
