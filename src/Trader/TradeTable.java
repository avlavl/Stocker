/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trader;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Aioros
 */
public class TradeTable extends javax.swing.JDialog {

    /**
     * Creates new form tradeTable
     *
     * @param parent
     * @param modal
     */
    public TradeTable(java.awt.Frame parent, boolean modal, MainView mv) {
        super(parent, modal);
        initComponents();

        int mode = mv.brmMode;
        int times = mv.bpIndexList.size();
        String[][] tableContent = new String[times * 2][8];
        for (int i = 0; i < times; i++) {
            String bDate = mv.dateList.get(mv.bpIndexList.get(i));
            String sDate = mv.dateList.get(mv.spIndexList.get(i));
            double bPrice = mv.priceList.get(mv.bpIndexList.get(i));
            double sPrice = mv.priceList.get(mv.spIndexList.get(i));
            double ratio = (mode == 0) ? 1 : ((double) 10000 / bPrice);
            double agio = (sPrice - bPrice) * ratio;
            double yield = 100 * (sPrice - bPrice) / bPrice;
            int days = mv.daysBetween(mv.bpIndexList.get(i), mv.spIndexList.get(i));
            double sCash = mv.fundList.get(mv.bpIndexList.get(i)) - (bPrice * ratio);
            double eCash = mv.fundList.get(mv.spIndexList.get(i));

            tableContent[2 * (times - i) - 2][0] = sDate;
            tableContent[2 * (times - i) - 2][1] = "卖出";
            tableContent[2 * (times - i) - 2][2] = sPrice + "元";
            tableContent[2 * (times - i) - 2][3] = (agio > 0) ? "盈利" : "亏损";
            tableContent[2 * (times - i) - 2][4] = (float) agio + "元";
            tableContent[2 * (times - i) - 2][5] = (float) yield + "%";
            tableContent[2 * (times - i) - 2][6] = days + "天";
            tableContent[2 * (times - i) - 2][7] = (float) eCash + "元";

            tableContent[2 * (times - i) - 1][0] = bDate;
            tableContent[2 * (times - i) - 1][1] = "买入";
            tableContent[2 * (times - i) - 1][2] = bPrice + "元";
            tableContent[2 * (times - i) - 1][3] = "*";
            tableContent[2 * (times - i) - 1][4] = "*";
            tableContent[2 * (times - i) - 1][5] = "*";
            tableContent[2 * (times - i) - 1][6] = "*";
            tableContent[2 * (times - i) - 1][7] = (float) sCash + "元";
        }
        jTableTrade.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 13));
        jTableTrade.setModel(new javax.swing.table.DefaultTableModel(
                tableContent,
                new String[]{
                    "交易日期", "类型", "价格", "盈亏", "收益", "收益率", "持仓时间", "可用资金"
                }) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTableTrade.setRowHeight(20);
        jScrollPaneTrade.setViewportView(jTableTrade);
        if (jTableTrade.getColumnModel().getColumnCount() > 0) {
            jTableTrade.getColumnModel().getColumn(0).setPreferredWidth(75);
            jTableTrade.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableTrade.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTableTrade.getColumnModel().getColumn(5).setPreferredWidth(80);
            jTableTrade.getColumnModel().getColumn(6).setPreferredWidth(60);
        }

        setTableRowColor(jTableTrade, new Color(200, 0, 0), new Color(0, 130, 2));

        setPreferredSize(new java.awt.Dimension(635, 40 * (times < 10 ? times : 10) + 90));
        jScrollPaneTrade.setPreferredSize(new java.awt.Dimension(522, 40 * (times < 10 ? times : 10) + 31));
        getContentPane().add(jScrollPaneTrade, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 600, -1));
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public static void setTableRowColor(javax.swing.JTable Table, final Color color1, final Color color2) {
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(javax.swing.JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    String status = (String) Table.getValueAt(row, 3);
                    if (status.equals("盈利")) {
                        setForeground(color1);
                    } else if (status.equals("亏损")) {
                        setForeground(color2);
                    } else {
                        setForeground(Color.BLACK);
                    }
                    return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                }
            };
            for (int i = 0; i < Table.getColumnCount(); i++) {
                Table.getColumnModel().getColumn(i).setCellRenderer(tcr);
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        jScrollPaneTrade = new javax.swing.JScrollPane();
        jTableTrade = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("交易记录");
        setPreferredSize(new java.awt.Dimension(550, 70));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneTrade.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jScrollPaneTrade.setPreferredSize(new java.awt.Dimension(452, 20));

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        jTableTrade.setDefaultRenderer(Object.class, tcr);
        jTableTrade.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableTrade.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 12));
        jTableTrade.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTableTrade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "交易日期", "类型", "价格", "盈亏", "收益", "收益率", "持仓时间", "可用资金"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTrade.setRowHeight(20);
        jScrollPaneTrade.setViewportView(jTableTrade);
        if (jTableTrade.getColumnModel().getColumnCount() > 0) {
            jTableTrade.getColumnModel().getColumn(0).setPreferredWidth(75);
            jTableTrade.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableTrade.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTableTrade.getColumnModel().getColumn(5).setPreferredWidth(80);
            jTableTrade.getColumnModel().getColumn(6).setPreferredWidth(60);
        }

        getContentPane().add(jScrollPaneTrade, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 600, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPaneTrade;
    private javax.swing.JTable jTableTrade;
    // End of variables declaration//GEN-END:variables
}
