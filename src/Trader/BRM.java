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

    public BRM(MainView mv) {
        mainView = mv;
        priceList = mv.closeList;
        dateList = mv.dateList;
    }

    protected void quota(boolean bs, double price) {
        bsFlag = bs;
        if (bs) {
            asset -= price;
            bPrice = price;
        } else if (initAsset != 0) {
            asset += price;
            sPrice = price;
            agioList.add(sPrice - bPrice);
            yieldList.add(100 * (sPrice - bPrice) / bPrice);
        }
    }

    protected void iterate(boolean bs, double price) {
        if (bs) {
            ratio = asset / price;
        } else if (bs == false) {
            asset = ratio * price;
        }
    }

    public ArrayList<Double> synthesize(Strategy stg) {
        initAsset = priceList.get(stg.bpIndexList.get(0));
        asset = initAsset;
        int times = stg.bpIndexList.size();
        int startIdx = stg.bpIndexList.get(0);
        int endIdx = stg.spIndexList.get(times - 1);
        for (int i = 0; i < priceList.size(); i++) {
            if (i < startIdx) {
                fundList.add(initAsset);
            } else if (i <= endIdx) {
                if (stg.bpIndexList.contains(i)) {
                    quota(true, priceList.get(i));
                } else if (stg.spIndexList.contains(i)) {
                    quota(false, priceList.get(i));
                }
                fundList.add(getCurrentAsset(priceList.get(i)));
            } else {
                fundList.add(fundList.get(endIdx));
            }
        }

        return fundList;
    }

    public double getInitAsset() {
        return initAsset;
    }

    public double getCurrentAsset(double price) {
        if (bsFlag) {
            return asset + price;
        } else {
            return asset;
        }
    }

    public double getNetProfit() {
        double profit = 0;
        for (Double agio : agioList) {
            profit += agio;
        }
        return profit;
    }

    public double getObjectRate(double price) {
        return (double) 100 * (price - initAsset) / initAsset;
    }

    public double getEarningRate() {
        double profit = getNetProfit();
        return (double) 100 * profit / initAsset;
    }

    public double getAnnualRate(double years) {
        double rate = (initAsset + getNetProfit()) / initAsset;
        return (double) 100 * (Math.pow(rate, (double) 1 / years) - 1);
    }

    public double getGainProfit() {
        double profit = 0;
        for (Double agio : agioList) {
            if (agio > 0) {
                profit += agio;
            }
        }
        return profit;
    }

    public double getLossProfit() {
        double profit = 0;
        for (Double agio : agioList) {
            if (agio <= 0) {
                profit += agio;
            }
        }
        return profit;
    }

    public double getMaxGain() {
        if (agioList.size() > 0) {
            return (double) Collections.max(agioList);
        }
        return 0;
    }

    public double getMaxLoss() {
        if (agioList.size() > 0) {
            return (double) Collections.min(agioList);
        }
        return 0;
    }

    public double getEvenEarningRate() {
        double totalYield = 0;
        for (Double yield : yieldList) {
            totalYield += yield;
        }
        return totalYield / yieldList.size();
    }

    public int getTradeTimes() {
        return agioList.size();
    }

    public int getGainTimes() {
        int times = 0;
        for (Double agio : agioList) {
            if (agio > 0) {
                times++;
            }
        }
        return times;
    }

    public double getWinRate() {
        int gainTimes = getGainTimes();
        return (double) gainTimes / agioList.size();
    }

    public double getMeanGain() {
        double profit = getGainProfit();
        int times = getGainTimes();
        return (double) profit / times;
    }

    public double getMeanLoss() {
        double profit = getLossProfit();
        int times = getTradeTimes() - getGainTimes();
        return (double) profit / times;
    }

    public double getOdds() {
        double gain = getMeanGain();
        double loss = getMeanLoss();
        return (double) gain / (-loss);
    }

    public double getExpectation() {
        double winRate = getWinRate();
        double odds = getOdds();
        return (double) (winRate * odds - (1 - winRate));
    }

    private MainView mainView;
    private ArrayList<Double> priceList = new ArrayList<>();
    private ArrayList<String> dateList = new ArrayList<>();
    public double asset = 10000000;
    public double initAsset = 0;
    private double ratio = 1;

    public double bPrice = 0;
    public double sPrice = 0;

    public ArrayList<Double> agioList = new ArrayList<>();
    public ArrayList<Double> yieldList = new ArrayList<>();
    public boolean bsFlag = false;

    ArrayList<Double> fundList = new ArrayList<>();
}
