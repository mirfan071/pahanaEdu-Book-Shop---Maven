package com.pahanaedu.service;
import com.pahanaedu.dao.BookDAO;
import com.pahanaedu.model.Book;

import java.sql.SQLException;
import java.util.List;

public class BookService {
	
	private static BookService instance;
	private BookDAO bookDAO;
	
	private BookService() {
	    bookDAO = new BookDAO();
	}

	public static BookService getInstance() {
	    if (instance == null) {
	        instance = new BookService();
	    }
	    return instance;
	}

	public void addBook(Book book) throws SQLException {
	    bookDAO.addBook(book);
	}

	public List<Book> getAllBooks() throws SQLException {
	    return bookDAO.getAllBooks();
	}

	public Book getBookById(int id) throws SQLException {
	    return bookDAO.getBookById(id);
	}

	public void updateBook(Book book) throws SQLException {
	    bookDAO.updateBook(book);
	}

	public void deleteBook(int id) throws SQLException {
	    bookDAO.deleteBook(id);
	}


}
