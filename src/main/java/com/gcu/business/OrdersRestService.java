package com.gcu.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gcu.models.OrderList;
import com.gcu.models.OrderModel;

@RestController
@RequestMapping("/service")
public class OrdersRestService {
	@Autowired
	private OrdersBusinessInterface service;
	
	//Json path and method
	@GetMapping(path="/getjson", produces= {MediaType.APPLICATION_PROBLEM_JSON_VALUE})
	public List<OrderModel> getOrdersAsJson(){
		
		return service.getOrder();
		
	}
	
	//xml path and method
	@GetMapping(path="/getxml", produces= {MediaType.APPLICATION_XML_VALUE})
	public OrderList getOrdersAsXml()
	{	
		OrderList list = new OrderList();
		list.setOrders(service.getOrder());
		return list;	
	}

}
