/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Trader;

import static Trader.FormulaLib.*;
import java.util.ArrayList;

/**
 *
 * @author zhangxr
 */
public class MALine {

    public MALine(ArrayList<Double> list) {
        pList = list;
    }

    public ArrayList<Double> getMAList(int num) {
        ArrayList<Double> maList = new ArrayList<>();
        int size = pList.size();
        for (int i = 0; i < size; i++) {
            maList.add(MA(pList, i, num));
        }
        return maList;
    }

    public ArrayList<Double> pList = new ArrayList<>();
}
