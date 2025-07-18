<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.pahanaedu.model.Book" %>
<%@ include file="header.jsp" %>

<%
    Book b = (Book) request.getAttribute("book");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Edit Book Details</title>
    <link rel="stylesheet" href="css/styleBook.css"> 
<body>

<div class="container">
    <div class="card">

        <div class="card-header">
            <h4 class="text-center">Edit Book</h4>
        </div>

        <div class="card-body">
           <form action="BookController?action=update" method="post">
		    <input type="hidden" name="id" value="<%= b.getId() %>">
		
		    <div class="mb-3">
		        <label>Title</label>
		        <input type="text" name="title" value="<%= b.getTitle() %>" required>
		    </div>
		
		    <div class="mb-3">
		        <label>Category</label>
		        <input type="text" name="category" value="<%= b.getCategory() %>" required>
		    </div>
		
		    <div class="mb-3">
		        <label>Author</label>
		        <input type="text" name="author" value="<%= b.getAuthor() %>" required>
		    </div>
		
		    <div class="mb-3">
		        <label>Language</label>
		        <input type="text" name="language" value="<%= b.getLanguage() %>" required>
		    </div>
		
		    <div class="mb-3">
		        <label>Price (LKR)</label>
		        <input type="number" name="price" step="0.01" value="<%= b.getPrice() %>" required>
		    </div>
		
		    <div class="mb-3">
		        <button type="submit">Update Book</button>
		    </div>
		</form>
		
		<div style="display: flex; flex-direction: column; gap: 15px;">
				 <div>
			      <button class="blue-button">
			     	 <a href="BookController?action=list" style="text-decoration:none; color:white;">View All Books</a>
			      </button>
			     </div>
			    
			   
				
				<%  
  				  if (admin != null && admin.equalsIgnoreCase("admin")) {
				%>
				
			    <div>
			      <button class="yellow-button">
			     	 <a href="adminPanel.jsp" style="text-decoration:none; color:black;"> Back to Admin Panel</a>
			      </button>
			    </div>
				
				<% 
				    } 
				%>  		
				
				</div>
					 
   			 
        </div>

    </div>
</div>

</body>
</html>
