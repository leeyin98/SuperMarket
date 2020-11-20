package com.leeyin98.tmodel;

import com.leeyin98.dao.impl.BillDAOImpl;
import com.leeyin98.entity.Bill;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.BillService;
import javax.swing.table.AbstractTableModel;
import java.sql.SQLException;
import java.util.List;

public class BillTableModel extends AbstractTableModel {
    private BillService service = (BillService) ObjectFactory.getObject("BillService");
    private String[] titles=null;
    private Object[][] datas=new Object[0][0];

    public BillTableModel(){
        titles=new String[]{"账单编号","商品名称","商品数量","交易金额","是否付款","供应商名称","商品描述","账单时间"};
        List<Bill> list = service.listAll();
        loadDataToModel(list);
    }

    public BillTableModel(int currentPage,int pageSize){
        titles=new String[]{"账单编号","商品名称","商品数量","交易金额","是否付款","供应商名称","商品描述","账单时间"};
        List<Bill> list =service.listByPage(currentPage, pageSize);
        loadDataByPageToModel(list,currentPage,pageSize);
    }

    public BillTableModel(String name){
        titles=new String[]{"账单编号","商品名称","商品数量","交易金额","是否付款","供应商名称","商品描述","账单时间"};
        List<Bill> list =service.findBillByName(name);
        loadDataByNameToModel(list,name);
    }

    public BillTableModel(int isPay){
        titles=new String[]{"账单编号","商品名称","商品数量","交易金额","是否付款","供应商名称","商品描述","账单时间"};
        List<Bill> list =service.findBillByPaid(isPay);
        loadDataByPaidToModel(list,isPay);
    }

    public BillTableModel(String name,int isPay){
        titles=new String[]{"账单编号","商品名称","商品数量","交易金额","是否付款","供应商名称","商品描述","账单时间"};
        List<Bill> list =service.findBillByNameAndPaid(name,isPay);
        loadDataByNameAndPaidToModel(list,name,isPay);
    }

    public void loadDataByNameToModel(List<Bill> list,String name){
        try {
            list = new BillDAOImpl().findBillByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(Bill b :list) {
                datas[i][0]=b.getA_id();
                datas[i][1]=b.getA_name();
                datas[i][2]=b.getA_nums();
                datas[i][3]=b.getA_amount();
                datas[i][4]=b.getA_ispayed();
                datas[i][5]=b.getS_name();
                datas[i][6]=b.getA_Info();
                datas[i][7]=b.getA_Date();
                i++;
            }
        }
    }

    public void loadDataByPaidToModel(List<Bill> list,int isPay){
        try {
            list = new BillDAOImpl().findBillByPaid(isPay);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(Bill b :list) {
                datas[i][0]=b.getA_id();
                datas[i][1]=b.getA_name();
                datas[i][2]=b.getA_nums();
                datas[i][3]=b.getA_amount();
                datas[i][4]=b.getA_ispayed();
                datas[i][5]=b.getS_name();
                datas[i][6]=b.getA_Info();
                datas[i][7]=b.getA_Date();
                i++;
            }
        }
    }

    public void loadDataByNameAndPaidToModel(List<Bill> list,String name,int isPay){
        try {
            list = new BillDAOImpl().findBillByNameAndPaid(name,isPay);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(Bill b :list) {
                datas[i][0]=b.getA_id();
                datas[i][1]=b.getA_name();
                datas[i][2]=b.getA_nums();
                datas[i][3]=b.getA_amount();
                datas[i][4]=b.getA_ispayed();
                datas[i][5]=b.getS_name();
                datas[i][6]=b.getA_Info();
                datas[i][7]=b.getA_Date();
                i++;
            }
        }
    }

    public void loadDataByPageToModel(List<Bill> list,int currentPage,int pageSize){
        try {
            list = new BillDAOImpl().listByPage(currentPage,pageSize);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(Bill b :list) {
                datas[i][0]=b.getA_id();
                datas[i][1]=b.getA_name();
                datas[i][2]=b.getA_nums();
                datas[i][3]=b.getA_amount();
                datas[i][4]=b.getA_ispayed();
                datas[i][5]=b.getS_name();
                datas[i][6]=b.getA_Info();
                datas[i][7]=b.getA_Date();
                i++;
            }
        }
    }

    public void loadDataToModel(List<Bill> list){
        try {
            list = new BillDAOImpl().listAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(list.size()>0&&list!=null) {
            datas = new Object[list.size()][titles.length];
            int i = 0 ;
            for(Bill b :list) {
                datas[i][0]=b.getA_id();
                datas[i][1]=b.getA_name();
                datas[i][2]=b.getA_nums();
                datas[i][3]=b.getA_amount();
                datas[i][4]=b.getA_ispayed();
                datas[i][5]=b.getS_name();
                datas[i][6]=b.getA_Info();
                datas[i][7]=b.getA_Date();
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
