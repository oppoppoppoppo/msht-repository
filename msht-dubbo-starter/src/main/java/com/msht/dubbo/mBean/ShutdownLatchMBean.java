package com.msht.dubbo.mBean;

public interface ShutdownLatchMBean {
	public abstract String shutdown();
	public abstract void await();
}
