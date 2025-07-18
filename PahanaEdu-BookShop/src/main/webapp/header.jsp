<%@ page session="true" %>
<%@ page import="com.pahanaedu.model.User" %>

<%
    User u = (User) session.getAttribute("user");
    String admin = (String) session.getAttribute("admin");

    if (u == null && (admin == null || !"admin".equalsIgnoreCase(admin))) {
        response.sendRedirect("Login.jsp");
        return;
    }

    String username = (u != null) ? u.getUsername() : "admin";
    String role = (u != null) ? u.getRole() : "admin";
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Pahana Edu Book Shop</title>
    <link rel="stylesheet" type="text/css" href="css/styleHeader.css">
</head>
<body>

<nav class="custom-navbar">
  <div class="nav-container">
  

  <a class="brand" href="<%= (admin != null && admin.equalsIgnoreCase("admin")) ? "adminPanel.jsp" : "mainInterface.jsp" %>"
  	  title="<%= (admin != null && admin.equalsIgnoreCase("admin")) 
            ? "Click to view Admin Panel" 
            : "Click to view Dashboard" %>">
  
      <img src="Images/logo.png" alt="Logo" class="logo">
      <span>Pahana Edu Book Shop</span>
    </a>

    <ul class="nav-links">
      <li class="dropdown">
        <span class="dropbtn">Customer Maintain</span>
        <div class="dropdown-content">
          <a href="addCustomer.jsp">Add Customer</a>
          <a href="CustomerController?action=list">View All Customers</a>
        </div>
      </li>
      <li class="dropdown">
        <span class="dropbtn">Maintain Books</span>
        <div class="dropdown-content">
          <a href="addBook.jsp">Add Books</a>
          <a href="allBooks">View All Books</a>
        </div>
      </li>
      <li class="dropdown">
        <span class="dropbtn">Billing Menu</span>
        <div class="dropdown-content">
          <a href="addBill">Add New Bill</a>
          <a href="viewSales">View Sales</a>
        </div>
      </li>              
     
    </ul>

    <ul class="nav-right">
     
      <li><a class="logout-link" href="logout">	 Logout</a></li>
    </ul>
    
       <ul class="nav-right">
          <li><a class="help-link" href="help.jsp">&nbsp Help &nbsp </a></li>
 	   </ul>
 	   
 	   <ul class="nav-right">
      <li><span class="user-info1"> Logged User: <%= username.toUpperCase() %> : <%=role %></span></li>
      
    </ul>
  </div>
</nav>
