/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invest;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author zhangxr
 */
public class Strategy {

    public Strategy(MainView mv) {
        mainView = mv;
        pList = mv.priceList;
        items = pList.size();
        sIndex = mv.sIdx;
        eIndex = mv.eIdx;
    }

    public void maCrossTrade(int idx, ArrayList<Double> list) {
//        boolean b = CROSS(idx, pList, list);
//        boolean s = CROSS(idx, list, pList);
        //saveTradeIndex(idx, b, s);
    }

    public boolean sysInvestEva(int p1, int p2, int p3, int p4) {
        double totalInput = 0;
        double thisInput = 0;
        double thisNum = 0;
        double totalnum = 0;
        double totalAsset = 0;
        double baseline = p1;
        ArrayList<String> bsDateList = new ArrayList<>();

        for (int i = 0; i < items; i++) {
            if ((i >= sIndex) && (i <= eIndex)) {
                totalAsset = totalnum * pList.get(i);
                if (totalAsset < totalInput) {
                    lossList.add(totalInput - totalAsset);
                    lossRateList.add((totalInput - totalAsset) / totalInput);
                }
                if (totalAsset > totalInput * (1 + (double) p3 / 100)) {
                    spIdxList.add(i);
                    bsDateList.add(mainView.dateList.get(i));
                    roundDateLists.add(bsDateList);
                    bsDateList = new ArrayList<>();
                    mainView.msgLogger("止盈日期：" + mainView.dateList.get(i) + " 投入：" + (float) totalInput + " 回报：" + (float) totalAsset);
                    inputList.add(totalInput);
                    assetList.add(totalAsset);
                    yieldList.add((totalAsset - totalInput) / totalInput);
                    addInvest += totalInput;
                    addOutput += totalAsset;
                    totalInput = 0;
                    totalAsset = 0;
                    totalnum = 0;
                }

                baseline = 400 + i * p2;
                diffRate = baseline / pList.get(i);
                if (pList.get(i) < baseline) {
                    bpIdxList.add(i);
                    diffRateList.add(diffRate);
                    thisInput = (40 + i * p2 / 10) * diffRate;
                    totalInput += thisInput;
                    thisNum = thisInput / pList.get(i);
                    totalnum += thisNum;
                    bsDateList.add(mainView.dateList.get(i));
                    mainView.msgLogger(mainView.dateList.get(i) + " 金额：" + (float) thisInput + " 数量：" + (float) thisNum);
                }
            }
            if (i > eIndex) {
                break;
            }
        }
        return true;
    }

    public double getAddInvest() {
        return addInvest;
    }

    public double getAddOutput() {
        return addOutput;
    }

    public double getNetProfit() {
        return addOutput - addInvest;
    }

    public double getYieldRate() {
        return 100 * (addOutput - addInvest) / addInvest;
    }

    public double getInvestYears() {
        double time = 0;
        for (ArrayList<String> roundDateList : roundDateLists) {
            time += roundDateList.size() - 1;
        }
        time = time * 7 / 365.25;
        return time;
    }

    public double getInvestTimeRatio() {
        return 100 * getInvestYears() / mainView.testYears;
    }

    public double getMeanPositionDays() {
        int num = 0;
        int time = 0;
        for (int i = 0; i < roundDateLists.size(); i++) {
            ArrayList<String> roundDateList = roundDateLists.get(i);
            for (int j = 0; j < roundDateList.size() - 1; j++) {
                time += mainView.daysBetween(roundDateList.get(j), roundDateList.get(roundDateList.size() - 1));
                num++;
            }
        }
        return (double) time / num;
    }

    public double getMeanAnnualRate() {
        int num = 0;
        double yield = 0;
        for (int i = 0; i < yieldList.size(); i++) {
            ArrayList<String> roundList = roundDateLists.get(i);
            for (int j = 0; j < roundList.size() - 1; j++) {
                int time = mainView.daysBetween(roundList.get(j), roundList.get(roundList.size() - 1));
                yield += (double) 100 * (yieldList.get(i) / (time / 365.25));
                num++;
            }
        }
        return yield / num;
    }

    public double getMeanDailyRate() {
        int num = 0;
        double yield = 0;
        for (int i = 0; i < yieldList.size(); i++) {
            ArrayList<String> roundList = roundDateLists.get(i);
            for (int j = 0; j < roundList.size() - 1; j++) {
                int time = mainView.daysBetween(roundList.get(j), roundList.get(roundList.size() - 1));
                yield += (double) 10000 * yieldList.get(i) / time;
                num++;
            }
        }
        return yield / num;
    }

    public int getInvestRounds() {
        return inputList.size();
    }

    public double getMaxInvest() {
        return Collections.max(inputList);
    }

    public double getMaxRoundTime() {
        ArrayList<Integer> cycleList = new ArrayList<>();
        for (ArrayList<String> roundList : roundDateLists) {
            int time = mainView.daysBetween(roundList.get(0), roundList.get(roundList.size() - 1));
            cycleList.add(time);
        }
        double years = (double) Collections.max(cycleList) / 365.25;
        return years;
    }

    public int getMaxInvestCount() {
        ArrayList<Integer> timesList = new ArrayList<>();
        for (ArrayList<String> roundDateList : roundDateLists) {
            timesList.add(roundDateList.size() - 1);
        }
        return Collections.max(timesList);
    }

    public double getMinInvestCount() {
        ArrayList<Integer> timesList = new ArrayList<>();
        for (ArrayList<String> roundDateList : roundDateLists) {
            timesList.add(roundDateList.size() - 1);
        }
        return Collections.min(timesList);
    }

    public double getMeanInvestCount() {
        int times = 0;
        for (ArrayList<String> roundDateList : roundDateLists) {
            times += roundDateList.size() - 1;
        }
        return (double) times / roundDateLists.size();
    }

    public double getMaxLoss() {
        return Collections.max(lossList);
    }

    public double getMaxLossRatio() {
        return 100 * Collections.max(lossRateList);
    }

    public double getMaxDiffRate() {
        return Collections.max(diffRateList);
    }

    public double getCurrentDiffRate() {
        return diffRate;
    }

    public MainView mainView;
    public ArrayList<Double> pList = new ArrayList<>();
    public ArrayList<Integer> bpIdxList = new ArrayList<>();
    public ArrayList<Integer> spIdxList = new ArrayList<>();

    public double initPoint;
    public double slope;
    public double winLevel;
    public double dispersion;

    public int items;
    public int sIndex = -1;
    public int eIndex = 0;

    private double addInvest = 0;
    private double addOutput = 0;
    ArrayList<Double> inputList = new ArrayList<>();
    ArrayList<Double> assetList = new ArrayList<>();
    ArrayList<ArrayList<String>> roundDateLists = new ArrayList<>();
    ArrayList<Double> yieldList = new ArrayList<>();
    ArrayList<Double> lossList = new ArrayList<>();
    ArrayList<Double> lossRateList = new ArrayList<>();
    double diffRate = 0;
    ArrayList<Double> diffRateList = new ArrayList<>();
}
