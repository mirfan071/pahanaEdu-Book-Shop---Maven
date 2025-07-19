package com.pahanaedu.controller;

import com.pahanaedu.model.Bill;
import com.pahanaedu.model.Book;
import com.pahanaedu.model.Customer;
import com.pahanaedu.service.BillService;
import com.pahanaedu.service.BookService;
import com.pahanaedu.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

@WebServlet("/BillController")
public class BillController extends HttpServlet {

    private BillService billService;
    private CustomerService customerService;
    private BookService bookService;

    @Override
    public void init() {
        billService = BillService.getInstance();
        customerService = CustomerService.getInstance();
        bookService = BookService.getInstance();
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null || "list".equals(action)) {
            List<Bill> bills = billService.getAllBills();
            request.setAttribute("billList", bills);
            request.getRequestDispatcher("viewBills.jsp").forward(request, response);
            return;
        }
        

        if ("add".equals(action)) {
            try {
                List<Customer> customers = customerService.getAllCustomers();
                List<Book> books = bookService.getAllBooks();
                request.setAttribute("customers", customers);
                request.setAttribute("books", books);
            } catch (SQLException e) {
                e.printStackTrace();	
                request.setAttribute("errorMessage", "Unable to load customers / books");
            }
            request.getRequestDispatcher("addBill.jsp").forward(request, response);
        }
        
        if ("delete".equals(action)) {
            String idStr = request.getParameter("id");
            if (idStr != null) {
                try {
                    int id = Integer.parseInt(idStr);
                    boolean deleted = billService.deleteBill(id);
                    if (!deleted) {
                        request.setAttribute("errorMessage", "Failed to delete bill with ID " + id);
                    }
                } catch (NumberFormatException e) {
                    request.setAttribute("errorMessage", "Invalid Bill ID");
                }
            }
            response.sendRedirect("BillController?action=list");
            return;
        }
        
    }

  
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accountNo = request.getParameter("customer");
        String itemsCsv = request.getParameter("items");
        String totalStr = request.getParameter("grandTotal").replace(",", "");
        String staffUser = request.getParameter("user");

        double grandTotal = 0.0;
        try {
            grandTotal = Double.parseDouble(totalStr);
        } catch (NumberFormatException ignored) {}

        Customer cust = customerService.getCustomerByAccount(accountNo);
        String customerName = (cust != null) ? cust.getName() : "Unknown";

        Bill bill = new Bill();
        bill.setAccountNumber(accountNo);
        bill.setCustomerName(customerName);
        bill.setBooksPurchased(itemsCsv);
        bill.setTotalAmount(grandTotal);
        bill.setStaffUsername(staffUser);

        boolean saved = billService.addBill(bill);

        if (!saved) {
            request.setAttribute("errorMessage", "Failed to save bill.");
          
            try {
                request.setAttribute("customers", customerService.getAllCustomers());
                request.setAttribute("books", bookService.getAllBooks());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.getRequestDispatcher("addBill.jsp").forward(request, response);
            return;
        }

        // Prepare book details for preview
        List<Map<String, Object>> bookDetails = new ArrayList<>();

        for (int i = 1; i <= 4; i++) {
            String bid = request.getParameter("book" + i);
            String qtyStr = request.getParameter("qty" + i);

            if (bid == null || bid.trim().isEmpty() || qtyStr == null || qtyStr.trim().isEmpty())
                continue;

            try {
                int bookId = Integer.parseInt(bid);
                int quantity = Integer.parseInt(qtyStr);
                if (quantity <= 0) continue;

                Book book = null;
                try {
                    book = bookService.getBookById(bookId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                if (book == null) {
                    System.out.println("Book not found for ID: " + bookId);
                    continue;
                }

                double unitPrice = book.getPrice();
                double lineTotal = unitPrice * quantity;

                Map<String, Object> row = new HashMap<>();
                row.put("title", book.getTitle());
                row.put("qty", quantity);
                row.put("unitPrice", unitPrice);
                row.put("total", lineTotal);
                bookDetails.add(row);

            } catch (NumberFormatException e) {
                System.out.println("Invalid book id or quantity in row " + i);
                e.printStackTrace();
            }
        }

        request.setAttribute("accountNumber", accountNo);
        request.setAttribute("customerName", customerName);
        request.setAttribute("totalAmount", grandTotal);
        request.setAttribute("billDate", new java.util.Date());
        request.setAttribute("bookDetails", bookDetails);

        request.getRequestDispatcher("BillPreview.jsp").forward(request, response);
    }
}
