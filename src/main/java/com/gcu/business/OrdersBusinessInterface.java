package com.gcu.business;

import java.util.List;

import com.gcu.models.OrderModel;

public interface OrdersBusinessInterface {
	
	
	public void test();
	public List<OrderModel>getOrder();
	public String addOrder(OrderModel order);
	public OrderModel getOneOrder(int id);
	public boolean changeProduct(OrderModel order);
	public String removeProduct(int id);
	public void init();
	public void destroy();
}

