package com.pahanaedu.dao;


import com.pahanaedu.model.Book;
import com.pahanaedu.model.Customer;
import java.sql.*;
import java.util.*;


public class CustomerDAO {

	public String generateNextAccountNumber() {
        String prefix = "5501";
        String nextAccount = prefix + "0001";

        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(
                 "SELECT account_number FROM customers WHERE account_number LIKE '5501%' ORDER BY account_number DESC LIMIT 1");
             ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                String lastAccount = rs.getString("account_number");
                int lastNum = Integer.parseInt(lastAccount.substring(4));
                int newNum = lastNum + 1;
                nextAccount = prefix + String.format("%04d", newNum);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return nextAccount;
    }
	
	 public boolean addCustomer(Customer customer) {
		 
	        String sql = "INSERT INTO customers (account_number, name, address, telephone, email) VALUES (?, ?, ?, ?, ?)";
	        
	        //String AccoutNumber = generateNextAccountNumber();
	        
	        try (Connection conn = DBConnectionFactory.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql)) {

	            ps.setString(1, customer.getAccountNumber());
	            ps.setString(2, customer.getName());
	            ps.setString(3, customer.getAddress());
	            ps.setString(4, customer.getTelephone());
	            ps.setString(5, customer.getEmail());
	         
	            int rows = ps.executeUpdate();
	           
	            return rows == 1;
	         	            	
	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return false;
	    }
	 

	    public List<Customer> getAllCustomers() {
	        List<Customer> list = new ArrayList<>();
	        
	        String sql = "SELECT * FROM customers";

	        try (Connection conn = DBConnectionFactory.getConnection();
	             PreparedStatement ps = conn.prepareStatement(sql);
	             ResultSet rs = ps.executeQuery()) {

	            while (rs.next()) {
	                Customer c = new Customer();
	                c.setId(rs.getInt("id"));
	                c.setAccountNumber(rs.getString("account_number"));
	                c.setName(rs.getString("name"));
	                c.setAddress(rs.getString("address"));
	                c.setTelephone(rs.getString("telephone"));
	                c.setEmail(rs.getString("email"));
	                c.setCreatedAt(rs.getString("created_at"));
	                list.add(c);
	            }

	        } catch (Exception e) {
	            e.printStackTrace();
	        }

	        return list;
	    }
	    
	    public List<Customer> searchCustomer(String keyword) {
		    List<Customer> customer = new ArrayList<>();
		    String sql = "SELECT * FROM customers WHERE LOWER(account_number) LIKE ? OR LOWER(name) LIKE ? OR LOWER(address) LIKE ? OR LOWER(telephone) LIKE ? OR LOWER(email) LIKE ?";

		    try (Connection conn = DBConnectionFactory.getConnection();
		         PreparedStatement ps = conn.prepareStatement(sql)) {

		        String like = "%" + keyword.toLowerCase() + "%";
		        ps.setString(1, like);
		        ps.setString(2, like);
		        ps.setString(3, like);
		        ps.setString(4, like);
		        ps.setString(5, like);

		        ResultSet rs = ps.executeQuery();
		        while (rs.next()) {
		            Customer c = new Customer();
		            c.setId(rs.getInt("id"));	
		            c.setAccountNumber(rs.getString("account_number"));
		            c.setName(rs.getString("name"));
		            c.setAddress(rs.getString("address"));
		            c.setTelephone(rs.getString("telephone"));
		            c.setEmail(rs.getString("email"));
		            customer.add(c);
		        }

		    } catch (SQLException e) {
		        e.printStackTrace();
		    }

		    return customer;
		}

	    public Customer getCustomerById(int id) throws SQLException {
			
		    String sql = "SELECT * FROM customers WHERE id = ?";
		    try (Connection conn = DBConnectionFactory.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, id);
		        ResultSet rs = stmt.executeQuery();
		        if (rs.next()) {
		        	
		        	Customer c = new Customer();
		            c.setId(rs.getInt("id"));
		            c.setAccountNumber(rs.getString("account_number"));
		            c.setName(rs.getString("name"));
		            c.setAddress(rs.getString("address"));
		            c.setTelephone(rs.getString("telephone"));
		            c.setEmail(rs.getString("email"));
		            return c;
		        }
		    }
		    return null;
		}
	    
	    public void deleteCustomer(int id) throws SQLException {
			
		    String sql = "DELETE FROM customers WHERE id=?";
		    try (Connection conn = DBConnectionFactory.getConnection();
		         PreparedStatement stmt = conn.prepareStatement(sql)) {
		        stmt.setInt(1, id);
		        stmt.executeUpdate();
		    }
		}
	}
	
