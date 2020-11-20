package com.leeyin98.tmodel;

import com.leeyin98.dao.impl.SupplierDAOImpl;
import com.leeyin98.entity.Supplier;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.SupplierService;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class GroupBySupplierTableModel extends AbstractTableModel {
    private SupplierService service = (SupplierService) ObjectFactory.getObject("SupplierService");
    private String[] titles=null;
    private Object[][] datas=new Object[0][0];

    public GroupBySupplierTableModel() {
        titles = new String[]{"供应商编号", "供应商名称", "总交易金额", "商品种类", "商品总数量"};
        List<Supplier> list = service.groupBySupplier();
        loadDataToModel(list);
    }

    public GroupBySupplierTableModel(int currentPage,int pageSize){
        titles=new String[]{"供应商编号","供应商名称","总交易金额","商品种类","商品总数量"};
        List<Supplier> list = service.groupBySupplier(currentPage,pageSize);
        loadDataToModel(list,currentPage,pageSize);
    }

    public void loadDataToModel(List<Supplier> list,int currentPage,int pageSize){
        try {
            list = new SupplierDAOImpl().groupBySupplier(currentPage,pageSize);
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
                i++;
            }
        }
    }

        public void loadDataToModel(List<Supplier> list){
            try {
                list = new SupplierDAOImpl().groupBySupplier();
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
