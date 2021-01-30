/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stagging;

import java.awt.Color;
import java.awt.Component;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author Aioros
 */
public class IpoTable extends javax.swing.JDialog {

    /**
     * Creates new form IpoTable
     *
     * @param parent
     * @param modal
     */
    public IpoTable(java.awt.Frame parent, boolean modal, ArrayList<IpoInfo> list) {
        super(parent, modal);
        initComponents();

        ipoInfoList = list;
        listSize = list.size();
        String[][] tableContent = new String[tableRows][16];
        for (int i = 0; i < tableRows; i++) {
            tableContent[i][0] = String.format("%d", i + 1);
            tableContent[i][1] = ipoInfoList.get(i).stockCode;
            tableContent[i][2] = ipoInfoList.get(i).stockName;
            tableContent[i][3] = ipoInfoList.get(i).marketPlate;
            tableContent[i][4] = ipoInfoList.get(i).offerDate;
            tableContent[i][5] = ipoInfoList.get(i).inquiryRange;
            tableContent[i][6] = ipoInfoList.get(i).handFund + "";
            tableContent[i][7] = ipoInfoList.get(i).luckyRate + "%";
            tableContent[i][8] = ipoInfoList.get(i).offerPrice + "";
            tableContent[i][9] = ipoInfoList.get(i).offerPE;
            tableContent[i][10] = ipoInfoList.get(i).greenShoeRatio + "%";
            tableContent[i][11] = ipoInfoList.get(i).superPurchaseMultiples + "";
            tableContent[i][12] = ipoInfoList.get(i).totalRaiseFunds + "";
            tableContent[i][13] = ipoInfoList.get(i).grayGain + "%";
            tableContent[i][14] = String.format("%.2f%%", ipoInfoList.get(i).openGain);
            tableContent[i][15] = ipoInfoList.get(i).closeGain + "%";
        }
        jTableIpo.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 13));
        jTableIpo.setModel(new javax.swing.table.DefaultTableModel(
                tableContent,
                new String[]{
                    "序号", "代码", "名称", "板块", "上市日", "询价区间", "一手资金", "中签率", "发行价", "市盈率", "绿鞋/公开", "超购倍数", "募资总额", "暗盘涨幅", "开盘涨幅", "收盘涨幅"
                }
        ) {
            boolean[] canEdit = new boolean[]{
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            @Override
            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jTableIpo.setRowHeight(20);
        jScrollPaneInvest.setViewportView(jTableIpo);
        if (jTableIpo.getColumnModel().getColumnCount() > 0) {
            jTableIpo.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTableIpo.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTableIpo.getColumnModel().getColumn(2).setPreferredWidth(90);
            jTableIpo.getColumnModel().getColumn(3).setPreferredWidth(50);
            jTableIpo.getColumnModel().getColumn(4).setPreferredWidth(80);
            jTableIpo.getColumnModel().getColumn(5).setPreferredWidth(70);
            jTableIpo.getColumnModel().getColumn(6).setPreferredWidth(60);
            jTableIpo.getColumnModel().getColumn(7).setPreferredWidth(60);
            jTableIpo.getColumnModel().getColumn(8).setPreferredWidth(50);
            jTableIpo.getColumnModel().getColumn(9).setPreferredWidth(60);
            jTableIpo.getColumnModel().getColumn(10).setPreferredWidth(60);
            jTableIpo.getColumnModel().getColumn(11).setPreferredWidth(60);
            jTableIpo.getColumnModel().getColumn(12).setPreferredWidth(60);
            jTableIpo.getColumnModel().getColumn(13).setPreferredWidth(70);
            jTableIpo.getColumnModel().getColumn(14).setPreferredWidth(70);
            jTableIpo.getColumnModel().getColumn(15).setPreferredWidth(70);
        }

        //setTableRowColor(jTableIpo, new Color(0, 128, 0), new Color(220, 0, 0));
        setPreferredSize(new java.awt.Dimension(1215, 60 * (listSize < 10 ? listSize : 10) + 90));
        jScrollPaneInvest.setPreferredSize(new java.awt.Dimension(1120, 60 * (listSize < 10 ? listSize : 10) + 31));
        getContentPane().add(jScrollPaneInvest, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1180, -1));
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
                    if ((column > 14) && (column < 18)) {
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
                    setHorizontalAlignment(JLabel.CENTER);
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
        for (int i = 0; i < tableRows; i++) {
            jTableIpo.setValueAt(String.format("%d", i + 1 + idx), i, 0);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).stockCode, i, 1);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).stockName, i, 2);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).marketPlate, i, 3);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).offerDate, i, 4);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).inquiryRange, i, 5);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).handFund, i, 6);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).luckyRate + "%", i, 7);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).offerPrice, i, 8);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).offerPE, i, 9);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).greenShoeRatio + "%", i, 10);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).superPurchaseMultiples, i, 11);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).totalRaiseFunds, i, 12);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).grayGain + "%", i, 13);
            jTableIpo.setValueAt(String.format("%.2f%%", ipoInfoList.get(i + idx).openGain), i, 14);
            jTableIpo.setValueAt(ipoInfoList.get(i + idx).closeGain + "%", i, 15);
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
        jTableIpo = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("新股信息");
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jScrollPaneInvest.setFont(new java.awt.Font("Dialog", 0, 11)); // NOI18N
        jScrollPaneInvest.setPreferredSize(new java.awt.Dimension(452, 20));

        DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();
        tcr.setHorizontalAlignment(JLabel.CENTER);
        jTableIpo.setDefaultRenderer(Object.class, tcr);
        jTableIpo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTableIpo.getTableHeader().setFont(new java.awt.Font("微软雅黑", 0, 12));
        jTableIpo.setFont(new java.awt.Font("微软雅黑", 0, 12)); // NOI18N
        jTableIpo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "序号", "代码", "名称", "板块", "上市日", "询价区间", "一手资金", "中签率", "发行价", "市盈率", "绿鞋/公开", "超购倍数", "募资总额", "暗盘涨幅", "开盘涨幅", "收盘涨幅"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableIpo.setRowHeight(20);
        jTableIpo.getTableHeader().addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTableIpoHeaderMouseClicked(evt);
            }
        });
        jTableIpo.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                jTableIpoMouseWheelMoved(evt);
            }
        });
        jScrollPaneInvest.setViewportView(jTableIpo);

        getContentPane().add(jScrollPaneInvest, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1160, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTableIpoMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_jTableIpoMouseWheelMoved
        if (evt.getWheelRotation() > 0) {
            if (listIndex < listSize - tableRows) {
                listIndex += 10;
                if (listIndex > listSize - tableRows) {
                    listIndex = listSize - tableRows;
                }
                updateTable(listIndex);
            }
        } else if (listIndex > 0) {
            listIndex -= 10;
            if (listIndex < 0) {
                listIndex = 0;
            }
            updateTable(listIndex);
        }
    }//GEN-LAST:event_jTableIpoMouseWheelMoved

    private void jTableIpoHeaderMouseClicked(java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() > 1) {
            int index = jTableIpo.columnAtPoint(evt.getPoint());
            jTableIpo.setColumnSelectionAllowed(true);
            jTableIpo.setRowSelectionAllowed(false);
            jTableIpo.setColumnSelectionInterval(index, index);
            switch (index) {
                case 3:
                    Collections.sort(ipoInfoList, (IpoInfo arg0, IpoInfo arg1) -> new String((order ? arg1 : arg0).marketPlate).compareTo((order ? arg0 : arg1).marketPlate));
                    break;
                case 4:
                    Collections.sort(ipoInfoList, (IpoInfo arg0, IpoInfo arg1) -> new String((order ? arg1 : arg0).offerDate).compareTo((order ? arg0 : arg1).offerDate));
                    break;
                case 6:
                    Collections.sort(ipoInfoList, (IpoInfo arg0, IpoInfo arg1) -> new Integer((order ? arg0 : arg1).handFund).compareTo((order ? arg1 : arg0).handFund));
                    break;
                case 7:
                    Collections.sort(ipoInfoList, (IpoInfo arg0, IpoInfo arg1) -> new Float((order ? arg0 : arg1).luckyRate).compareTo((order ? arg1 : arg0).luckyRate));
                    break;
                case 8:
                    Collections.sort(ipoInfoList, (IpoInfo arg0, IpoInfo arg1) -> new Float((order ? arg1 : arg0).offerPrice).compareTo((order ? arg0 : arg1).offerPrice));
                    break;
                case 10:
                    Collections.sort(ipoInfoList, (IpoInfo arg0, IpoInfo arg1) -> new Float((order ? arg1 : arg0).greenShoeRatio).compareTo((order ? arg0 : arg1).greenShoeRatio));
                    break;
                case 11:
                    Collections.sort(ipoInfoList, (IpoInfo arg0, IpoInfo arg1) -> new Float((order ? arg1 : arg0).superPurchaseMultiples).compareTo((order ? arg0 : arg1).superPurchaseMultiples));
                    break;
                case 12:
                    Collections.sort(ipoInfoList, (IpoInfo arg0, IpoInfo arg1) -> new Float((order ? arg1 : arg0).totalRaiseFunds).compareTo((order ? arg0 : arg1).totalRaiseFunds));
                    break;
                case 13:
                    Collections.sort(ipoInfoList, (IpoInfo arg0, IpoInfo arg1) -> new Float((order ? arg1 : arg0).grayGain).compareTo((order ? arg0 : arg1).grayGain));
                    break;
                case 14:
                    Collections.sort(ipoInfoList, (IpoInfo arg0, IpoInfo arg1) -> new Float((order ? arg1 : arg0).openGain).compareTo((order ? arg0 : arg1).openGain));
                    break;
                case 15:
                    Collections.sort(ipoInfoList, (IpoInfo arg0, IpoInfo arg1) -> new Float((order ? arg1 : arg0).closeGain).compareTo((order ? arg0 : arg1).closeGain));
                    break;
                default:
                    break;
            }
            order = !order;
            listIndex = 0;
            updateTable(listIndex);
        }
    }

    private int listSize = 0;
    private int listIndex = 0;
    private int tableRows = 30;
    private boolean order = true;
    private ArrayList<IpoInfo> ipoInfoList;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPaneInvest;
    private javax.swing.JTable jTableIpo;
    // End of variables declaration//GEN-END:variables
}
