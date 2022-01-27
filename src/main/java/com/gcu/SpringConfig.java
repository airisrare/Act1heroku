package com.gcu;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;
import com.gcu.business.OrdersBusinessInterface;
import com.gcu.business.OrdersBusinessService;

@Configuration
public class SpringConfig {
	@Bean(name="OrdersBusinessService", initMethod="init", destroyMethod="destroy")
	//@Scope(value="prototype", proxyMode=ScopedProxyMode.TARGET_CLASS)
	//@RequestScope
	
	//@SessionScope
	
	
	public OrdersBusinessInterface getOrdersBusiness()
	{
		//created an instance of the orders business service and then injected where we auto wired it
		return new OrdersBusinessService();
	}

}
