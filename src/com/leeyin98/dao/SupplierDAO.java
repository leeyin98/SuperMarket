package com.leeyin98.dao;

import com.leeyin98.entity.Supplier;
import java.sql.SQLException;
import java.util.List;

public interface SupplierDAO {
    public List<Supplier> listAll() throws SQLException;
    public boolean deleteSupplierById(int sid) throws SQLException;
    public boolean insertSupplier(Supplier s) throws SQLException;
    public boolean updateSupplier(Supplier s,int sNo) throws SQLException;
    public List<Supplier> findSupplierByName(String sName) throws SQLException;
    public List<Supplier> findBillByID(int sid) throws SQLException;
    public List<Supplier> listByPage(int currentPage, int pageSize) throws SQLException;
    public List<Supplier> groupBySupplier(int currentPage, int pageSize) throws SQLException;
    public List<Supplier> groupBySupplier() throws SQLException;
}
