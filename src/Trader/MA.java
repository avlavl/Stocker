/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trader;

/**
 *
 * @author zhangxr
 */
public class MA {

    public MA() {
    }

    protected void updateData(String line, int num) {
        MA5Y = MA5T;
        MA10Y = MA10T;
        MA20Y = MA20T;
        MA60Y = MA60T;
        PRICEY = PRICET;

        String[] words = line.split("\t");
        String date = words[0];
        PRICET = Double.parseDouble(words[4]);
        try {
            MA5T = Double.parseDouble(words[num - 4]);
            MA10T = Double.parseDouble(words[num - 3]);
            MA20T = Double.parseDouble(words[num - 2]);
            MA60T = Double.parseDouble(words[num - 1]);
        } catch (Exception e) {
        }
    }

    public boolean isBreakUp(int maNum) {
        switch (maNum) {
            case 5:
                return (PRICEY < MA5Y) && (PRICET > MA5T);
            case 10:
                return (PRICEY < MA10Y) && (PRICET > MA10T);
            case 20:
                return (PRICEY < MA20Y) && (PRICET > MA20T);
            case 60:
                return (PRICEY < MA60Y) && (PRICET > MA60T);
            default:
                return false;
        }
    }

    public boolean isBreakDown(int maNum) {
        switch (maNum) {
            case 5:
                return (PRICEY > MA5Y) && (PRICET < MA5T);
            case 10:
                return (PRICEY > MA10Y) && (PRICET < MA10T);
            case 20:
                return (PRICEY > MA20Y) && (PRICET < MA20T);
            case 60:
                return (PRICEY > MA60Y) && (PRICET < MA60T);
            default:
                return false;
        }
    }

    public double MA5Y = 0;
    public double MA5T = 0;
    public double MA10Y = 0;
    public double MA10T = 0;
    public double MA20Y = 0;
    public double MA20T = 0;
    public double MA60Y = 0;
    public double MA60T = 0;

    public double PRICEY = 0;
    public double PRICET = 0;
}
