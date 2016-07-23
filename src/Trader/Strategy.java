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
        boolean b = CROSS(idx, priceList, list);
        boolean s = CROSS(idx, list, priceList);
        trade(idx, b, s);
    }

    public void maCrossTrade(int idx, ArrayList<Double> sList, ArrayList<Double> lList) {
        boolean b = CROSS(idx, sList, lList);
        boolean s = CROSS(idx, lList, sList);
        trade(idx, b, s);
    }

    public void trade(int idx, boolean buy, boolean sell) {
        if (buy) {
            bpIndexList.add(idx);
        }
        if (sell & (bpIndexList.size() > 0)) {
            spIndexList.add(idx);
        }
    }

    public int getPositionDays() {
        int days = 0;
        for (int i = 0; i < bpIndexList.size(); i++) {
            days += spIndexList.get(i) - bpIndexList.get(i);
        }
        return days;
    }

    public double getPositionDaysRate() {
        int days = 0;
        for (int i = 0; i < bpIndexList.size(); i++) {
            days += spIndexList.get(i) - bpIndexList.get(i);
        }

        double rate = (double) 100 * days / mainView.tradeDays;
        return rate;
    }

    public double getMeanPositionDays() {
        int days = 0;
        for (int i = 0; i < bpIndexList.size(); i++) {
            days += spIndexList.get(i) - bpIndexList.get(i);
        }
        return (double) days / bpIndexList.size();
    }

    public double getMeanGainDays() {
        int days = 0;
        int times = 0;
        for (int i = 0; i < bpIndexList.size(); i++) {
            if (priceList.get(spIndexList.get(i)) > priceList.get(bpIndexList.get(i))) {
                days += spIndexList.get(i) - bpIndexList.get(i);
                times++;
            }
        }
        return (double) days / times;
    }

    public double getMeanLossDays() {
        int days = 0;
        int times = 0;
        for (int i = 0; i < bpIndexList.size(); i++) {
            if (priceList.get(spIndexList.get(i)) <= priceList.get(bpIndexList.get(i))) {
                days += spIndexList.get(i) - bpIndexList.get(i);
                times++;
            }
        }
        return (double) days / times;
    }

    public ArrayList<Integer> bpIndexList = new ArrayList<>();
    public ArrayList<Integer> spIndexList = new ArrayList<>();

    private MainView mainView;
    private ArrayList<Double> priceList = new ArrayList<>();
    public BRM brm;
    public Livermore livermore;
    public MACD macd;
    public MALine ma;
}
