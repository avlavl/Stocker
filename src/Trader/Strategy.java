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
        brm = b;
    }

    public void livermoreTrade(double price) {
        tradingDays++;
        if (positionDays > 0) {
            positionDays++;
        }
        if (livermore.enterRiseStatus() || ((brm.initAsset == 0) && livermore.Status.equals("mainRiseStatus"))) {
            brm.quota(true, price);
            positionDays++;
            mainView.msgLogger("买入价：" + price + "\t剩余款：" + (float) brm.asset);
        } else if (livermore.enterFallStatus()) {
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

    public void macdCrossTrade(int idx) {
        tradingDays++;
        if (positionDays > 0) {
            positionDays++;
        }
        if (CROSS(idx, macd.difList, macd.deaList)) {
            brm.quota(true, macd.priceList.get(idx));
            positionDays++;
            mainView.msgLogger("买入价：" + macd.priceList.get(idx) + "\t剩余款：" + (float) brm.asset);
        } else if (CROSS(idx, macd.deaList, macd.difList)) {
            if (brm.initAsset != 0) {
                if (macd.priceList.get(idx) > brm.buyPrice) {
                    gainPositionDaysArray.add(positionDays);
                } else {
                    lossPositionDaysArray.add(positionDays);
                }
                positionDays = 0;
                brm.quota(false, macd.priceList.get(idx));
                cycleYears = (double) tradingDays / 244;
                mainView.msgLogger("卖出价：" + macd.priceList.get(idx) + "\t总资产：" + (float) brm.asset);
            }
        }
    }

    public void maCrossTrade(ArrayList<Double> list, int idx) {
        tradingDays++;
        if (positionDays > 0) {
            positionDays++;
        }
        if (CROSS(idx, ma.priceList, list)) {
            brm.quota(true, ma.priceList.get(idx));
            positionDays++;
            mainView.msgLogger("买入价：" + ma.priceList.get(idx) + "\t剩余款：" + (float) brm.asset);
        } else if (CROSS(idx, list, ma.priceList)) {
            if (brm.initAsset != 0) {
                if (ma.priceList.get(idx) > brm.buyPrice) {
                    gainPositionDaysArray.add(positionDays);
                } else {
                    lossPositionDaysArray.add(positionDays);
                }
                positionDays = 0;
                brm.quota(false, ma.priceList.get(idx));
                cycleYears = (double) tradingDays / 244;
                mainView.msgLogger("卖出价：" + ma.priceList.get(idx) + "\t总资产：" + (float) brm.asset);
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
        double rate = (double) 100 * sumDays / tradingDays;
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

    protected MainView mainView;
    public BRM brm;
    public Livermore livermore;
    public MACD macd;
    public MALine ma;
}
