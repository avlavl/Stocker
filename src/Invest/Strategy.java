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

    public Strategy(MainView mv, double start, double slope) {
        mainView = mv;
        pList = mv.priceList;
        dList = mv.dateList;
        items = pList.size();
        sIndex = mv.sIdx;
        eIndex = mv.eIdx;
        investCoef = mv.investCoef;
        this.start = start;
        this.slope = slope;
    }

    public boolean sysInvestEva(double wLevel, double dCoef, double iLevel) {
        winLevel = wLevel / 100;
        diffCoef = dCoef;
        totalInput = 0;
        totalPrice = 0;
        totalNumber = 0;
        double input = 0;
        double number = 0;
        double weight = 0;
        weights = new double[items];
        basePoints = new double[items];
        profitRatios = new double[items];

        for (int i = sIndex; i <= eIndex; i++) {
            weight = 0;
            if (totalInput > 0) {
                totalPrice = totalNumber * pList.get(i);
                profit = totalPrice - totalInput;
                profitRatio = profit / totalInput;
                profitList.add(profit);
                profitRatios[i] = profitRatio;
                if (profitRatio >= winLevel) {
                    weight -= totalPrice;
                    RecordData recordData = new RecordData(dList.get(i), "赎回");
                    bsDateList.add(dList.get(i));
                    roundDateLists.add(bsDateList);
                    bsDateList = new ArrayList<>();
                    totalInputList.add(totalInput);
                    totalPriceList.add(totalPrice);
                    yieldList.add(profitRatio);
                    addInvest += totalInput;
                    addOutput += totalPrice;
                    recordData.price = pList.get(i);
                    recordData.number = totalNumber;
                    recordData.cost = totalInput / totalNumber;
                    recordData.totalInput = totalInput;
                    recordData.profit = profit;
                    recordData.profitRatio = profitRatio * 100;
                    recordDataList.add(recordData);
                    totalInput = 0;
                    totalPrice = 0;
                    totalNumber = 0;
                    profit = 0;
                    profitRatio = 0;
                }
            }

            basePoint = start + i * slope;
            basePoints[i] = basePoint;
            diffRate = pList.get(i) / basePoint;
            diffRateList.add(diffRate);
            if (pList.get(i) < basePoint * iLevel) {
                RecordData recordData = new RecordData(dList.get(i), "投入");
                input = (basePoint / investCoef) / Math.pow(diffRate, diffCoef);
                number = input / pList.get(i);
                weight += input;
                totalPrice += input;
                totalInput += input;
                totalNumber += number;
                bsDateList.add(dList.get(i));
                recordData.price = pList.get(i);
                recordData.diff = diffRate;
                recordData.input = input;
                recordData.number = number;
                recordData.cost = totalInput / totalNumber;
                recordData.totalInput = totalInput;
                recordData.profit = profit;
                profitRatio = profit / totalInput;
                profitRatios[i] = profitRatio;
                recordData.profitRatio = profitRatio * 100;
                recordDataList.add(recordData);
            } else if (totalInput > 0) {
                RecordData recordData = new RecordData(dList.get(i), "持有");
                recordData.price = pList.get(i);
                recordData.diff = diffRate;
                recordData.number = totalNumber;
                recordData.cost = totalInput / totalNumber;
                recordData.totalInput = totalInput;
                recordData.profit = profit;
                recordData.profitRatio = profitRatio * 100;
                recordDataList.add(recordData);
            }
            weights[i] = weight;
        }

        return (getInvestRounds() > 0);
    }

    public boolean sysSimpleInvestEva(double wLevel, double dCoef, double iLevel) {
        winLevel = wLevel / 100;
        diffCoef = dCoef;
        totalInput = 0;
        totalPrice = 0;
        totalNumber = 0;
        double input = 0;
        double number = 0;

        for (int i = sIndex; i <= eIndex; i++) {
            if (totalInput > 0) {
                totalPrice = totalNumber * pList.get(i);
                profit = totalPrice - totalInput;
                profitRatio = profit / totalInput;
                if (profitRatio >= winLevel) {
                    bsDateList.add(dList.get(i));
                    roundDateLists.add(bsDateList);
                    bsDateList = new ArrayList<>();
                    totalInputList.add(totalInput);
                    totalPriceList.add(totalPrice);
                    yieldList.add(profitRatio);
                    addInvest += totalInput;
                    addOutput += totalPrice;
                    totalInput = 0;
                    totalPrice = 0;
                    totalNumber = 0;
                    profit = 0;
                    profitRatio = 0;
                }
            }

            basePoint = start + i * slope;
            diffRate = pList.get(i) / basePoint;
            diffRateList.add(diffRate);
            if (pList.get(i) < basePoint * iLevel) {
                input = (basePoint / investCoef) / Math.pow(diffRate, diffCoef);
                number = input / pList.get(i);
                totalInput += input;
                totalNumber += number;
                bsDateList.add(dList.get(i));
            }
        }

        return (getInvestRounds() > 0);
    }

    public int getInvestRounds() {
        return totalInputList.size();
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

    public int getInvestCounts() {
        int times = 0;
        for (ArrayList<String> roundDateList : roundDateLists) {
            times += roundDateList.size() - 1;
        }
        times += bsDateList.size();
        return times;
    }

    public double getInvestTimeRatio() {
        return 100 * (double) getInvestCounts() * 7 / (mainView.testYears * 365.25);
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

    public double getMeanInvestCount() {
        int times = 0;
        for (ArrayList<String> roundDateList : roundDateLists) {
            times += roundDateList.size() - 1;
        }
        return (double) times / roundDateLists.size();
    }

    public double getMeanDailyRate() {
        int num = 0;
        double yield = 0;
        for (int i = 0; i < yieldList.size(); i++) {
            ArrayList<String> roundDateList = roundDateLists.get(i);
            String endDate = roundDateList.get(roundDateList.size() - 1);
            num += roundDateList.size() - 1;
            for (int j = 0; j < roundDateList.size() - 1; j++) {
                int time = mainView.daysBetween(roundDateList.get(j), endDate);
                yield += (double) yieldList.get(i) / time;
            }
        }
        return 10000 * yield / num;
    }

    public double getMeanPositionDays() {
        int num = 0;
        int days = 0;
        for (int i = 0; i < roundDateLists.size(); i++) {
            ArrayList<String> roundDateList = roundDateLists.get(i);
            String endDate = roundDateList.get(roundDateList.size() - 1);
            num += roundDateList.size() - 1;
            for (int j = 0; j < roundDateList.size() - 1; j++) {
                days += mainView.daysBetween(roundDateList.get(j), endDate);
            }
        }
        return (double) days / num;
    }

    public double getMaxInvest() {
        return Collections.max(totalInputList);
    }

    public double getMeanInvest() {
        double totalInput = 0;
        for (double input : totalInputList) {
            totalInput += input;
        }
        return totalInput / totalInputList.size();
    }

    public double getMaxLoss() {
        return Collections.min(profitList);
    }

    public double getMaxLossRatio() {
        double loss = 0;
        for (double ratio : profitRatios) {
            if (ratio < loss) {
                loss = ratio;
            }
        }
        return loss * 100;
    }

    public double getMeanDiffRate() {
        double totalDiffRate = 0;
        for (double diffRate : diffRateList) {
            totalDiffRate += diffRate;
        }
        return totalDiffRate / diffRateList.size();
    }

    public double getMeanNegaDiffRate() {
        double totalDiffRate = 0;
        int num = 0;
        for (double diffRate : diffRateList) {
            if (diffRate < 1) {
                totalDiffRate += diffRate;
                num++;
            }
        }
        return totalDiffRate / num;
    }

    public double getMinDiffRate() {
        return Collections.min(diffRateList);
    }

    public double getMeanInvestRate() {
        return 1 / Math.pow(getMeanNegaDiffRate(), diffCoef);
    }

    public double getMaxInvestRate() {
        return 1 / Math.pow(getMinDiffRate(), diffCoef);
    }

    public double getCurrentAsset() {
        return totalPrice;
    }

    public double getCurrentRatio() {
        return profitRatio * 100;
    }

    public double getKeyPoint() {
        if (totalInput > 0) {
            double keyPoint = (totalInput / totalNumber) * (1 + winLevel);
            return keyPoint;
        }
        return basePoint;
    }

    public double getKeyRatio() {
        if (totalInput > 0) {
            double keyRatio = totalInput * (1 + winLevel) / totalPrice - 1;
            return 100 * keyRatio;
        }
        return 100 * (basePoint - pList.get(items - 1)) / pList.get(items - 1);
    }

    public class RecordData {

        public RecordData(String date, String type) {
            this.date = date;
            this.type = type;
        }
        public String date;
        public String type;
        public double price;
        public double diff;
        public double input;
        public double number;
        public double cost;
        public double totalInput;
        public double profit;
        public double profitRatio;
    }

    public MainView mainView;
    public ArrayList<Double> pList = new ArrayList<>();
    public ArrayList<String> dList = new ArrayList<>();

    public double start;
    public double slope;
    public double winLevel;
    public double dispersion;
    public double basePoint;
    public double[] basePoints;

    public int items;
    public int sIndex = -1;
    public int eIndex = 0;
    public double[] weights;

    private double addInvest = 0;
    private double addOutput = 0;
    public double totalInput = 0;
    public double totalPrice = 0;
    public double totalNumber = 0;
    private ArrayList<String> bsDateList = new ArrayList<>();
    private final ArrayList<Double> totalInputList = new ArrayList<>();
    private final ArrayList<Double> totalPriceList = new ArrayList<>();
    private final ArrayList<ArrayList<String>> roundDateLists = new ArrayList<>();
    private final ArrayList<Double> yieldList = new ArrayList<>();
    private final ArrayList<Double> profitList = new ArrayList<>();
    public double[] profitRatios;
    private double diffRate = 0;
    private double profit = 0;
    private double profitRatio = 0;
    private double diffCoef = 1;
    public double investCoef = 1;
    private final ArrayList<Double> diffRateList = new ArrayList<>();

    public ArrayList<RecordData> recordDataList = new ArrayList<>();
}
