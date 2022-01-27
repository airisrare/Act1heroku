package com.gcu.data;

import java.util.ArrayList;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.models.OrderModel;

@Service
public class OrdersDataService implements DataAccessInterface <OrderModel>
{
	@SuppressWarnings("unused")
	@Autowired
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;

	//Non default constructor for injection
	public OrdersDataService(DataSource dataSource)
{
	this.dataSource = dataSource;
	this.jdbcTemplateObject = new JdbcTemplate(dataSource);
}
	
	
	
	//CRUD: Finder to return all entities
	@Override
	public List<OrderModel> findAll() 
	{
		String sql = "Select * FROM ORDERS";
		List<OrderModel> orders = new ArrayList<OrderModel>();
		try {
			
			//Execute SQL
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next())
			{
				orders.add(new OrderModel(srs.getInt("ID"),
						srs.getString("ORDER_NO"),
						srs.getString("PRODUCT_NAME"),
						srs.getFloat("PRICE"),
						srs.getInt("QUANTITY")));
			}
		}
		catch(Exception e)
		
		
		
		{
			e.printStackTrace();
		}
		
		return orders;
	}

	@Override
	public OrderModel findById(int id) {
		//SQL Query to be executed
				String sql = "SELECT * FROM ORDERS WHERE ID = ?";
				//instantiates an OrderModel object to hold the information acquired from the database
				OrderModel orders;
				try 
				{
					//gets the information for the flight from the database with the given id
					SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql, id);
					srs.next();
					orders = new OrderModel(srs.getInt("ID"),
							srs.getString("ORDER_NO"),
							srs.getString("PRODUCT_NAME"),
							srs.getFloat("PRICE"),
							srs.getInt("QUANTITY"));
					//returns the Order details
					return orders;
				}
				catch(ArrayIndexOutOfBoundsException e)
				{
					System.out.println("There is no Order with that ID.");
					return null;
				}
				catch(Exception e)
				{
					e.printStackTrace();
					return null;
				}
	}

	@Override
	public String create(OrderModel order) 
	{
		String sql = "INSERT INTO ORDERS(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES(?, ?, ?, ?)";
		try {
			
			//Execute SQL Insert
			int rows = jdbcTemplateObject.update(sql,
													order.getOrderNo(),
													order.getProductName(),
													order.getPrice(),
													order.getQuantity());
			
			return "Successfully added";
		}
		catch(Exception e)
		{
			e.printStackTrace();
			return "Failed";
		}
	
		
	}

	@Override
	public String remove(int id) {
		//SQL Query to be executed
		String sql = "DELETE FROM ORDERS WHERE ID = ?";
		try 
		{
			//deletes flight from database given the ID
			jdbcTemplateObject.update(sql, id);
			//returns success message
			return "Successfully deleted Order from database";
		}
		catch(Exception e)
		{
			//prints stack trace to console
			e.printStackTrace();
			//returns failure message
			return "Order deletion failed.";
		}
		
	}



	@Override
	
	public boolean update(OrderModel order) {

        //System.out.println(order.getId()  + "order passed in from update");
        String sql = "UPDATE ORDERS SET ORDER_NO = ?, PRODUCT_NAME = ?, PRICE = ?, QUANTITY = ? WHERE ID = ?";

        try {
            int rows = jdbcTemplateObject.update(sql, order.getOrderNo(), order.getProductName(), order.getPrice(), order.getQuantity(), order.getId());
            return rows == 1 ? true : false;

        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return false;
//
}
	
	
	//public OrderModel update(OrderModel order) 
//	{
//		//SQL Query to be executed
//				String sql = "UPDATE ORDERS SET ORDER_NO = ?, PRODUCT_NAME = ?, PRICE = ?, QUANTITY = ?, WHERE ID = ?";
//				OrderModel orderCheck;
//		try
//		{
//			//executes the sql query with the given information(updates database)
//			jdbcTemplateObject.update(sql, order.getOrderNo(), order.getProductName(), order.getPrice(), order.getQuantity(), order.getId());
//			//gets the order with the same id from the database to ensure that the update occurred
//			orderCheck = findById(order.getId());
//			return orderCheck;
//			
//		}
//		catch(Exception e)
//		{
//			//prints stack trace to console
//			e.printStackTrace();
//			//returns the original flight information if the update fails
//			return order;
//		}
	}







