/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trader;

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
        String[][] tableContent = new String[times * 2][7];
        for (int i = 0; i < times; i++) {
            String bDate = mv.dateList.get(mv.bpIndexList.get(i));
            String sDate = mv.dateList.get(mv.spIndexList.get(i));
            double bPrice = mv.priceList.get(mv.bpIndexList.get(i));
            double sPrice = mv.priceList.get(mv.spIndexList.get(i));
            double ratio = (mode == 0) ? 1 : ((double) 10000 / bPrice);
            double agio = (sPrice - bPrice) * ratio;
            double yield = 100 * (sPrice - bPrice) / bPrice;
            double sCash = mv.fundList.get(mv.bpIndexList.get(i)) - (bPrice * ratio);
            double eCash = mv.fundList.get(mv.spIndexList.get(i));

            tableContent[2 * i][0] = bDate;
            tableContent[2 * i][1] = "买入";
            tableContent[2 * i][2] = bPrice + "元";
            tableContent[2 * i][3] = "*";
            tableContent[2 * i][4] = "*";
            tableContent[2 * i][5] = "*";
            tableContent[2 * i][6] = (float) sCash + "元";

            tableContent[2 * i + 1][0] = sDate;
            tableContent[2 * i + 1][1] = "卖出";
            tableContent[2 * i + 1][2] = sPrice + "元";
            tableContent[2 * i + 1][3] = (agio > 0) ? "盈利" : "亏损";
            tableContent[2 * i + 1][4] = (float) agio + "元";
            tableContent[2 * i + 1][5] = (float) yield + "%";
            tableContent[2 * i + 1][6] = (float) eCash + "元";
        }
        jTableTrade.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 13));
        jTableTrade.setModel(new javax.swing.table.DefaultTableModel(
                tableContent,
                new String[]{
                    "交易日期", "类型", "价格", "盈亏", "收益", "收益率", "可用资金"
                }) {

            Class[] types = new Class[]{
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            @Override
            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });

        jScrollPaneTrade.setViewportView(jTableTrade);
        if (jTableTrade.getColumnModel().getColumnCount() > 0) {
            jTableTrade.getColumnModel().getColumn(0).setPreferredWidth(75);
            jTableTrade.getColumnModel().getColumn(0).setMaxWidth(75);
            jTableTrade.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableTrade.getColumnModel().getColumn(1).setMaxWidth(40);
            jTableTrade.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTableTrade.getColumnModel().getColumn(3).setMaxWidth(40);
        }

        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
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

        jScrollPaneTrade.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        jTableTrade.setDefaultRenderer(Object.class, tcr);
        jTableTrade.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableTrade.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 12));
        jTableTrade.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTableTrade.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "交易日期", "类型", "价格", "盈亏", "收益", "收益率", "可用资金"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableTrade.setRowHeight(20);
        jScrollPaneTrade.setViewportView(jTableTrade);
        if (jTableTrade.getColumnModel().getColumnCount() > 0) {
            jTableTrade.getColumnModel().getColumn(0).setPreferredWidth(75);
            jTableTrade.getColumnModel().getColumn(0).setMaxWidth(75);
            jTableTrade.getColumnModel().getColumn(1).setPreferredWidth(40);
            jTableTrade.getColumnModel().getColumn(1).setMaxWidth(40);
            jTableTrade.getColumnModel().getColumn(3).setPreferredWidth(40);
            jTableTrade.getColumnModel().getColumn(3).setMaxWidth(40);
        }

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneTrade, javax.swing.GroupLayout.PREFERRED_SIZE, 530, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPaneTrade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPaneTrade;
    private javax.swing.JTable jTableTrade;
    // End of variables declaration//GEN-END:variables
}
