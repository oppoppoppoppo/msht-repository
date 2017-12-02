package com.msht.dubbo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
@SpringBootApplication
public class SpringbootApplication {
	protected static Logger logger = LoggerFactory
			.getLogger(SpringbootApplication.class);

	public static void main(String[] args) {
		logger.info("SpringBootWithDubboStarterApplication Start.");
		logger.info("******************************************************");
		SpringApplication application = new SpringApplication(
				new Object[] { SpringbootApplication.class });
		application.run(args);
	}
}
