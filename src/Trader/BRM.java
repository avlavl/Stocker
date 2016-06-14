/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trader;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author zhangxr
 */
public class BRM {

    public BRM(double ast) {
        asset = ast;
    }

    protected void quota(boolean bs, double price) {
        bs_flag = bs;
        if (bs) {
            if (initAsset == 0) {
                initAsset = price;
            } else {
                asset -= price;
            }
            buyPrice = price;
        } else if (initAsset != 0) {
            asset += price;
            sellPrice = price;
            double agio = sellPrice - buyPrice;
            if (agio > 0) {
                gainAgioArray.add(agio);
            } else {
                lossAgioArray.add(agio);
            }
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

    public double getNetProfit() {
        double profit = 0;
        for (Double gain : gainAgioArray) {
            profit += gain;
        }
        for (Double loss : lossAgioArray) {
            profit += loss;
        }
        return profit;
    }

    public double getGainProfit() {
        double profit = 0;
        for (Double gain : gainAgioArray) {
            profit += gain;
        }
        return profit;
    }

    public double getLossProfit() {
        double profit = 0;
        for (Double loss : lossAgioArray) {
            profit += loss;
        }
        return profit;
    }

    public int getGainTimes() {
        return gainAgioArray.size();
    }

    public int getLossTimes() {
        return lossAgioArray.size();
    }

    public double getWinRate() {
        int gainTimes = gainAgioArray.size();
        int lossTimes = lossAgioArray.size();
        return (double) gainTimes / (gainTimes + lossTimes);
    }

    public double getMeanGain() {
        double profit = 0;
        for (Double gain : gainAgioArray) {
            profit += gain;
        }
        return (double) profit / gainAgioArray.size();
    }

    public double getMeanLoss() {
        double profit = 0;
        for (Double loss : lossAgioArray) {
            profit += loss;
        }
        return (double) profit / lossAgioArray.size();
    }

    public double getOdds() {
        double gain = getMeanGain();
        double loss = getMeanLoss();
        return (double) gain / (-loss);
    }

    public double getEarningRate() {
        double profit = getNetProfit();
        return (double) 100 * profit / initAsset;
    }

    public double getAnnualRate(double years) {
        double rate = (initAsset + getNetProfit()) / initAsset;
        return (double) 100 * (Math.pow(rate, (double) 1 / years) - 1);
    }

    public double getObjectRate(double price) {
        return (double) 100 * (price - initAsset) / initAsset;
    }

    public double getCurrentAsset(double price) {
        if (bs_flag) {
            return asset + price;
        } else {
            return asset;
        }
    }

    public double getMaxGain() {
        if (gainAgioArray.size() > 0) {
            return (double) Collections.max(gainAgioArray);
        }
        return 0;
    }

    public double getMaxLoss() {
        if (lossAgioArray.size() > 0) {
            return (double) Collections.min(lossAgioArray);
        }
        return 0;
    }

    public double getExpectation() {
        double winRate = getWinRate();
        double odds = getOdds();
        return (double) (winRate * odds - (1 - winRate));
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

    public double initAsset = 0;
    private double ratio = 1;

    public double buyPrice = 0;
    public double sellPrice = 0;

    public ArrayList<Double> gainAgioArray = new ArrayList<>();
    public ArrayList<Double> lossAgioArray = new ArrayList<>();

    private boolean bs_flag = false;

}
