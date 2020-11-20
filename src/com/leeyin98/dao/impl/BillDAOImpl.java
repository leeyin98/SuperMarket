package com.leeyin98.dao.impl;

import com.leeyin98.dao.BillDAO;
import com.leeyin98.dao.IObjectMapper;
import com.leeyin98.entity.Bill;
import com.leeyin98.entity.Supplier;
import com.leeyin98.util.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class BillDAOImpl implements BillDAO {
    @Override
    public List<Bill> listAll() throws SQLException{
        return JdbcTemplate.executeQuery("select bno,bname,bnum,bmoney,bunit,ispay,supplier,description,btime from tb_bill",new billObjectMap(),null);
    }

    @Override
    public boolean deleteBillById(int bno) throws SQLException {
        boolean isTrue = false;
        int i = JdbcTemplate.executeUpdate("delete from tb_bill where bno=?", bno);
        if (i>0){
            isTrue = true;
        }
        return isTrue;
    }

    @Override
    public boolean insertBill(Bill b) throws SQLException {
        boolean isTrue = false;
        int i = JdbcTemplate.executeUpdate("INSERT INTO tb_bill (bname,bnum,bmoney,bunit,ispay,supplier,description,btime) VALUES ( ?,?, ?, ?, ?, ?,?,?)",b.getA_name(),b.getA_nums(),b.getA_amount(),b.getA_unit(),b.getA_ispayed(),b.getS_name(),b.getA_Info(),b.getA_Date());
        if (i>0){
            isTrue = true;
        }
        return isTrue;
    }

    @Override
    public boolean updateBill(Bill b,int bno) throws SQLException {
        boolean isTrue = false;
        int i = JdbcTemplate.executeUpdate("update tb_bill set bname=?,bnum=?,bmoney=?,bunit=?,ispay=?,supplier=?,description=?,btime=? where bno=?", b.getA_name(),b.getA_nums(),b.getA_amount(),b.getA_unit(),b.getA_ispayed(),b.getS_name(),b.getA_Info(),b.getA_Date(),bno);
        if (i>0){
            isTrue = true;
        }
        return isTrue;
    }

    @Override
    public List<Bill> findBillByNameAndPaid(String bName, int payID) throws SQLException {
        String isPay;
        if (payID==0){
            isPay = "否";
        }else {
            isPay = "是";
        }
        return JdbcTemplate.executeQuery("select bno,bname,bnum,bmoney,bunit,ispay,supplier,description,btime from tb_bill where bname=? and ispay=?", new billObjectMap(), bName,isPay);
    }

    @Override
    public List<Bill> findBillByName(String bName) throws SQLException {
        return JdbcTemplate.executeQuery("select bno,bname,bnum,bmoney,bunit,ispay,supplier,description,btime from tb_bill where bname=? ", new billObjectMap(), bName);
    }

    @Override
    public List<Bill> findBillByPaid(int payID) throws SQLException {
        String isPay;
        if (payID==0){
            isPay = "否";
        }else {
            isPay = "是";
        }
        return JdbcTemplate.executeQuery("select bno,bname,bnum,bmoney,bunit,ispay,supplier,description,btime from tb_bill where ispay=?", new billObjectMap(), isPay);
    }

    @Override
    public List<Bill> findBillByID(int bid) throws SQLException {
        return JdbcTemplate.executeQuery("select bno,bname,bnum,bmoney,bunit,ispay,supplier,description,btime from tb_bill where bno=?", new billObjectMap(), bid);

    }

    @Override
    public List<Bill> listByPage(int currentPage, int pageSize) throws SQLException {
        return JdbcTemplate.executeQuery("select bno,bname,bnum,bmoney,bunit,ispay,supplier,description,btime from tb_bill order by bno limit ?,?",new billObjectMap(),(currentPage-1)*pageSize,pageSize);
    }

    @Override
    public List<Supplier> listSupplier() throws SQLException {
        return JdbcTemplate.executeQuery("select sno,sname,sdescription,contact,phonenum,address from tb_supplier", new IObjectMapper<Supplier>() {
            @Override
            public Supplier getObjectFromResultSet(ResultSet rs)
                    throws SQLException {
                Supplier s = new Supplier();
                s.setS_id(rs.getInt("sno"));
                s.setS_name(rs.getString("sname"));
                s.setS_info(rs.getString("sdescription"));
                s.setS_linkman(rs.getString("contact"));
                s.setS_phone(rs.getString("phonenum"));
                s.setS_address(rs.getString("address"));
                return s;
            }
        }, null);
    }

    @Override
    public List<Bill> groupByGood(int currentPage,int pageSize) throws SQLException {
        return JdbcTemplate.executeQuery("select bname,SUM(bnum) bnum,SUM(bmoney) bmoney,supplier from tb_bill group by bname limit ?,?",new groupByGoodObjectMap(),(currentPage-1)*pageSize,pageSize);
    }

    @Override
    public List<Bill> groupByGood() throws SQLException {
        return JdbcTemplate.executeQuery("select bname,SUM(bnum) bnum,SUM(bmoney) bmoney,supplier from tb_bill group by bname",new groupByGoodObjectMap(),null);
    }


    class billObjectMap implements IObjectMapper<Bill> {
        @Override
        public Bill getObjectFromResultSet(ResultSet rs) throws SQLException {
            Bill b = new Bill();
            b.setA_id(rs.getInt("bno"));
            b.setA_name(rs.getString("bname"));
            b.setA_nums(rs.getInt("bnum"));
            b.setA_amount(rs.getInt("bmoney"));
            b.setA_unit(rs.getString("bunit"));
            b.setA_ispayed(rs.getString("ispay"));
            b.setS_name(rs.getString("supplier"));
            b.setA_Info(rs.getString("description"));
            b.setA_Date(rs.getDate("btime"));
            return b;
        }
    }

    class groupByGoodObjectMap implements IObjectMapper<Bill> {
        @Override
        public Bill getObjectFromResultSet(ResultSet rs) throws SQLException {
            Bill b = new Bill();
            b.setA_name(rs.getString("bname"));
            b.setA_nums(rs.getInt("bnum"));
            b.setA_amount(rs.getInt("bmoney"));
            b.setS_name(rs.getString("supplier"));
            return b;
        }
    }
}
