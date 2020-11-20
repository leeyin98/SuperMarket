package com.leeyin98.tmodel;

import com.leeyin98.dao.impl.BillDAOImpl;
import com.leeyin98.entity.Bill;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.BillService;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class GroupByGoodTableModel extends AbstractTableModel {
    private BillService service = (BillService) ObjectFactory.getObject("BillService");
    private String[] titles=null;
    private Object[][] datas=new Object[0][0];

    public GroupByGoodTableModel(){
        titles=new String[]{"商品名称","交易数量","交易金额","供应商名称"};
        List<Bill> list = service.groupByGood();
        loadDataToModel(list);
    }

    public GroupByGoodTableModel(int currentPage,int pageSize){
        titles=new String[]{"商品名称","交易数量","交易金额","供应商名称"};
        List<Bill> list = service.groupByGood(currentPage,pageSize);
        loadDataToModel(list,currentPage,pageSize);
    }

    public void loadDataToModel(List<Bill> list,int currentPage,int pageSize){
        try {
            list = new BillDAOImpl().groupByGood(currentPage,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(Bill b :list) {
                datas[i][0]=b.getA_name();
                datas[i][1]=b.getA_nums();
                datas[i][2]=b.getA_amount();
                datas[i][3]=b.getS_name();
                i++;
            }
        }
    }

    public void loadDataToModel(List<Bill> list){
        try {
            list = new BillDAOImpl().groupByGood();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(Bill b :list) {
                datas[i][0]=b.getA_name();
                datas[i][1]=b.getA_nums();
                datas[i][2]=b.getA_amount();
                datas[i][3]=b.getS_name();
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
