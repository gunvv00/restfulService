package kr.co.myservice.restfulapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class RestfulapiApplication {

	public static void main(String[] args) {
		ApplicationContext ac = SpringApplication.run(RestfulapiApplication.class, args);
		String[] allBeanName = ac.getBeanDefinitionNames();
		for (String beanName : allBeanName) {
			System.out.println("beanName = " + beanName);
		}
	}

}
