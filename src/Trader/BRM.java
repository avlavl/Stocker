/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trader;

/**
 *
 * @author zhangxr
 */
public class BRM {

    public BRM(double ast) {
        asset = ast;
    }

    protected void quota(boolean bs, double price) {
        if (bs) {
            asset -= price;
        } else {
            asset += price;
        }
    }

    protected void iterate(boolean bs, double price) {
        if (bs) {
            ratio = asset / price;
        } else if (bs == false) {
            asset = ratio * price;
        }
    }

    protected void BRMC(boolean bs, double scale, double price) {
        if (bs) {
            int count = (int) (asset * scale / (int) (price * 100));
            stockCount += count;
            moneyAsset -= price * 100 * count;

//            jTextAreaMain.append("[" + dateString + "] 买入" + scale * 10 + "成仓位， 买入价：" + price + "买入数量：" + count + "手 ");
//            jTextAreaMain.append("股票数量" + stockCount + " 市值：" + (price * 100 * stockCount) + "总资产：" + (moneyAsset + price * 100 * stockCount) + "可用资金：" + moneyAsset + "\n");
        } else if ((bs == false) && (stockCount > 0)) {
            int count = stockCount - (int) (stockCount * (1 - scale));
            stockCount -= count;
            moneyAsset += price * 100 * count;
            if (scale != 1) {
                //jTextAreaMain.append("[" + dateString + "] 卖出" + scale * 10 + "成仓位， 卖出价：" + price + "买出数量：" + count + "手 ");
                //jTextAreaMain.append("股票数量" + stockCount + " 市值：" + (price * 100 * stockCount) + "总资产：" + (moneyAsset + price * 100 * stockCount) + "可用资金：" + moneyAsset + "\n");
            } else {
                asset = moneyAsset;
                //jTextAreaMain.append("[" + dateString + "] 清仓! 卖出价：" + price + "买出数量：" + count + "手 ");
                //jTextAreaMain.append("总资产：" + asset + "可用资金：" + moneyAsset + "\n");
            }
        }
    }

    protected void BRMD(int status, double price) {
        switch (status) {
            case 1:     //开仓
                openCount = (int) (asset * 0.4 / (int) (price * 100));
                openPrice = price;
                stockCount = openCount;
                moneyAsset -= price * 100 * openCount;

                //jTextAreaMain.append("[" + dateString + "] 开仓！买入价：" + price + "，买入数量：" + openCount + "手 ");
                //jTextAreaMain.append("总资产：" + (moneyAsset + price * 100 * stockCount) + "，股票总量" + stockCount + "手，市值：" + (price * 100 * stockCount) + "可用资金：" + moneyAsset + "\n");
                break;
            case 2:     //加仓1
                addCount1 = (int) (asset * 0.4 / (int) (price * 100));
                addPrice1 = price;
                stockCount += addCount1;
                moneyAsset -= price * 100 * addCount1;

                //jTextAreaMain.append("[" + dateString + "] 首次加仓，买入价：" + price + "，买入数量：" + addCount1 + "手 ");
                //jTextAreaMain.append("总资产：" + (moneyAsset + price * 100 * stockCount) + "，股票总量" + stockCount + "手，市值：" + (price * 100 * stockCount) + "可用资金：" + moneyAsset + "\n");
                break;
            case 3:     //加仓2
                addCount2 = (int) (asset * 0.2 / (int) (price * 100));
                addPrice2 = price;
                stockCount += addCount2;
                moneyAsset -= price * 100 * addCount2;

                //jTextAreaMain.append("[" + dateString + "] 二次加仓，买入价：" + price + "，买入数量：" + addCount2 + "手 ");
                //jTextAreaMain.append("总资产：" + (moneyAsset + price * 100 * stockCount) + "，股票总量" + stockCount + "手，市值：" + (price * 100 * stockCount) + "可用资金：" + moneyAsset + "\n");
                break;
            case -1:    //减仓
                if (stockCount > 0) {
                    int count = stockCount - (int) (stockCount * 0.5);
                    stockCount -= count;
                    moneyAsset += price * 100 * count;

                    //jTextAreaMain.append("[" + dateString + "] 减仓，卖出价：" + price + "，买出数量：" + count + "手 ");
                    //jTextAreaMain.append("总资产：" + (moneyAsset + price * 100 * stockCount) + "，股票总量" + stockCount + "手，市值：" + (price * 100 * stockCount) + "可用资金：" + moneyAsset + "\n");
                }
                break;
            case 0: //清仓
                if (stockCount > 0) {
                    int count = stockCount;
                    stockCount = 0;
                    moneyAsset += price * 100 * count;
                    asset = moneyAsset;

                    //jTextAreaMain.append("[" + dateString + "] 清仓！卖出价：" + price + "，买出数量：" + count + "手 ");
                    //jTextAreaMain.append("总资产：" + asset + "可用资金：" + moneyAsset + "\n");
                }
                break;
            default:
                break;
        }
    }

    public double asset = 10000000;
    private double moneyAsset = 10000000;
    private double stockAsset = 0;
    private int stockCount = 0;
    private int openCount = 0;
    private int addCount1 = 0;
    private int addCount2 = 0;
    private double openPrice = 0;
    private double addPrice1 = 0;
    private double addPrice2 = 0;
    private int position = 0;

    public double propertyOrig = 0;
    private double ratio = 1;
}
