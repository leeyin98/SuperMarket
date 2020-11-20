package com.leeyin98.dao;

import com.leeyin98.entity.Bill;
import com.leeyin98.entity.Supplier;
import java.sql.SQLException;
import java.util.List;

public interface BillDAO {
    public List<Bill> listAll() throws SQLException;
    public boolean deleteBillById(int bid) throws SQLException;
    public boolean insertBill(Bill b) throws SQLException;
    public boolean updateBill(Bill b,int bno) throws SQLException;
    public List<Bill> findBillByNameAndPaid(String bName, int payID) throws SQLException;
    public List<Bill> findBillByName(String bName) throws SQLException;
    public List<Bill> findBillByPaid(int payID) throws SQLException;
    public List<Bill> findBillByID(int bid) throws SQLException;
    public List<Bill> listByPage(int currentPage,int pageSize) throws SQLException;
    public List<Supplier> listSupplier() throws SQLException;
    public List<Bill> groupByGood(int currentPage,int pageSize) throws SQLException;
    public List<Bill> groupByGood() throws SQLException;
}
