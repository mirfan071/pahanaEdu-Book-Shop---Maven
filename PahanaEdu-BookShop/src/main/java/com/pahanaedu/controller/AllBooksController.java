package com.pahanaedu.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pahanaedu.service.BookService;
import com.pahanaedu.model.Book;

@WebServlet("/allBooks")
public class AllBooksController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private BookService bookService;
    
	public void init() {
	    bookService = BookService.getInstance(); // singleton pattern
	    
	    
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String query = request.getParameter("query");
	    List<Book> books;

	    try {
	        if (query != null && !query.trim().isEmpty()) {
	            books = bookService.searchBooks(query);
	        } else {
	            books = bookService.getAllBooks();
	        }

	        request.setAttribute("bookList", books);
	        request.getRequestDispatcher("viewBooks.jsp").forward(request, response);

	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "Error fetching books.");
	        request.getRequestDispatcher("WEB-INF/view/error.jsp").forward(request, response);
	    }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
