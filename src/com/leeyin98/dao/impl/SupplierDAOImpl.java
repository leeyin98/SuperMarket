package com.leeyin98.dao.impl;

import com.leeyin98.dao.IObjectMapper;
import com.leeyin98.dao.SupplierDAO;
import com.leeyin98.entity.Supplier;
import com.leeyin98.util.JdbcTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class SupplierDAOImpl implements SupplierDAO {
    @Override
    public List<Supplier> listAll() throws SQLException {
        return JdbcTemplate.executeQuery("select sno,sname,sdescription,contact,phonenum,faxes,address from tb_supplier",new supplierObjectMap(),null);
    }

    @Override
    public boolean deleteSupplierById(int sid) throws SQLException {
        boolean isTrue = false;
        int i = JdbcTemplate.executeUpdate("delete from tb_supplier where sno=?", sid);
        if (i>0){
            isTrue = true;
        }
        return isTrue;
    }

    @Override
    public boolean insertSupplier(Supplier s) throws SQLException {
        boolean isTrue = false;
        int i = JdbcTemplate.executeUpdate("INSERT INTO tb_supplier (sname,sdescription,contact,phonenum,faxes,address) VALUES ( ?,?, ?, ?, ?, ?)",s.getS_name(),s.getS_info(),s.getS_linkman(),s.getS_phone(),s.getS_faxes(),s.getS_address());
        if (i>0){
            isTrue = true;
        }
        return isTrue;
    }

    @Override
    public boolean updateSupplier(Supplier s,int sNo) throws SQLException {
        boolean isTrue = false;
        int i = JdbcTemplate.executeUpdate("update tb_supplier set sname=?,sdescription=?,contact=?,phonenum=?,faxes=?,address=? where sno=?", s.getS_name(),s.getS_info(),s.getS_linkman(),s.getS_phone(),s.getS_faxes(),s.getS_address(),sNo);
        if (i>0){
            isTrue = true;
        }
        return isTrue;
    }

    @Override
    public List<Supplier> findSupplierByName(String sName) throws SQLException {
        return JdbcTemplate.executeQuery("select sno,sname,sdescription,contact,phonenum,faxes,address from tb_supplier where sname=? ", new supplierObjectMap(), sName);
    }

    @Override
    public List<Supplier> findBillByID(int sid) throws SQLException {
        return JdbcTemplate.executeQuery("select sno,sname,sdescription,contact,phonenum,faxes,address from tb_supplier where sno=?", new supplierObjectMap(), sid);

    }

    @Override
    public List<Supplier> listByPage(int currentPage, int pageSize) throws SQLException {
        return JdbcTemplate.executeQuery("select sno,sname,sdescription,contact,phonenum,faxes,address from tb_supplier order by sno limit ?,?",new supplierObjectMap(),(currentPage-1)*pageSize,pageSize);

    }

    @Override
    public List<Supplier> groupBySupplier(int currentPage, int pageSize) throws SQLException {
        return JdbcTemplate.executeQuery(" select sno , sname , b.totalmoney , type , b.totalnum from ( select count(bname) type , supplier , sum(bmoney) totalmoney , sum(bnum) totalnum from tb_bill group by supplier ) as b,tb_supplier where b.supplier = sname group by sname limit ?,? ",
                new groupBySupplierObjectMap(),currentPage,pageSize);
    }

    @Override
    public List<Supplier> groupBySupplier() throws SQLException {
        return JdbcTemplate.executeQuery(" select sno , sname , b.totalmoney , type , b.totalnum from(select count(bname) type , supplier , sum(bmoney) totalmoney , sum(bnum) totalnum from tb_bill group by supplier ) as b,tb_supplier where b.supplier = sname group by sname ",
                new groupBySupplierObjectMap(),null);
    }

    class supplierObjectMap implements IObjectMapper<Supplier> {
        @Override
        public Supplier getObjectFromResultSet(ResultSet rs) throws SQLException {
            Supplier s = new Supplier();
            s.setS_id(rs.getInt("sno"));
            s.setS_name(rs.getString("sname"));
            s.setS_info(rs.getString("sdescription"));
            s.setS_linkman(rs.getString("contact"));
            s.setS_phone(rs.getString("phonenum"));
            s.setS_address(rs.getString("address"));
            return s;
        }
    }

    class groupBySupplierObjectMap implements IObjectMapper<Supplier> {
        @Override
        public Supplier getObjectFromResultSet(ResultSet rs) throws SQLException {
            Supplier s = new Supplier();
            s.setS_id(rs.getInt("sno"));
            s.setS_name(rs.getString("sname"));
            s.setS_info(rs.getString("b.totalmoney"));
            s.setS_linkman(rs.getString("type"));
            s.setS_phone(rs.getString("b.totalnum"));
            return s;
        }
    }
}
