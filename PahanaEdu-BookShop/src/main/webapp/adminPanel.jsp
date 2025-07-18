<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page session="true" %>
<%
    String username = (String) session.getAttribute("username");
    String role = (String) session.getAttribute("role");

    if (username == null || !"admin".equalsIgnoreCase(role)) {
        response.sendRedirect("Login.jsp");  // use correct filename
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
  <title>Admin Panel</title>
  
  <link rel="stylesheet" href="css/styleAdminPanel.css" />
  <link rel="stylesheet" href="css/styleMainInt.css">
	<link rel="stylesheet" href="style.css" />
  
  
</head>
<body>

<div class="navbar-container">
  <div class="navbar">
    <a class="navbar-brand" href="#">
      <img src="Images/logo.png" alt="Logo" style="height: 30px; margin-right: 10px;">
      "Pahana Edu" - Book Shop
    </a>
    <div class="navbar-right">
      <span class="username">Logged as Admin</span>
      <a href="logout" class="logout-btn">Logout</a>
    </div>
  </div>
</div>


<!-- Admin Panel -->
<div class="panel-container">
  <div class="panel-title">Admin Control Panel</div>



<div class="button-container">
  			<a href="addUser.jsp" class="dashboard-btn add-user">Add User</a>
		   	<a href="user?action=list" class="dashboard-btn view-user">View All Users</a>
		    <a href="CustomerController?action=add" class="dashboard-btn add-customer">Add Customer</a>
		    <a href="CustomerController?action=list" class="dashboard-btn view-customer">View All Customers</a>
		    <a href="BookController?action=add" class="dashboard-btn add-book">Add Book</a>
		    <a href="BookController?action=list" class="dashboard-btn view-book">View All Books</a>
		    <a href="addBill" class="dashboard-btn add-bill">Add New Bill</a>
		    <a href="viewSales" class="dashboard-btn view-sales">View All Sales</a>
		</div>
		</div>
</body>
</html>
