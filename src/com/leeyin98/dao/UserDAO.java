package com.leeyin98.dao;

import com.leeyin98.entity.User;
import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    public List<User> listAll() throws SQLException;
    public boolean deleteUserById(int uid) throws SQLException;
    public boolean insertUser(User u) throws SQLException;
    public boolean updateUser(User u,int uNo) throws SQLException;
    public List<User> findUserByName(String uName) throws SQLException;
    public List<User> findUserByID(int uid) throws SQLException;
    public List<User> listByPage(int currentPage, int pageSize) throws SQLException;
    public boolean loginUser(String uname,String upassword,String authority) throws SQLException;
}
