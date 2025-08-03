<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <title>Staff Log In</title>
  <link rel="stylesheet" href="css/styleLogin.css" />
</head>

<body>
  <div class="main-layout">
    <div class="background-image">
      <div class="intro-overlay">
        <h1>Welcome to PahanaEdu Book Shop</h1>
        <p>Your one-stop shop management system. Please log in to continue.</p>
      </div>
    </div>

    <div class="container-login">
      <h2>Staff Login</h2>

      <% String error = (String) request.getAttribute("errorMessage");
         if (error != null) { %>
        <div class="error-message"><%= error %></div>
      <% } %>

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
  </div>
</body>

</html>
