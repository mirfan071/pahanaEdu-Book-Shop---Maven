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

import com.pahanaedu.model.Book;

import com.pahanaedu.service.BookService;


@WebServlet("/BookController")
public class BookController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	private BookService bookService;

	
	public void init() {
		
	    bookService = BookService.getInstance();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
	    try {
	        if (action == null || action.equals("list")) {

	        	listBooks(request, response);
	            
	        } else if (action.equals("add")) {
	        	
	        	showAddForm(request, response);
	          
           
	        } else if (action.equals("edit")) {
	        	
	        	showEditForm(request, response);
    
	            
	        } else if (action.equals("delete")) {
	        	
	        	deleteBook(request, response);
	        	
	        }
	        
	    } catch (SQLException e) {
	        request.setAttribute("errorMessage", e.getMessage());
	        request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
	    }
	    
	    

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
//		String action = request.getParameter("action");
//	    try {
//	        
//	    	if (action.equals("add")) {
//	            
//	        	Book book = extractBook(request);
//	            bookService.addBook(book);
////	            response.sendRedirect("BookController?action=list");
//	            response.sendRedirect("addBook.jsp");
//	        
//	        } else if (action.equals("update")) {
//	        
//	        	Book book = extractBook(request);
//	            book.setId(Integer.parseInt(request.getParameter("id")));
//	            bookService.updateBook(book);
//	            response.sendRedirect("BookController?action=list");
//	        
//	        }
//	    } catch (SQLException e) {
//	        request.setAttribute("errorMessage", e.getMessage());
//	        request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
//	    }
		
		
		String action = request.getParameter("action");
		
	    try {
	    	
	        if ("add".equals(action)) {
	            Book book = extractBook(request);

	            // Check for duplicate book with name and language before adding to DB
	          
	            
	            if (bookService.isDuplicateBook(book.getTitle(), book.getLanguage())) {
	                request.setAttribute("error", "Book with same title in same language already exists.");
	                
	                // Retain previously entered values
	                request.setAttribute("book", book);
	                
	                request.getRequestDispatcher("addBook.jsp").forward(request, response);
	                return;
	            }

	            bookService.addBook(book);
	            HttpSession session = request.getSession();
	            session.setAttribute("message", "Book added successfully!");
	            response.sendRedirect("addBook.jsp");
	            
	        } 
	        else if ("update".equals(action)) {
	            Book book = extractBook(request);
	            book.setId(Integer.parseInt(request.getParameter("id")));
	            bookService.updateBook(book);
	            response.sendRedirect("BookController?action=list&updated=true");
	        }
	        
	    } catch (SQLException e) {
	        request.setAttribute("errorMessage", e.getMessage());
	        request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
	    }
	}

	private Book extractBook(HttpServletRequest request) {
	    String title = request.getParameter("title");
	    String category = request.getParameter("category");
	    String author = request.getParameter("author");
	    String language = request.getParameter("language");
	    double price = Double.parseDouble(request.getParameter("price"));
	    return new Book(0, title, category, author, language, price);
	}

	private void listBooks(HttpServletRequest request, HttpServletResponse response)
	        throws SQLException, ServletException, IOException {
		
	    List<Book> books = bookService.getAllBooks();
	    request.setAttribute("bookList", books);
	    request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
	}
	
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.getRequestDispatcher("addBook.jsp").forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
    	
	        int id = Integer.parseInt(request.getParameter("id"));
	        Book book = bookService.getBookById(id);
	        
		       request.setAttribute("book", book);
		       request.getRequestDispatcher("editBook.jsp").forward(request, response);
	    }
    
    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
    	
		       int id = Integer.parseInt(request.getParameter("id"));
		       
		       try {
		           bookService.deleteBook(id);  
		           response.sendRedirect("BookController?action=list&deleted=true"); 
		       } catch (SQLException e) {
		           e.printStackTrace();
		           request.setAttribute("errorMessage", "Unable to delete book: " + e.getMessage());
		           request.getRequestDispatcher("/WEB-INF/view/error.jsp").forward(request, response);
		       }
    }
}
