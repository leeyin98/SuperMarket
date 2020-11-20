package com.leeyin98.service;

import com.leeyin98.entity.User;
import java.util.List;

public interface UserService {
    public List<User> listAll();
    public boolean deleteUserById(int uid);
    public boolean insertUser(User u);
    public boolean updateUser(User u,int uNo);
    public boolean loginUser(User u,String uname,String upassword,String authority);
    public List<User> findUserByName(String uName);
    public List<User> findUserByID(int uid);
    public List<User> listByPage(int currentPage, int pageSize);
}
