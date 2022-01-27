package com.gcu.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gcu.business.OrdersBusinessService;
import com.gcu.models.OrderModel;

@Controller
@RequestMapping("/orders")
public class OrderController 
{
	@Autowired 
	public OrdersBusinessService ordersBusinessService;
	
		@GetMapping("/")
		
		 // Method that shows the main orders page
	
		public String showOrder(Model model)
		{
			model.addAttribute("order", ordersBusinessService.getOrder());
			return "orders.html";
		}
		
		
		//Form fill where a user can create an order for people to buy
			@GetMapping("/createpage")
				public String createOrderPage(Model model)
				{
					model.addAttribute("orderModel", new OrderModel());
				    return "orderscreate.html";
				}
		
		
		//Method to post the results from the order create page, so far just posting 
		@PostMapping("/createOrder1")
		public String createOrder(@Valid OrderModel orderModel,BindingResult bindingResult, Model model)
		{
			if (bindingResult.hasErrors())
			{
				model.addAttribute("title", "Order page");
				return "orderscreate.html";
			}
			
				ordersBusinessService.addOrder(orderModel);
				model.addAttribute("orders", ordersBusinessService.getOrder());
				return "orders";
		}
		
		//Here we are going to view an order
		@PostMapping("/viewOrder")
		public String viewOrder(@RequestParam("id") String idString, Model model)
		{
			OrderModel order = (OrderModel) ordersBusinessService.getOneOrder(Integer.valueOf(idString));
			System.out.println(order);
			model.addAttribute("order", order);
			return "viewOrder.html";
		}
		
		//Form fill where a user can create an order for people to buy
		@GetMapping("/editOrderPage")
		public String editOrderPage(@RequestParam("id") String idString, Model model)
		{
			int id = Integer.valueOf(idString);
			model.addAttribute("orderModel", (OrderModel)ordersBusinessService.getOneOrder(id));
			return "editOrder.html";
		}
		
		
		
		
		//Edit the specific order
		@PostMapping("/editOrder")
		public String editOrder(@RequestParam("id") int id, OrderModel orderModel,BindingResult bindingResult, Model model)
		{
			//OrderModel order = ordersBusinessService.getOneOrder(id);
			
			            
			if (bindingResult.hasErrors())
			{
				model.addAttribute("title", "Order edit page");
				return "editorder";
			}
			//int id = Integer.valueOf(idString);
			System.out.println(orderModel);
			System.out.println(orderModel.getId());
			orderModel.setId(id);
			ordersBusinessService.changeProduct(orderModel);
			model.addAttribute("orders", ordersBusinessService.getOrder());
			return "orders.html";
		
		}
		
		@PostMapping("/deleteOrder")
		/**
		 * Deletes an order from the database
		 */
		public String deleteFlight(OrderModel orderModel, Model model)
		{
			ordersBusinessService.removeProduct(orderModel.getId());
			model.addAttribute("orders", ordersBusinessService.getOrder());
			return "orders.html";
		}
		
		
		
		
		
		
		
	
	

}
