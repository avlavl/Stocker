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
