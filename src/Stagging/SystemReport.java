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
public class SystemReport {

    public SystemReport() {
    }

    public SystemReport(String para) {
        parameter = para;
    }

    public String parameter;

    public int investRounds;
    public float addInvest;
    public float addOutput;
    public float netProfit;
    public float yieldRate;
    public float testYears;
    public int investCounts;
    public float investTimeRatio;
    public float maxRoundTime;
    public int maxInvestCount;
    public float meanInvestCount;
    public float meanDailyRate;
    public float meanPositionDays;
    public float maxInvest;
    public float meanInvest;
    public float maxLoss;
    public float maxLossRatio;
    public float meanDiffRate;
    public float meanNegaDiffRate;
    public float minDiffRate;
    public float meanInvestRate;
    public float maxInvestRate;
}
