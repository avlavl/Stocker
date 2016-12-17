/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invest;

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
public class InvestChart extends javax.swing.JDialog {

    /**
     * Creates new form PriceChart
     */
    public InvestChart(java.awt.Frame parent, boolean modal, MainView mv) {
        super(parent, modal);
        initComponents();

        setLocationRelativeTo(parent);
        final URL filename = getClass().getResource("res/Livermore2.jpg");
        if (filename != null) {
            setIconImage(new ImageIcon(filename, "Icon").getImage());
        }
        setVisible(true);

        mainView = mv;
        pList = mainView.priceList;
        strategy = mainView.strategy;
        items = pList.size();

        jPanel_xyChart = new javax.swing.JPanel() {

            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                int counts = (items > xplots) ? xplots : items;

                /* Background */
                g.setColor(Color.BLACK);
                g.fillRect(0, 0, xplots, yplots);
                g.setColor(new Color(0, 50, 50));
                for (int i = 0; i < 10; i++) {
                    g.drawLine(0, (yplots / 10) * i, xplots, (yplots / 10) * i);
                    g.drawLine((xplots / 10) * i, 0, (xplots / 10) * i, yplots);
                }

                /* Headline bar */
                g.setColor(Color.LIGHT_GRAY);
                g.drawString(mainView.stockName, 0, 15);
                g.setColor(Color.PINK);
                g.drawString(String.format("开始:"), 100, 15);
                g.drawString(String.format("最高:"), 200, 15);
                g.drawString(String.format("最低:"), 300, 15);
                g.drawString(String.format("结束:"), 400, 15);
                g.drawString(String.format("涨幅:"), 500, 15);
                double si = pList.get(offset);
                double ei = pList.get(offset + counts - 1);
                double hi = Collections.max(pList.subList(offset, offset + counts));
                double li = Collections.min(pList.subList(offset, offset + counts));
                double ri = (ei / si - 1) * 100;
                g.setColor(Color.WHITE);
                g.drawString(String.format("%4.2f", si), 130, 15);
                g.drawString(String.format("%4.2f", hi), 230, 15);
                g.drawString(String.format("%4.2f", li), 330, 15);
                g.drawString(String.format("%4.2f", ei), 430, 15);
                g.drawString(String.format("%4.2f%%", ri), 530, 15);

                /* Main line chart */
                for (int i = 1; i < counts; i++) {
                    if (mainView.evaluated) {
                        if (strategy.weights[offset + i] > 0) {
                            g.setColor(Color.GREEN);
                        } else if (strategy.weights[offset + i] < 0) {
                            g.setColor(Color.RED);
                        } else if ((g.getColor() == Color.RED) || (g.getColor() == Color.GRAY)) {
                            g.setColor(Color.GRAY);
                        } else {
                            g.setColor(Color.BLUE);
                        }
                    }
                    int ystart = yplots - (int) Math.round(pList.get(offset + i - 1) / scalar);
                    int yend = yplots - (int) Math.round(pList.get(offset + i) / scalar);
                    g.drawLine(i - 1, ystart, i, yend);
                    if (g.getColor() == Color.RED) {
                        g.drawLine(i - 4, yend, i, yend);
                    }
                }
                if (mainView.evaluated) {
                    g.setColor(Color.YELLOW);
                    for (int i = 1; i < counts; i++) {
                        int ystart = yplots - (int) Math.round(strategy.basePoints[offset + i - 1] / scalar);
                        int yend = yplots - (int) Math.round(strategy.basePoints[offset + i] / scalar);
                        g.drawLine(i - 1, ystart, i, yend);
                    }
                }

                /* Mouse click content */
                if ((mainView.evaluated) && (mouseFlag)) {
                    if (coordx > items - 1) {
                        coordx = items - 1;
                    }
                    g.setColor(Color.WHITE);
                    g.drawLine(0, coordy, xplots, coordy);
                    g.drawLine(coordx, 0, coordx, yplots);
                    g.setColor(Color.PINK);
                    g.drawString(String.format("日期:"), 600, 15);
                    g.drawString(String.format("收盘:"), 700, 15);
                    g.drawString(String.format("赢亏:"), 800, 15);
                    g.setColor(Color.WHITE);
                    g.drawString(mainView.dateList.get(offset + coordx), 630, 15);
                    g.drawString(String.format("%4.2f", mainView.priceList.get(offset + coordx)), 730, 15);
                    double profit = strategy.profitRatios[offset + coordx];
                    g.drawString(String.format("%4.2f%%", 100 * profit), 830, 15);
                }

                /* Bottom date */
                g.setColor(Color.ORANGE);
                for (int i = 0; i < 10; i++) {
                    if (offset + i * 100 < items) {
                        g.drawString(mainView.dateList.get(offset + i * 100), i * 100, yplots);
                    }
                }

                /* Left price */
                g.setColor(new Color(250, 20, 5));
                for (int i = 0; i < 10; i++) {
                    g.drawString(String.format("%d", (int) (scalar * 50 * (10 - i))), 0, i * 50);
                }
            }
        };

        xplots = jPanel_xyCan.getWidth();
        yplots = jPanel_xyCan.getHeight();
        if (items > xplots) {
            offset = items - xplots;
        }
        jPanel_xyChart.setBounds(0, 0, xplots, yplots);
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
        jLabelScale = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("投资曲线");
        setMinimumSize(new java.awt.Dimension(1024, 570));
        setPreferredSize(new java.awt.Dimension(1030, 600));
        setResizable(false);
        setSize(new java.awt.Dimension(1024, 600));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel_xyCan.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel_xyCan.setPreferredSize(new java.awt.Dimension(1000, 500));
        jPanel_xyCan.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jPanel_xyCanMouseMoved(evt);
            }
        });
        jPanel_xyCan.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jPanel_xyCanMouseWheelMoved(evt);
            }
        });
        jPanel_xyCan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanel_xyCanMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel_xyCanLayout = new javax.swing.GroupLayout(jPanel_xyCan);
        jPanel_xyCan.setLayout(jPanel_xyCanLayout);
        jPanel_xyCanLayout.setHorizontalGroup(
            jPanel_xyCanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 996, Short.MAX_VALUE)
        );
        jPanel_xyCanLayout.setVerticalGroup(
            jPanel_xyCanLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 496, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel_xyCan, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 12, 1000, 500));

        buttonGroup1.add(jRadioButtonDay);
        jRadioButtonDay.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jRadioButtonDay.setText("日");
        jRadioButtonDay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonDayActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonDay, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, -1, -1));

        buttonGroup1.add(jRadioButtonMonth);
        jRadioButtonMonth.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jRadioButtonMonth.setText("月");
        jRadioButtonMonth.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonMonthActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonMonth, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 530, -1, -1));

        buttonGroup1.add(jRadioButtonQuar);
        jRadioButtonQuar.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jRadioButtonQuar.setSelected(true);
        jRadioButtonQuar.setText("季");
        jRadioButtonQuar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonQuarActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonQuar, new org.netbeans.lib.awtextra.AbsoluteConstraints(84, 530, -1, -1));

        buttonGroup1.add(jRadioButtonYear);
        jRadioButtonYear.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jRadioButtonYear.setText("年");
        jRadioButtonYear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButtonYearActionPerformed(evt);
            }
        });
        getContentPane().add(jRadioButtonYear, new org.netbeans.lib.awtextra.AbsoluteConstraints(121, 530, -1, -1));

        jComboBoxScale.setFont(new java.awt.Font("Dialog", 0, 12)); // NOI18N
        jComboBoxScale.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "10", "20", "50", "100", "250", "500", "1000", "2000", "3000", "4000", "5000", "6000", "8000", "10000", "15000", "20000", "30000" }));
        jComboBoxScale.setSelectedIndex(13);
        jComboBoxScale.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxScaleActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBoxScale, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 530, -1, -1));

        jLabelScale.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jLabelScale.setText("范围：");
        getContentPane().add(jLabelScale, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 530, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jPanel_xyCanMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jPanel_xyCanMouseWheelMoved
        if (items > xplots) {
            if (evt.getWheelRotation() > 0) {
                offset += step;
                if (offset > items - xplots) {
                    offset = items - xplots;
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

    private void jPanel_xyCanMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_xyCanMouseMoved
        coordx = evt.getX();
        coordy = evt.getY();
        if (mouseFlag) {
            jPanel_xyChart.updateUI();
        }
    }//GEN-LAST:event_jPanel_xyCanMouseMoved

    private void jPanel_xyCanMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanel_xyCanMouseClicked
        if (evt.getClickCount() > 1) {
            mouseFlag = true;
        } else {
            mouseFlag = false;
        }
        jPanel_xyChart.updateUI();
    }//GEN-LAST:event_jPanel_xyCanMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> jComboBoxScale;
    private javax.swing.JLabel jLabelScale;
    private javax.swing.JPanel jPanel_xyCan;
    private javax.swing.JRadioButton jRadioButtonDay;
    private javax.swing.JRadioButton jRadioButtonMonth;
    private javax.swing.JRadioButton jRadioButtonQuar;
    private javax.swing.JRadioButton jRadioButtonYear;
    // End of variables declaration//GEN-END:variables

    public javax.swing.JPanel jPanel_xyChart;
    public int xplots = 0;
    public int yplots = 0;
    public double scalar = 20;
    private final double[] scalar_buf = {0.02, 0.04, 0.1, 0.2, 0.5, 1, 2, 4, 6, 8, 10, 12, 16, 20, 30, 40, 60};

    protected MainView mainView;
    public ArrayList<Double> pList = new ArrayList<>();
    public Strategy strategy;
    private int items = 0;
    private int offset = 0;
    private int step = 63;
    private int coordx = 0;
    private int coordy = 0;
    private boolean mouseFlag = false;
}
