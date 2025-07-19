package com.pahanaedu.service;

import com.pahanaedu.dao.BillDAO;
import com.pahanaedu.model.Bill;

import java.util.List;

public class BillService {

    private static BillService instance;
    private final BillDAO billDAO;

    private BillService() {
        billDAO = new BillDAO();
    }

    public static BillService getInstance() {
        if (instance == null) {
            instance = new BillService();
        }
        return instance;
    }

    /* ---------- CRUD wrappers ---------- */

    public boolean addBill(Bill bill) {
        return billDAO.addBill(bill);
    }

    public List<Bill> getAllBills() {
        return billDAO.getAllBills();
    }

    public Bill getBillById(int id) {
        return billDAO.getBillById(id);
    }

   
    // public boolean deleteBill(int id) { … }
    // public boolean updateBill(Bill b) { … }
}
