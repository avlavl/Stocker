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

    public float openTotalGain;
    public float closeTotalGain;
    public float openTotalEarning;
    public float closeTotalEarning;
    public int investCounts;
    public float openWeightEarning;
    public float closeWeightEarning;
    public float openWeightRestictEarning;
    public float closeWeightRestictEarning;
}
