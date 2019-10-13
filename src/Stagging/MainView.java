/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stagging;

import java.awt.Cursor;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author zhangxr
 */
public class MainView extends javax.swing.JFrame {

    public class CheckData {

        public CheckData(String m, String para) {
            mode = m;
            parameter = para;
        }
        public String mode;
        public String parameter;
        public String status;
        public int days;
        public double key;
        public double percent;
        public String amount;
    }

    /**
     * Creates new form StockerView
     */
    public MainView() {
        initComponents();
        setLocationRelativeTo(null);
        final URL filename = getClass().getResource("/resource/Livermore3.jpg");
        if (filename != null) {
            setIconImage(new ImageIcon(filename, "Icon").getImage());
        }
        importData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        jMenuItemCopy = new javax.swing.JMenuItem();
        jMenuItemClear = new javax.swing.JMenuItem();
        jPanelMain = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePoint = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextAreaMain = new javax.swing.JTextArea();
        jPanelSysFilter = new javax.swing.JPanel();
        jLabelPara1 = new javax.swing.JLabel();
        jLabelPara2 = new javax.swing.JLabel();
        jLabelPara3 = new javax.swing.JLabel();
        jLabelPara4 = new javax.swing.JLabel();
        jLabelDash1 = new javax.swing.JLabel();
        jLabelDash2 = new javax.swing.JLabel();
        jLabelDash3 = new javax.swing.JLabel();
        jLabelDash4 = new javax.swing.JLabel();
        jTextFieldPS1 = new javax.swing.JTextField();
        jTextFieldPE1 = new javax.swing.JTextField();
        jTextFieldPS2 = new javax.swing.JTextField();
        jTextFieldPE2 = new javax.swing.JTextField();
        jTextFieldPS3 = new javax.swing.JTextField();
        jTextFieldPE3 = new javax.swing.JTextField();
        jTextFieldPS4 = new javax.swing.JTextField();
        jTextFieldPE4 = new javax.swing.JTextField();
        jButtonFilterStart = new javax.swing.JButton();
        jButtonFilterCheck = new javax.swing.JButton();
        jPanelCongfig = new javax.swing.JPanel();
        jTextFieldDiffCoef = new javax.swing.JTextField();
        jTextFieldSlope = new javax.swing.JTextField();
        jLabelSlope = new javax.swing.JLabel();
        jLabelDiffCoef = new javax.swing.JLabel();
        jTextFieldWinLevel = new javax.swing.JTextField();
        jTextFieldStart = new javax.swing.JTextField();
        jLabelStartPoint = new javax.swing.JLabel();
        jLabelWinLevel = new javax.swing.JLabel();
        jButtonInvestEva = new javax.swing.JButton();
        jButtonIpoInfo = new javax.swing.JButton();
        jLabelInvestCoef = new javax.swing.JLabel();
        jTextFieldInvestCoef = new javax.swing.JTextField();
        jLabelInvestLevel = new javax.swing.JLabel();
        jTextFieldInvestLevel = new javax.swing.JTextField();
        jButtonInvestEva1 = new javax.swing.JButton();
        jMenuBar = new javax.swing.JMenuBar();
        jMenuFile = new javax.swing.JMenu();
        jMenuItemOpen = new javax.swing.JMenuItem();
        jMenuRun = new javax.swing.JMenu();
        jMenuItemDZH = new javax.swing.JMenuItem();
        jMenuHelp = new javax.swing.JMenu();
        jMenuItemAbout = new javax.swing.JMenuItem();

        jMenuItemCopy.setText("复制");
        jMenuItemCopy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCopyActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemCopy);

        jMenuItemClear.setText("清除");
        jMenuItemClear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemClearActionPerformed(evt);
            }
        });
        jPopupMenu1.add(jMenuItemClear);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("智能证券系统-基金定投");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelMain.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTablePoint.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 12));
        jTablePoint.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTablePoint.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"定投轮数", null, "平均日化率", ""},
                {"累加投入", null, "平均持仓期", null},
                {"累加产出", null, "最大投入", ""},
                {"净利润", null, "平均投入", null},
                {"总收益率", null, "最大亏损", null},
                {"测试年限", null, "最大回撤比", ""},
                {"定投笔数", "", "平均离差", null},
                {"定投时间比", null, "平均负离差", null},
                {"最长周期", "", "最小负离差", null},
                {"最长定投轮", null, "平均买入比", null},
                {"平均定投轮", null, "最大买入比", null}
            },
            new String [] {
                "统计指标", "全部交易", "统计指标", "全部交易"
            }
        ));
        jScrollPane1.setViewportView(jTablePoint);
        if (jTablePoint.getColumnModel().getColumnCount() > 0) {
            jTablePoint.getColumnModel().getColumn(0).setMinWidth(80);
            jTablePoint.getColumnModel().getColumn(2).setMinWidth(80);
        }

        jPanelMain.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, 340, 206));

        jTextAreaMain.setColumns(20);
        jTextAreaMain.setFont(new java.awt.Font("仿宋", 0, 11)); // NOI18N
        jTextAreaMain.setRows(5);
        jTextAreaMain.setComponentPopupMenu(jPopupMenu1);
        jTextAreaMain.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextAreaMainMouseReleased(evt);
            }
        });
        jScrollPane2.setViewportView(jTextAreaMain);

        jPanelMain.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 340, 80));

        jPanelSysFilter.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "优选系统", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑", 0, 12))); // NOI18N
        jPanelSysFilter.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jPanelSysFilter.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPara1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabelPara1.setText("P1:");
        jPanelSysFilter.add(jLabelPara1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 27, -1, -1));

        jLabelPara2.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabelPara2.setText("P2:");
        jPanelSysFilter.add(jLabelPara2, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 27, -1, -1));

        jLabelPara3.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabelPara3.setText("P3:");
        jPanelSysFilter.add(jLabelPara3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 50, -1, -1));

        jLabelPara4.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabelPara4.setText("P4:");
        jPanelSysFilter.add(jLabelPara4, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 51, -1, -1));

        jLabelDash1.setText("-");
        jPanelSysFilter.add(jLabelDash1, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 29, -1, -1));

        jLabelDash2.setText("-");
        jPanelSysFilter.add(jLabelDash2, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 30, -1, -1));

        jLabelDash3.setText("-");
        jPanelSysFilter.add(jLabelDash3, new org.netbeans.lib.awtextra.AbsoluteConstraints(72, 52, -1, -1));

        jLabelDash4.setText("-");
        jPanelSysFilter.add(jLabelDash4, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 52, -1, -1));

        jTextFieldPS1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextFieldPS1.setText("20");
        jPanelSysFilter.add(jTextFieldPS1, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 26, 35, -1));

        jTextFieldPE1.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextFieldPE1.setText("200");
        jPanelSysFilter.add(jTextFieldPE1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 26, 35, -1));

        jTextFieldPS2.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextFieldPS2.setText("1.5");
        jPanelSysFilter.add(jTextFieldPS2, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 26, 35, -1));

        jTextFieldPE2.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextFieldPE2.setText("2.0");
        jPanelSysFilter.add(jTextFieldPE2, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 26, 35, -1));

        jTextFieldPS3.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextFieldPS3.setText("0.8");
        jPanelSysFilter.add(jTextFieldPS3, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 50, 35, -1));

        jTextFieldPE3.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jTextFieldPE3.setText("1.0");
        jPanelSysFilter.add(jTextFieldPE3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 50, 35, -1));

        jTextFieldPS4.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jPanelSysFilter.add(jTextFieldPS4, new org.netbeans.lib.awtextra.AbsoluteConstraints(145, 50, 35, -1));

        jTextFieldPE4.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        jPanelSysFilter.add(jTextFieldPE4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 35, -1));

        jButtonFilterStart.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jButtonFilterStart.setText("开始");
        jButtonFilterStart.setMargin(new java.awt.Insets(2, 8, 2, 8));
        jButtonFilterStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFilterStartActionPerformed(evt);
            }
        });
        jPanelSysFilter.add(jButtonFilterStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 23, -1, -1));

        jButtonFilterCheck.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jButtonFilterCheck.setText("查看");
        jButtonFilterCheck.setEnabled(false);
        jButtonFilterCheck.setMargin(new java.awt.Insets(2, 8, 2, 8));
        jButtonFilterCheck.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonFilterCheckActionPerformed(evt);
            }
        });
        jPanelSysFilter.add(jButtonFilterCheck, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 48, -1, -1));

        jPanelMain.add(jPanelSysFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 305, 290, 90));

        jPanelCongfig.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "定投配置", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("微软雅黑", 0, 12))); // NOI18N
        jPanelCongfig.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTextFieldDiffCoef.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTextFieldDiffCoef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldDiffCoef.setText("1.5");
        jPanelCongfig.add(jTextFieldDiffCoef, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 52, 50, -1));

        jTextFieldSlope.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTextFieldSlope.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldSlope.setText("2.1");
        jPanelCongfig.add(jTextFieldSlope, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 22, 50, -1));

        jLabelSlope.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabelSlope.setText("斜率：");
        jPanelCongfig.add(jLabelSlope, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 25, -1, -1));

        jLabelDiffCoef.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabelDiffCoef.setText("离差因子：");
        jPanelCongfig.add(jLabelDiffCoef, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 55, -1, -1));

        jTextFieldWinLevel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTextFieldWinLevel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldWinLevel.setText("20");
        jPanelCongfig.add(jTextFieldWinLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 52, 50, -1));

        jTextFieldStart.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTextFieldStart.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldStart.setText("410");
        jPanelCongfig.add(jTextFieldStart, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 22, 50, -1));

        jLabelStartPoint.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabelStartPoint.setText("起始值：");
        jPanelCongfig.add(jLabelStartPoint, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 25, -1, -1));

        jLabelWinLevel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabelWinLevel.setText("止赢位：");
        jPanelCongfig.add(jLabelWinLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 55, -1, -1));

        jButtonInvestEva.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jButtonInvestEva.setText("投资评测");
        jButtonInvestEva.setMargin(new java.awt.Insets(2, 8, 2, 8));
        jButtonInvestEva.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInvestEvaActionPerformed(evt);
            }
        });
        jPanelCongfig.add(jButtonInvestEva, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 120, -1, 30));

        jButtonIpoInfo.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jButtonIpoInfo.setText("新股信息");
        jButtonIpoInfo.setMargin(new java.awt.Insets(2, 8, 2, 8));
        jButtonIpoInfo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonIpoInfoActionPerformed(evt);
            }
        });
        jPanelCongfig.add(jButtonIpoInfo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 120, -1, 30));

        jLabelInvestCoef.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabelInvestCoef.setText("定投系数：");
        jPanelCongfig.add(jLabelInvestCoef, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 85, -1, -1));

        jTextFieldInvestCoef.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTextFieldInvestCoef.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldInvestCoef.setText("10");
        jPanelCongfig.add(jTextFieldInvestCoef, new org.netbeans.lib.awtextra.AbsoluteConstraints(225, 82, 50, -1));

        jLabelInvestLevel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabelInvestLevel.setText("投资位比：");
        jPanelCongfig.add(jLabelInvestLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 85, -1, -1));

        jTextFieldInvestLevel.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTextFieldInvestLevel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jTextFieldInvestLevel.setText("0.80");
        jPanelCongfig.add(jTextFieldInvestLevel, new org.netbeans.lib.awtextra.AbsoluteConstraints(85, 82, 50, -1));

        jButtonInvestEva1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jButtonInvestEva1.setText("评测");
        jButtonInvestEva1.setMargin(new java.awt.Insets(2, 8, 2, 8));
        jButtonInvestEva1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonInvestEva1ActionPerformed(evt);
            }
        });
        jPanelCongfig.add(jButtonInvestEva1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 70, 30));

        jPanelMain.add(jPanelCongfig, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 45, 290, 165));

        getContentPane().add(jPanelMain, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 660, 400));

        jMenuFile.setText("文件");

        jMenuItemOpen.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemOpen.setText("打开...");
        jMenuItemOpen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemOpenActionPerformed(evt);
            }
        });
        jMenuFile.add(jMenuItemOpen);

        jMenuBar.add(jMenuFile);

        jMenuRun.setText("运行");

        jMenuItemDZH.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_D, java.awt.event.InputEvent.ALT_MASK));
        jMenuItemDZH.setText("大智慧");
        jMenuItemDZH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDZHActionPerformed(evt);
            }
        });
        jMenuRun.add(jMenuItemDZH);

        jMenuBar.add(jMenuRun);

        jMenuHelp.setText("帮助");

        jMenuItemAbout.setText("关于");
        jMenuItemAbout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAboutActionPerformed(evt);
            }
        });
        jMenuHelp.add(jMenuItemAbout);

        jMenuBar.add(jMenuHelp);

        setJMenuBar(jMenuBar);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemOpenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemOpenActionPerformed
    }//GEN-LAST:event_jMenuItemOpenActionPerformed

    private void jMenuItemDZHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDZHActionPerformed
        runExeFile("C:\\dzh365\\dzh2.exe");
    }//GEN-LAST:event_jMenuItemDZHActionPerformed

    private void jMenuItemAboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAboutActionPerformed
        JOptionPane.showMessageDialog(new JFrame(), "智能证券系统-港股打新 V19.04\n版权所有(C) 张向荣(Aioros Zhang)");
    }//GEN-LAST:event_jMenuItemAboutActionPerformed

    private void jTextAreaMainMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextAreaMainMouseReleased
        if (evt.isPopupTrigger()) {
            jPopupMenu1.show(this, evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_jTextAreaMainMouseReleased

    private void jMenuItemClearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemClearActionPerformed
        jTextAreaMain.setText("");
    }//GEN-LAST:event_jMenuItemClearActionPerformed

    private void jMenuItemCopyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCopyActionPerformed
        jTextAreaMain.selectAll();
        jTextAreaMain.copy();
    }//GEN-LAST:event_jMenuItemCopyActionPerformed

    private void jButtonIpoInfoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonIpoInfoActionPerformed
        importData();
        IpoTable ipoTable = new IpoTable(this, false, ipoInfoList);
    }//GEN-LAST:event_jButtonIpoInfoActionPerformed

    private void jButtonInvestEvaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInvestEvaActionPerformed
        boolean ret = false;

        double start = Double.parseDouble(jTextFieldStart.getText());
        double slope = Double.parseDouble(jTextFieldSlope.getText());
        investLevel = Double.parseDouble(jTextFieldInvestLevel.getText());
        investCoef = Double.parseDouble(jTextFieldInvestCoef.getText());
        strategy = new Strategy(ipoInfoList);

        getInvestMode();
        double p1 = 0, p2 = 0, p3 = 0;
        String[] words = investPara.split(",");
        p1 = Double.parseDouble(words[0]);
        p2 = Double.parseDouble(words[1]);
        p3 = Double.parseDouble(words[2]);

//        ret = strategy.sysEva(p1, p2, p3);
//        if (!ret) {
//            JOptionPane.showMessageDialog(new JFrame(), "无效的参数设置！");
//            return;
//        }
        SystemReport sr = updateSystemReport(strategy);
        updateTable(sr);

        evaluated = true;
    }//GEN-LAST:event_jButtonInvestEvaActionPerformed

    private void jButtonFilterStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFilterStartActionPerformed
        long time = System.currentTimeMillis();
        jTextAreaMain.setText("");

        double start = Double.parseDouble(jTextFieldStart.getText());
        double slope = Double.parseDouble(jTextFieldSlope.getText());

        setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        SystemReport sr;
        ArrayList<SystemReport> srList = new ArrayList<>();
        double ps1 = Double.parseDouble(jTextFieldPS1.getText());
        double pe1 = Double.parseDouble(jTextFieldPE1.getText());
        double ps2 = Double.parseDouble(jTextFieldPS2.getText());
        double pe2 = Double.parseDouble(jTextFieldPE2.getText());
        double ps3 = Double.parseDouble(jTextFieldPS3.getText());
        double pe3 = Double.parseDouble(jTextFieldPE3.getText());

        for (double i = ps1; i <= pe1; i += 10) {
            for (double j = ps2; j <= pe2; j += 0.1) {
                for (double k = ps3; k <= pe3; k += 0.1) {
//                    strategy = new Strategy(this, start, slope);
//                    if (strategy.sysSimpleInvestEva(i, j, k)) {
//                        String para = String.format("%d,%.1f,%.1f", (int) i, j, k);
//                        sr = updateSimpleReport(para, strategy);
//                        srList.add(sr);
//                    }
                }
            }
        }
        setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));

        jButtonFilterCheck.setEnabled(true);
        Collections.sort(srList, (SystemReport arg0, SystemReport arg1) -> new Float(arg1.backTotalEarning).compareTo(arg0.backTotalEarning));
        //rankTable = new RankTable(this, false, this, srList);

        time = System.currentTimeMillis() - time;
        float excTime = (float) time / 1000;
        System.out.println("Elapsed time: " + excTime + "s");
    }//GEN-LAST:event_jButtonFilterStartActionPerformed

    private void jButtonFilterCheckActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonFilterCheckActionPerformed
        //rankTable.setVisible(true);
    }//GEN-LAST:event_jButtonFilterCheckActionPerformed

    private void jButtonInvestEva1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonInvestEva1ActionPerformed
        float blackTotal = 0;
        float openTotal = 0;
        float closeTotal = 0;
        strategy = new Strategy(ipoInfoList);
        for (int i = 1; i < 50; i++) {
            blackTotal = strategy.getBlackWeightRestictEarning((float) i / 10);
            openTotal = strategy.getOpenWeightRestictEarning((float) i / 10);
            closeTotal = strategy.getCloseWeightRestictEarning((float) i / 10);
            msgLogger(i + "\tblack:" + blackTotal + "\topen:" + openTotal + "\tclose:" + closeTotal);
        }
    }//GEN-LAST:event_jButtonInvestEva1ActionPerformed

    /**
     ********************* Start of User-defined function ********************
     */
    protected void importData() {
        try {
            File file = new File(fileIn);
            InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "gbk");
            BufferedReader br = new BufferedReader(isr);
            String[] words = br.readLine().split("\t");
            column = words.length;
            ipoInfoList = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                words = line.split("\t+");
                IpoInfo ipoInfo = new IpoInfo(words[0], words[1]);
                ipoInfo.marketPlate = words[2];
                ipoInfo.offerDate = words[3];
                ipoInfo.handFund = Float.parseFloat(words[4]);
                ipoInfo.luckyRate = Float.parseFloat(words[5].substring(0, words[5].length() - 1));
                ipoInfo.offerPrice = Float.parseFloat(words[6]);
                ipoInfo.superPurchaseMultiples = Float.parseFloat(words[7]);
                ipoInfo.totalRaiseFunds = Float.parseFloat(words[8]);
                ipoInfo.blackGain = Float.parseFloat(words[9].substring(0, words[9].length() - 1));
                ipoInfo.blackPrice = ipoInfo.offerPrice * (1 + ipoInfo.blackGain / 100);
                ipoInfo.closeGain = Float.parseFloat(words[10].substring(0, words[10].length() - 1));
                ipoInfo.openPrice = Float.parseFloat(words[11]);
                ipoInfo.openGain = 100 * (ipoInfo.openPrice - ipoInfo.offerPrice) / ipoInfo.offerPrice;
                ipoInfo.closePrice = Float.parseFloat(words[14]);
                ipoInfoList.add(ipoInfo);
            }
            stocks = ipoInfoList.size();
            br.close();
            isr.close();
        } catch (IOException | NumberFormatException e) {
            e.printStackTrace();
        }

        evaluated = false;
    }

    public void investParaEva(String para) {
        String[] paras = para.replaceAll(" ", "").split(",");
        jTextFieldWinLevel.setText(paras[0]);
        jTextFieldDiffCoef.setText(paras[1]);
        jTextFieldInvestLevel.setText(paras[2]);

        jButtonInvestEva.doClick();
    }

    public void getInvestMode() {
        investPara = jTextFieldWinLevel.getText() + "," + jTextFieldDiffCoef.getText() + "," + jTextFieldInvestLevel.getText();
    }

    protected SystemReport updateSystemReport(Strategy stg) {
        SystemReport sr = new SystemReport();

        sr.backTotalGain = stg.getBlackTotalGain();
        sr.openTotalGain = stg.getOpenTotalGain();
        sr.closeTotalGain = stg.getCloseTotalGain();
        sr.backTotalEarning = stg.getBlackTotalEarning();
        sr.openTotalEarning = stg.getOpenTotalEarning();
        sr.closeTotalEarning = stg.getCloseTotalEarning();
        sr.blackWeightEarning = stg.getBlackWeightEarning();
        sr.openWeightEarning = stg.getOpenWeightEarning();
        sr.closeWeightEarning = stg.getCloseWeightEarning();
        sr.blackWeightRestictEarning = stg.getBlackWeightRestictEarning(1);
        sr.openWeightRestictEarning = stg.getOpenWeightRestictEarning(1);
        sr.closeWeightRestictEarning = stg.getCloseWeightRestictEarning(1);
//        sr.maxInvest = (float) stg.getMaxInvest();
//        sr.meanInvest = (float) stg.getMeanInvest();
//        sr.maxLoss = (float) stg.getMaxLoss();
//        sr.maxLossRatio = (float) stg.getMaxLossRatio();
//        sr.meanDiffRate = (float) stg.getMeanDiffRate();
//        sr.meanNegaDiffRate = (float) stg.getMeanNegaDiffRate();
//        sr.minDiffRate = (float) stg.getMinDiffRate();
//        sr.meanInvestRate = (float) stg.getMeanInvestRate();
//        sr.maxInvestRate = (float) stg.getMaxInvestRate();
        return sr;
    }

    protected SystemReport updateSimpleReport(String para, Strategy stg) {
        SystemReport sr = new SystemReport(para);

        sr.backTotalEarning = (float) stg.getBlackTotalEarning();
        sr.blackWeightRestictEarning = (float) stg.getBlackWeightRestictEarning(1);
        sr.openWeightEarning = (float) stg.getOpenWeightEarning();
        sr.maxInvest = (float) stg.getMaxInvest();
        sr.blackWeightEarning = (float) stg.getBlackWeightEarning();
        sr.meanInvestRate = (float) stg.getMeanInvestRate();
        sr.maxInvestRate = (float) stg.getMaxInvestRate();

        return sr;
    }

    protected void updateTable(SystemReport sr) {
        jTablePoint.setValueAt("暗盘累计涨幅", 0, 0);
        jTablePoint.setValueAt(sr.backTotalGain + "%", 0, 1);
        jTablePoint.setValueAt("开盘累计涨幅", 1, 0);
        jTablePoint.setValueAt(sr.openTotalGain + "%", 1, 1);
        jTablePoint.setValueAt("收盘累计涨幅", 2, 0);
        jTablePoint.setValueAt(sr.closeTotalGain + "%", 2, 1);
        jTablePoint.setValueAt("暗盘累计收益", 3, 0);
        jTablePoint.setValueAt(sr.backTotalEarning + "元", 3, 1);
        jTablePoint.setValueAt("开盘累计收益", 4, 0);
        jTablePoint.setValueAt(sr.openTotalEarning + "元", 4, 1);
        jTablePoint.setValueAt("收盘累计收益", 5, 0);
        jTablePoint.setValueAt(sr.closeTotalEarning + "元", 5, 1);
        jTablePoint.setValueAt("暗盘加权收益", 6, 0);
        jTablePoint.setValueAt(sr.blackWeightEarning + "元", 6, 1);
        jTablePoint.setValueAt("开盘加权收益", 7, 0);
        jTablePoint.setValueAt(sr.openWeightEarning + "元", 7, 1);
        jTablePoint.setValueAt("收盘加权收益", 8, 0);
        jTablePoint.setValueAt(sr.closeWeightEarning + "元", 8, 1);
        jTablePoint.setValueAt("暗盘加权收益R", 9, 0);
        jTablePoint.setValueAt(sr.blackWeightRestictEarning + "元", 9, 1);
        jTablePoint.setValueAt("开盘加权收益R", 10, 0);
        jTablePoint.setValueAt(sr.openWeightRestictEarning + "元", 10, 1);
        jTablePoint.setValueAt("收盘加权收益R", 0, 2);
        jTablePoint.setValueAt(sr.closeWeightRestictEarning + "元", 0, 3);

        jTablePoint.setValueAt("平均持仓期", 1, 2);
        jTablePoint.setValueAt(sr.openWeightRestictEarning + "天", 1, 3);
        jTablePoint.setValueAt("最大投入", 2, 2);
        jTablePoint.setValueAt(sr.maxInvest + "元", 2, 3);
        jTablePoint.setValueAt("平均投入", 3, 2);
        jTablePoint.setValueAt(sr.meanInvest + "元", 3, 3);
        jTablePoint.setValueAt("最大亏损", 4, 2);
        jTablePoint.setValueAt(sr.maxLoss + "元", 4, 3);
        jTablePoint.setValueAt("最大回撤比", 5, 2);
        jTablePoint.setValueAt(sr.maxLossRatio + "%", 5, 3);
        jTablePoint.setValueAt("平均离差", 6, 2);
        jTablePoint.setValueAt(sr.meanDiffRate, 6, 3);
        jTablePoint.setValueAt("平均负离差", 7, 2);
        jTablePoint.setValueAt(sr.meanNegaDiffRate, 7, 3);
        jTablePoint.setValueAt("最小负离差", 8, 2);
        jTablePoint.setValueAt(sr.minDiffRate, 8, 3);
        jTablePoint.setValueAt("平均买入比", 9, 2);
        jTablePoint.setValueAt(sr.meanInvestRate, 9, 3);
        jTablePoint.setValueAt("最大买入比", 10, 2);
        jTablePoint.setValueAt(sr.maxInvestRate, 10, 3);
    }

    public void msgLogger(String str) {
        jTextAreaMain.append(str + System.getProperty("line.separator"));
    }

    public void fileLogger(String str) {
        if (fileWriter != null) {
            try {
                fileWriter.write(str + System.getProperty("line.separator"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void runExeFile(String file) {
        try {
            Runtime rt = Runtime.getRuntime();
            File exeFile = new File(file);
            if (exeFile.exists()) {
                String cmd = "cmd.exe /c " + file;
                rt.exec(cmd);
            } else {
                JOptionPane.showMessageDialog(new JFrame(), "请先安装相关软件到根目录！");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private String fileIn = "data\\others\\港股新股.txt";
    public FileWriter fileWriter;

    public String stockName = "W上证指数";
    public String stockCode = "000001";
    public int column = 0;
    public int stocks = 0;

    public int sIdx = -1;
    public int eIdx = 0;
    public int investDays = 0;
    public double testYears = 0;
    public double investCoef = 1;
    public double investLevel = 1;

    public Strategy strategy;
    public boolean evaluated = false;
    //public RankTable rankTable;
    public String investPara;
    public ArrayList<CheckData> chkDataList;

    ArrayList<IpoInfo> ipoInfoList;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonFilterCheck;
    private javax.swing.JButton jButtonFilterStart;
    private javax.swing.JButton jButtonInvestEva;
    private javax.swing.JButton jButtonInvestEva1;
    private javax.swing.JButton jButtonIpoInfo;
    private javax.swing.JLabel jLabelDash1;
    private javax.swing.JLabel jLabelDash2;
    private javax.swing.JLabel jLabelDash3;
    private javax.swing.JLabel jLabelDash4;
    private javax.swing.JLabel jLabelDiffCoef;
    private javax.swing.JLabel jLabelInvestCoef;
    private javax.swing.JLabel jLabelInvestLevel;
    private javax.swing.JLabel jLabelPara1;
    private javax.swing.JLabel jLabelPara2;
    private javax.swing.JLabel jLabelPara3;
    private javax.swing.JLabel jLabelPara4;
    private javax.swing.JLabel jLabelSlope;
    private javax.swing.JLabel jLabelStartPoint;
    private javax.swing.JLabel jLabelWinLevel;
    private javax.swing.JMenuBar jMenuBar;
    private javax.swing.JMenu jMenuFile;
    private javax.swing.JMenu jMenuHelp;
    private javax.swing.JMenuItem jMenuItemAbout;
    private javax.swing.JMenuItem jMenuItemClear;
    private javax.swing.JMenuItem jMenuItemCopy;
    private javax.swing.JMenuItem jMenuItemDZH;
    private javax.swing.JMenuItem jMenuItemOpen;
    private javax.swing.JMenu jMenuRun;
    private javax.swing.JPanel jPanelCongfig;
    private javax.swing.JPanel jPanelMain;
    private javax.swing.JPanel jPanelSysFilter;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTablePoint;
    private javax.swing.JTextArea jTextAreaMain;
    private javax.swing.JTextField jTextFieldDiffCoef;
    private javax.swing.JTextField jTextFieldInvestCoef;
    private javax.swing.JTextField jTextFieldInvestLevel;
    private javax.swing.JTextField jTextFieldPE1;
    private javax.swing.JTextField jTextFieldPE2;
    private javax.swing.JTextField jTextFieldPE3;
    private javax.swing.JTextField jTextFieldPE4;
    private javax.swing.JTextField jTextFieldPS1;
    private javax.swing.JTextField jTextFieldPS2;
    private javax.swing.JTextField jTextFieldPS3;
    private javax.swing.JTextField jTextFieldPS4;
    private javax.swing.JTextField jTextFieldSlope;
    private javax.swing.JTextField jTextFieldStart;
    private javax.swing.JTextField jTextFieldWinLevel;
    // End of variables declaration//GEN-END:variables
}
