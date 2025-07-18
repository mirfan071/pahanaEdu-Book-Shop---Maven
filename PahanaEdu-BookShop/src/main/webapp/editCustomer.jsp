<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ page import="com.pahanaedu.model.Customer" %>
<%@ include file="header.jsp" %>

<%
    Customer c = (Customer) request.getAttribute("customer");
    if (c == null) {
%>
    <div class="alert-danger">Customer not found.</div>
<%
    return;
    }
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>Edit Customer Details</title>
  <link rel="stylesheet" href="css/styleCus.css">

  <style>
    input:focus, textarea:focus, select:focus {
      background-color: #fff9c4 !important;
      outline: none;
      box-shadow: none;
    }
  </style>
</head>

<body>
  <div class="container">
    <div class="card">
      
      <div class="card-header">
        <h2 class="text-center">Edit Customer</h2>	
      </div>

      <div class="card-body">
        <form action="CustomerController?action=edit" method="post">
          <input type="hidden" name="action" value="update">
          <input type="hidden" name="id" value="<%= c.getId() %>">

          <div class="mb-3">
            <label>Account Number</label>
            <input type="text" value="<%= c.getAccountNumber() %>" disabled>
            <input type="hidden" name="account_number" value="<%= c.getAccountNumber() %>">
          </div>

          <div class="mb-3">
            <label>Name</label>
            <input type="text" name="name" value="<%= c.getName() %>" required>
          </div>

          <div class="mb-3">
            <label>Address</label>
            <input type="text" name="address" value="<%= c.getAddress() %>">
          </div>

          <div class="mb-3">
            <label>Telephone</label>
            <input type="text" name="telephone" value="<%= c.getTelephone() %>" pattern="\d{10}" title="Enter 10-digit number" required>
          </div>

          <div class="mb-3">
            <label>Email</label>
            <input type="email" name="email" value="<%= c.getEmail() %>" required>
          </div>

          <div class="mb-3">
            <button type="submit">Update Customer</button>
          </div>
        </form>
      </div>

    </div>
  </div>
</body>
</html>
