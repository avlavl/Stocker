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
        mode = mv.brmMode;
        pList = mv.priceList;
        bpIdxList = mv.bpIndexList;
        spIdxList = mv.spIndexList;
    }

    protected void quant(boolean bs, double price) {
        if (bs) {
            bPrice = price;
            cash -= price;
            holdFlag = true;
        } else if (holdFlag) {
            sPrice = price;
            agioList.add(sPrice - bPrice);
            yieldList.add(100 * (sPrice - bPrice) / bPrice);
            cash += price;
            holdFlag = false;
        }
    }

    protected void quota(boolean bs, double price) {
        if (bs) {
            bPrice = price;
            cash -= amount;
            ratio = amount / price;
            holdFlag = true;
        } else if (holdFlag) {
            sPrice = price;
            double yield = (sPrice - bPrice) / bPrice;
            yieldList.add(100 * yield);
            agioList.add(amount * yield);
            cash += amount * (1 + yield);
            holdFlag = false;
        }
    }

    protected void iterate(boolean bs, double price) {
        if (bs) {
            bPrice = price;
            ratio = cash / price;
            cash = 0;
            holdFlag = true;
        } else if (holdFlag) {
            sPrice = price;
            double yield = (sPrice - bPrice) / bPrice;
            yieldList.add(100 * yield);
            agioList.add((sPrice - bPrice) * ratio);
            cash = price * ratio;
            holdFlag = false;
        }
    }

    protected void trade(int mode, boolean bs, double price) {
        switch (mode) {
            case 0:
                quant(bs, price);
                break;
            case 1:
                quota(bs, price);
                break;
            case 2:
                iterate(bs, price);
                break;
        }
    }

    public ArrayList<Double> synthesize() {
        initAsset = (mode == 0) ? pList.get(bpIdxList.get(0)) : amount;
        cash = initAsset;

        int times = bpIdxList.size();
        int startIdx = bpIdxList.get(0);
        int endIdx = spIdxList.get(times - 1);
        for (int i = 0; i < pList.size(); i++) {
            if (i < startIdx) {
                fundList.add(initAsset);
            } else if (i <= endIdx) {
                if (bpIdxList.contains(i)) {
                    trade(mode, true, pList.get(i));
                } else if (spIdxList.contains(i)) {
                    trade(mode, false, pList.get(i));
                }
                fundList.add(getCurrentAsset(i));
            } else {
                fundList.add(fundList.get(endIdx));
            }
        }
        return fundList;
    }

    public double getInitAsset() {
        return initAsset;
    }

    public double getCurrentAsset(int idx) {
        if (holdFlag) {
            return cash + ratio * (pList.get(idx));
        } else {
            return cash;
        }
    }

    public double getNetProfit() {
        double profit = 0;
        for (Double agio : agioList) {
            profit += agio;
        }
        return profit;
    }

    public double getObjectRate(int idxs, int idxe) {
        double sPrice = pList.get(idxs);
        double ePrice = pList.get(idxe);
        return (double) 100 * (ePrice - sPrice) / sPrice;
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

    public double getPositionAnnualRate() {
        double totalYield = 0;
        for (Double yield : yieldList) {
            totalYield += yield / 100;
        }
        int totalDays = 0;
        for (int i = 0; i < bpIdxList.size(); i++) {
            totalDays += mainView.daysBetween(bpIdxList.get(i), spIdxList.get(i));
        }

        return (double) 100 * (Math.pow(totalYield + 1, (double) 365.25 / totalDays) - 1);
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
        return (double) 100 * gainTimes / agioList.size();
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
        double winRate = getWinRate() / 100;
        double odds = getOdds();
        return (double) (winRate * odds - (1 - winRate));
    }

    private MainView mainView;
    private ArrayList<Double> pList = new ArrayList<>();
    private ArrayList<Integer> bpIdxList = new ArrayList<>();
    private ArrayList<Integer> spIdxList = new ArrayList<>();
    public ArrayList<Double> agioList = new ArrayList<>();
    public ArrayList<Double> yieldList = new ArrayList<>();
    public ArrayList<Double> fundList = new ArrayList<>();
    public double amount = 10000;
    public double cash = 0;
    public double initAsset = 0;
    private double ratio = 1;
    public boolean holdFlag = false;
    public double bPrice = 0;
    public double sPrice = 0;

    public int mode = 1;
}
