<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="header.jsp" %>
<%@ page import="com.pahanaedu.model.Book" %>


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
          <input type="text" id="title" name="title" value="<%= request.getAttribute("book") != null ? ((Book)request.getAttribute("book")).getTitle() : "" %>" required />

        </div>

        <div class="mb-3">
          <label for="category">Category</label>
          <input type="text" name="category" value="<%= request.getAttribute("book") != null ? ((Book)request.getAttribute("book")).getCategory() : "" %>" required>
        </div>

        <div class="mb-3">
          <label for="author">Author</label>
          <input type="text" name="author" value="<%= request.getAttribute("book") != null ? ((Book)request.getAttribute("book")).getAuthor() : "" %>" required>
        </div>

        <div class="mb-3">
          <label for="language">Language</label>
          <select name="language" required>
            <option value="English" <%= "English".equals(request.getAttribute("role")) ? "selected" : "" %>>English</option>
            <option value="Sinhala" <%= "Sinhala".equals(request.getAttribute("role")) ? "selected" : "" %>>Sinhala</option>
            <option value="Tamil" <%= "Tamil".equals(request.getAttribute("role")) ? "selected" : "" %>>Tamil</option>
          </select>
        </div>

        <div class="mb-3">
          <label for="price">Price (LKR)</label>
          <input type="number" name="price" step="0.01" value="<%= request.getAttribute("book") != null ? ((Book)request.getAttribute("book")).getPrice(): "" %>" required>
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
