package org.tpri.djcom.util;

import org.springframework.context.ApplicationContext;

public class BeanFactory {
	private  static ApplicationContext applicationContext;

	public static void setApplicationContext(ApplicationContext applicationContext) {
		BeanFactory.applicationContext = applicationContext;
	}
	
	public static Object getBean(String beanName){
		return BeanFactory.applicationContext.getBean(beanName);
	}
	
}
