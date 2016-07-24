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

    public void livermoreTrade(int idx) {
        boolean b = livermore.enterRiseStatus();
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

    public void maCrossTrade(int idx, ArrayList<Double> list) {
        boolean b = CROSS(idx, pList, list);
        boolean s = CROSS(idx, list, pList);
        trade(idx, b, s);
    }

    public void maCrossTrade(int idx, ArrayList<Double> sList, ArrayList<Double> lList) {
        boolean b = CROSS(idx, sList, lList);
        boolean s = CROSS(idx, lList, sList);
        trade(idx, b, s);
    }

    public void trade(int idx, boolean buy, boolean sell) {
        if (buy) {
            bpIdxList.add(idx);
        }
        if (sell & (bpIdxList.size() > 0)) {
            spIdxList.add(idx);
        }
    }

    public int getPositionDays() {
        int days = 0;
        for (int i = 0; i < bpIdxList.size(); i++) {
            days += spIdxList.get(i) - bpIdxList.get(i);
        }
        return days;
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
            days += spIdxList.get(i) - bpIdxList.get(i);
        }
        return (double) days / bpIdxList.size();
    }

    public double getMeanGainDays() {
        int days = 0;
        int times = 0;
        for (int i = 0; i < bpIdxList.size(); i++) {
            if (pList.get(spIdxList.get(i)) > pList.get(bpIdxList.get(i))) {
                days += spIdxList.get(i) - bpIdxList.get(i);
                times++;
            }
        }
        return (double) days / times;
    }

    public double getMeanLossDays() {
        int days = 0;
        int times = 0;
        for (int i = 0; i < bpIdxList.size(); i++) {
            if (pList.get(spIdxList.get(i)) <= pList.get(bpIdxList.get(i))) {
                days += spIdxList.get(i) - bpIdxList.get(i);
                times++;
            }
        }
        return (double) days / times;
    }

    private MainView mainView;
    private ArrayList<Double> pList = new ArrayList<>();
    public ArrayList<Integer> bpIdxList = new ArrayList<>();
    public ArrayList<Integer> spIdxList = new ArrayList<>();
    public MACD macd;
    public MALine ma;
    public Livermore livermore;
}
