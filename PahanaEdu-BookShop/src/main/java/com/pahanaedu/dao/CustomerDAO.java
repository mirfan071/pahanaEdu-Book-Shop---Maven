package com.pahanaedu.dao;


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

	}
	
