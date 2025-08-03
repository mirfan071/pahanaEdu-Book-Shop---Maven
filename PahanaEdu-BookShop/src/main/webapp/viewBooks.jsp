<%@ page import="java.util.*, com.pahanaedu.model.Book" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<%
    String deleted = request.getParameter("deleted");
    if ("true".equals(deleted)) {
%>
<script>
    alert("Book Details Deleted Successfully");
</script>
<%
    }
    String updated = request.getParameter("updated");
    if ("true".equals(updated)) {
%>
<script>
    alert("Book Details Updated Successfully");
</script>
<%
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>View Books</title>
    <link rel="stylesheet" href="style.css"> 
</head>
<body>

<div class="container">

    <h2>Books List</h2>

    <form method="get" action="allBooks" class="search-form">
        <div class="form-group">
            <input type="text" name="query" placeholder="Search by title, author, category, language & price">
        </div>
        <div class="form-group">
            <button type="submit" class="yellow-button">Search</button>
        </div>
    </form>

<%
  //  String admin = (String) session.getAttribute("admin");
  //  String role = (String) session.getAttribute("role");
    if ("admin".equalsIgnoreCase(admin)) {
%>
    <div>
        <button class="yellow-button">
            <a href="adminPanel.jsp" style="text-decoration:none; color:black;">Back to Admin Panel</a>
        </button>
    </div>
<%
    }
%>

    <table class="container-table;">
        <thead>
            <tr>
                <th>#</th>
                <th>Title</th>
                <th>Category</th>
                <th>Author</th>
                <th>Language</th>
                <th>Price (LKR)</th>
                <% if (!"Cashier".equalsIgnoreCase(role)) { %>
                    <th>Actions</th>
                <% } %>
            </tr>
        </thead>
        <tbody>
        <%
            List<Book> books = (List<Book>) request.getAttribute("bookList");
            if (books != null && !books.isEmpty()) {
                int count = 1;
                java.text.NumberFormat nf = java.text.NumberFormat.getNumberInstance(new java.util.Locale("en", "US"));
                nf.setMinimumFractionDigits(2);

                for (Book b : books) {
        %>
        <tr>
            <td><%= count++ %></td>
            <td><%= b.getTitle() %></td>
            <td><%= b.getCategory() %></td>
            <td><%= b.getAuthor() %></td>
            <td><%= b.getLanguage() %></td>
            <td><%= nf.format(b.getPrice()) %></td>
            <% if (!"Cashier".equalsIgnoreCase(role)) { %>
            <td>
                <div class="action-buttons">
                    <a href="BookController?action=edit&id=<%= b.getId() %>" class="btn-edit">Edit</a>
                    <% if (!"Manager".equalsIgnoreCase(role)) { %>
                    <a href="BookController?action=delete&id=<%= b.getId() %>" class="btn-delete"
                       onclick="return confirm('Are you sure you want to delete this book?');">Delete</a>
                    <% } %>
                </div>
            </td>
            <% } %>
        </tr>
        <%
                }
            } else {
        %>
        <tr>
            <td colspan="7" class="error-message">No books found.</td>
        </tr>
        <% } %>
        </tbody>
    </table>

    <% if (books != null) { %>
    <div style="margin-top: 15px;">
        <strong>Total Books: <%= books.size() %></strong>
    </div>
    <% } %>

    <div style="text-align:center; margin-top: 20px;">
        <form action="allBooks" method="get">
            <button type="submit" class="yellow-button">View All Books</button>
        </form>
    </div>

</div>

<%@ include file="footer.jsp" %>

</body>
</html>
