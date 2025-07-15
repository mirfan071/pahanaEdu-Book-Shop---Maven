<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Staff Log In</title>
  <link rel="stylesheet" href="style.css">
</head>

<body>
  <div class="container-login">
    <h2>Book Shop Staff Login</h2>
    
    <% String error = (String) request.getAttribute("errorMessage");
	if (error != null) { %>

	<div class="error-message"><%= error %></div>  <% } %>

    <form action="login" method="post">
      <div class="form-group">
        <label for="username">Username</label>
        <input type="text" id="username" name="username" required />
      </div>
      <div class="form-group">
        <label for="password">Password</label>
        <input type="password" id="password" name="password" required minlength="6" />
      </div>
      <button type="submit" class="wide-button">Log In</button>
    </form>

 
  </div>
  <div>
  <%@ include file="footer.jsp" %>
  </div>
  
  
  
</body>
</html>
