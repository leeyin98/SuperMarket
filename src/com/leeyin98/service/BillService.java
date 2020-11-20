package com.leeyin98.service;

import com.leeyin98.entity.Bill;
import com.leeyin98.entity.Supplier;
import java.util.List;

public interface BillService {
    public List<Bill> listAll();
    public boolean deleteBillById(int bid);
    public boolean insertBill(Bill b);
    public boolean updateBill(Bill b,int bno);
    public List<Bill> findBillByNameAndPaid(String bName, int isPaid);
    public List<Bill> findBillByName(String bName);
    public List<Bill> findBillByPaid(int isPaid);
    public List<Bill> findBillByID(int bid);
    public List<Bill> listByPage(int currentPage,int pageSize);
    public List<Supplier> listSupplier();
    public List<Bill> groupByGood(int currentPage,int pageSize);
    public List<Bill> groupByGood();
}
