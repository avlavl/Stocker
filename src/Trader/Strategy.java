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

    public Strategy(MainView mv) {
        mainView = mv;
        pList = mv.priceList;
    }

    public void barCrossTrade(int idx, double value) {
        boolean b = CROSS(idx, macd.barList, value);
        boolean s = CROSS(idx, value, macd.barList);
        saveTradeIndex(idx, b, s);
    }

    public void difCrossTrade(int idx, double value) {
        boolean b = CROSS(idx, macd.difList, value);
        boolean s = CROSS(idx, value, macd.difList);
        saveTradeIndex(idx, b, s);
    }

    public void maCrossTrade(int idx, ArrayList<Double> list) {
        boolean b = CROSS(idx, pList, list);
        boolean s = CROSS(idx, list, pList);
        saveTradeIndex(idx, b, s);
    }

    public void maCrossTrade(int idx, ArrayList<Double> sList, ArrayList<Double> lList) {
        boolean b = CROSS(idx, sList, lList);
        boolean s = CROSS(idx, lList, sList);
        saveTradeIndex(idx, b, s);
    }

    public void lmLongTrade(int idx) {
        boolean b = livermore.enterRiseTrend();
        boolean s = livermore.enterFallTrend();
        saveTradeIndex(idx, b, s);
    }

    public void lmShortTrade(int idx) {
        boolean b = livermore.enterMainRise();
        boolean s = livermore.exitMainRise();
        saveTradeIndex(idx, b, s);
    }

    public void barMACrossTrade(int idx, double value, ArrayList<Double> sList, ArrayList<Double> lList) {
        boolean c1 = (REFD(macd.barList, idx, 1) > value) && (REFD(sList, idx, 1) > REFD(lList, idx, 1));
        boolean c2 = (macd.barList.get(idx) > value) && (sList.get(idx) > lList.get(idx));
        boolean b = (!c1) && c2;
        boolean s = c1 && (!c2);
        saveTradeIndex(idx, b, s);
    }

    public void difMACrossTrade(int idx, double value, ArrayList<Double> sList, ArrayList<Double> lList) {
        boolean c1 = (REFD(macd.difList, idx, 1) > value) && (REFD(sList, idx, 1) > REFD(lList, idx, 1));
        boolean c2 = (macd.difList.get(idx) > value) && (sList.get(idx) > lList.get(idx));
        boolean b = (!c1) && c2;
        boolean s = c1 && (!c2);
        saveTradeIndex(idx, b, s);
    }

    public void barLMLCrossTrade(int idx, double value) {
        boolean c1 = (REFD(macd.barList, idx, 1) > value) && (livermore.STATUSY > 0);
        boolean c2 = (macd.barList.get(idx) > value) && (livermore.STATUST > 0);
        boolean b = (!c1) && c2;
        boolean s = c1 && (!c2);
        saveTradeIndex(idx, b, s);
    }

    public void difLMLCrossTrade(int idx, double value) {
        boolean c1 = (REFD(macd.difList, idx, 1) > value) && (livermore.STATUSY > 0);
        boolean c2 = (macd.difList.get(idx) > value) && (livermore.STATUST > 0);
        boolean b = (!c1) && c2;
        boolean s = c1 && (!c2);
        saveTradeIndex(idx, b, s);
    }

    public void barLMSCrossTrade(int idx, double value) {
        boolean c1 = (REFD(macd.barList, idx, 1) > value) && (livermore.STATUSY == 1);
        boolean c2 = (macd.barList.get(idx) > value) && (livermore.STATUST == 1);
        boolean b = (!c1) && c2;
        boolean s = c1 && (!c2);
        saveTradeIndex(idx, b, s);
    }

    public void difLMSCrossTrade(int idx, double value) {
        boolean c1 = (REFD(macd.difList, idx, 1) > value) && (livermore.STATUSY == 1);
        boolean c2 = (macd.difList.get(idx) > value) && (livermore.STATUST == 1);
        boolean b = (!c1) && c2;
        boolean s = c1 && (!c2);
        saveTradeIndex(idx, b, s);
    }

    public void saveTradeIndex(int idx, boolean buy, boolean sell) {
        if (buy) {
            bpIdxList.add(idx);
        }
        if (sell & (bpIdxList.size() > 0)) {
            spIdxList.add(idx);
        }
    }

    public double getPositionYears() {
        int days = 0;
        for (int i = 0; i < bpIdxList.size(); i++) {
            days += mainView.daysBetween(bpIdxList.get(i), spIdxList.get(i));
        }
        return (double) days / 365.25;
    }

    public double getPositionDaysRate() {
        int days = 0;
        for (int i = 0; i < bpIdxList.size(); i++) {
            days += spIdxList.get(i) - bpIdxList.get(i);
        }

        double rate = (double) 100 * days / mainView.tradeDays;
        return rate;
    }

    public double getMeanPositionDays() {
        int days = 0;
        for (int i = 0; i < bpIdxList.size(); i++) {
            days += mainView.daysBetween(bpIdxList.get(i), spIdxList.get(i));
        }
        return (double) days / bpIdxList.size();
    }

    public double getMeanGainDays() {
        int days = 0;
        int times = 0;
        for (int i = 0; i < bpIdxList.size(); i++) {
            if (pList.get(spIdxList.get(i)) > pList.get(bpIdxList.get(i))) {
                days += mainView.daysBetween(bpIdxList.get(i), spIdxList.get(i));
                times++;
            }
        }
        return (double) ((times > 0) ? days / times : 0);
    }

    public double getMeanLossDays() {
        int days = 0;
        int times = 0;
        for (int i = 0; i < bpIdxList.size(); i++) {
            if (pList.get(spIdxList.get(i)) <= pList.get(bpIdxList.get(i))) {
                days += mainView.daysBetween(bpIdxList.get(i), spIdxList.get(i));
                times++;
            }
        }
        return (double) ((times > 0) ? days / times : 0);
    }

    private MainView mainView;
    private ArrayList<Double> pList = new ArrayList<>();
    public ArrayList<Integer> bpIdxList = new ArrayList<>();
    public ArrayList<Integer> spIdxList = new ArrayList<>();
    public MACD macd;
    public MALine ma;
    public Livermore livermore;
}
