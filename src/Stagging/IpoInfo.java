/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Stagging;

/**
 *
 * @author zhangxr
 */
public class IpoInfo {

    public IpoInfo(String code, String name) {
        stockCode = code;
        stockName = name;
    }

    public String stockCode;
    public String stockName;

    public String marketPlate;
    public String offerDate;
    public String inquiryRange;
    public int handFund;
    public float luckyRate;
    public float offerPrice;
    public String offerPE;
    public float greenShoeRatio;
    public String comparableCompany;
    public float superPurchaseMultiples;
    public float totalRaiseFunds;
    public float grayGain;
    public float closeGain;
    public float openPrice;
    public float highPrice;
    public float lowPrice;
    public float closePrice;
    public String underwriter;

    public float handFundReal;
    public float grayPrice;
    public float openGain;

    public void getLevel2() {
        openGain = 100 * (openPrice - offerPrice) / offerPrice;
        grayPrice = offerPrice * (1 + grayGain / 100);
        getHandFundReal();
    }

    public void getHandFundReal() {
        if (inquiryRange.contains("-")) {
            float inquiryHigh = Float.parseFloat(inquiryRange.substring(inquiryRange.indexOf("-") + 1));
            handFundReal = (handFund / inquiryHigh) * offerPrice;
        } else {
            handFundReal = handFund;
        }
    }
}
