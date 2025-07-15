	<%@ page language="java" contentType="text/html; charset=UTF-8"
	    pageEncoding="UTF-8"%>
	<%@ page import="com.pahanaedu.model.User" %>
	<%
	    String admin = (String) session.getAttribute("admin");
	    if (admin == null || !"admin".equalsIgnoreCase(admin)) {
	        response.sendRedirect("Login.jsp");
	        return;
	    }
	%>
	
	<%
	    User u = (User) request.getAttribute("user");
	    if (u == null) {
	        response.sendRedirect("viewUsers.jsp");
	        return;
	    }
	%>
	<!DOCTYPE html>
	<html>
	<head>
	    <meta charset="UTF-8">
	    <title>Update User</title>
	    <link rel="stylesheet" href="style.css">
	</head>
	<body>
	    <div class="container-login">
	        <h2>Update User</h2>
	        <form action="user?action=update" method="post">
	            <input type="hidden" name="id" value="<%= u.getId() %>">
	            
	            <div class="form-group">
	                <label for="fullname">Full Name</label>
	                <input type="text" id="fullname" name="fullname" value="<%= u.getFullname() %>" required>
	            </div>
	
	            <div class="form-group">
	                <label for="username">Username</label>
	                <input type="text" id="username" name="username" value="<%= u.getUsername() %>" required>
	            </div>
	
	            <div class="form-group">
	                <label for="email">Email Address</label>
	                <input type="text" id="email" name="email" value="<%= u.getEmail() %>" required>
	            </div>
	
	                 <div class="form-group">
				        <label for="role">User Role</label>
					        <select id="role" name="role" required>
					              <option value="Cashier" <%= u.getRole().equals("Cashier") ? "selected" : "" %>>Cashier</option>
	             				  <option value="Manager" <%= u.getRole().equals("Manager") ? "selected" : "" %>>Manager</option>
					        </select>
			     	 </div>
			     	 
			     	   <div class="form-group">
					         <label>New Password (Leave blank to keep existing password)</label>
	           				 <input type="password" name="password" minlength="6">
					   </div>
			     	 
	
	            <button type="submit" class="wide-button">Update</button>
	        </form>
	        <div style="margin-top: 20px; text-align: center;">
	  <a href="adminPanel.jsp">
	    <button type="button" class="blue-button">Back to Admin Panel</button>
	  </a>
	</div>
	
	</div>
	    <div>
	    <%@ include file="footer.jsp" %>
	    </div>
	  
	</body>
	</html>
