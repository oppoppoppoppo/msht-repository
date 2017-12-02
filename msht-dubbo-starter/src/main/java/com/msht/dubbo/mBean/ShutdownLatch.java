package com.msht.dubbo.mBean;

import java.lang.management.ManagementFactory;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import javax.management.InstanceAlreadyExistsException;
import javax.management.MBeanRegistrationException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.NotCompliantMBeanException;
import javax.management.ObjectName;

public class ShutdownLatch implements ShutdownLatchMBean{
	
	protected AtomicBoolean running;
	private long checkIntervalInSeconds;
	private String domain;
	
	public ShutdownLatch() {
		this.running = new AtomicBoolean(false);
		this.checkIntervalInSeconds = 10L;
		this.domain = "com.msht.cn";
	}
	
	public ShutdownLatch(String domain) {
		this.running = new AtomicBoolean(false);
		this.checkIntervalInSeconds = 10L;
		this.domain = domain;
	}
	
	@Override
	public String shutdown() {
		if (running.compareAndSet(true, false))
			return "Shutdown signal sent, shutting down...";
		else
			return "Shutdown signal had been sent, no need again and again...";
	}

	@Override
	public void await() {
		if(running.compareAndSet(false, true)){
			MBeanServer mBeanServer = ManagementFactory.getPlatformMBeanServer();
			try {
				mBeanServer.registerMBean(this, new ObjectName(domain, "name", "ShutdownLatch"));
			      while (this.running.get()){
			          TimeUnit.SECONDS.sleep(this.checkIntervalInSeconds);
			      }
			} catch (InstanceAlreadyExistsException e) {
				e.printStackTrace();
			} catch (MBeanRegistrationException e) {
				e.printStackTrace();
			} catch (NotCompliantMBeanException e) {
				e.printStackTrace();
			} catch (MalformedObjectNameException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}
