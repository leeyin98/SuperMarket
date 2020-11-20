package com.leeyin98.ui;

import com.leeyin98.dao.IObjectMapper;
import com.leeyin98.dao.impl.UserDAOImpl;
import com.leeyin98.entity.Bill;
import com.leeyin98.entity.Supplier;
import com.leeyin98.factory.ObjectFactory;
import com.leeyin98.service.SupplierService;
import com.leeyin98.tmodel.*;
import com.leeyin98.util.DbUtil;
import com.leeyin98.util.JdbcTemplate;
import com.leeyin98.util.StringUtil;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class MenuMain extends JFrame implements ActionListener {
    private CardLayout cardlayout;
    private JFrame loginFrame;
    private JFrame mainFrame;
    private Timer time;
    private JLabel nameLabel;
    private JLabel passwordLabel;
    private JButton loginBtn;
    private JButton resetBtn;
    private JButton billBtn;
    private JButton supplierBtn;
    private JButton userBtn;
    private JButton reportBtn;
    private JButton exitBtn;
    private JScrollPane billJsp;
    private JScrollPane supplierJsp;
    private JScrollPane userJsp;
    private JScrollPane groupByGoodJsp;
    private JScrollPane groupBySupplierJsp;
    private JTable billTable;
    private JTable supplierTable;
    private JTable userTable;
    private JTable groupByGoodTable;
    private JTable groupBySupplierTable;
    private JPanel defaultPanel;
    private JPanel billPanel;
    private JPanel supplierPanel;
    private JPanel userPanel;
    private JPanel reportPanel;
    private JPanel cardPanel;
    private JPanel groupCardPanel;
    private JPanel groupByGoodPanel;
    private JPanel groupBySupplierPanel;
    private JLabel groupPageIndex = new JLabel();
    private JComboBox IDBox = new JComboBox();
    private JTextField jtf = new JTextField(15);
    private JPasswordField jpf = new JPasswordField(15);
    private int billPage = 1;
    private int supplierPage = 1;
    private int userPage = 1;
    private int groupByGoodPage = 1;
    private int groupBySupplierPage = 1;
    private int pageSize = 12;
    private int billTotalPage = 0;
    private int supplierTotalPage = 0;
    private int userTotalPage = 0;
    private int groupByGoodTotalPage = 0;
    private int groupBySupplierTotalPage = 0;
    private int billTotalData = 0;
    private int supplierTotalData = 0;
    private int userTotalData = 0;
    private int switchNum;
    private int txtNum = 0;

    public static void main(String[] args) {
        MenuMain window = new MenuMain();
    }

    public MenuMain() {
        login();
    }

    private void login() {
        // 初始化窗体
        loginFrame = new JFrame("登录界面");
        //屏幕中央显示
        int x = (int) (loginFrame.getToolkit().getScreenSize().getWidth() - loginFrame.getWidth()) / 2;
        int y = (int) (loginFrame.getToolkit().getScreenSize().getHeight() - loginFrame.getHeight()) / 2;
        loginFrame.setBounds(700, 300, 630, 445);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.getContentPane().setLayout(null);

        //添加姓名标签
        nameLabel = new JLabel("姓名: ");
        nameLabel.setBounds(210, 160, 50, 50);
        nameLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        loginFrame.add(nameLabel);
        //添加输入框
        jtf.setBounds(255, 175, 100, 25);
        loginFrame.add(jtf);

        //添加密码标签
        passwordLabel = new JLabel("密码: ");
        passwordLabel.setBounds(210, 195, 50, 50);
        passwordLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        loginFrame.add(passwordLabel);
        //添加输入框
        jpf.setBounds(255, 210, 100, 25);
        loginFrame.add(jpf);

        //添加身份标签
        JLabel identityLabel = new JLabel("身份: ");
        identityLabel.setBounds(210, 230, 50, 50);
        identityLabel.setFont(new Font("微软雅黑", Font.BOLD, 18));
        loginFrame.add(identityLabel);
        //添加下拉选择框
        IDBox.addItem("普通员工");
        IDBox.addItem("部门经理");
        IDBox.setBounds(255, 245, 100, 25);
        loginFrame.add(IDBox);

        //添加登录按钮
        loginBtn = new JButton("登录");
        loginBtn.setFont(new Font("微软雅黑", Font.BOLD, 18));
        loginBtn.setBounds(180, 300, 80, 30);
        loginFrame.add(loginBtn);
        //添加监听
        loginBtn.addActionListener(this);

        //添加重置按钮
        resetBtn = new JButton("重置");
        resetBtn.setFont(new Font("微软雅黑", Font.BOLD, 18));
        resetBtn.setBounds(300, 300, 80, 30);
        loginFrame.add(resetBtn);
        //添加监听
        resetBtn.addActionListener(this);

        // 设置图片背景
        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        URL resource = this.getClass().getResource("/com/leeyin98/images/login_box.jpg"); // 获取背景图片路径
        ImageIcon icon = new ImageIcon(resource); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小
        loginFrame.getContentPane().add(lblBackground); // 将组件添加到面板中

        loginFrame.setVisible(true);
    }


    public void mainMenu() {
        JPanel jp = new JPanel();
        jp.setLayout(null);
        //142
        jp.setBounds(205, 142, 795, 430);
        cardlayout = new CardLayout();
        cardPanel = new JPanel();
        cardPanel.setBounds(0, 0, 795, 430);
        cardPanel.setLayout(cardlayout);
        //卡片实例化
        defaultPanel = new JPanel();
        billPanel = new JPanel();
        supplierPanel = new JPanel();
        userPanel = new JPanel();
        reportPanel = new JPanel();
        //设置布局管理器（绝对定位）
        defaultPanel.setLayout(null);
        billPanel.setLayout(null);
        supplierPanel.setLayout(null);
        userPanel.setLayout(null);
        reportPanel.setLayout(null);
        // 初始化窗体
        mainFrame = new JFrame("系统界面");
        //屏幕中央显示
        int x = (int) (mainFrame.getToolkit().getScreenSize().getWidth() - mainFrame.getWidth()) / 2;
        int y = (int) (mainFrame.getToolkit().getScreenSize().getHeight() - mainFrame.getHeight()) / 2;
        mainFrame.setBounds(500, 200, 1050, 650);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.getContentPane().setLayout(null);

        //顶部显示信息
        JLabel welcomeLabel = new JLabel("欢迎您：");
        welcomeLabel.setBounds(520, 60, 60, 20);
        welcomeLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
        mainFrame.add(welcomeLabel);

        JLabel loginNameLabel = new JLabel(jtf.getText());
        loginNameLabel.setBounds(580, 76, 60, 20);
        loginNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 15));
        mainFrame.add(loginNameLabel);

        //时间显示
        JLabel timeLabel = new JLabel("");
        timeLabel.setBounds(830, 76, 200, 20);
        timeLabel.setFont(new Font("微软雅黑", Font.BOLD, 12));
        time = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                timeLabel.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            }
        });
        time.start();
        mainFrame.add(timeLabel);

        //账单管理按钮
        billBtn = new JButton();
        URL resource1 = this.getClass().getResource("/com/leeyin98/images/btn_bill.gif"); // 获取背景图片路径
        ImageIcon icon1 = new ImageIcon(resource1); // 创建背景图片对象
        billBtn.setIcon(icon1); // 设置标签组件要显示的图标
        billBtn.setBounds(10, 110, icon1.getIconWidth(), icon1.getIconHeight()); // 设置组件的显示位置及大小
        mainFrame.getContentPane().add(billBtn); // 将组件添加到面板中
        billBtn.addActionListener(this);

        //供应商管理按钮
        supplierBtn = new JButton();
        URL resource2 = this.getClass().getResource("/com/leeyin98/images/btn_suppliers.gif"); // 获取背景图片路径
        ImageIcon icon2 = new ImageIcon(resource2); // 创建背景图片对象
        supplierBtn.setIcon(icon2); // 设置标签组件要显示的图标
        supplierBtn.setBounds(10, 160, icon2.getIconWidth(), icon2.getIconHeight()); // 设置组件的显示位置及大小
        mainFrame.getContentPane().add(supplierBtn); // 将组件添加到面板中
        supplierBtn.addActionListener(this);

        //用户管理按钮
        userBtn = new JButton();
        URL resource3 = this.getClass().getResource("/com/leeyin98/images/btn_users.gif"); // 获取背景图片路径
        ImageIcon icon3 = new ImageIcon(resource3); // 创建背景图片对象
        userBtn.setIcon(icon3); // 设置标签组件要显示的图标
        userBtn.setBounds(10, 210, icon3.getIconWidth(), icon3.getIconHeight()); // 设置组件的显示位置及大小
        mainFrame.getContentPane().add(userBtn); // 将组件添加到面板中
        userBtn.addActionListener(this);

        //报表管理按钮
        reportBtn = new JButton();
        URL resource4 = this.getClass().getResource("/com/leeyin98/images/btn_reports.gif"); // 获取背景图片路径
        ImageIcon icon4 = new ImageIcon(resource4); // 创建背景图片对象
        reportBtn.setIcon(icon4); // 设置标签组件要显示的图标
        reportBtn.setBounds(10, 260, icon4.getIconWidth(), icon4.getIconHeight()); // 设置组件的显示位置及大小
        mainFrame.getContentPane().add(reportBtn); // 将组件添加到面板中
        reportBtn.addActionListener(this);

        //退出系统按钮
        exitBtn = new JButton();
        URL resource5 = this.getClass().getResource("/com/leeyin98/images/btn_exit.gif"); // 获取背景图片路径
        ImageIcon icon5 = new ImageIcon(resource5); // 创建背景图片对象
        exitBtn.setIcon(icon5); // 设置标签组件要显示的图标
        exitBtn.setBounds(10, 310, icon5.getIconWidth(), icon5.getIconHeight()); // 设置组件的显示位置及大小
        mainFrame.getContentPane().add(exitBtn); // 将组件添加到面板中
        exitBtn.addActionListener(this);


        //第一张卡片（默认卡片）：欢迎文字显示
        defaultPanel.setBounds(205, 142, 795, 430);
        JLabel jlabel1 = new JLabel("欢迎使用超市管理系统！ ");
        jlabel1.setBounds(220, 160, 500, 100);
        jlabel1.setFont(new Font("微软雅黑", Font.BOLD, 35));
        defaultPanel.add(jlabel1);
        cardPanel.add(defaultPanel, "welcome");
//        cardlayout.show(cardPanel,"welcome");
        mainFrame.add(defaultPanel);


        //第二张卡片：账单管理
        billPanel.setBounds(205, 142, 795, 430);
        //添加标签
        JLabel billMgrLabel = new JLabel("账单管理>>");
        billMgrLabel.setBounds(10, 80, 230, 40);
        billMgrLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));
        billPanel.add(billMgrLabel);
        //组合查询行
        //商品名称输入框
        JLabel goodNameLabel = new JLabel("商品名称: ");
        goodNameLabel.setBounds(10, 20, 100, 30);
        goodNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 13));
        billPanel.add(goodNameLabel);
        JTextField gooNameJtf = new JTextField(30);
        gooNameJtf.setBounds(100, 20, 120, 30);
        billPanel.add(gooNameJtf);
        //下拉选择框
        JLabel isPaidLabel = new JLabel("是否付款: ");
        isPaidLabel.setBounds(250, 20, 80, 30);
        isPaidLabel.setFont(new Font("微软雅黑", Font.BOLD, 13));
        billPanel.add(isPaidLabel);
        JComboBox isPaidBox = new JComboBox();
        isPaidBox.addItem("请选择");
        isPaidBox.addItem("是");
        isPaidBox.addItem("否");
        isPaidBox.setBounds(350, 20, 70, 25);
        billPanel.add(isPaidBox);
        //查询按钮
        JButton billFindBtn = new JButton("组合查询");
        billFindBtn.setBounds(500, 20, 100, 30);
        billPanel.add(billFindBtn);
        billFindBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == billFindBtn) {
                    if (gooNameJtf.getText().equals("") && "请选择".equals(isPaidBox.getSelectedItem())) {
                        JOptionPane.showMessageDialog(null, "请选择查询条件！", "提示", JOptionPane.ERROR_MESSAGE);
                    } else if (!gooNameJtf.getText().equals("") && "请选择".equals(isPaidBox.getSelectedItem())) {
                        billTable.setModel(new BillTableModel(gooNameJtf.getText().trim()));
                    } else if (!"请选择".equals(isPaidBox.getSelectedItem()) && gooNameJtf.getText().equals("")) {
                        int payID;
                        if ("是".equals(isPaidBox.getSelectedItem())) {
                            payID = 1;
                        } else {
                            payID = 0;
                        }
                        billTable.setModel(new BillTableModel(payID));
                    } else if (!gooNameJtf.getText().equals("") && !"请选择".equals(isPaidBox.getSelectedItem())) {
                        int payID;
                        if ("是".equals(isPaidBox.getSelectedItem())) {
                            payID = 1;
                        } else {
                            payID = 0;
                        }
                        billTable.setModel(new BillTableModel(gooNameJtf.getText().trim(), payID));
                    }
                }
            }
        });
        billTable = new JTable(new BillTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }//表格不允许被编辑
        });
        billJsp = new JScrollPane(billTable);
        billJsp.setBounds(0, 140, 795, 215);
        billPanel.add(billJsp);
        //账单数据导出按钮
        JButton exportBtn = new JButton("导出");
        exportBtn.setBounds(280, 90, 80, 30);
        billPanel.add(exportBtn);
        exportBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == exportBtn) {
                    try {
                        JFileChooser jfc = new JFileChooser("D:\\");
                        jfc.setFileSelectionMode(JFileChooser.SAVE_DIALOG | JFileChooser.DIRECTORIES_ONLY);
                        jfc.showSaveDialog(new JPanel());
                        File file = jfc.getSelectedFile();
                        String path = file.getAbsolutePath() + "\\export" + txtNum + ".txt";
                        getTxt(path);
                        JOptionPane.showMessageDialog(null, "数据导出成功");
                        txtNum++;
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }
        });
        //账单数据添加按钮
        JButton billAddBtn = new JButton("添加数据");
        billAddBtn.setBounds(400, 80, 100, 40);
        billPanel.add(billAddBtn);
        billAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                billTotalData = billTotalData + 1;
                new BillAddFrame(billTable);
            }
        });
        //账单数据修改按钮
        JButton billUpdateBtn = new JButton("修改数据");
        billUpdateBtn.setBounds(520, 80, 100, 40);
        billPanel.add(billUpdateBtn);
        billUpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowNum = billTable.getSelectedRowCount();
                System.out.println(rowNum);
                if (rowNum < 1) {
                    JOptionPane.showMessageDialog(null, "对不起，您未选择修改的行！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (rowNum > 1) {
                    JOptionPane.showMessageDialog(null, "对不起，请选择单行进行修改！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    new BillUpdateFrame(billTable, rowNum);
                }
            }
        });
        //账单数据删除按钮
        JButton billDeleteBtn = new JButton("删除数据");
        billDeleteBtn.setBounds(640, 80, 100, 40);
        billPanel.add(billDeleteBtn);
        billDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowNum = billTable.getSelectedRowCount();
                System.out.println(rowNum);
                if (rowNum < 1) {
                    JOptionPane.showMessageDialog(null, "请选择需要修改的行！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "是否确认删除所选中的行", "消息", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        billTotalData = billTotalData - 1;
                        new BillDeleteFrame(billTable, rowNum);
                    }
                }
            }
        });

        billTotalPage = getTotalPage(billTable);
        //当前页数文本
        JLabel billPageIndex = new JLabel(billPage + "/" + billTotalPage);
        billPageIndex.setBounds(760, 360, 60, 30);
        billPanel.add(billPageIndex);
        //上一页按钮
        JButton previousBillBtn = new JButton("上一页");
        previousBillBtn.setBounds(250, 380, 80, 30);
        billPanel.add(previousBillBtn);
        previousBillBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (billPage == 1) {
                    JOptionPane.showMessageDialog(null, "当前已是第一页！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (billPage > 1) {
                    billPage = billPage - 1;
                    billTable.setModel(new BillTableModel(billPage, pageSize));
                    billPageIndex.setText(billPage + "/" + billTotalPage);
                }
            }
        });
        //下一页按钮
        JButton nextBillBtn = new JButton("下一页");
        nextBillBtn.setBounds(340, 380, 80, 30);
        billPanel.add(nextBillBtn);
        nextBillBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (billPage == billTotalPage) {
                    JOptionPane.showMessageDialog(null, "当前已是最后一页！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    billPage = billPage + 1;
                    billTable.setModel(new BillTableModel(billPage, pageSize));
                    billPageIndex.setText(billPage + "/" + billTotalPage);
                }
            }
        });
        //前往第N页按钮
        JTextField goBillJtf = new JTextField(10);
        goBillJtf.setBounds(430, 380, 30, 30);
        JButton goBillBtn = new JButton("前往");
        goBillBtn.setBounds(470, 380, 70, 30);
        billPanel.add(goBillJtf);
        billPanel.add(goBillBtn);
        goBillBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pageNum = goBillJtf.getText().trim();
                if (StringUtil.isEmpty(pageNum)) {
                    JOptionPane.showMessageDialog(null, "请输入需前往的页数", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    Pattern pattern = Pattern.compile("[1-9]*");
                    Matcher isNum = pattern.matcher(pageNum);
                    if (!isNum.matches()) {
                        JOptionPane.showMessageDialog(null, "请正确输入需前往的页数", "提示", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int n = Integer.parseInt(pageNum);
                        if (n > billTotalPage) {
                            JOptionPane.showMessageDialog(null, "对不起，您输入的页数已超过最后一页！", "提示", JOptionPane.ERROR_MESSAGE);

                        } else {
                            billPage = n;
                            billTable.setModel(new BillTableModel(billPage, pageSize));
                            billPageIndex.setText(billPage + "/" + billTotalPage);
                        }
                    }
                }
            }
        });
        cardPanel.add(billPanel, "billBtn");


        //第三张卡片：供应商管理
        supplierPanel.setBounds(205, 142, 795, 430);
        //添加标签
        JLabel supplierMgrLabel = new JLabel("供应商管理>>");
        supplierMgrLabel.setBounds(10, 80, 230, 40);
        supplierMgrLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));
        supplierPanel.add(supplierMgrLabel);
        //组合查询行
        //供应商名称输入框
        JLabel supplierNameLabel = new JLabel("供应商名称: ");
        supplierNameLabel.setBounds(10, 20, 100, 30);
        supplierNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 13));
        supplierPanel.add(supplierNameLabel);
        JTextField supplierNameJtf = new JTextField(30);
        supplierNameJtf.setBounds(100, 20, 150, 30);
        supplierPanel.add(supplierNameJtf);
        //查询按钮
        JButton supplierFindBtn = new JButton("查询");
        supplierFindBtn.setBounds(300, 20, 100, 30);
        supplierPanel.add(supplierFindBtn);
        supplierFindBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (supplierNameJtf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "对不起，您未输入查询条件！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    supplierTable.setModel(new SupplierTableModel(supplierNameJtf.getText()));
                }
            }
        });
        supplierTable = new JTable(new SupplierTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }//表格不允许被编辑
        });
        supplierJsp = new JScrollPane(supplierTable);
        supplierJsp.setBounds(0, 140, 795, 215);
        supplierPanel.add(supplierJsp);
        //供应商数据导入按钮
        JButton importBtn = new JButton("导入");
        importBtn.setBounds(280, 90, 80, 30);
        supplierPanel.add(importBtn);
        importBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == importBtn) {
                    JFileChooser jfc = new JFileChooser("D:\\");
                    jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
                    jfc.showOpenDialog(new JPanel());
                    File file = jfc.getSelectedFile();
                    String path = file.getAbsolutePath();;
                    System.out.println(path);
                    readFileByLines(path);
                }
            }
        });

        //供应商数据添加按钮
        JButton supplierAddBtn = new JButton("添加数据");
        supplierAddBtn.setBounds(400, 80, 100, 40);
        supplierPanel.add(supplierAddBtn);
        supplierAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                supplierTotalData = supplierTotalData + 1;
                new SupplierAddFrame(supplierTable);
            }
        });
        //供应商数据修改按钮
        JButton supplierUpdateBtn = new JButton("修改数据");
        supplierUpdateBtn.setBounds(520, 80, 100, 40);
        supplierPanel.add(supplierUpdateBtn);
        supplierUpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowNum = supplierTable.getSelectedRowCount();
                System.out.println(rowNum);
                if (rowNum < 1) {
                    JOptionPane.showMessageDialog(null, "对不起，您未选择修改的行！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (rowNum > 1) {
                    JOptionPane.showMessageDialog(null, "对不起，请选择单行进行修改！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    new SupplierUpdateFrame(supplierTable, rowNum);
                }
            }
        });
        //供应商数据删除按钮
        JButton supplierDeleteBtn = new JButton("删除数据");
        supplierDeleteBtn.setBounds(640, 80, 100, 40);
        supplierPanel.add(supplierDeleteBtn);
        supplierDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowNum = supplierTable.getSelectedRowCount();
                if (rowNum < 1) {
                    JOptionPane.showMessageDialog(null, "请选择需要修改的行！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "是否确认删除所选中的行", "消息", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        supplierTotalData = supplierTotalData - 1;
                        new SupplierDeleteFrame(supplierTable, rowNum);
                    }
                }
            }
        });

        supplierTotalPage = getTotalPage(supplierTable);
        //当前页数文本
        JLabel supplierPageIndex = new JLabel(supplierPage + "/" + supplierTotalPage);
        supplierPageIndex.setBounds(760, 360, 60, 30);
        supplierPanel.add(supplierPageIndex);
        //上一页按钮
        JButton previousSupplierBtn = new JButton("上一页");
        previousSupplierBtn.setBounds(250, 380, 80, 30);
        supplierPanel.add(previousSupplierBtn);
        previousSupplierBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (supplierPage == 1) {
                    JOptionPane.showMessageDialog(null, "当前已是第一页！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (supplierPage > 1) {
                    supplierPage = supplierPage - 1;
                    supplierTable.setModel(new SupplierTableModel(supplierPage, pageSize));
                    supplierPageIndex.setText(supplierPage + "/" + supplierTotalPage);
                }
            }
        });
        //下一页按钮
        JButton nextSupplierBtn = new JButton("下一页");
        nextSupplierBtn.setBounds(340, 380, 80, 30);
        supplierPanel.add(nextSupplierBtn);
        nextSupplierBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (supplierPage == supplierTotalPage) {
                    JOptionPane.showMessageDialog(null, "当前已是最后一页！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    supplierPage = supplierPage + 1;
                    supplierTable.setModel(new SupplierTableModel(supplierPage, pageSize));
                    supplierPageIndex.setText(supplierPage + "/" + supplierTotalPage);
                }
            }
        });
        //前往第N页按钮
        JTextField goSupplierJtf = new JTextField(10);
        goSupplierJtf.setBounds(430, 380, 30, 30);
        JButton goSupplierBtn = new JButton("前往");
        goSupplierBtn.setBounds(470, 380, 70, 30);
        supplierPanel.add(goSupplierJtf);
        supplierPanel.add(goSupplierBtn);
        goSupplierBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pageNum = goSupplierJtf.getText().trim();
                if (StringUtil.isEmpty(pageNum)) {
                    JOptionPane.showMessageDialog(null, "请输入需前往的页数", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    Pattern pattern = Pattern.compile("[0-9]*");
                    Matcher isNum = pattern.matcher(pageNum);
                    if (!isNum.matches()) {
                        JOptionPane.showMessageDialog(null, "请正确输入需前往的页数", "提示", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int n = Integer.parseInt(pageNum);
                        if (n > supplierTotalPage) {
                            JOptionPane.showMessageDialog(null, "对不起，您输入的页数已超过最后一页！", "提示", JOptionPane.ERROR_MESSAGE);
                        } else {
                            supplierPage = n;
                            supplierTable.setModel(new SupplierTableModel(supplierPage, pageSize));
                            supplierPageIndex.setText(supplierPage + "/" + supplierTotalPage);
                        }
                    }
                }
            }
        });
        cardPanel.add(supplierPanel, "supplierBtn");


        //第四张卡片：用户管理
        userPanel.setBounds(205, 142, 795, 430);
        //添加标签
        JLabel userMgrLabel = new JLabel("用户管理>>");
        userMgrLabel.setBounds(10, 80, 230, 40);
        userMgrLabel.setFont(new Font("微软雅黑", Font.BOLD, 32));
        userPanel.add(userMgrLabel);
        //组合查询行
        //用户名称输入框
        JLabel userNameLabel = new JLabel("用户名称: ");
        userNameLabel.setBounds(10, 20, 100, 30);
        userNameLabel.setFont(new Font("微软雅黑", Font.BOLD, 13));
        userPanel.add(userNameLabel);
        JTextField userNameJtf = new JTextField(30);
        userNameJtf.setBounds(100, 20, 150, 30);
        userPanel.add(userNameJtf);
        //查询按钮
        JButton userFindBtn = new JButton("查询");
        userFindBtn.setBounds(300, 20, 100, 30);
        userPanel.add(userFindBtn);
        userFindBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userNameJtf.getText().equals("")) {
                    JOptionPane.showMessageDialog(null, "对不起，您未输入查询条件！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    userTable.setModel(new UserTableModel(userNameJtf.getText().trim()));
                }
            }
        });
        userTable = new JTable(new UserTableModel() {
            public boolean isCellEditable(int row, int column) {
                return false;
            }//表格不允许被编辑
        });
        userJsp = new JScrollPane(userTable);
        userJsp.setBounds(0, 140, 795, 215);
        userPanel.add(userJsp);
        //用户数据添加按钮
        JButton userAddBtn = new JButton("添加数据");
        userAddBtn.setBounds(400, 80, 100, 40);
        userPanel.add(userAddBtn);
        userAddBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userTotalData = userTotalData + 1;
                new UserAddFrame(userTable);
            }
        });
        //用户数据修改按钮
        JButton userUpdateBtn = new JButton("修改数据");
        userUpdateBtn.setBounds(520, 80, 100, 40);
        userPanel.add(userUpdateBtn);
        userUpdateBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowNum = userTable.getSelectedRowCount();
                if (rowNum < 1) {
                    JOptionPane.showMessageDialog(null, "对不起，您未选择修改的行！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (rowNum > 1) {
                    JOptionPane.showMessageDialog(null, "对不起，请选择单行进行修改！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    new UserUpdateFrame(userTable, rowNum);
                }
            }
        });
        //用户数据删除按钮
        JButton userDeleteBtn = new JButton("删除数据");
        userDeleteBtn.setBounds(640, 80, 100, 40);
        userPanel.add(userDeleteBtn);
        userDeleteBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int rowNum = userTable.getSelectedRowCount();
                if (rowNum < 1) {
                    JOptionPane.showMessageDialog(null, "请选择需要修改的行！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(null, "是否确认删除所选中的行", "消息", JOptionPane.OK_CANCEL_OPTION);
                    if (result == JOptionPane.OK_OPTION) {
                        userTotalData = userTotalData - 1;
                        new UserDeleteFrame(userTable, rowNum);
                    }
                }
            }
        });
        userTotalPage = getTotalPage(userTable);
        //当前页数文本
        JLabel userPageIndex = new JLabel(userPage + "/" + userTotalPage);
        userPageIndex.setBounds(760, 360, 60, 30);
        userPanel.add(userPageIndex);
        //上一页按钮
        JButton previousUserBtn = new JButton("上一页");
        previousUserBtn.setBounds(250, 380, 80, 30);
        userPanel.add(previousUserBtn);
        previousUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userPage == 1) {
                    JOptionPane.showMessageDialog(null, "当前已是第一页！", "提示", JOptionPane.ERROR_MESSAGE);
                } else if (userPage > 1) {
                    userPage = userPage - 1;
                    userTable.setModel(new UserTableModel(userPage, pageSize));
                    userPageIndex.setText(userPage + "/" + userTotalPage);
                }
            }
        });
        //下一页按钮
        JButton nextUserBtn = new JButton("下一页");
        nextUserBtn.setBounds(340, 380, 80, 30);
        userPanel.add(nextUserBtn);
        nextUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (userPage == userTotalPage) {
                    JOptionPane.showMessageDialog(null, "当前已是最后一页！", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    userPage = userPage + 1;
                    userTable.setModel(new UserTableModel(userPage, pageSize));
                    userPageIndex.setText(userPage + "/" + userTotalPage);
                }
            }
        });
        //前往第N页按钮
        JTextField goUserJtf = new JTextField(10);
        goUserJtf.setBounds(430, 380, 30, 30);
        JButton goUserBtn = new JButton("前往");
        goUserBtn.setBounds(470, 380, 70, 30);
        userPanel.add(goUserJtf);
        userPanel.add(goUserBtn);
        goUserBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pageNum = goUserJtf.getText().trim();
                if (StringUtil.isEmpty(pageNum)) {
                    JOptionPane.showMessageDialog(null, "请输入需前往的页数", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    Pattern pattern = Pattern.compile("[0-9]*");
                    Matcher isNum = pattern.matcher(pageNum);
                    if (!isNum.matches()) {
                        JOptionPane.showMessageDialog(null, "请正确输入需前往的页数", "提示", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int n = Integer.parseInt(pageNum);
                        if (n > userTotalPage) {
                            JOptionPane.showMessageDialog(null, "对不起，您输入的页数已超过最后一页！", "提示", JOptionPane.ERROR_MESSAGE);

                        } else {
                            userPage = n;
                            userTable.setModel(new UserTableModel(userPage, pageSize));
                            userPageIndex.setText(userPage + "/" + userTotalPage);
                        }
                    }
                }
            }
        });
        cardPanel.add(userPanel, "userBtn");


        //第五张卡片：报表管理
        groupCardPanel = new JPanel();
        JPanel groupJpanel = new JPanel();
        groupJpanel.setLayout(null);
        groupJpanel.setBounds(0, 130, 795, 215);
        groupCardPanel.setBounds(0, 0, 795, 215);
        groupCardPanel.setLayout(cardlayout);
        groupJpanel.add(groupCardPanel);
        reportPanel.setBounds(205, 142, 795, 430);
        JRadioButton groupByGood = new JRadioButton("商品分组查询");
        JRadioButton groupBySupplier = new JRadioButton("供应商分组查询");
        groupByGood.setBounds(200, 50, 150, 40);
        groupBySupplier.setBounds(500, 50, 150, 40);
        ButtonGroup group = new ButtonGroup();
        group.add(groupByGood);
        group.add(groupBySupplier);
        groupByGood.setSelected(true);
        reportPanel.add(groupJpanel);
        reportPanel.add(groupByGood);
        reportPanel.add(groupBySupplier);
        //商品分组查询
        groupByGoodTable = new JTable(new GroupByGoodTableModel()) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }//表格不允许被编辑
        };
        groupByGoodJsp = new JScrollPane(groupByGoodTable);
        groupByGoodJsp.setBounds(0, 0, 795, 215);
        groupByGoodPanel = new JPanel();
        groupByGoodPanel.setBounds(0, 140, 795, 215);
        groupByGoodPanel.add(groupByGoodJsp);
        groupCardPanel.add(groupByGoodPanel, "groupByGood");
        groupByGood.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchNum = 0;
                groupByGoodTable.setModel(new GroupByGoodTableModel(1, pageSize));
                groupPageIndex.setText(groupByGoodPage + "/" + groupByGoodTotalPage);
                cardlayout.show(groupCardPanel, "groupByGood");
            }
        });

        //供应商分组查询
        groupBySupplierTable = new JTable(new GroupBySupplierTableModel()) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }//表格不允许被编辑
        };
        groupBySupplierJsp = new JScrollPane(groupBySupplierTable);
        groupBySupplierJsp.setBounds(0, 140, 795, 215);
        groupBySupplierPanel = new JPanel();
        groupBySupplierPanel.setBounds(0, 140, 795, 215);
        groupBySupplierPanel.add(groupBySupplierJsp);
        groupCardPanel.add(groupBySupplierPanel, "groupBySupplier");
        groupBySupplier.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchNum = 1;
                groupBySupplierTable.setModel(new GroupBySupplierTableModel(1, pageSize));
                groupPageIndex.setText(groupBySupplierPage + "/" + groupBySupplierTotalPage);
                cardlayout.show(groupCardPanel, "groupBySupplier");
            }
        });


        groupByGoodTotalPage = getTotalPage(groupByGoodTable);
        groupBySupplierTotalPage = getTotalPage(groupBySupplierTable);
        groupPageIndex.setText(groupByGoodPage + "/" + groupByGoodTotalPage);
        groupPageIndex.setBounds(760, 360, 60, 30);
        reportPanel.add(groupPageIndex);
        //上一页按钮
        JButton previousGroupBtn = new JButton("上一页");
        previousGroupBtn.setBounds(250, 380, 80, 30);
        reportPanel.add(previousGroupBtn);
        previousGroupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (switchNum == 0) {
                    if (groupByGoodPage == 1) {
                        JOptionPane.showMessageDialog(null, "当前已是第一页！", "提示", JOptionPane.ERROR_MESSAGE);
                    } else if (groupByGoodPage > 1) {
                        groupByGoodPage = groupByGoodPage - 1;
                        groupByGoodTable.setModel(new GroupByGoodTableModel(groupByGoodPage, pageSize));
                        groupPageIndex.setText(groupByGoodPage + "/" + groupByGoodTotalPage);
                    }
                } else {
                    if (groupBySupplierPage == 1) {
                        JOptionPane.showMessageDialog(null, "当前已是第一页！", "提示", JOptionPane.ERROR_MESSAGE);
                    } else if (groupBySupplierPage > 1) {
                        groupBySupplierPage = groupBySupplierPage - 1;
                        groupBySupplierTable.setModel(new GroupBySupplierTableModel(groupBySupplierPage, pageSize));
                        groupPageIndex.setText(groupBySupplierPage + "/" + groupBySupplierTotalPage);
                    }
                }
            }
        });
        //下一页按钮
        JButton nextGroupBtn = new JButton("下一页");
        nextGroupBtn.setBounds(340, 380, 80, 30);
        reportPanel.add(nextGroupBtn);
        nextGroupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (switchNum == 0) {
                    if (groupByGoodPage == groupByGoodTotalPage) {
                        JOptionPane.showMessageDialog(null, "当前已是最后一页！", "提示", JOptionPane.ERROR_MESSAGE);
                    } else {
                        groupByGoodPage = groupByGoodPage + 1;
                        groupByGoodTable.setModel(new GroupByGoodTableModel(groupByGoodPage, pageSize));
                        groupPageIndex.setText(groupByGoodPage + "/" + groupByGoodTotalPage);
                    }
                } else {
                    if (groupBySupplierPage == groupBySupplierTotalPage) {
                        JOptionPane.showMessageDialog(null, "当前已是最后一页！", "提示", JOptionPane.ERROR_MESSAGE);
                    } else {
                        groupBySupplierPage = groupBySupplierPage + 1;
                        groupBySupplierTable.setModel(new GroupByGoodTableModel(groupBySupplierPage, pageSize));
                        groupPageIndex.setText(groupBySupplierPage + "/" + groupBySupplierTotalPage);
                    }
                }
            }
        });
        //前往第N页按钮
        JTextField goGroupJtf = new JTextField(10);
        goGroupJtf.setBounds(430, 380, 30, 30);
        JButton goGroupBtn = new JButton("前往");
        goGroupBtn.setBounds(470, 380, 70, 30);
        reportPanel.add(goGroupJtf);
        reportPanel.add(goGroupBtn);
        goGroupBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pageNum = goGroupJtf.getText().trim();
                if (StringUtil.isEmpty(pageNum)) {
                    JOptionPane.showMessageDialog(null, "请输入需前往的页数", "提示", JOptionPane.ERROR_MESSAGE);
                } else {
                    Pattern pattern = Pattern.compile("[0-9]*");
                    Matcher isNum = pattern.matcher(pageNum);
                    if (!isNum.matches()) {
                        JOptionPane.showMessageDialog(null, "请正确输入需前往的页数", "提示", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int n = Integer.parseInt(pageNum);
                        if (switchNum == 0) {
                            if (n > groupByGoodTotalPage) {
                                JOptionPane.showMessageDialog(null, "对不起，您输入的页数已超过最后一页！", "提示",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                groupByGoodPage = n;
                                groupByGoodTable.setModel(new GroupByGoodTableModel(groupByGoodPage, pageSize));
                                groupPageIndex.setText(groupByGoodPage + "/" + groupByGoodTotalPage);
                            }
                        } else {
                            if (n > groupBySupplierTotalPage) {
                                JOptionPane.showMessageDialog(null, "对不起，您输入的页数已超过最后一页！", "提示",
                                        JOptionPane.ERROR_MESSAGE);
                            } else {
                                groupBySupplierPage = n;
                                groupBySupplierTable.setModel(new GroupByGoodTableModel(groupBySupplierPage, pageSize));
                                groupPageIndex.setText(groupBySupplierPage + "/" + groupBySupplierTotalPage);
                            }
                        }
                    }
                }
            }
        });
        cardPanel.add(reportPanel, "reportBtn");


        // 设置背景
        JLabel lblBackground = new JLabel(); // 创建一个标签组件对象
        URL resource = this.getClass().getResource("/com/leeyin98/images/MainFrame.png"); // 获取背景图片路径
        ImageIcon icon = new ImageIcon(resource); // 创建背景图片对象
        lblBackground.setIcon(icon); // 设置标签组件要显示的图标
        lblBackground.setBounds(0, 0, icon.getIconWidth(), icon.getIconHeight()); // 设置组件的显示位置及大小
        mainFrame.getContentPane().add(lblBackground); // 将组件添加到面板中

        jp.add(cardPanel);
        mainFrame.add(jp);
        mainFrame.setResizable(false);
        mainFrame.setVisible(true);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        UserDAOImpl udi = new UserDAOImpl();
        String combo = (String) IDBox.getSelectedItem();
        if (e.getSource() == loginBtn) {
            try {
                DbUtil.getConnection();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            String uname = jtf.getText();
            String upassword = jpf.getText();
            String authority = (String) IDBox.getSelectedItem();
            boolean is = udi.loginUser(uname, upassword, authority);
            if (is == true) {
                loginFrame.dispose();
                mainMenu();
//                cardlayout.show(cardPanel,"welcome");
                jtf.setText("");
                jpf.setText("");
            } else {
                JOptionPane.showMessageDialog(null, "你输入的密码或选择的身份有误，请重新输入或选择！", "错误", JOptionPane.ERROR_MESSAGE);
            }
        } else if (e.getSource() == resetBtn) {
            jtf.setText("");
            jpf.setText("");
            IDBox.setSelectedItem(0);
        } else if (e.getSource() == billBtn) {
            billTable.setModel(new BillTableModel(1, pageSize));
            cardlayout.show(cardPanel, "billBtn");
        } else if (e.getSource() == supplierBtn) {
            if (combo.equals("普通员工")) {
                JOptionPane.showMessageDialog(null, "对不起，你的权限不足！", "提示", JOptionPane.ERROR_MESSAGE);
            } else {
                supplierTable.setModel(new SupplierTableModel(1, pageSize));
                cardlayout.show(cardPanel, "supplierBtn");
            }
        } else if (e.getSource() == userBtn) {
            if (combo.equals("普通员工")) {
                JOptionPane.showMessageDialog(null, "对不起，你的权限不足！", "提示", JOptionPane.ERROR_MESSAGE);
            } else {
                userTable.setModel(new UserTableModel(1, pageSize));
                cardlayout.show(cardPanel, "userBtn");
            }
        } else if (e.getSource() == reportBtn) {
            if (combo.equals("普通员工")) {
                JOptionPane.showMessageDialog(null, "对不起，你的权限不足！", "提示", JOptionPane.ERROR_MESSAGE);
            } else {
                switchNum = 0;
                groupByGoodTable.setModel(new GroupByGoodTableModel(1, pageSize));
                cardlayout.show(cardPanel, "reportBtn");
            }
        } else if (e.getSource() == exitBtn) {
            int result = JOptionPane.showConfirmDialog(null, "是否确定要退出系统？", "消息", JOptionPane.OK_CANCEL_OPTION);
            if (result == JOptionPane.OK_OPTION) {
                time.stop();
                mainFrame.dispose();
                IDBox.removeItem("普通员工");
                IDBox.removeItem("部门经理");
                login();
            }
        }
    }


    //得到总页数
    public int getTotalPage(JTable jt) {
        int totalData = jt.getRowCount();
        int totalPage = 0;
        if (totalData / pageSize < 1) {
            totalPage = 1;
        } else if (totalData % pageSize == 0 && totalData / pageSize >= 1) {
            totalPage = totalData / pageSize;
        } else if (totalData % pageSize != 0 && totalData / pageSize >= 1) {
            totalPage = (totalData / pageSize) + 1;
        }
        return totalPage;
    }

    //数据导出
    public void getTxt(String path) throws Exception {
        String sql;
        FileWriter fw = null;
        File file = new File(path);
        if (!file.exists()) {
            file.createNewFile();
        }
        fw = new FileWriter(file);
        try {

            sql = "select bno,bname,bnum,bmoney,bunit,ispay,supplier,description,btime from tb_bill";
            fw.write("商品编号 	   商品名称   商品数量    交易金额    是否付款    商品描述    账单时间     供应商名称\n");

            java.util.List<Bill> list = JdbcTemplate.executeQuery(sql, new billObjectMap());
            for (Bill b : list) {
                fw.write(b.getA_id() + "	 " +
                        b.getA_name() + "	 " + b.getA_nums() + "	 " + b.getA_amount() + "	 " +
                        b.getA_ispayed() + "  " + b.getA_Info() + "	 " + b.getA_Date() + "	  " +
                        " 	 " + b.getS_name() + "\r\n");
                fw.flush();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            fw.close();
        }
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

    //数据导入
    public void readFileByLines(String fileName) {
        File file = new File(fileName);
        BufferedReader reader = null;
        String sql ="INSERT INTO tb_supplier (sname,sdescription,contact,phonenum,faxes,address) VALUES ( ?,?, ?, ?, ?, ?)";
        try {
            System.out.println("以行为单位读取文件内容，一次读一整行：");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            SupplierService service = (SupplierService) ObjectFactory.getObject("SupplierService");
            // 一次读入一行，直到读入null为文件结束
            while ((tempString = reader.readLine()) != null) {
                // 显示行号
                String [] arr = tempString.split("\\s+");
                System.out.println(arr.length);
                String supplierName =  arr[1];
                String item1 =  arr[2];
                String contactName =  arr[3];
                String phone =  arr[4];
                String faxes =  arr[5];
                String address =  arr[6];
                Supplier s = new Supplier(supplierName,item1,contactName,phone,faxes,address);
                service.insertSupplier(s);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }  finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
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
            s.setS_faxes(rs.getString("faxes"));
            s.setS_address(rs.getString("address"));
            return s;
        }
    }
}
