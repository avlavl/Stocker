/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invest;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author zhangxr
 */
public class RankTable extends javax.swing.JDialog {

    /**
     * Creates new form RankTable
     */
    public RankTable(java.awt.Frame parent, boolean modal, MainView mv, ArrayList<SystemReport> list) {
        super(parent, modal);
        initComponents();
        mainView = mv;

        SRList = list;
        rankSize = list.size();
        jTableRank.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 12));
        jTableRank.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        int rows = (rankSize > 20) ? 20 : rankSize;
        String[][] tableContent = new String[rows][9];
        for (int i = 0; i < rows; i++) {
            tableContent[i][0] = "" + (i + 1);
            tableContent[i][1] = "" + SRList.get(i).parameter;
            tableContent[i][2] = "" + SRList.get(i).addInvest;
            tableContent[i][3] = "" + SRList.get(i).netProfit;
            tableContent[i][4] = String.format("万%.3f", SRList.get(i).meanDailyRate);
            tableContent[i][5] = String.format("%.2f天", SRList.get(i).meanPositionDays);
            tableContent[i][6] = String.format("%.2f", SRList.get(i).maxInvest);
            tableContent[i][7] = String.format("%.2f年", SRList.get(i).maxRoundTime);
            tableContent[i][8] = String.format("%.2f", SRList.get(i).maxLoss);
        }

        jTableRank.setModel(new javax.swing.table.DefaultTableModel(
                tableContent,
                new String[]{
                    "排名", "参数", "累加投入", "净利润", "平均日化", "平均仓期", "最大投入", "最长周期", "最大亏损"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        jScrollPaneRank.setViewportView(jTableRank);
        if (jTableRank.getColumnModel().getColumnCount() > 0) {
            jTableRank.getColumnModel().getColumn(0).setMaxWidth(38);
            jTableRank.getColumnModel().getColumn(1).setMinWidth(90);
            jTableRank.getColumnModel().getColumn(7).setMaxWidth(62);
        }
        setPreferredSize(new java.awt.Dimension(690, 20 * rows + 88));
        jScrollPaneRank.setPreferredSize(new java.awt.Dimension(652, 20 * rows + 30));
        getContentPane().add(jScrollPaneRank, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, -1, -1));
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public void updateTable(int idx) {
        int rows = (rankSize > 20) ? 20 : rankSize;
        for (int i = 0; i < rows; i++) {
            jTableRank.setValueAt(i + idx + 1, i, 0);
            jTableRank.setValueAt(SRList.get(i + idx).parameter, i, 1);
            jTableRank.setValueAt(SRList.get(i + idx).addInvest, i, 2);
            jTableRank.setValueAt(SRList.get(i + idx).netProfit, i, 3);
            jTableRank.setValueAt(String.format("万%.3f", SRList.get(i + idx).meanDailyRate), i, 4);
            jTableRank.setValueAt(String.format("%.2f天", SRList.get(i + idx).meanPositionDays), i, 5);
            jTableRank.setValueAt(String.format("%.2f", SRList.get(i + idx).maxInvest), i, 6);
            jTableRank.setValueAt(String.format("%.2f年", SRList.get(i + idx).maxRoundTime), i, 7);
            jTableRank.setValueAt(String.format("%.2f", SRList.get(i + idx).maxLoss), i, 8);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPaneRank = new javax.swing.JScrollPane();
        jTableRank = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("交易系统排名");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneRank.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jScrollPaneRank.setPreferredSize(new java.awt.Dimension(452, 20));

        jTableRank.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableRank.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 12));
        jTableRank.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTableRank.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "排名", "参数", "累加投入", "净利润", "平均日化", "平均仓期", "最大投入", "最长周期", "最大亏损"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableRank.setRowHeight(20);
        jTableRank.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRankHeaderMouseClicked(evt);
            }
        });
        jTableRank.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jTableRankMouseWheelMoved(evt);
            }
        });
        jTableRank.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableRankMouseClicked(evt);
            }
        });
        jScrollPaneRank.setViewportView(jTableRank);
        if (jTableRank.getColumnModel().getColumnCount() > 0) {
            jTableRank.getColumnModel().getColumn(0).setMaxWidth(38);
            jTableRank.getColumnModel().getColumn(1).setMinWidth(90);
            jTableRank.getColumnModel().getColumn(7).setMaxWidth(62);
        }

        getContentPane().add(jScrollPaneRank, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 662, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableRankMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableRankMouseClicked
        jTableRank.setColumnSelectionAllowed(false);
        jTableRank.setRowSelectionAllowed(true);
        if (evt.getClickCount() > 1) {
            int index = jTableRank.getSelectedRow();
            String para = (String) jTableRank.getValueAt(index, 1);
            mainView.investParaEva(para);
        }
    }//GEN-LAST:event_jTableRankMouseClicked

    private void jTableRankMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jTableRankMouseWheelMoved
        if (evt.getWheelRotation() > 0) {
            if (rankIndex < rankSize - 20) {
                rankIndex += 1;
                updateTable(rankIndex);
            }
        } else if (rankIndex > 0) {
            rankIndex -= 1;
            updateTable(rankIndex);
        }
    }//GEN-LAST:event_jTableRankMouseWheelMoved

    private void jTableRankHeaderMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() > 1) {
            int index = jTableRank.columnAtPoint(evt.getPoint());
            jTableRank.setColumnSelectionAllowed(true);
            jTableRank.setRowSelectionAllowed(false);
            jTableRank.setColumnSelectionInterval(index, index);
            switch (index) {
                case 2:
                    Collections.sort(SRList, (SystemReport arg0, SystemReport arg1) -> new Float(arg1.addInvest).compareTo(arg0.addInvest));
                    break;
                case 3:
                    Collections.sort(SRList, (SystemReport arg0, SystemReport arg1) -> new Float(arg1.netProfit).compareTo(arg0.netProfit));
                    break;
                case 4:
                    Collections.sort(SRList, (SystemReport arg0, SystemReport arg1) -> new Float(arg1.meanDailyRate).compareTo(new Float(arg0.meanDailyRate)));
                    break;
                case 5:
                    Collections.sort(SRList, (SystemReport arg0, SystemReport arg1) -> new Float(arg1.meanPositionDays).compareTo(arg0.meanPositionDays));
                    break;
                case 6:
                    Collections.sort(SRList, (SystemReport arg0, SystemReport arg1) -> new Float(arg1.maxInvest).compareTo(arg0.maxInvest));
                    break;
                case 7:
                    Collections.sort(SRList, (SystemReport arg0, SystemReport arg1) -> new Float(arg1.maxRoundTime).compareTo(arg0.maxRoundTime));
                    break;
                case 8:
                    Collections.sort(SRList, (SystemReport arg0, SystemReport arg1) -> new Float(arg1.maxLoss).compareTo(arg0.maxLoss));
                    break;
                default:
                    break;
            }
            rankIndex = 0;
            updateTable(rankIndex);
        }
    }

    private MainView mainView;
    private ArrayList<SystemReport> SRList;
    private int rankIndex = 0;
    private int rankSize = 1;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPaneRank;
    private javax.swing.JTable jTableRank;
    // End of variables declaration//GEN-END:variables
}
