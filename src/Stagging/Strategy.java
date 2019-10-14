/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stagging;

import java.util.ArrayList;

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

        return (getOTotalGain() > 0);
    }

    public float getOTotalGain() {
        float totalGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalGain += ipoInfo.openGain;
        }
        return totalGain;
    }

    public float getCTotalGain() {
        float totalGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalGain += ipoInfo.closeGain;
        }
        return totalGain;
    }

    public float getOTotalEarn() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.handFund * ipoInfo.openGain / 100;
        }
        return totalEarning;
    }

    public float getCTotalEarn() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.handFund * ipoInfo.closeGain / 100;
        }
        return totalEarning;
    }

    public float getOWeightEarn() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.openGain / 10000;
        }
        return totalEarning;
    }

    public float getCWeightEarn() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.closeGain / 10000;
        }
        return totalEarning;
    }

    public float getOWeightEarnRestict(int type, float point) {
        float totalEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            float value = 0;
            switch (type) {
                case 1:
                    value = ipoInfo.luckyRate;
                    break;
                case 2:
                    value = ipoInfo.superPurchaseMultiples;
                    break;
                case 3:
                    value = ipoInfo.totalRaiseFunds;
                    break;
                case 4:
                    value = ipoInfo.offerPrice;
                    break;
                default:
                    value = ipoInfo.totalRaiseFunds;
                    break;
            }
            if (value < point) {
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.openGain / 10000;
            }
        }
        return totalEarn;
    }

    public float getCWeightEarnRestict(int type, float point) {
        float totalEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            float value = 0;
            switch (type) {
                case 1:
                    value = ipoInfo.luckyRate;
                    break;
                case 2:
                    value = ipoInfo.superPurchaseMultiples;
                    break;
                case 3:
                    value = ipoInfo.totalRaiseFunds;
                    break;
                case 4:
                    value = ipoInfo.offerPrice;
                    break;
                default:
                    value = ipoInfo.totalRaiseFunds;
                    break;
            }
            if (value < point) {
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.closeGain / 10000;
            }
        }
        return totalEarn;
    }

    public float getOCWeightEarnRestict(int type, float point) {
        float totalEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            float value = 0;
            switch (type) {
                case 1:
                    value = ipoInfo.luckyRate;
                    break;
                case 2:
                    value = ipoInfo.superPurchaseMultiples;
                    break;
                case 3:
                    value = ipoInfo.totalRaiseFunds;
                    break;
                case 4:
                    value = ipoInfo.offerPrice;
                    break;
                default:
                    value = ipoInfo.totalRaiseFunds;
                    break;
            }
            if (value < point) {
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.openGain / 10000;
            } else {
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.closeGain / 10000;
            }
        }
        return totalEarn;
    }

    public float getCOWeightEarnRestict(int type, float point) {
        float totalEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            float value = 0;
            switch (type) {
                case 1:
                    value = ipoInfo.luckyRate;
                    break;
                case 2:
                    value = ipoInfo.superPurchaseMultiples;
                    break;
                case 3:
                    value = ipoInfo.totalRaiseFunds;
                    break;
                case 4:
                    value = ipoInfo.offerPrice;
                    break;
                default:
                    value = ipoInfo.totalRaiseFunds;
                    break;
            }
            if (value < point) {
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.closeGain / 10000;
            } else {
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFund * ipoInfo.openGain / 10000;
            }
        }
        return totalEarn;
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
