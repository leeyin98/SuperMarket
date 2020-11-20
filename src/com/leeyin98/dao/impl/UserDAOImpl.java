package com.leeyin98.dao.impl;

import com.leeyin98.dao.IObjectMapper;
import com.leeyin98.dao.UserDAO;
import com.leeyin98.entity.User;
import com.leeyin98.util.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserDAOImpl implements UserDAO {
    @Override
    public List<User> listAll() throws SQLException{

        return JdbcTemplate.executeQuery("select * from tb_user",new UserMap(),null);
    }

    @Override
    public boolean deleteUserById(int uid) throws SQLException{
        boolean isTrue = false;
        if (JdbcTemplate.executeUpdate("delete from tb_user where uno=?",uid)>0){
            isTrue = true;
        }
        return isTrue;
    }

    @Override
    public boolean insertUser(User u) throws SQLException{
        boolean isTrue = false;
        if (JdbcTemplate.executeUpdate("INSERT INTO tb_user (uname,upassword,sex,age,utel,addr,authority) VALUES ( ?,?, ?, ?, ?, ?,?)",u.getU_name(),u.getU_password(),u.getU_gender(),u.getU_age(),u.getU_phone(),u.getU_address(),u.getU_auth())>0){
            isTrue = true;
        }
        return isTrue;
    }

    @Override
    public boolean updateUser(User u,int uNo) throws SQLException{
        boolean isTrue = false;
        if (JdbcTemplate.executeUpdate("update tb_user set uname=?,upassword=?,sex=?,age=?,utel=?,addr=?,authority=? where uno=?",u.getU_name(),u.getU_password(),u.getU_gender(),u.getU_age(),u.getU_phone(),u.getU_address(),u.getU_auth(),uNo)>0){
            isTrue = true;
        }
        return isTrue;
    }

    @Override
    public List<User> findUserByName(String uName) throws SQLException{
        return JdbcTemplate.executeQuery("select uno,uname,upassword,sex,age,utel,addr,authority from tb_user where uname=? ",new UserMap(),uName);
    }

    @Override
    public List<User> findUserByID(int uid) throws SQLException {
        return JdbcTemplate.executeQuery("select uno,uname,upassword,sex,age,utel,addr,authority from tb_user where uno=?", new UserMap(), uid);
    }

    @Override
    public List<User> listByPage(int currentPage, int pageSize) throws SQLException {
        return JdbcTemplate.executeQuery("select uno,uname,upassword,sex,age,utel,addr,authority from tb_user order by uno limit ?,?",new UserMap(),(currentPage-1)*pageSize,pageSize);
    }

    @Override
    public boolean loginUser(String uname,String upassword,String authority) {
        boolean isTrue = false;
        try {
            isTrue = JdbcTemplate.executeQuery1("select * from tb_user where uname=? and upassword=? and authority=?",uname,upassword,authority);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isTrue;
    }

    class UserMap implements IObjectMapper<User>{
        @Override
        public User getObjectFromResultSet(ResultSet rs) throws SQLException {
            User user = new User();
            user.setU_id(rs.getInt("uno"));
            user.setU_name(rs.getString("uname"));
            user.setU_password(rs.getString("upassword"));
            user.setU_gender(rs.getString("sex"));
            user.setU_age(rs.getInt("age"));
            user.setU_phone(rs.getString("utel"));
            user.setU_address(rs.getString("addr"));
            user.setU_auth(rs.getString("authority"));
            return user;
        }
    }
}
