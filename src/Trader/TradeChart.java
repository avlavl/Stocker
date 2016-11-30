/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trader;

import java.awt.Color;
import java.awt.Graphics;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;

/**
 *
 * @author zhangxr
 */
public class TradeChart extends javax.swing.JDialog {

    /**
     * Creates new form PriceChart
     */
    public TradeChart(java.awt.Frame parent, boolean modal, MainView mv) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(parent);
        final URL filename = getClass().getResource("res/Livermore2.jpg");
        if (filename != null) {
            setIconImage(new ImageIcon(filename, "Icon").getImage());
        }
        setVisible(true);

        mainView = mv;
        pList = (mv.gradeFlag == 0) ? mv.priceList : mv.priceList2;
        fundList = mainView.fundList;
        units = pList.size();

        jPanel_xyChart = new javax.swing.JPanel() {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int counts = (units > xplots) ? xplots : units;

                g.setColor(Color.BLACK);
                g.fillRect(0, 0, xplots, yplots);
                g.setColor(new Color(0, 50, 50));
                for (int i = 0; i < 10; i++) {
                    g.drawLine(0, (yplots / 10) * i, xplots, (yplots / 10) * i);
                    g.drawLine((xplots / 10) * i, 0, (xplots / 10) * i, yplots);
                }

                g.setColor(Color.LIGHT_GRAY);
                g.drawString(mainView.stockName, 0, 15);

                g.setColor(Color.MAGENTA);
                g.drawString(String.format("开始:"), 100, 15);
                g.drawString(String.format("结束:"), 250, 15);
                g.drawString(String.format("最高:"), 400, 15);
                g.drawString(String.format("最低:"), 550, 15);
                g.drawString(String.format("涨幅:"), 700, 15);
                double si = pList.get(offset);
                double ei = pList.get(offset + counts - 1);
                double hi = Collections.max(pList.subList(offset, offset + counts));
                double li = Collections.min(pList.subList(offset, offset + counts));
                double ri = (ei / si - 1) * 100;
                g.setColor(Color.WHITE);
                g.drawString(String.format("%4.2f", si), 130, 15);
                g.drawString(String.format("%4.2f", ei), 280, 15);
                g.drawString(String.format("%4.2f", hi), 430, 15);
                g.drawString(String.format("%4.2f", li), 580, 15);
                g.drawString(String.format("%4.2f%%", ri), 730, 15);

                if (mainView.evaluated) {
                    g.setColor(Color.GREEN);
                    for (int i = 0; i < mainView.bpIndexList.size(); i++) {
                        if ((offset >= mainView.bpIndexList.get(i)) && (offset < mainView.spIndexList.get(i))) {
                            g.setColor(Color.RED);
                            break;
                        }
                    }
                }
                for (int i = 0; i < counts - 1; i++) {
                    if (mainView.evaluated) {
                        if (mainView.bpIndexList.contains(offset + i)) {
                            g.setColor(Color.RED);
                        }
                        if (mainView.spIndexList.contains(offset + i)) {
                            g.setColor(Color.GREEN);
                        }
                    }
                    int ystart = yplots - (int) Math.round(pList.get(offset + i) / scalar);
                    int yend = yplots - (int) Math.round(pList.get(offset + i + 1) / scalar);
                    if (ystart >= yplots - 2) {
                        ystart = yplots - 2;
                    }
                    if (yend >= yplots - 2) {
                        yend = yplots - 2;
                    }
                    g.drawLine(i, ystart, i + 1, yend);
                }
                if (mainView.evaluated) {
                    double sf = fundList.get(offset);
                    double ef = fundList.get(offset + counts - 1);
                    double hf = Collections.max(fundList.subList(offset, offset + counts));
                    double lf = Collections.min(fundList.subList(offset, offset + counts));
                    double rf = (ef / sf - 1) * 100;
                    g.setColor(new Color(255, 255, 150));
                    g.drawString(String.format("%4.2f", sf), 180, 15);
                    g.drawString(String.format("%4.2f", ef), 330, 15);
                    g.drawString(String.format("%4.2f", hf), 480, 15);
                    g.drawString(String.format("%4.2f", lf), 630, 15);
                    g.drawString(String.format("%4.2f%%", rf), 780, 15);
                    for (int i = 0; i < counts - 1; i++) {
                        int ystart = yplots - (int) Math.round(fundList.get(offset + i) / scalar);
                        int yend = yplots - (int) Math.round(fundList.get(offset + i + 1) / scalar);
                        if (ystart >= yplots - 2) {
                            ystart = yplots - 2;
                        }
                        if (yend >= yplots - 2) {
                            yend = yplots - 2;
                        }
                        g.drawLine(i, ystart, i + 1, yend);
                    }
                }

                g.setColor(Color.YELLOW);
                for (int i = 0; i < 10; i++) {
                    if (offset + i * 100 < units) {
                        g.drawString(mainView.dateList.get(offset + i * 100), i * 100, yplots);
                    }
                }
                g.setColor(new Color(250, 20, 5));
                for (int i = 0; i < 10; i++) {
                    g.drawString(String.format("%d", (int) (scalar * 50 * (10 - i))), 0, i * 50);
                }
            }
        };

        xplots = jPanel_xyCan.getWidth() - 4;
        yplots = jPanel_xyCan.getHeight() - 4;
        if (units > xplots) {
            offset = units - xplots;
        }
        jPanel_xyChart.setBounds(2, 2, xplots, yplots);
        jPanel_xyCan.add(jPanel_xyChart);
        jPanel_xyChart.updateUI();
        jPanel_xyCan.updateUI();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel_xyCan = new javax.swing.JPanel();
        jRadioButtonDay = new javax.swing.JRadioButton();
        jRadioButtonMonth = new javax.swing.JRadioButton();
        jRadioButtonQuar = new javax.swing.JRadioButton();
        jRadioButtonYear = new javax.swing.JRadioButton();
        jComboBoxScale = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("交易曲线");
        setMinimumSize(new java.awt.Dimension(1024, 570));
        setPreferredSize(new java.awt.Dimension(1030, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_xyCan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel_xyCan.setPreferredSize(new java.awt.Dimension(1004, 504));
        jPanel_xyCan.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jPanel_xyCanMouseWheelMoved(evt);
            }
        });

        javax.swing.GroupLayout jPanel_xyCanLayout = new javax.swing.GroupLayout(jPanel_xyCan);
        jPanel_xyCan.setLayout(jPanel_xyCanLayout);
        jPanel_xyCanLayout.setHorizontalGroup(
            jPanel_xyCanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1000, Short.MAX_VALUE)
        );
        jPanel_xyCanLayout.setVerticalGroup(
            jPanel_xyCanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel_xyCan, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1004, 504));

        buttonGroup1.add(jRadioButtonDay);
        jRadioButtonDay.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jRadioButtonDay.setText("日");
        jRadioButtonDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDayActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 538, -1, -1));

        buttonGroup1.add(jRadioButtonMonth);
        jRadioButtonMonth.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jRadioButtonMonth.setText("月");
        jRadioButtonMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMonthActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 538, -1, -1));

        buttonGroup1.add(jRadioButtonQuar);
        jRadioButtonQuar.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jRadioButtonQuar.setSelected(true);
        jRadioButtonQuar.setText("季");
        jRadioButtonQuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonQuarActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonQuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 538, -1, -1));

        buttonGroup1.add(jRadioButtonYear);
        jRadioButtonYear.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jRadioButtonYear.setText("年");
        jRadioButtonYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonYearActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 538, -1, -1));

        jComboBoxScale.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jComboBoxScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "250", "500", "1000", "2000", "3000", "4000", "5000", "6000", "8000", "10000", "15000", "20000", "30000" }));
        jComboBoxScale.setSelectedIndex(11);
        jComboBoxScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxScaleActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxScale, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 540, -1, -1));

        jLabel1.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabel1.setText("范围：");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 540, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_xyCanMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jPanel_xyCanMouseWheelMoved
        if (units > xplots) {
            if (evt.getWheelRotation() > 0) {
                offset += step;
                if (offset > units - xplots) {
                    offset = units - xplots;
                }
            } else {
                offset -= step;
                if (offset < 0) {
                    offset = 0;
                }
            }

            jPanel_xyChart.updateUI();
            jPanel_xyCan.updateUI();
        }
    }//GEN-LAST:event_jPanel_xyCanMouseWheelMoved

    private void jRadioButtonDayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonDayActionPerformed
        step = 1;
    }//GEN-LAST:event_jRadioButtonDayActionPerformed

    private void jRadioButtonMonthActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonMonthActionPerformed
        step = 21;
    }//GEN-LAST:event_jRadioButtonMonthActionPerformed

    private void jRadioButtonQuarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonQuarActionPerformed
        step = 63;
    }//GEN-LAST:event_jRadioButtonQuarActionPerformed

    private void jRadioButtonYearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButtonYearActionPerformed
        step = 245;
    }//GEN-LAST:event_jRadioButtonYearActionPerformed

    private void jComboBoxScaleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxScaleActionPerformed
        int index = jComboBoxScale.getSelectedIndex();
        scalar = scalar_buf[index];
        jPanel_xyChart.updateUI();
    }//GEN-LAST:event_jComboBoxScaleActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBoxScale;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel_xyCan;
    private javax.swing.JRadioButton jRadioButtonDay;
    private javax.swing.JRadioButton jRadioButtonMonth;
    private javax.swing.JRadioButton jRadioButtonQuar;
    private javax.swing.JRadioButton jRadioButtonYear;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JPanel jPanel_xyChart;
    public int xplots = 0;
    public int yplots = 0;
    public double scalar = 12;
    private final double[] scalar_buf = {0.02, 0.04, 0.1, 0.2, 0.5, 1, 2, 4, 6, 8, 10, 12, 16, 20, 30, 40, 60};

    protected MainView mainView;
    public ArrayList<Double> pList = new ArrayList<>();
    public ArrayList<Double> fundList = new ArrayList<>();
    private int units = 0;
    private int offset = 0;
    private int step = 63;
}
