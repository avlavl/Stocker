/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stagging;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author zhangxr
 */
public class Strategy {

    public Strategy(ArrayList<IpoInfo> list) {
        ipoInfoList = list;
    }

    public boolean sysEva(double wLevel, double dCoef, double iLevel) {
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

        return (getBlackTotalGain() > 0);
    }

    public float getBlackTotalGain() {
        float totalGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalGain += ipoInfo.blackGain;
        }
        return totalGain;
    }

    public float getOpenTotalGain() {
        float totalGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalGain += ipoInfo.openGain;
        }
        return totalGain;
    }

    public float getCloseTotalGain() {
        float totalGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalGain += ipoInfo.closeGain;
        }
        return totalGain;
    }

    public float getBlackTotalEarning() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.handFund * ipoInfo.blackGain / 100;
        }
        return totalEarning;
    }

    public float getOpenTotalEarning() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.handFund * ipoInfo.openGain / 100;
        }
        return totalEarning;
    }

    public float getCloseTotalEarning() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.handFund * ipoInfo.closeGain / 100;
        }
        return totalEarning;
    }

    public float getBlackWeightEarning() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.blackGain / 10000;
        }
        return totalEarning;
    }

    public float getOpenWeightEarning() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.openGain / 10000;
        }
        return totalEarning;
    }

    public float getCloseWeightEarning() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.closeGain / 10000;
        }
        return totalEarning;
    }

    public float getBlackWeightRestictEarning(float price) {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.totalRaiseFunds < price) {
                totalEarning += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.blackGain / 10000;
            }
        }
        return totalEarning;
    }

    public float getOpenWeightRestictEarning(float price) {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.totalRaiseFunds < price) {
                totalEarning += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.openGain / 10000;
            }
        }
        return totalEarning;
    }

    public float getCloseWeightRestictEarning(float price) {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.totalRaiseFunds < price) {
                totalEarning += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.closeGain / 10000;
            }
        }
        return totalEarning;
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
    ArrayList<IpoInfo> ipoInfoList;
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
