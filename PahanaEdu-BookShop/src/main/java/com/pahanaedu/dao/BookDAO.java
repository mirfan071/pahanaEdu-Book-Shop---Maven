package com.pahanaedu.dao;
import com.pahanaedu.model.Book;
import java.sql.*;
import java.util.*;

public class BookDAO {
	
	
	public void addBook(Book book) throws SQLException {
		
		String sql = "INSERT INTO books (title, category, author, language, price) VALUES (?, ?, ?, ?, ?)";
		try (Connection conn = DBConnectionFactory.getConnection();
		PreparedStatement stmt = conn.prepareStatement(sql)) {
		stmt.setString(1, book.getTitle());
		stmt.setString(2, book.getCategory());
		stmt.setString(3, book.getAuthor());
		stmt.setString(4, book.getLanguage());
		stmt.setDouble(5, book.getPrice());
		stmt.executeUpdate();
		
		}
		
	}
	
	public List<Book> getAllBooks() throws SQLException {
		
	    List<Book> list = new ArrayList<>();
	    String sql = "SELECT * FROM books";
	    try (Connection conn = DBConnectionFactory.getConnection();
	         Statement stmt = conn.createStatement();
	         ResultSet rs = stmt.executeQuery(sql)) {
	        while (rs.next()) {
	            list.add(new Book(
	                rs.getInt("id"),
	                rs.getString("title"),
	                rs.getString("category"),
	                rs.getString("author"),
	                rs.getString("language"),
	                rs.getDouble("price")));
	        }
	    }
	    return list;
	}

	public Book getBookById(int id) throws SQLException {
		
	    String sql = "SELECT * FROM books WHERE id = ?";
	    try (Connection conn = DBConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        ResultSet rs = stmt.executeQuery();
	        if (rs.next()) {
	            return new Book(
	                rs.getInt("id"),
	                rs.getString("title"),
	                rs.getString("category"),
	                rs.getString("author"),
	                rs.getString("language"),
	                rs.getDouble("price"));
	        }
	    }
	    return null;
	}
	
	

	public void updateBook(Book book) throws SQLException {
		
	    String sql = "UPDATE books SET title=?, category=?, author=?, language=?, price=? WHERE id=?";
	    try (Connection conn = DBConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setString(1, book.getTitle());
	        stmt.setString(2, book.getCategory());
	        stmt.setString(3, book.getAuthor());
	        stmt.setString(4, book.getLanguage());
	        stmt.setDouble(5, book.getPrice());
	        stmt.setInt(6, book.getId());
	        stmt.executeUpdate();
	    }
	}

	
	
	public void deleteBook(int id) throws SQLException {
		
	    String sql = "DELETE FROM books WHERE id=?";
	    try (Connection conn = DBConnectionFactory.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {
	        stmt.setInt(1, id);
	        stmt.executeUpdate();
	    }
	}

}
