/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Invest;

/**
 *
 * @author zhangxr
 */
public class SystemReport {

    public SystemReport() {
    }

    public SystemReport(String mode, String para) {
        tradeMode = mode;
        parameter = para;
    }

    public String tradeMode;
    public String parameter;

    public float addInvest;
    public float addOutput;
    public float netProfit;
    public float yieldRate;
    public float testYears;
    public float investYears;
    public float investTimeRatio;
    public float meanPositionDays;
    public float meanAnnualRate;
    public float meanDailyRate;
    public int investRounds;
    public float maxInvest;
    public float maxRoundTime;
    public float maxInvestCount;
    public float minInvestCount;
    public float meanInvestCount;
    public float maxLoss;
    public float maxLossRatio;
    public float minDiffRate;
    public float currentDiffRate;
}
