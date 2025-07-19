package com.pahanaedu.dao;

import com.pahanaedu.model.Bill;
import java.sql.*;
import java.util.*;

public class BillDAO {

    public boolean addBill(Bill bill) {
        String sql = "INSERT INTO billing "
                   + "(customer_account_number, customer_name, books_purchased, "
                   + " total_amount, Generated_by) "
                   + "VALUES (?,?,?,?,?)";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, bill.getAccountNumber());
            ps.setString(2, bill.getCustomerName());
            ps.setString(3, bill.getBooksPurchased());
            ps.setDouble(4, bill.getTotalAmount());
            ps.setString(5, bill.getStaffUsername());
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<Bill> getAllBills() {
        List<Bill> list = new ArrayList<>();
        String sql = "SELECT * FROM billing ORDER BY billing_time DESC";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Bill b = new Bill();
                b.setId(rs.getInt("id"));
                b.setAccountNumber(rs.getString("customer_account_number"));
                b.setCustomerName(rs.getString("customer_name"));
                b.setBooksPurchased(rs.getString("books_purchased"));
                b.setTotalAmount(rs.getDouble("total_amount"));
                b.setBillingTime(rs.getTimestamp("billing_time"));
                b.setStaffUsername(rs.getString("Generated_by"));
                list.add(b);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Bill getBillById(int id) {
        String sql = "SELECT * FROM billing WHERE id = ?";
        try (Connection conn = DBConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Bill b = new Bill();
                    b.setId(rs.getInt("id"));
                    b.setAccountNumber(rs.getString("customer_account_number"));
                    b.setCustomerName(rs.getString("customer_name"));
                    b.setBooksPurchased(rs.getString("books_purchased"));
                    b.setTotalAmount(rs.getDouble("total_amount"));
                    b.setBillingTime(rs.getTimestamp("billing_time"));
                    b.setStaffUsername(rs.getString("Generated_by"));
                    return b;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
