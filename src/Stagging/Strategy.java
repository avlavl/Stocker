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

    public Strategy(ArrayList<IpoInfo> list, int sp) {
        ipoInfoList = list;
        sellPoint = sp;
    }

    public float getSellGain(IpoInfo ipoInfo) {
        return (sellPoint == 0) ? ipoInfo.grayGain : ((sellPoint == 1) ? ipoInfo.openGain : ipoInfo.closeGain);
    }

    public int getSelectedStocks() {
        return ipoInfoList.size();
    }

    public int getGainStocks() {
        int gainStocks = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            gainStocks += (getSellGain(ipoInfo) > 0) ? 1 : 0;
        }
        return gainStocks;
    }

    public int getDropStocks() {
        int dripStocks = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            dripStocks += (getSellGain(ipoInfo) <= 0) ? 1 : 0;
        }
        return dripStocks;
    }

    public float getTotalGain() {
        float totalGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalGain += getSellGain(ipoInfo);
        }
        return totalGain;
    }

    public float getTotalEarn() {
        float totalEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarn += ipoInfo.handFundReal * getSellGain(ipoInfo) / 100;
        }
        return totalEarn;
    }

    public float getWeightGain() {
        float totalGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalGain += ipoInfo.luckyRate * getSellGain(ipoInfo) / 100;
        }
        return totalGain;
    }

    public float getWeightEarn() {
        float totalEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarn += ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo) / 10000;
        }
        return totalEarn;
    }

    public float getEvenTotalGain() {
        float evenTotalGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            evenTotalGain += getSellGain(ipoInfo);
        }
        evenTotalGain = evenTotalGain / ipoInfoList.size();
        return evenTotalGain;
    }

    public float getEvenTotalEarn() {
        float evenTotalEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            evenTotalEarn += ipoInfo.handFundReal * getSellGain(ipoInfo) / 100;
        }
        evenTotalEarn = evenTotalEarn / ipoInfoList.size();
        return evenTotalEarn;
    }

    public float getEvenWeightGain() {
        float evenWeightGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            evenWeightGain += ipoInfo.luckyRate * getSellGain(ipoInfo) / 100;
        }
        evenWeightGain = evenWeightGain / ipoInfoList.size();
        return evenWeightGain;
    }

    public float getEvenWeightEarn() {
        float evenWeightEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            evenWeightEarn += ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo) / 10000;
        }
        evenWeightEarn = evenWeightEarn / ipoInfoList.size();
        return evenWeightEarn;
    }

    public float getMaxStockGain() {
        float maxStockGain = getSellGain(ipoInfoList.get(0));
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (getSellGain(ipoInfo) > maxStockGain) {
                maxStockGain = getSellGain(ipoInfo);
            }
        }
        return maxStockGain;
    }

    public float getMaxStockEarn() {
        float maxStockEarn = ipoInfoList.get(0).handFundReal * getSellGain(ipoInfoList.get(0)) / 100;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.handFundReal * getSellGain(ipoInfo) / 100 > maxStockEarn) {
                maxStockEarn = ipoInfo.handFundReal * getSellGain(ipoInfo) / 100;
            }
        }
        return maxStockEarn;
    }

    public float getMaxWeightGain() {
        float maxWeightGain = ipoInfoList.get(0).luckyRate * getSellGain(ipoInfoList.get(0)) / 100;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.luckyRate * getSellGain(ipoInfo) / 100 > maxWeightGain) {
                maxWeightGain = ipoInfo.luckyRate * getSellGain(ipoInfo) / 100;
            }
        }
        return maxWeightGain;
    }

    public float getMaxWeightEarn() {
        float maxWeightEarn = ipoInfoList.get(0).luckyRate * ipoInfoList.get(0).handFundReal * getSellGain(ipoInfoList.get(0)) / 10000;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo) / 10000 > maxWeightEarn) {
                maxWeightEarn = ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo) / 10000;
            }
        }
        return maxWeightEarn;
    }

    public float getMaxStockDrop() {
        float maxStockDrop = getSellGain(ipoInfoList.get(0));
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (getSellGain(ipoInfo) < maxStockDrop) {
                maxStockDrop = getSellGain(ipoInfo);
            }
        }
        return maxStockDrop;
    }

    public float getMaxStockLoss() {
        float maxStockLoss = ipoInfoList.get(0).handFundReal * getSellGain(ipoInfoList.get(0)) / 100;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.handFundReal * getSellGain(ipoInfo) / 100 < maxStockLoss) {
                maxStockLoss = ipoInfo.handFundReal * getSellGain(ipoInfo) / 100;
            }
        }
        return maxStockLoss;
    }

    public float getMaxWeightDrop() {
        float maxWeightDrop = ipoInfoList.get(0).luckyRate * getSellGain(ipoInfoList.get(0)) / 100;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.luckyRate * getSellGain(ipoInfo) / 100 < maxWeightDrop) {
                maxWeightDrop = ipoInfo.luckyRate * getSellGain(ipoInfo) / 100;
            }
        }
        return maxWeightDrop;
    }

    public float getMaxWeightLoss() {
        float maxWeightLoss = ipoInfoList.get(0).luckyRate * ipoInfoList.get(0).handFundReal * getSellGain(ipoInfoList.get(0)) / 10000;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo) / 10000 < maxWeightLoss) {
                maxWeightLoss = ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo) / 10000;
            }
        }
        return maxWeightLoss;
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
            totalEarning += ipoInfo.handFundReal * ipoInfo.openGain / 100;
        }
        return totalEarning;
    }

    public float getCTotalEarn() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.handFundReal * ipoInfo.closeGain / 100;
        }
        return totalEarning;
    }

    public float getOWeightEarn() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.luckyRate * ipoInfo.handFundReal * ipoInfo.openGain / 10000;
        }
        return totalEarning;
    }

    public float getCWeightEarn() {
        float totalEarning = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarning += ipoInfo.luckyRate * ipoInfo.handFundReal * ipoInfo.closeGain / 10000;
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
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFundReal * ipoInfo.openGain / 10000;
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
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFundReal * ipoInfo.closeGain / 10000;
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
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFundReal * ipoInfo.openGain / 10000;
            } else {
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFundReal * ipoInfo.closeGain / 10000;
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
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFundReal * ipoInfo.closeGain / 10000;
            } else {
                totalEarn += ipoInfo.luckyRate * ipoInfo.handFundReal * ipoInfo.openGain / 10000;
            }
        }
        return totalEarn;
    }

    ArrayList<IpoInfo> ipoInfoList;
    private int sellPoint = 2;
}
