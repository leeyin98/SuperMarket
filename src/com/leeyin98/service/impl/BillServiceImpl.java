package com.leeyin98.service.impl;

import com.leeyin98.dao.BillDAO;
import com.leeyin98.dao.impl.BillDAOImpl;
import com.leeyin98.entity.Bill;
import com.leeyin98.entity.Supplier;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.BillService;
import com.leeyin98.ts.ITransaction;
import com.leeyin98.util.DbUtil;
import java.sql.SQLException;
import java.util.List;

public class BillServiceImpl implements BillService {
    private BillDAO dao = (BillDAO) ObjectFactory.getObject("BillDAO");
    private ITransaction ts = (ITransaction) ObjectFactory.getObject("ITransaction");

    @Override
    public List<Bill> listAll() {
        List<Bill> list = null;
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
    public boolean deleteBillById(int bid) {
        boolean isTrue = false;
        try {
            ts.begin();
            isTrue = dao.deleteBillById(bid);
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
    public boolean insertBill(Bill b) {
        boolean isTrue = false;
        try {
            ts.begin();
            isTrue = dao.insertBill(b);
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
    public boolean updateBill(Bill b,int bno) {
        boolean isTrue = false;
        try {
            ts.begin();
            isTrue = dao.updateBill(b,bno);
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
    public List<Bill> findBillByNameAndPaid(String bName, int isPaid) {
        List billList = null;
        try {
            billList = new BillDAOImpl().listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            billList = dao.findBillByNameAndPaid(bName, isPaid);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return billList;
    }

    @Override
    public List<Bill> findBillByName(String bName) {
        List billList = null;
        try {
            billList = new BillDAOImpl().listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            billList = dao.findBillByName(bName);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return billList;
    }

    @Override
    public List<Bill> findBillByPaid(int isPaid) {
        List billList = null;
        try {
            billList = new BillDAOImpl().listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            billList = dao.findBillByPaid(isPaid);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return billList;
    }

    @Override
    public List<Bill> findBillByID(int bid) {
        List billList = null;
        try {
            billList = new BillDAOImpl().listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            billList = dao.findBillByID(bid);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally{
            try {
                DbUtil.closeConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return billList;
    }

    @Override
    public List<Bill> listByPage(int currentPage, int pageSize) {
        List<Bill> list = null;
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
    public List<Supplier> listSupplier() {
        List<Supplier> list = null;
        try {
            list = dao.listSupplier();
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
    public List<Bill> groupByGood(int currentPage,int pageSize) {
        List<Bill> list = null;
        try {
            list = dao.groupByGood(currentPage,pageSize);
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
    public List<Bill> groupByGood() {
        List<Bill> list = null;
        try {
            list = dao.groupByGood();
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
