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

	
	public List<Book> searchBooks(String keyword) {
	    List<Book> books = new ArrayList<>();
	    String sql = "SELECT * FROM books WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ? OR LOWER(category) LIKE ? OR LOWER(language) LIKE ?";

	    try (Connection conn = DBConnectionFactory.getConnection();
	         PreparedStatement ps = conn.prepareStatement(sql)) {

	        String like = "%" + keyword.toLowerCase() + "%";
	        ps.setString(1, like);
	        ps.setString(2, like);
	        ps.setString(3, like);
	        ps.setString(4, like);

	        ResultSet rs = ps.executeQuery();
	        while (rs.next()) {
	            Book b = new Book();
	            b.setId(rs.getInt("id"));	
	            b.setTitle(rs.getString("title"));
	            b.setCategory(rs.getString("category"));
	            b.setAuthor(rs.getString("author"));
	            b.setLanguage(rs.getString("language"));
	            b.setPrice(rs.getDouble("price"));
	            books.add(b);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    return books;
	}

	   public boolean isBookNameOrLanguageExists(String bookname, String language) throws SQLException {
	        
		   String sql = "SELECT COUNT(*) FROM books WHERE LOWER(TRIM(title)) = ? AND LOWER(TRIM(language)) = ?";
	        
	        try (Connection conn = DBConnectionFactory.getConnection();
	             PreparedStatement stmt = conn.prepareStatement(sql)) {
	            stmt.setString(1, bookname);
	            stmt.setString(2, language);
	            ResultSet rs = stmt.executeQuery();
	            
	      
	            if (rs.next()) {
	                return rs.getInt(1) > 0;
	            }
	        }
	        return false;
	    }
	
}
