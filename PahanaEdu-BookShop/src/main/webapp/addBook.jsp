<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>


<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add New Book</title>
    
    <link rel="stylesheet" href="css/styleBook.css"> 
<body>

<div class="container">
  <div class="card">
  
    <div class="card-header">
      <h4 class="text-center">Add New Book</h4>
    </div>
    
    
    <div class="card-body">
           	    <%	String message = (String) session.getAttribute("message");
				    if (message != null) {
				%>
				    <div class="success-message"><%= message %></div>
				<%
				        session.removeAttribute("message"); 
				    } 
				    
				    String error = (String) request.getAttribute("error");
				    if (error != null) {
				%>
				   <div class="error-message"><%= error %></div>
				<%
				    }
				%>
    
      <form action="BookController?action=add" method="post">

        <div class="mb-3">
          <label for="title">Book Title</label>
          <input type="text" name="title" required>
        </div>

        <div class="mb-3">
          <label for="category">Category</label>
          <input type="text" name="category" required>
        </div>

        <div class="mb-3">
          <label for="author">Author</label>
          <input type="text" name="author" required>
        </div>

        <div class="mb-3">
          <label for="language">Language</label>
          <select name="language" required>
            <option value="English">English</option>
            <option value="Sinhala">Sinhala</option>
            <option value="Tamil">Tamil</option>
          </select>
        </div>

        <div class="mb-3">
          <label for="price">Price (LKR)</label>
          <input type="number" name="price" step="0.01" required>
        </div>

        <div class="mb-3">
          <button type="submit">Add Book</button>
        </div>

      </form>
      
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

</body>
</html>
