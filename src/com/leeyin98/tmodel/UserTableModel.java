package com.leeyin98.tmodel;

import com.leeyin98.dao.impl.UserDAOImpl;
import com.leeyin98.entity.User;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.UserService;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class UserTableModel extends AbstractTableModel {
    private UserService service = (UserService) ObjectFactory.getObject("UserService");
    private String[] titles=null;
    private Object[][] datas=new Object[0][0];

    public UserTableModel(){
        titles=new String[]{"编号","姓名","性别","年龄","电话","地址","权限"};
        List<User> list = service.listAll();
        loadDataToModel(list);
    }

    public UserTableModel(int currentPage,int pageSize){
        titles=new String[]{"编号","姓名","性别","年龄","电话","地址","权限"};
        List<User> list =service.listByPage(currentPage, pageSize);
        loadDataByPageToModel(list,currentPage,pageSize);
    }

    public UserTableModel(String name){
        titles=new String[]{"编号","姓名","性别","年龄","电话","地址","权限"};
        List<User> list =service.findUserByName(name);
        loadDataByNameToModel(list,name);
    }

    public void loadDataToModel(List<User> list){
        try {
            list = new UserDAOImpl().listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(User u :list) {
                datas[i][0]=u.getU_id();
                datas[i][1]=u.getU_name();
                datas[i][2]=u.getU_gender();
                datas[i][3]=u.getU_age();
                datas[i][4]=u.getU_phone();
                datas[i][5]=u.getU_address();
                datas[i][6]=u.getU_auth();
                i++;
            }
        }
    }

    public void loadDataByNameToModel(List<User> list,String name){
        try {
            list = new UserDAOImpl().findUserByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(User u :list) {
                datas[i][0]=u.getU_id();
                datas[i][1]=u.getU_name();
                datas[i][2]=u.getU_gender();
                datas[i][3]=u.getU_age();
                datas[i][4]=u.getU_phone();
                datas[i][5]=u.getU_address();
                datas[i][6]=u.getU_auth();
                i++;
            }
        }
    }

    public void loadDataByPageToModel(List<User> list,int currentPage,int pageSize){
        try {
            list = new UserDAOImpl().listByPage(currentPage,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(User u :list) {
                datas[i][0]=u.getU_id();
                datas[i][1]=u.getU_name();
                datas[i][2]=u.getU_gender();
                datas[i][3]=u.getU_age();
                datas[i][4]=u.getU_phone();
                datas[i][5]=u.getU_address();
                datas[i][6]=u.getU_auth();
                i++;
            }
        }
    }
    @Override
    public int getRowCount() {
        return datas.length;
    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return datas[rowIndex][columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return titles[column];
    }
}
