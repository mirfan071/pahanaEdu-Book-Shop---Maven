<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>
  

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
    <link rel="stylesheet" href="css/styleMainInt.css">
	<link rel="stylesheet" href="style.css" />
</head>
<body>

<div class="main-container">
<div class="title-box">
    <h1>Dashboard</h1>
</div>


		<div class="button-container">
		    <a href="addCustomer.jsp" class="dashboard-btn add-customer">Add Customer</a>
		    <a href="allCustomers" class="dashboard-btn view-customer">View All Customers</a>
		    <a href="addBook.jsp" class="dashboard-btn add-book">Add Book</a>
		    <a href="allBooks" class="dashboard-btn view-book">View All Books</a>
		    <a href="addBill" class="dashboard-btn add-bill">Add New Bill</a>
		    <a href="viewSales" class="dashboard-btn view-sales">View All Sales</a>
		</div>

</div>

<div>
<%@ include file ="footer.jsp"%>
</div>

</body>
</html>

