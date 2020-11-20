package com.leeyin98.service.impl;

import com.leeyin98.dao.UserDAO;
import com.leeyin98.dao.impl.BillDAOImpl;
import com.leeyin98.entity.User;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.UserService;
import com.leeyin98.ts.ITransaction;
import com.leeyin98.util.DbUtil;
import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {
    private UserDAO dao = (UserDAO) ObjectFactory.getObject("UserDAO");
    private ITransaction ts = (ITransaction) ObjectFactory.getObject("ITransaction");
    @Override
    public List<User> listAll() {
        List<User> list = null;
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
    public boolean deleteUserById(int uid) {
        boolean isTrue = false;
        try {
            ts.begin();
            isTrue = dao.deleteUserById(uid);
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
    public boolean insertUser(User u) {
        boolean isTrue = false;
        try {
            ts.begin();
            isTrue = dao.insertUser(u);
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
    public boolean updateUser(User u,int uNo) {
        boolean isTrue = false;
        try {
            ts.begin();
            isTrue = dao.updateUser(u,uNo);
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
    public List<User> findUserByName(String uName) {
        List supplierList = null;
        try {
            supplierList = new BillDAOImpl().listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            supplierList = dao.findUserByName(uName);
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
    public List<User> findUserByID(int uid) {
        List supplierList = null;
        try {
            supplierList = new BillDAOImpl().listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            supplierList = dao.findUserByID(uid);
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
    public List<User> listByPage(int currentPage, int pageSize) {
        List<User> list = null;
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
    public boolean loginUser(User u, String uname, String upassword, String authority) {
        return false;
    }
}
