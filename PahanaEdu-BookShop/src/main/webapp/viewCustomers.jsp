<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.pahanaedu.model.Customer" %>

<%
    String deleted = request.getParameter("deleted");
    if ("true".equals(deleted)) {
%>
    <script>
        alert("Customer details deleted Successfully");
    </script>
<%
    } 
%>
<%
    String updated = request.getParameter("updated");
    if ("true".equals(updated)) {
%>
    <script>
        alert("Customer details updated Successfully");
    </script>
<%
    } 
%>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View All Customers</title>
    <link rel="stylesheet" href="style.css"> 
</head>
<body>

<div class="container">

    <h2>Customers List</h2>

    <form method="get" action="searchCustomer" class="search-form">
        <div class="form-group">
            <input type="text" name="accountNumber" placeholder="Search by Account Number"
                   value="<%= request.getParameter("accountNumber") != null ? request.getParameter("accountNumber") : "" %>">
        </div>
        <div class="form-group">
            <input type="text" name="name" placeholder="Search by Name"
                   value="<%= request.getParameter("name") != null ? request.getParameter("name") : "" %>">
        </div>
        <div class="form-group">
            <input type="text" name="telephone" placeholder="Search by Telephone"
                   value="<%= request.getParameter("telephone") != null ? request.getParameter("telephone") : "" %>">
        </div>
        <div class="form-group">
            <button type="submit" class="yellow-button">Search</button>
        </div>
    </form>


<% 
  
    if (admin != null && admin.equalsIgnoreCase("admin")) {
%>
    <div>
      <button class="yellow-button">
     	 <a href="adminPanel.jsp" style="text-decoration:none; color:black;">Back to Admin Panel</a>
      </button>
    </div>
<% 
    } 
%>

    <table>
        <thead>
        <tr>
            <th>#</th>
            <th>Account Number</th>
            <th>Name</th>
            <th>Address</th>
            <th>Telephone</th>
            <th>Email</th>
            <% if (!"Cashier".equalsIgnoreCase(role)) { %>
                <th>Actions</th>
            <% } %>
        </tr>
        </thead>
        <tbody>
        <%
            List<Customer> customers1 = (List<Customer>) request.getAttribute("customerList");
            int count = 1;
            if (customers1 != null && !customers1.isEmpty()) {
                for (Customer c : customers1) {
                    String rawTel = c.getTelephone();
                    String formattedTel = rawTel;
                    if (rawTel != null && rawTel.length() == 10) {
                        formattedTel = "(" + rawTel.substring(0, 3) + ") " + rawTel.substring(3, 6) + "-" + rawTel.substring(6);
                    }
        %>
        <tr>
            <td><%= count++ %></td>
            <td><%= c.getAccountNumber() %></td>
            <td><%= c.getName() %></td>
            <td><%= c.getAddress() %></td>
            <td><%= formattedTel %></td>
            <td><%= c.getEmail() %></td>
            <% if (!"Cashier".equalsIgnoreCase(role)) { %>
            	
           
            <td>
                <a href="editCustomer?id=<%= c.getAccountNumber() %>" class="btn-edit">Edit</a>
                
                   <% if (!"Manager".equalsIgnoreCase(role)) { %>
                   
                <a href="deleteCustomer?id=<%= c.getId() %>" class="btn-delete"
                   onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a>
            </td>
            <% }} %>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="7" class="error-message">No customers found.</td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <% if (customers1 != null) { %>
    <div>
        <strong>Total Customers: <%= customers1.size() %></strong>
    </div>
    <% } %>

    <div style="text-align:center; margin-top:20px;">
        <form action="allCustomers" method="get">
            <button type="submit" class="blue-button">View All Customers</button>
        </form>
    </div>

</div>
</body>
</html>
