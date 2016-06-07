/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trader;

import java.util.ArrayList;

/**
 *
 * @author zhangxr
 */
public class Strategy {

    public Strategy(MainView mv, BRM b) {
        mainView = mv;
        brm = b;
    }

    public void macdCrossTrade(double price) {
        tradingDays++;
        if (positionDays > 0) {
            positionDays++;
        }
        macd.arithmetic(price);
        if (macd.isGoldCross(true)) {
            brm.quota(true, price);
            positionDays++;
            mainView.msgLogger("买入价：" + price + "\t剩余款：" + (float) brm.asset);
        } else if (macd.isDeadCross(true)) {
            if (brm.initAsset != 0) {
                if (price > brm.buyPrice) {
                    gainPositionDaysArray.add(positionDays);
                } else {
                    lossPositionDaysArray.add(positionDays);
                }
                positionDays = 0;
                brm.quota(false, price);
                cycleYears = (double) tradingDays / 244;
                mainView.msgLogger("卖出价：" + price + "\t总资产：" + (float) brm.asset);
            }
        }
    }

    public double getPositionDaysRate() {
        int sumDays = 0;
        for (Integer days : gainPositionDaysArray) {
            sumDays += days;
        }
        for (Integer days : lossPositionDaysArray) {
            sumDays += days;
        }
        double rate = (double) sumDays / tradingDays;
        return rate;
    }

    public double getMeanPositionDays(BRM brm) {
        int sumDays = 0;
        for (Integer days : gainPositionDaysArray) {
            sumDays += days;
        }
        for (Integer days : lossPositionDaysArray) {
            sumDays += days;
        }
        int tradingTimes = brm.getGainTimes() + brm.getLossTimes();
        return (double) sumDays / tradingTimes;
    }

    public double getMeanGainDays(BRM brm) {
        int sumDays = 0;
        for (Integer days : gainPositionDaysArray) {
            sumDays += days;
        }

        int tradingTimes = brm.getGainTimes();
        return (double) sumDays / tradingTimes;
    }

    public double getMeanLossDays(BRM brm) {
        int sumDays = 0;
        for (Integer days : lossPositionDaysArray) {
            sumDays += days;
        }

        int tradingTimes = brm.getLossTimes();
        return (double) sumDays / tradingTimes;
    }

    public int tradingDays = 0;
    public int positionDays = 0;
    public double cycleYears = 0;
    public ArrayList<Integer> gainPositionDaysArray = new ArrayList<>();
    public ArrayList<Integer> lossPositionDaysArray = new ArrayList<>();

    MACD macd;
    BRM brm;
    protected MainView mainView;
}
