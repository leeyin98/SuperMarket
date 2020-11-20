package com.leeyin98.ui;

import com.leeyin98.entity.Bill;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.BillService;
import com.leeyin98.tmodel.BillTableModel;

import javax.swing.*;

public class BillDeleteFrame {
//    public BillDeleteFrame(JTable jt, int rowNum){
//        BillService service = (BillService) ObjectFactory.getObject("BillService");
//        rowNum = jt.getSelectedRow();
//        int goodNum = (int)jt.getValueAt(rowNum, 0);
//        boolean isTrue = service.deleteBillById(goodNum);
//        jt.setModel(new BillTableModel());
//    }

    public BillDeleteFrame(JTable jt,int rowNum){
        BillService service = (BillService) ObjectFactory.getObject("BillService");
        int[] rowCount = jt.getSelectedRows();
        for (int i =0;i<rowNum;i++){
            int goodID = (int)jt.getValueAt(rowCount[i], 0);
            boolean isTrue = service.deleteBillById(goodID);
        }
        jt.setModel(new BillTableModel());
    }
}
