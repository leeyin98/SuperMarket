package com.leeyin98.ui;


import com.leeyin98.entity.Supplier;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.SupplierService;
import com.leeyin98.tmodel.SupplierTableModel;
import com.leeyin98.util.StringUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplierAddFrame extends JFrame {
    public SupplierAddFrame(JTable jt){
        this.setTitle("添加供应商信息");
        this.setSize(350, 450);
        //屏幕中央显示
        int x = (int)(this.getToolkit().getScreenSize().getWidth()-this.getWidth())/2;
        int y = (int)(this.getToolkit().getScreenSize().getHeight()-this.getHeight())/2;
            this.setLocation(x,y);

//        JPanel jp = new JPanel();
//            this.add(jp, BorderLayout.NORTH);
//        JLabel jlb_title = new JLabel("添加账单信息");
//            jp.add(jlb_title);

        JPanel jpContent = new JPanel();
            jpContent.setLayout(new GridLayout(7,2));

        JLabel jlbName = new JLabel("供应商名称: ");
        JTextField jtfName = new JTextField();
        JLabel jlbDescri = new JLabel("供应商描述：");
        JTextArea jtaDescri = new JTextArea();
        JLabel jlbContact = new JLabel("供应商联系人：");
        JTextField jtfContact = new JTextField();
        JLabel jlbPhone = new JLabel("供应商电话：");
        JTextField jtfPhone= new JTextField();
        JLabel jlbFaxes = new JLabel("供应商传真：");
        JTextField jtfFaxes= new JTextField();
        JLabel jlbAddr = new JLabel("供应商地址：");
        JTextField jtfAddr= new JTextField();
        JButton jtbn_add= new JButton("添加");
        JButton jtbn_close= new JButton("关闭");
        jpContent.add(jlbName);
        jpContent.add(jtfName);
        jpContent.add(jlbDescri);
        jpContent.add(jtaDescri);
        jpContent.add(jlbContact);
        jpContent.add(jtfContact);
        jpContent.add(jlbPhone);
        jpContent.add(jtfPhone);
        jpContent.add(jlbFaxes);
        jpContent.add(jtfFaxes);
        jpContent.add(jlbAddr);
        jpContent.add(jtfAddr);
        jpContent.add(jtbn_add);
        jpContent.add(jtbn_close);

            jtbn_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String supplierName = jtfName.getText().trim();
                if(StringUtil.isEmpty(supplierName)) {
                    JOptionPane.showMessageDialog(SupplierAddFrame.this, "对不起，供应商名称不能为空！");
                    return ;
                }
                String contactName = jtfContact.getText().trim();
                if(StringUtil.isEmpty(contactName)) {
                    JOptionPane.showMessageDialog(SupplierAddFrame.this, "对不起，供应商联系人不能为空！");
                    return ;
                }
                String phone = jtfPhone.getText().trim();
                if(StringUtil.isEmpty(phone)) {
                    JOptionPane.showMessageDialog(SupplierAddFrame.this, "对不起，供应商联系电话不能为空！");
                    return ;
                }else {
                    Pattern pattern = Pattern.compile("-?[0-9]+?[0-9]+");
                    Matcher isNum = pattern.matcher(phone);
                    if(!isNum.matches()){
                        JOptionPane.showMessageDialog(SupplierAddFrame.this, "对不起，供应商联系电话输入有误！");
                        return;
                    }
                }
                String faxes = jtfFaxes.getText().trim();
                if(!StringUtil.isEmpty(faxes)) {
                    Pattern pattern = Pattern.compile("[0-9]+?[0-9]+");
                    Matcher isNum = pattern.matcher(faxes);
                    if(!isNum.matches()){
                        JOptionPane.showMessageDialog(SupplierAddFrame.this, "对不起，供应商传真输入有误！");
                        return;
                    }
                }
                String address = jtfAddr.getText().trim();
                if(StringUtil.isEmpty(address)) {
                    JOptionPane.showMessageDialog(SupplierAddFrame.this, "对不起，供应商地址不能为空！");
                    return ;
                }


                String item1 = jtaDescri.getText();
                //将数据封装成Sport对象
                Supplier s = new Supplier(supplierName,item1,contactName,phone,faxes,address);

                SupplierService service = (SupplierService) ObjectFactory.getObject("SupplierService");


                if(service.insertSupplier(s)!=false) {
                    JOptionPane.showMessageDialog(SupplierAddFrame.this, "数据添加成功");
                    //希望操作一下jtable对象(刷新数据)
                    jt.setModel(new SupplierTableModel(1,12));
                    SupplierAddFrame.this.dispose();
                }else {
                    JOptionPane.showMessageDialog(SupplierAddFrame.this, "数据添加失败");
                    return;
                }
            }
        });
        jtbn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SupplierAddFrame.this.dispose();
            }
        });
            this.add(jpContent,BorderLayout.CENTER);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setVisible(true);
    }
}
