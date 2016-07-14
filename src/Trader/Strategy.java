/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trader;

import static Trader.FormulaLib.*;
import java.util.ArrayList;

/**
 *
 * @author zhangxr
 */
public class Strategy {

    public Strategy(MainView mv, BRM b) {
        mainView = mv;
        priceList = mv.closeList;
        brm = b;
    }

    public void livermoreTrade(int idx) {
        boolean b = livermore.enterRiseStatus() || ((brm.initAsset == 0) && livermore.Status.equals("mainRiseStatus"));
        boolean s = livermore.enterFallStatus();

        trade(idx, b, s);
    }

    public void barCrossTrade(int idx, double value) {
        boolean b = CROSS(idx, macd.barList, value);
        boolean s = CROSS(idx, value, macd.barList);

        trade(idx, b, s);
    }

    public void difCrossTrade(int idx, double value) {
        boolean b = CROSS(idx, macd.difList, value);
        boolean s = CROSS(idx, value, macd.difList);

        trade(idx, b, s);
    }

    public void maCrossTrade(ArrayList<Double> list, int idx) {
        boolean b = CROSS(idx, ma.priceList, list);
        boolean s = CROSS(idx, list, ma.priceList);

        trade(idx, b, s);
    }

    public void trade(int idx, boolean buy, boolean sell) {
        double price = priceList.get(idx);
        tradeDays++;
        tradeYears = (double) tradeDays / 244;
        if (positionDays > 0) {
            positionDays++;
        }
        if (buy) {
            bIndexList.add(idx);
            brm.quota(true, price);
            positionDays++;
            mainView.msgLogger("买入价：" + price + "\t剩余款：" + (float) brm.asset);
        } else if (sell) {
            if (brm.initAsset != 0) {
                sIndexList.add(idx);
                double agio = price - brm.buyPrice;
                if (agio > 0) {
                    gainPositionDaysList.add(positionDays);
                } else {
                    lossPositionDaysList.add(positionDays);
                }
                positionDays = 0;
                brm.quota(false, price);
                mainView.msgLogger("卖出价：" + price + "\t总资产：" + (float) brm.asset + "\t" + ((agio > 0) ? "赢利：" : "亏损：") + (float) Math.abs(agio));
            }
        }
    }

    public double getPositionDaysRate() {
        int sumDays = 0;
        for (Integer days : gainPositionDaysList) {
            sumDays += days;
        }
        for (Integer days : lossPositionDaysList) {
            sumDays += days;
        }
        double rate = (double) 100 * sumDays / tradeDays;
        return rate;
    }

    public double getMeanPositionDays(BRM brm) {
        int sumDays = 0;
        for (Integer days : gainPositionDaysList) {
            sumDays += days;
        }
        for (Integer days : lossPositionDaysList) {
            sumDays += days;
        }
        int tradingTimes = brm.getGainTimes() + brm.getLossTimes();
        return (double) sumDays / tradingTimes;
    }

    public double getMeanGainDays(BRM brm) {
        int sumDays = 0;
        for (Integer days : gainPositionDaysList) {
            sumDays += days;
        }

        int tradingTimes = brm.getGainTimes();
        return (double) sumDays / tradingTimes;
    }

    public double getMeanLossDays(BRM brm) {
        int sumDays = 0;
        for (Integer days : lossPositionDaysList) {
            sumDays += days;
        }

        int tradingTimes = brm.getLossTimes();
        return (double) sumDays / tradingTimes;
    }

    public int tradeDays = 0;
    public double tradeYears = 0;
    public int positionDays = 0;
    public ArrayList<Integer> gainPositionDaysList = new ArrayList<>();
    public ArrayList<Integer> lossPositionDaysList = new ArrayList<>();
    public ArrayList<Integer> bIndexList = new ArrayList<>();
    public ArrayList<Integer> sIndexList = new ArrayList<>();

    protected MainView mainView;
    private ArrayList<Double> priceList = new ArrayList<>();
    public BRM brm;
    public Livermore livermore;
    public MACD macd;
    public MALine ma;
}
