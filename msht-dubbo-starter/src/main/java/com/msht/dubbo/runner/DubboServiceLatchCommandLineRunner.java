package com.msht.dubbo.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;

import com.msht.dubbo.mBean.ShutdownLatch;

public class DubboServiceLatchCommandLineRunner implements CommandLineRunner{
	
	protected static Logger logger = LoggerFactory.getLogger(DubboServiceLatchCommandLineRunner.class);
	
	private String domain;
	
	public DubboServiceLatchCommandLineRunner() {
		this.domain = "com.msht";
	}
	public String getDomain(){
		return domain;
	}

	public void setDomain(String domain){
		this.domain = domain;
	}
	
	@Override
	public void run(String... args) throws Exception {
		logger.info("DubboServiceLatchCommandLineRunner start.");
		ShutdownLatch latch = new ShutdownLatch(getDomain());
		latch.await();
	}

}
