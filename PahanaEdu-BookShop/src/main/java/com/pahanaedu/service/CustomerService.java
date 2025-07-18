package com.pahanaedu.service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Book;
import com.pahanaedu.model.Customer;

public class CustomerService {

	private static CustomerService instance;
	 private CustomerDAO customerDAO = new CustomerDAO();
	 
	 private CustomerService() {
		   customerDAO = new CustomerDAO();
		}
	 
		public static CustomerService getInstance() {
		    if (instance == null) {
		        instance = new CustomerService();
		    }
		    return instance;
		}

	    public boolean addCustomer(Customer customer) {
	       return customerDAO.addCustomer(customer);
	    }

	    public List<Customer> getAllCustomers() throws SQLException {
	        return customerDAO.getAllCustomers();
	    }
	    
		public List<Customer> searchCustomer(String keyword) {
			return customerDAO.searchCustomer(keyword);
			}
		
		public Customer getCustomerById(int id) throws SQLException {
		    return customerDAO.getCustomerById(id);
		}

		  public boolean updateCustomer(Customer customer) {
		        return customerDAO.updateCustomer(customer);
		    }

		public void deleteCustomer(int id) throws SQLException {
			customerDAO.deleteCustomer(id);;
		}
}	
