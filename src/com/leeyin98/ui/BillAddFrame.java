package com.leeyin98.ui;

import com.leeyin98.entity.Bill;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.BillService;
import com.leeyin98.tmodel.BillTableModel;
import com.leeyin98.util.ComboBoxUtil;
import com.leeyin98.util.StringUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BillAddFrame extends JFrame {
    public BillAddFrame(JTable jt){
        this.setTitle("添加账单信息");
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
            jpContent.setLayout(new GridLayout(9,2));

        JLabel jlbName = new JLabel("商品名称: ");
        JTextField jtfName = new JTextField();
        JLabel jlbNum = new JLabel("交易数量：");
        JTextField jtfNum = new JTextField();
        JLabel jlbUnit = new JLabel("交易单位：");
        JTextField jtfUnit= new JTextField();
        JLabel jlbMoney = new JLabel("交易金额：");
        JTextField jtfMoney= new JTextField();
        JLabel jlbIsPay = new JLabel("是否付款：");
        JComboBox jcbIsPay = new JComboBox();
        jcbIsPay.addItem("是");
        jcbIsPay.addItem("否");
        JLabel jlbSupplier = new JLabel("所属供应商：");
        JComboBox jcbSupplier = new ComboBoxUtil().getComboBox();
        JLabel jlbDescri = new JLabel("商品描述：");
        JTextArea jtaDescri = new JTextArea();
        JLabel jlbTime = new JLabel("交易时间：");
        JTextField jtfTime= new JTextField();
        JButton jtbn_add= new JButton("添加");
        JButton jtbn_close= new JButton("关闭");
            jpContent.add(jlbName);
            jpContent.add(jtfName);
            jpContent.add(jlbNum);
            jpContent.add(jtfNum);
            jpContent.add(jlbUnit);
            jpContent.add(jtfUnit);
            jpContent.add(jlbMoney);
            jpContent.add(jtfMoney);
            jpContent.add(jlbIsPay);
            jpContent.add(jcbIsPay);
            jpContent.add(jlbSupplier);
            jpContent.add(jcbSupplier);
            jpContent.add(jlbDescri);
            jpContent.add(jtaDescri);
            jpContent.add(jlbTime);
            jpContent.add(jtfTime);
            jpContent.add(jtbn_add);
            jpContent.add(jtbn_close);

            jtbn_add.addActionListener(new ActionListener() {
                int num = 0;
                double money = 0.0;
                Date intime=null;
            @Override
            public void actionPerformed(ActionEvent e) {
                String goodName = jtfName.getText().trim();
                if(StringUtil.isEmpty(goodName)) {
                    JOptionPane.showMessageDialog(BillAddFrame.this, "对不起，商品名称不能为空！");
                    return ;
                }
                String goodNum = jtfNum.getText().trim();
                if(StringUtil.isEmpty(goodNum)) {
                    JOptionPane.showMessageDialog(BillAddFrame.this, "对不起，交易数量不能为空！");
                    return ;
                }else {
                    Pattern pattern = Pattern.compile("[0-9]*");
                    Matcher isNum = pattern.matcher(goodNum);
                    if(!isNum.matches()){
                        JOptionPane.showMessageDialog(BillAddFrame.this, "对不起，交易金额应为数值类型！");
                        return;
                    }else {
                        num = Integer.parseInt(goodNum);
                    }
                }
                String goodUnit = jtfUnit.getText().trim();
                if(StringUtil.isEmpty(goodUnit)) {
                    JOptionPane.showMessageDialog(BillAddFrame.this, "对不起，交易单位不能为空！");
                    return ;
                }
                String payMoney = jtfMoney.getText().trim();
                if(StringUtil.isEmpty(payMoney)) {
                    JOptionPane.showMessageDialog(BillAddFrame.this, "对不起，交易金额不能为空！");
                    return ;
                }else {
                    Pattern pattern = Pattern.compile("[0-9]+.?[0-9]+");
                    Matcher isNum = pattern.matcher(payMoney);
                    if(!isNum.matches()){
                        JOptionPane.showMessageDialog(BillAddFrame.this, "对不起，交易金额应为数值类型！");
                        return;
                    }else {
                        money = Double.parseDouble(payMoney);
                    }
                }
                String payTime = jtfTime.getText().trim();
                if(StringUtil.isEmpty(payTime)) {
                    JOptionPane.showMessageDialog(BillAddFrame.this, "对不起，交易时间不能为空！");
                    return ;
                }else {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        intime = sdf.parse(payTime);
                    } catch (ParseException e1) {
                        e1.printStackTrace();
                        JOptionPane.showMessageDialog(BillAddFrame.this, "对不起日期格式不正确");
                        return ;
                    }
                }


                String item1 = (String) jcbIsPay.getSelectedItem();
                String item2 = (String) jcbSupplier.getSelectedItem();
                String item3 = jtaDescri.getText();
                //将数据封装成Sport对象
                Bill b = new Bill(goodName,num,money,goodUnit,item1,item2,item3,intime);

                BillService service = (BillService) ObjectFactory.getObject("BillService");


                if(service.insertBill(b)!=false) {
                    JOptionPane.showMessageDialog(BillAddFrame.this, "数据添加成功");
                    //希望操作一下jtable对象(刷新数据)
                    jt.setModel(new BillTableModel(1,12));
                    BillAddFrame.this.dispose();
                }else {
                    JOptionPane.showMessageDialog(BillAddFrame.this, "数据添加失败");
                    return;
                }
            }
        });
        jtbn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BillAddFrame.this.dispose();
            }
        });
            this.add(jpContent,BorderLayout.CENTER);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setVisible(true);
    }
}
