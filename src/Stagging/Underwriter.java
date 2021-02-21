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
public class Underwriter {

    public Underwriter(String name) {
        this.name = name;
    }

    public String name;
    public ArrayList<IpoInfo> ipoInfoList = new ArrayList<>();

    public int count;
    public int winCount;
    public float winRatio;
    public float totalGain;
    public float totalEarn;
    public float evenGain;
    public float evenEarn;

    public void getStatisInfo() {
        count = ipoInfoList.size();
        winCount = 0;
        totalGain = 0;
        totalEarn = 0;

        for (IpoInfo ipoInfo : ipoInfoList) {
            winCount += (ipoInfo.closeGain > 0) ? 1 : 0;
            totalGain += ipoInfo.closeGain;
            totalEarn += ipoInfo.handFundReal * ipoInfo.closeGain / 100;
        }
        winRatio = (float) winCount / count;
        evenGain = totalGain / count;
        evenEarn = totalEarn / count;
    }
}
