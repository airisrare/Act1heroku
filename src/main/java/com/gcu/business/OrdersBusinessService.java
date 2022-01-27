package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gcu.data.DataAccessInterface;
import com.gcu.models.OrderModel;

public class OrdersBusinessService implements OrdersBusinessInterface {

	@Autowired
	DataAccessInterface<OrderModel> service;
	
	public void init()
	{
		System.out.println("In the Orders Business Service.Init()");
	}
	
	public void destroy()
	{
		System.out.println("In the Orders Business Service.destroy()");
	}
	
	@Override
	public void test() {
		// TODO Auto-generated method stub
		System.out.println("Hello from the orders business service");
		
	}

	@Override
	public List<OrderModel> getOrder() {
		return service.findAll();
	}
	
	
	public boolean changeProduct(OrderModel order)
	{
		return service.update(order);
	}


	@Override
	public String addOrder(OrderModel order) {
		// TODO Auto-generated method stub
		return service.create(order);
	}

	public OrderModel getOneOrder(int id) {
		
		return service.findById(id);
	}
	
	@Override
	public String removeProduct(int id)
	{
		return service.remove(id);
	}
	
	

}
