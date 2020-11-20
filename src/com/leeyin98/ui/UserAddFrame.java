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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserAddFrame extends JFrame {
    public UserAddFrame(JTable jt){
        this.setTitle("添加用户信息");
        this.setSize(350, 450);
        //屏幕中央显示
        int x = (int)(this.getToolkit().getScreenSize().getWidth()-this.getWidth())/2;
        int y = (int)(this.getToolkit().getScreenSize().getHeight()-this.getHeight())/2;
            this.setLocation(x,y);

        JPanel jpContent = new JPanel();
            jpContent.setLayout(new GridLayout(9,2));

        JLabel jlbName = new JLabel("用户名称: ");
        JTextField jtfName = new JTextField();
        JLabel jlbPassword = new JLabel("用户密码：");
        JPasswordField jtfPassword = new JPasswordField();
        JLabel jlbPasswordTrue = new JLabel("确认密码：");
        JPasswordField jtfPasswordTrue = new JPasswordField();
        JLabel jlbSex = new JLabel("用户性别：");
        JComboBox sexBox = new JComboBox();
        sexBox.addItem("男");
        sexBox.addItem("女");
        JLabel jlbAge = new JLabel("用户年龄：");
        JTextField jtfAge= new JTextField();
        JLabel jlbPhone = new JLabel("用户电话：");
        JTextField jtfPhone= new JTextField();
        JLabel jlbAddress = new JLabel("用户地址：");
        JTextArea jtfAddress= new JTextArea();
        JLabel jlbAuth = new JLabel("用户权限：");
        JComboBox authBox = new JComboBox();
        authBox.addItem("普通用户");
        authBox.addItem("部门经理");
        JButton jtbn_add= new JButton("添加");
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
        jpContent.add(jtbn_add);
        jpContent.add(jtbn_close);

            jtbn_add.addActionListener(new ActionListener() {
                int age = 0;
            @Override
            public void actionPerformed(ActionEvent e) {
                String userName = jtfName.getText().trim();
                if(StringUtil.isEmpty(userName)) {
                    JOptionPane.showMessageDialog(UserAddFrame.this, "对不起，用户名称不能为空！");
                    return ;
                }
                String password = jtfPassword.getText().trim();
                if(StringUtil.isEmpty(password)) {
                    JOptionPane.showMessageDialog(UserAddFrame.this, "对不起，用户密码不能为空！");
                    return ;
                }
                String passwordTrue = jtfPasswordTrue.getText().trim();
                if(StringUtil.isEmpty(passwordTrue)) {
                    JOptionPane.showMessageDialog(UserAddFrame.this, "对不起，确认密码不能为空！");
                    return ;
                }
                if(!password.equals(passwordTrue)){
                    JOptionPane.showMessageDialog(UserAddFrame.this, "对不起，两次密码输入不同！");
                    return;
                }

                String phone = jtfPhone.getText().trim();
                if(StringUtil.isEmpty(phone)) {
                    JOptionPane.showMessageDialog(UserAddFrame.this, "对不起，用户电话不能为空！");
                    return ;
                }else {
                    Pattern pattern = Pattern.compile("-?[0-9]+?[0-9]+");
                    Matcher isNum = pattern.matcher(phone);
                    if(!isNum.matches()){
                        JOptionPane.showMessageDialog(UserAddFrame.this, "对不起，用户电话输入有误！");
                        return;
                    }
                }


                String ageStr = jtfAge.getText().trim();
                if(StringUtil.isEmpty(ageStr)){
                    JOptionPane.showMessageDialog(UserAddFrame.this, "对不起，用户年龄不能为空！");
                    return;
                }else {
                    Pattern pattern = Pattern.compile("[0-9]*");
                    Matcher isNum = pattern.matcher(ageStr);
                    if(!isNum.matches()){
                        JOptionPane.showMessageDialog(UserAddFrame.this, "对不起，年龄输入有误！");
                        return;
                    }else {
                        age = Integer.parseInt(ageStr);
                        if(age>150){
                            JOptionPane.showMessageDialog(UserAddFrame.this, "对不起，年龄输入有误！");
                            return;
                        }
                    }
                }
                String address = jtfAddress.getText().trim();
                String sex = (String) sexBox.getSelectedItem();
                String auth = (String)authBox.getSelectedItem();
                //将数据封装成Sport对象
                User u = new User(userName,password,sex,age,phone,address,auth);

                UserService service = (UserService) ObjectFactory.getObject("UserService");


                if(service.insertUser(u)!=false) {
                    JOptionPane.showMessageDialog(UserAddFrame.this, "数据添加成功");
                    //希望操作一下jtable对象(刷新数据)
                    jt.setModel(new UserTableModel(1,12));
                    UserAddFrame.this.dispose();
                }else {
                    JOptionPane.showMessageDialog(UserAddFrame.this, "数据添加失败");
                    return;
                }
            }
        });
        jtbn_close.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserAddFrame.this.dispose();
            }
        });
            this.add(jpContent,BorderLayout.CENTER);
            this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            this.setVisible(true);
    }
}
