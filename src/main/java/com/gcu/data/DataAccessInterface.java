package com.gcu.data;

import java.util.List;

//Reusable piece of code we can use
public interface DataAccessInterface <T> 
{
	public List<T> findAll();
	public T findById(int id);
	public String create(T t);
	public boolean update(T t);
	public String remove(int id);
}
