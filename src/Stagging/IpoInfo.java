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
    public float handFund;
    public float luckyRate;
    public float offerPrice;
    public float superPurchaseMultiples;
    public float totalRaiseFunds;
    public float blackGain;
    public float openGain;
    public float closeGain;
    public float blackPrice;
    public float openPrice;
    public float highPrice;
    public float lowPrice;
    public float closePrice;
}
