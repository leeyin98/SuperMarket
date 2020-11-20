package com.leeyin98.ui;

import com.leeyin98.entity.User;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.UserService;
import com.leeyin98.tmodel.UserTableModel;
import com.leeyin98.util.StringUtil;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserUpdateFrame extends JFrame{
    public UserUpdateFrame(JTable jt, int rowNum){
        UserService service = (UserService) ObjectFactory.getObject("UserService");
        rowNum = jt.getSelectedRow();
        int selectNo = (int)jt.getValueAt(rowNum, 0);
        List<User> list = service.findUserByID(selectNo);
        User u = new User();
        for (Object o:list){
            u=(User) o;
        }
        this.setTitle("修改账单信息");
        this.setSize(350, 450);
        //屏幕中央显示
        int x = (int)(this.getToolkit().getScreenSize().getWidth()-this.getWidth())/2;
        int y = (int)(this.getToolkit().getScreenSize().getHeight()-this.getHeight())/2;
        this.setLocation(x,y);


        JPanel jpContent = new JPanel();
        jpContent.setLayout(new GridLayout(9,2));

        JLabel jlbName = new JLabel("用户名称: ");
        JTextField jtfName = new JTextField(u.getU_name());
        JLabel jlbPassword = new JLabel("用户密码：");
        JPasswordField jtfPassword = new JPasswordField(u.getU_password());
        JLabel jlbPasswordTrue = new JLabel("确认密码：");
        JPasswordField jtfPasswordTrue = new JPasswordField(u.getU_password());
        JLabel jlbSex = new JLabel("用户性别：");
        JComboBox sexBox = new JComboBox();
        sexBox.addItem("男");
        sexBox.addItem("女");
        sexBox.setSelectedItem(u.getU_gender());
        JLabel jlbAge = new JLabel("用户年龄：");
        JTextField jtfAge= new JTextField(""+u.getU_age());
        JLabel jlbPhone = new JLabel("用户电话：");
        JTextField jtfPhone= new JTextField(u.getU_phone());
        JLabel jlbAddress = new JLabel("用户地址：");
        JTextArea jtfAddress= new JTextArea(u.getU_address());
        JLabel jlbAuth = new JLabel("用户权限：");
        JComboBox authBox = new JComboBox();
        authBox.addItem("普通用户");
        authBox.addItem("部门经理");
        authBox.setSelectedItem(u.getU_auth());
        JButton jtbn_update= new JButton("修改");
        JButton jtbn_close= new JButton("关闭");
        jpContent.add(jlbName);
        jpContent.add(jtfName);
        jpContent.add(jlbPassword);
        jpContent.add(jtfPassword);
        jpContent.add(jlbPasswordTrue);
        jpContent.add(jtfPasswordTrue);
        jpContent.add(jlbSex);
        jpContent.add(sexBox);
        jpContent.add(jlbAge);
        jpContent.add(jtfAge);
        jpContent.add(jlbPhone);
        jpContent.add(jtfPhone);
        jpContent.add(jlbAddress);
        jpContent.add(jtfAddress);
        jpContent.add(jlbAuth);
        jpContent.add(authBox);
        jpContent.add(jtbn_update);
        jpContent.add(jtbn_close);

        jtbn_update.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String supplierName = jtfName.getText().trim();
                    if(StringUtil.isEmpty(supplierName)) {
                        JOptionPane.showMessageDialog(UserUpdateFrame.this, "对不起，用户名称不能为空！");
                        return ;
                    }
                    String password = jtfPasswordTrue.getText().trim();
                    if(StringUtil.isEmpty(password)) {
                        JOptionPane.showMessageDialog(UserUpdateFrame.this, "对不起，用户密码不能为空！");
                        return ;
                    }
                    String passwordTrue = jtfPasswordTrue.getText().trim();
                    if(StringUtil.isEmpty(passwordTrue)) {
                        JOptionPane.showMessageDialog(UserUpdateFrame.this, "对不起，确认密码不能为空！");
                        return ;
                    }
                    if(!password.equals(passwordTrue)){
                        JOptionPane.showMessageDialog(UserUpdateFrame.this, "对不起，两次密码输入不同！");
                        return;
                    }

                    String phone = jtfPhone.getText().trim();
                    if(StringUtil.isEmpty(phone)) {
                        JOptionPane.showMessageDialog(UserUpdateFrame.this, "对不起，用户电话不能为空！");
                        return ;
                    }else {
                        Pattern pattern = Pattern.compile("-?[0-9]+?[0-9]+");
                        Matcher isNum = pattern.matcher(phone);
                        if(!isNum.matches()){
                            JOptionPane.showMessageDialog(UserUpdateFrame.this, "对不起，用户电话输入有误！");
                            return;
                        }
                    }


                    String ageStr = jtfAge.getText().trim();
                    int age =0;
                    if(StringUtil.isEmpty(ageStr)){
                        JOptionPane.showMessageDialog(UserUpdateFrame.this, "对不起，用户年龄不能为空！");
                        return;
                    }else {
                        Pattern pattern = Pattern.compile("[0-9]*");
                        Matcher isNum = pattern.matcher(ageStr);
                        if(!isNum.matches()){
                            JOptionPane.showMessageDialog(UserUpdateFrame.this, "对不起，年龄输入有误！");
                            return;
                        }else {
                            age = Integer.parseInt(ageStr);
                        }
                    }

                    String address = jtfAddress.getText().trim();
                    String sex = (String) sexBox.getSelectedItem();
                    String auth = (String)authBox.getSelectedItem();
                    //将数据封装成Sport对象
                    User u = new User(supplierName,password,sex,age,phone,address,auth);

                    UserService service = (UserService) ObjectFactory.getObject("UserService");


                    if(service.updateUser(u,selectNo)!=false) {
                        JOptionPane.showMessageDialog(UserUpdateFrame.this, "数据修改成功");
                        //希望操作一下jtable对象(刷新数据)
                        jt.setModel(new UserTableModel(1,12));
                        UserUpdateFrame.this.dispose();
                    }else {
                        JOptionPane.showMessageDialog(UserUpdateFrame.this, "数据修改失败");
                        return;
                    }
                }
        });
        jtbn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserUpdateFrame.this.dispose();
            }
        });

        this.add(jpContent,BorderLayout.CENTER);
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }
}
