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

    public SystemReport(float point) {
        criticalPoint = point;
    }

    public int totalStocks;
    public int selectedStocks;
    public int gainStocks;
    public int dropStocks;

    public float totalGain;
    public float totalEarn;
    public float weightGain;
    public float weightEarn;
    public float evenTotalGain;
    public float evenTotalEarn;
    public float evenWeightGain;
    public float evenWeightEarn;

    public float maxStockGain;
    public float maxStockEarn;
    public float maxWeightGain;
    public float maxWeightEarn;
    public float maxStockDrop;
    public float maxStockLoss;
    public float maxWeightDrop;
    public float maxWeightLoss;

    public float criticalPoint;

    public float OTotalGain;
    public float CTotalGain;
    public float OTotalEarn;
    public float CTotalEarn;
    public int investCounts;
    public float OWeightEarn;
    public float CWeightEarn;
    public float OWeightEarnRestict;
    public float CWeightEarnRestict;
    public float OCWeightEarnRestict;
    public float COWeightEarnRestict;
}
