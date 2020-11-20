package com.leeyin98.service.impl;

import com.leeyin98.dao.SupplierDAO;
import com.leeyin98.dao.impl.BillDAOImpl;
import com.leeyin98.entity.Supplier;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.SupplierService;
import com.leeyin98.ts.ITransaction;
import com.leeyin98.util.DbUtil;
import java.sql.SQLException;
import java.util.List;

public class SupplierServiceImpl implements SupplierService {
    private SupplierDAO dao = (SupplierDAO) ObjectFactory.getObject("SupplierDAO");
    private ITransaction ts = (ITransaction) ObjectFactory.getObject("ITransaction");
    @Override
    public List<Supplier> listAll() {
        List<Supplier> list = null;
        try {
            list = dao.listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public boolean deleteSupplierById(int sid) {
        boolean isTrue = false;
        try {
            ts.begin();
            isTrue = dao.deleteSupplierById(sid);
            ts.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            ts.rollback();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isTrue;
    }

    @Override
    public boolean insertSupplier(Supplier s) {
        boolean isTrue = false;
        try {
            ts.begin();
            isTrue = dao.insertSupplier(s);
            ts.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            ts.rollback();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isTrue;
    }

    @Override
    public boolean updateSupplier(Supplier s,int sNo) {
        boolean isTrue = false;
        try {
            ts.begin();
            isTrue = dao.updateSupplier(s,sNo);
            ts.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            ts.rollback();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return isTrue;
    }

    @Override
    public List<Supplier> findSupplierByName(String sName) {
        List supplierList = null;
        try {
            supplierList = new BillDAOImpl().listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            supplierList = dao.findSupplierByName(sName);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return supplierList;
    }

    @Override
    public List<Supplier> findSupplierByID(int sid) {
        List supplierList = null;
        try {
            supplierList = new BillDAOImpl().listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            supplierList = dao.findBillByID(sid);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return supplierList;
    }

    @Override
    public List<Supplier> listByPage(int currentPage, int pageSize) {
        List<Supplier> list = null;
        try {
            list = dao.listByPage(currentPage,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<Supplier> groupBySupplier(int currentPage, int pageSize) {
        List<Supplier> list = null;
        try {
            list = dao.groupBySupplier(currentPage,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }

    @Override
    public List<Supplier> groupBySupplier() {
        List<Supplier> list = null;
        try {
            list = dao.groupBySupplier();
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }
}
