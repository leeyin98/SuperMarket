package com.leeyin98.service;

import com.leeyin98.entity.Supplier;
import java.util.List;

public interface SupplierService {
    public List<Supplier> listAll();
    public boolean deleteSupplierById(int sid);
    public boolean insertSupplier(Supplier s);
    public boolean updateSupplier(Supplier s,int sNo);
    public List<Supplier> findSupplierByName(String sName);
    public List<Supplier> findSupplierByID(int sid);
    public List<Supplier> listByPage(int currentPage, int pageSize);
    public List<Supplier> groupBySupplier(int currentPage, int pageSize);
    public List<Supplier> groupBySupplier();
}
