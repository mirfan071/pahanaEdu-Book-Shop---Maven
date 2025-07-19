<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, com.pahanaedu.model.Book, com.pahanaedu.model.Customer" %>
<%@ include file="header.jsp" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Add Bill</title>
    <link rel="stylesheet" type="text/css" href="css/styleBill.css">
</head>
<body>
<div class="container">
    <h3 class="title">Create New Bill</h3>

    <form method="post" action="BillController" onsubmit="updateItemsField()">

        <div class="form-group">
            <label for="customer">Select Customer</label>
            <select name="customer" required>
                <option value="">-- Choose Customer --</option>
                <%
                    List<Customer> customers = (List<Customer>) request.getAttribute("customers");
                    if (customers != null) {
                        for (Customer c : customers) {
                %>
                <option value="<%= c.getAccountNumber() %>">
                    <%= c.getAccountNumber() %> - <%= c.getName() %>
                </option>
                <%
                        }
                    }
                %>
            </select>
        </div>

        <hr>

        <%
            List<Book> books = (List<Book>) request.getAttribute("books");
        %>

        <% for (int i = 1; i <= 4; i++) { %>
        <div class="row">
            <div class="form-group">
                <label>Book <%= i %></label>
                <select class="book-select" name="book<%= i %>" onchange="updatePrice(this)">
                    <option value="">-- Select Book --</option>
                    <% if (books != null) {
                        for (Book b : books) { %>
                            <option value="<%= b.getId() %>" data-price="<%= b.getPrice() %>"><%= b.getTitle() %></option>
                    <% } } %>
                </select>
            </div>
            <div class="form-group">
                <label>Qty</label>
                <input type="number" name="qty<%= i %>" min="0" class="quantity-input" onchange="updateTotal(this)">
            </div>
            <div class="form-group">
                <label>Unit Price</label>
                <input type="text" readonly class="unit-price readonly" name="unitPrice<%= i %>">
            </div>
            <div class="form-group">
                <label>Total</label>
                <input type="text" readonly class="book-total readonly" name="bookTotal<%= i %>">
            </div>
        </div>
        <% } %>

        <input type="hidden" id="items" name="items" value="">

        <div class="row">
            <div class="form-group right-align">
                <label>Grand Total (LKR)</label>
                <input type="text" id="grandTotal" name="grandTotal" class="readonly bold" readonly>
                <input type="hidden" id="user" name="user" value="<%= username %>">
            </div>
        </div>

        <div class="form-group center-align">
            <button type="submit" class="submit-btn">Generate Bill</button>
        </div>
    </form>
</div>

<script>
function formatNumber(num) {
    return parseFloat(num).toLocaleString('en-US', { minimumFractionDigits: 2, maximumFractionDigits: 2 });
}

function updatePrice(select) {
    const price = select.options[select.selectedIndex].getAttribute('data-price');
    const row = select.closest('.row');
    const unitPriceInput = row.querySelector('.unit-price');
    const qtyInput = row.querySelector('.quantity-input');
    const totalInput = row.querySelector('.book-total');

    if (price) {
        unitPriceInput.value = formatNumber(price);
        updateTotal(qtyInput);
    } else {
        unitPriceInput.value = '';
        totalInput.value = '';
        updateGrandTotal();
    }
}

function updateTotal(qtyInput) {
    const row = qtyInput.closest('.row');
    const unitPriceStr = row.querySelector('.unit-price').value.replace(/,/g, '');
    const unitPrice = parseFloat(unitPriceStr) || 0;
    const quantity = parseInt(qtyInput.value) || 0;
    const total = unitPrice * quantity;
    row.querySelector('.book-total').value = formatNumber(total);
    updateGrandTotal();
}

function updateGrandTotal() {
    let grandTotal = 0;
    document.querySelectorAll('.book-total').forEach(input => {
        const val = input.value.replace(/,/g, '');
        grandTotal += parseFloat(val) || 0;
    });
    document.getElementById('grandTotal').value = formatNumber(grandTotal);
    
}

function updateItemsField() {
    const selected = [];

    document.querySelectorAll('.row').forEach(row => {
        const sel = row.querySelector('.book-select');
        const qty = row.querySelector('.quantity-input');

        if (!sel || !qty) return;

        const opt = sel.options[sel.selectedIndex];
        const q   = parseInt(qty.value);

        if (opt && opt.value !== "" && q > 0) {
            selected.push(opt.text + '(' + q + ')');
        }
    });

    document.getElementById('items').value = selected.join(', ');
}

/* Re‑compute list whenever qty or book changes */
document.querySelectorAll('.book-select').forEach(s =>
    s.addEventListener('change', updateItemsField));
document.querySelectorAll('.quantity-input').forEach(q =>
    q.addEventListener('input',  updateItemsField));
    
</script>

</body>
</html>
