package com.leeyin98.ui;

import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.SupplierService;
import com.leeyin98.tmodel.SupplierTableModel;
import javax.swing.*;

public class SupplierDeleteFrame {
//    public SupplierDeleteFrame(JTable jt, int rowNum){
//        SupplierService service = (SupplierService) ObjectFactory.getObject("SupplierService");
//        rowNum = jt.getSelectedRow();
//        int supplierID = (int)jt.getValueAt(rowNum, 0);
//        boolean isTrue = service.deleteSupplierById(supplierID);
//        jt.setModel(new SupplierTableModel());
//    }
    public SupplierDeleteFrame(JTable jt, int rowNum){
        SupplierService service = (SupplierService) ObjectFactory.getObject("SupplierService");
        int[] rowCount = jt.getSelectedRows();
        for (int i =0;i<rowNum;i++){
            int supplierID = (int)jt.getValueAt(rowCount[i], 0);
            boolean isTrue = service.deleteSupplierById(supplierID);
        }
        jt.setModel(new SupplierTableModel());
    }
}
