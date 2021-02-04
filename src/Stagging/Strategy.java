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

    public float getSellGain(IpoInfo ipoInfo, int sp) {
        return (sp == 0) ? ipoInfo.grayGain : ((sp == 1) ? ipoInfo.openGain : ipoInfo.closeGain);
    }

    public int getSelectedStocks() {
        return ipoInfoList.size();
    }

    public int getGainStocks(int sp) {
        int gainStocks = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            gainStocks += (getSellGain(ipoInfo, sp) > 0) ? 1 : 0;
        }
        return gainStocks;
    }

    public int getDropStocks(int sp) {
        int dripStocks = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            dripStocks += (getSellGain(ipoInfo, sp) <= 0) ? 1 : 0;
        }
        return dripStocks;
    }

    public float getTotalGain(int sp) {
        float totalGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalGain += getSellGain(ipoInfo, sp);
        }
        return totalGain;
    }

    public float getTotalEarn(int sp) {
        float totalEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarn += ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 100;
        }
        return totalEarn;
    }

    public float getWeightGain(int sp) {
        float totalGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalGain += ipoInfo.luckyRate * getSellGain(ipoInfo, sp) / 100;
        }
        return totalGain;
    }

    public float getWeightEarn(int sp) {
        float totalEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            totalEarn += ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 10000;
        }
        return totalEarn;
    }

    public float getEvenTotalGain(int sp) {
        float evenTotalGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            evenTotalGain += getSellGain(ipoInfo, sp);
        }
        evenTotalGain = evenTotalGain / ipoInfoList.size();
        return evenTotalGain;
    }

    public float getEvenTotalEarn(int sp) {
        float evenTotalEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            evenTotalEarn += ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 100;
        }
        evenTotalEarn = evenTotalEarn / ipoInfoList.size();
        return evenTotalEarn;
    }

    public float getEvenWeightGain(int sp) {
        float evenWeightGain = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            evenWeightGain += ipoInfo.luckyRate * getSellGain(ipoInfo, sp) / 100;
        }
        evenWeightGain = evenWeightGain / ipoInfoList.size();
        return evenWeightGain;
    }

    public float getEvenWeightEarn(int sp) {
        float evenWeightEarn = 0;
        for (IpoInfo ipoInfo : ipoInfoList) {
            evenWeightEarn += ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 10000;
        }
        evenWeightEarn = evenWeightEarn / ipoInfoList.size();
        return evenWeightEarn;
    }

    public float getMaxStockGain(int sp) {
        float maxStockGain = getSellGain(ipoInfoList.get(0), sp);
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (getSellGain(ipoInfo, sp) > maxStockGain) {
                maxStockGain = getSellGain(ipoInfo, sp);
            }
        }
        return maxStockGain;
    }

    public float getMaxStockEarn(int sp) {
        float maxStockEarn = ipoInfoList.get(0).handFundReal * getSellGain(ipoInfoList.get(0), sp) / 100;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 100 > maxStockEarn) {
                maxStockEarn = ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 100;
            }
        }
        return maxStockEarn;
    }

    public float getMaxWeightGain(int sp) {
        float maxWeightGain = ipoInfoList.get(0).luckyRate * getSellGain(ipoInfoList.get(0), sp) / 100;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.luckyRate * getSellGain(ipoInfo, sp) / 100 > maxWeightGain) {
                maxWeightGain = ipoInfo.luckyRate * getSellGain(ipoInfo, sp) / 100;
            }
        }
        return maxWeightGain;
    }

    public float getMaxWeightEarn(int sp) {
        float maxWeightEarn = ipoInfoList.get(0).luckyRate * ipoInfoList.get(0).handFundReal * getSellGain(ipoInfoList.get(0), sp) / 10000;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 10000 > maxWeightEarn) {
                maxWeightEarn = ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 10000;
            }
        }
        return maxWeightEarn;
    }

    public float getMaxStockDrop(int sp) {
        float maxStockDrop = getSellGain(ipoInfoList.get(0), sp);
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (getSellGain(ipoInfo, sp) < maxStockDrop) {
                maxStockDrop = getSellGain(ipoInfo, sp);
            }
        }
        return maxStockDrop;
    }

    public float getMaxStockLoss(int sp) {
        float maxStockLoss = ipoInfoList.get(0).handFundReal * getSellGain(ipoInfoList.get(0), sp) / 100;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 100 < maxStockLoss) {
                maxStockLoss = ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 100;
            }
        }
        return maxStockLoss;
    }

    public float getMaxWeightDrop(int sp) {
        float maxWeightDrop = ipoInfoList.get(0).luckyRate * getSellGain(ipoInfoList.get(0), sp) / 100;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.luckyRate * getSellGain(ipoInfo, sp) / 100 < maxWeightDrop) {
                maxWeightDrop = ipoInfo.luckyRate * getSellGain(ipoInfo, sp) / 100;
            }
        }
        return maxWeightDrop;
    }

    public float getMaxWeightLoss(int sp) {
        float maxWeightLoss = ipoInfoList.get(0).luckyRate * ipoInfoList.get(0).handFundReal * getSellGain(ipoInfoList.get(0), sp) / 10000;
        for (IpoInfo ipoInfo : ipoInfoList) {
            if (ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 10000 < maxWeightLoss) {
                maxWeightLoss = ipoInfo.luckyRate * ipoInfo.handFundReal * getSellGain(ipoInfo, sp) / 10000;
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
}
