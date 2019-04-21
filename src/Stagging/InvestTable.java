/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stagging;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Aioros
 */
public class InvestTable extends javax.swing.JDialog {

    /**
     * Creates new form InvestTable
     *
     * @param parent
     * @param modal
     */
    public InvestTable(java.awt.Frame parent, boolean modal, ArrayList<IpoInfo> list) {
        super(parent, modal);
        initComponents();

        ipoInfoList = list;
        lists = list.size();
        String[][] tableContent = new String[lists][20];
        for (int i = 0; i < lists; i++) {
            tableContent[i][0] = ipoInfoList.get(i).stockCode;
            tableContent[i][1] = ipoInfoList.get(i).stockName;
            tableContent[i][2] = ipoInfoList.get(i).comparableCompany;
            tableContent[i][3] = ipoInfoList.get(i).offerDate;
            tableContent[i][4] = ipoInfoList.get(i).marketPlate;
            tableContent[i][5] = ipoInfoList.get(i).inquiryRange;
            tableContent[i][6] = ipoInfoList.get(i).luckyRate + "%";
            tableContent[i][7] = ipoInfoList.get(i).offerPe;
            tableContent[i][8] = ipoInfoList.get(i).superPurchaseMultiples + "";
            tableContent[i][9] = ipoInfoList.get(i).totalRaiseFunds + "";
            tableContent[i][10] = ipoInfoList.get(i).handFund + "";
            tableContent[i][11] = ipoInfoList.get(i).offerPrice + "";
            tableContent[i][12] = ipoInfoList.get(i).blackPrice + "";
            tableContent[i][13] = ipoInfoList.get(i).openPrice + "";
            tableContent[i][14] = ipoInfoList.get(i).closePrice + "";
            tableContent[i][15] = ipoInfoList.get(i).blackGain + "%";
            tableContent[i][16] = ipoInfoList.get(i).openGain + "%";
            tableContent[i][17] = ipoInfoList.get(i).closeGain + "%";
            tableContent[i][18] = ipoInfoList.get(i).expectedFirstdayGain;
            tableContent[i][19] = ipoInfoList.get(i).subscribeSuggestion;
        }
        jTableInvest.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 13));
        jTableInvest.setModel(new javax.swing.table.DefaultTableModel(
                tableContent,
                new String[]{
                    "代码", "名称", "可比公司", "上市日", "板块", "询价区间", "中签率", "发行市盈率", "超购倍数", "募资总额", "一手资金", "发行价", "暗盘", "开盘", "收盘", "暗盘涨幅", "开盘涨幅", "收盘涨幅", "预计涨幅", "申购建议"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTableInvest.setRowHeight(20);
        jScrollPaneInvest.setViewportView(jTableInvest);
        if (jTableInvest.getColumnModel().getColumnCount() > 0) {
            jTableInvest.getColumnModel().getColumn(0).setPreferredWidth(50);
            jTableInvest.getColumnModel().getColumn(1).setPreferredWidth(90);
            jTableInvest.getColumnModel().getColumn(2).setPreferredWidth(90);
            jTableInvest.getColumnModel().getColumn(3).setPreferredWidth(120);
            jTableInvest.getColumnModel().getColumn(4).setPreferredWidth(50);
            jTableInvest.getColumnModel().getColumn(5).setPreferredWidth(80);
            jTableInvest.getColumnModel().getColumn(6).setPreferredWidth(60);
            jTableInvest.getColumnModel().getColumn(7).setPreferredWidth(90);
            jTableInvest.getColumnModel().getColumn(8).setPreferredWidth(70);
            jTableInvest.getColumnModel().getColumn(9).setPreferredWidth(70);
            jTableInvest.getColumnModel().getColumn(10).setPreferredWidth(70);
            jTableInvest.getColumnModel().getColumn(11).setPreferredWidth(60);
            jTableInvest.getColumnModel().getColumn(12).setPreferredWidth(50);
            jTableInvest.getColumnModel().getColumn(13).setPreferredWidth(50);
            jTableInvest.getColumnModel().getColumn(14).setPreferredWidth(50);
        }

        //setTableRowColor(jTableInvest, new Color(0, 128, 0), new Color(220, 0, 0));
        setPreferredSize(new java.awt.Dimension(1415, 40 * (lists < 10 ? lists : 10) + 90));
        jScrollPaneInvest.setPreferredSize(new java.awt.Dimension(1320, 40 * (lists < 10 ? lists : 10) + 31));
        getContentPane().add(jScrollPaneInvest, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1380, -1));
        pack();
        setLocationRelativeTo(parent);
        setVisible(true);
    }

    public static void setTableRowColor(JTable Table, final Color color1, final Color color2) {
        try {
            DefaultTableCellRenderer tcr = new DefaultTableCellRenderer() {
                @Override
                public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                    String status = (String) Table.getValueAt(row, column);
                    if (column > 10) {
                        if (status.startsWith("-")) {
                            setForeground(color1);
                        } else if (Double.parseDouble(status.substring(0, status.length() - 1)) == 0) {
                            setForeground(new Color(20, 20, 20));
                        } else {
                            setForeground(color2);
                        }
                    } else {
                        setForeground(new Color(0, 0, 0));
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

    public void updateTable(int idx) {
        int rows = (lists > 20) ? 20 : lists;
        for (int i = 0; i < rows; i++) {
//            jTableInvest.setValueAt(mv.codeList.get(i + idx), i, 0);
//            jTableInvest.setValueAt(mv.nameList.get(i + idx), i, 1);
//            jTableInvest.setValueAt(mv.plateList.get(i + idx), i, 2);
//            jTableInvest.setValueAt(mv.dateList.get(i + idx), i, 3);
//            jTableInvest.setValueAt(mv.luckyRateList.get(i + idx), i, 4);
//            jTableInvest.setValueAt(mv.inquiryRangeList.get(i + idx), i, 5);
//            jTableInvest.setValueAt(mv.blockMoneyList.get(i + idx), i, 6);
//            jTableInvest.setValueAt(mv.issuePriceList.get(i + idx), i, 7);
//            jTableInvest.setValueAt(String.format("%.3f", mv.blackList.get(i)), i, 8);
//            jTableInvest.setValueAt(mv.openList.get(i + idx), i, 9);
//            jTableInvest.setValueAt(mv.closeList.get(i + idx), i, 10);
//            jTableInvest.setValueAt(mv.blackRisingRateList.get(i + idx), i, 11);
//            jTableInvest.setValueAt(String.format("%.2f%%", mv.openRisingRateList.get(i)), i, 7);
//            jTableInvest.setValueAt(mv.closeRisingRateList.get(i + idx), i, 7);
        }
    }

    private void jTableRankHeaderMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() > 1) {
            int index = jTableInvest.columnAtPoint(evt.getPoint());
            jTableInvest.setColumnSelectionAllowed(true);
            jTableInvest.setRowSelectionAllowed(false);
            jTableInvest.setColumnSelectionInterval(index, index);
            switch (index) {
//                case 11:
//                    Collections.sort(SRList, (Trader.SystemReport arg0, Trader.SystemReport arg1) -> new Float(arg1.currentAsset).compareTo(arg0.currentAsset));
//                    break;
//                case 12:
//                    Collections.sort(SRList, (Trader.SystemReport arg0, Trader.SystemReport arg1) -> new Float(arg1.standardAnnualRate).compareTo(arg0.standardAnnualRate));
//                    break;
//                case 13:
//                    Collections.sort(SRList, (Trader.SystemReport arg0, Trader.SystemReport arg1) -> new Float(arg0.positionDaysRate).compareTo(arg1.positionDaysRate));
//                    break;
//                default:
//                    break;
            }
            rankIndex = 0;
            updateTable(rankIndex);
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

        jScrollPaneInvest = new javax.swing.JScrollPane();
        jTableInvest = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("交易记录");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneInvest.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jScrollPaneInvest.setPreferredSize(new java.awt.Dimension(452, 20));

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        jTableInvest.setDefaultRenderer(Object.class, tcr);
        jTableInvest.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableInvest.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 12));
        jTableInvest.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTableInvest.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "代码", "名称", "板块", "上市日", "中签率", "询价区间", "一手资金", "发行价", "暗盘", "开盘", "收盘", "暗盘涨幅", "开盘涨幅", "首日涨幅", "null", "null", "null", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableInvest.setRowHeight(20);
        jTableInvest.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                //jTableInvestHeaderMouseClicked(evt);
            }
        });
        jScrollPaneInvest.setViewportView(jTableInvest);

        getContentPane().add(jScrollPaneInvest, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1160, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPaneInvest;
    private javax.swing.JTable jTableInvest;
    // End of variables declaration//GEN-END:variables
    private int lists = 0;
    private int rankIndex = 0;
    private ArrayList<IpoInfo> ipoInfoList;
}
