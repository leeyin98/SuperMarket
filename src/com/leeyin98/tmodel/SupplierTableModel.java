package com.leeyin98.tmodel;

import com.leeyin98.dao.impl.SupplierDAOImpl;
import com.leeyin98.entity.Supplier;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.SupplierService;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class SupplierTableModel extends AbstractTableModel {
    private SupplierService service = (SupplierService) ObjectFactory.getObject("SupplierService");
    private String[] titles=null;
    private Object[][] datas=new Object[0][0];

    public SupplierTableModel(){
        titles=new String[]{"编号","供应商名称","供应商描述","联系人","电话","地址"};
        List<Supplier> list = service.listAll();
        loadDataToModel(list);
    }

    public SupplierTableModel(int currentPage,int pageSize){
        titles=new String[]{"编号","供应商名称","供应商描述","联系人","电话","地址"};
        List<Supplier> list =service.listByPage(currentPage, pageSize);
        loadDataByPageToModel(list,currentPage,pageSize);
    }

    public SupplierTableModel(String name){
        titles=new String[]{"编号","供应商名称","供应商描述","联系人","电话","地址"};
        List<Supplier> list =service.findSupplierByName(name);
        loadDataByNameToModel(list,name);
    }

    public void loadDataToModel(List<Supplier> list){
        try {
            list = new SupplierDAOImpl().listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(Supplier s :list) {
                datas[i][0]=s.getS_id();
                datas[i][1]=s.getS_name();
                datas[i][2]=s.getS_info();
                datas[i][3]=s.getS_linkman();
                datas[i][4]=s.getS_phone();
                datas[i][5]=s.getS_address();
                i++;
            }
        }
    }

    public void loadDataByNameToModel(List<Supplier> list,String name){
        try {
            list = new SupplierDAOImpl().findSupplierByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(Supplier s :list) {
                datas[i][0]=s.getS_id();
                datas[i][1]=s.getS_name();
                datas[i][2]=s.getS_info();
                datas[i][3]=s.getS_linkman();
                datas[i][4]=s.getS_phone();
                datas[i][5]=s.getS_address();
                i++;
            }
        }
    }

    public void loadDataByPageToModel(List<Supplier> list,int currentPage,int pageSize){
        try {
            list = new SupplierDAOImpl().listByPage(currentPage,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(Supplier s :list) {
                datas[i][0]=s.getS_id();
                datas[i][1]=s.getS_name();
                datas[i][2]=s.getS_info();
                datas[i][3]=s.getS_linkman();
                datas[i][4]=s.getS_phone();
                datas[i][5]=s.getS_address();
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
