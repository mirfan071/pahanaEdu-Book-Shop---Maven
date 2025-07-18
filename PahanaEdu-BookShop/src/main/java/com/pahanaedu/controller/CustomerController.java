package com.pahanaedu.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.pahanaedu.dao.CustomerDAO;
import com.pahanaedu.model.Book;
import com.pahanaedu.model.Customer;

import com.pahanaedu.service.CustomerService;


@WebServlet("/CustomerController")
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private CustomerService customerService;
    
public void init() {
		
	    customerService = CustomerService.getInstance();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//String action = request.getParameter("action");
//	    try {
//	        if (action == null || action.equals("list")) {
//
//	        	listCustomers(request, response);
//	            
//	       
//	        
//	    } catch (SQLException e) {
//	        request.setAttribute("errorMessage", e.getMessage());
//	        request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
//	    }
	    
	    String action = request.getParameter("action");
	    
	    try {
            if (action == null || "list".equals(action)) {
                // ── search OR full list ─────────────────────────────
                String query = request.getParameter("query");
                List<Customer> customers =
                        (query == null || query.trim().isEmpty())
                        ? customerService.getAllCustomers()
                        : customerService.searchCustomer(query.trim());

                request.setAttribute("customerList", customers);
                request.getRequestDispatcher("viewCustomers.jsp").forward(request, response);
                return;                        // ← VERY IMPORTANT
            }

            if ("add".equals(action)) {
                showAddForm(request, response);
                return;
            }
	     else if (action.equals("add")) {
        	
        	showAddForm(request, response);
          
       
        } else if (action.equals("edit")) {
        	
        	showEditForm(request, response);

            
        } else if (action.equals("delete")) {
        	
        	deleteCustomer(request, response);
        	
        }
            

        } catch (SQLException e) {
            request.setAttribute("errorMessage", e.getMessage());
            request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
        }
    }
	


	

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		   String action = request.getParameter("action");

	        if ("add".equals(action)) {
	        	
	            Customer customer = extractCustomer(request);
	            HttpSession session = request.getSession();

	            if (customerService.addCustomer(customer)) {
	            	
	  	            session.setAttribute("message", "Customer added successfully!");
	  	            
	  	            
	            } else {
	            	
	            	 session.setAttribute("error", "Failed to add customer.");
	            	 
	            }

	            request.getRequestDispatcher("addCustomer.jsp").forward(request, response);

	        }
	        else if ("edit".equals(action)) {
	            int id = Integer.parseInt(request.getParameter("id"));

	            Customer customer = new Customer();
	            customer.setId(id);
	            customer.setName(request.getParameter("name"));
	            customer.setAddress(request.getParameter("address"));
	            customer.setTelephone(request.getParameter("telephone"));
	            customer.setEmail(request.getParameter("email"));

	            if (customerService.updateCustomer(customer)) {
	                
	                response.sendRedirect("CustomerController?action=list&updated=true");
	            } else {
	                request.setAttribute("message", "Update failed.");
	                request.setAttribute("customer", customer); 
	                request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
	            }
	        }
	}
	
	
	private void listCustomers(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, ServletException, IOException {
		
	    List<Customer> customer = customerService.getAllCustomers();
	    request.setAttribute("customerList", customer);
	    request.getRequestDispatcher("viewCustomers.jsp").forward(request, response);
	}
	
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("addCustomer.jsp").forward(request, response);
    }
    
	private Customer extractCustomer(HttpServletRequest request) {
		
		CustomerDAO account = new CustomerDAO();
		 
		String AccountNumber = account.generateNextAccountNumber();
	    	    
		Customer customer = new Customer();
		
		customer.setAccountNumber(AccountNumber);
		customer.setName(request.getParameter("name"));
	    customer.setAddress(request.getParameter("address"));
	    customer.setTelephone(request.getParameter("telephone"));
	    customer.setEmail(request.getParameter("email"));
	    return customer;
	}
	
    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
		       int id = Integer.parseInt(request.getParameter("id"));
		       
		       try {
		           customerService.deleteCustomer(id);  
		           response.sendRedirect("CustomerController?action=list&deleted=true"); 
		       } catch (SQLException e) {
		           e.printStackTrace();
		           request.setAttribute("errorMessage", "Unable to delete book: " + e.getMessage());
		           request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
		       }
    }


    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	
	        int id = Integer.parseInt(request.getParameter("id"));
	        Customer customer = customerService.getCustomerById(id);
	        
		       request.setAttribute("customer", customer);
		       request.getRequestDispatcher("editCustomer.jsp").forward(request, response);
	    }
}
