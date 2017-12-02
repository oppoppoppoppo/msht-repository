package com.msht.dubbo.configuration;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnNotWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.annotation.Order;

import com.msht.dubbo.runner.DubboServiceLatchCommandLineRunner;

@Configuration
@Order
@PropertySource(ignoreResourceNotFound=true, encoding="utf-8", value={"classpath:dubbo.properties", "file:./dubbo.properties"})
@ImportResource(locations={"classpath:META-INF/dubbo-service.xml"})
public class DubboAutoConfiguration {
	  protected static Logger logger = LoggerFactory.getLogger(DubboAutoConfiguration.class);

	  @Value("${shutdown.latch.domain.name:com.msht.cn}")
	  private String shutdownLatchDomainName;

	  @Bean
	  @ConditionalOnNotWebApplication
	  public DubboServiceLatchCommandLineRunner configureDubboServiceLatchCommandLineRunner(){
	    logger.info("DubboAutoConfiguration enabled by adding DubboServiceLatchCommandLineRunner");
	    DubboServiceLatchCommandLineRunner runner = new DubboServiceLatchCommandLineRunner();
	    runner.setDomain(this.shutdownLatchDomainName);
	    return runner;
	  }
}
