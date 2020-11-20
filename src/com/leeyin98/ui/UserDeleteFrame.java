package com.leeyin98.ui;

import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.SupplierService;
import com.leeyin98.service.UserService;
import com.leeyin98.tmodel.SupplierTableModel;
import com.leeyin98.tmodel.UserTableModel;

import javax.swing.*;

public class UserDeleteFrame {
//    public UserDeleteFrame(JTable jt, int rowNum){
//        UserService service = (UserService) ObjectFactory.getObject("UserService");
//        rowNum = jt.getSelectedRow();
//        int userID = (int)jt.getValueAt(rowNum, 0);
//        boolean isTrue = service.deleteUserById(userID);
//        jt.setModel(new UserTableModel());
//    }
    public UserDeleteFrame(JTable jt, int rowNum){
        UserService service = (UserService) ObjectFactory.getObject("UserService");
        int[] rowCount = jt.getSelectedRows();
        for (int i =0;i<rowNum;i++){
            int userID = (int)jt.getValueAt(rowCount[i], 0);
            boolean isTrue = service.deleteUserById(userID);
        }
        jt.setModel(new UserTableModel());
    }
}
