package com.gcu.controller;

import java.util.ArrayList;


import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessInterface;
import com.gcu.business.SecurityBusinessService;
import com.gcu.models.LoginModel;
import com.gcu.models.OrderModel;

@Controller
@RequestMapping("")
public class LoginController 
{
	//Find method that uses ordersbusiness interface and inject code where variable is
	@Autowired
	private OrdersBusinessInterface service;
	
	//class member variable, using instance of security business service
	@Autowired
	private SecurityBusinessService security;
	
	
	@GetMapping(path= {"", "/"})
	public String display(Model model)
	{
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		return "login";
	}
	
	//all form post methods will look something like this
	//model bound to the form
	//binding
	//model
	//URI must match the action in the html
	@PostMapping("/doLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		
		//Check for validation Errors
		if(bindingResult.hasErrors())
		{
			model.addAttribute("title", "Login Form");
			return "login";
		}
		
		
		//Make call to the orders business service
		service.test();
		
		
		//make a call to authenticate in security business service
		security.authenticate(loginModel.getUsername(),loginModel.getPassword());
		
		
		//Get some orders
		List<OrderModel> orders = service.getOrder();
		
		//Display Order View
		model.addAttribute("title", "My Orders");
		model.addAttribute("orders",orders);
		
		return "orders";
	}
	
	
	
}
