<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Help - User Roles and Functionalities</title>
    <link rel="stylesheet" type="text/css" href="css/styleHeader.css">
    <style>
        .help-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
            background-color: #ffffff;
            border-radius: 10px;
            overflow: hidden;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        .help-table th,
        .help-table td {
            padding: 12px 15px;
            text-align: center;
            border-bottom: 1px solid #ddd;
            font-size: 15px;
        }

        .help-table th {
            background-color: #007bff;
            color: white;
        }

        .help-table tr:hover {
            background-color: #f1f1f1;
        }

        .main-container h2 {
            margin-bottom: 10px;
            font-size: 28px;
            color: #333;
        }

        .main-container p {
            font-size: 16px;
            color: #555;
        }

        .yes {
            color: green;
            font-weight: bold;
        }

        .no {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="main-container">
    <h2>User Roles and Functionalities</h2>
    <p>This table shows permissions for each user role.</p>

    <table class="help-table">
        <thead>
            <tr>
                <th>User Role</th>
                <th>Add User</th>
                <th>Edit User</th>
                <th>Delete User</th>
                <th>View Users</th>
                <th>Add Book</th>
                <th>Edit Book</th>
                <th>Delete Book</th>
                <th>View Books</th>
                <th>Add Customer</th>
                <th>Edit Customer</th>
                <th>Delete Customer</th>
                <th>View Customers</th>
                <th>Add Bill</th>
                <th>View Sales</th>
            </tr>
        </thead>
        <tbody>
            <tr>
                <td><strong>Admin</strong></td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
            </tr>
            <tr>
                <td><strong>Customer Manager</strong></td>
                <td class="no">No</td>
                <td class="no">No</td>
                <td class="no">No</td>
                <td class="no">No</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="no">No</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="no">No</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
            </tr>
            <tr>
                <td><strong>Cashier</strong></td>
                <td class="no">No</td>
                <td class="no">No</td>
                <td class="no">No</td>
                <td class="no">No</td>
                <td class="yes">Yes</td>
                <td class="no">No</td>
                <td class="no">No</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="no">No</td>
                <td class="no">No</td>
                <td class="yes">Yes</td>
                <td class="yes">Yes</td>
                <td class="no">No</td>
            </tr>
        </tbody>
    </table>
</div>

</body>
</html>
