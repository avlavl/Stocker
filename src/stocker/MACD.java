/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stocker;

/**
 *
 * @author zhangxr
 */
public class MACD {

    public MACD(int s, int l, int m) {
        sho = s;
        lon = l;
        mid = m;
    }

    protected void arithmetic(double price) {
        EMASY = EMAST;
        EMALY = EMALT;
        DIFY = DIFT;
        DEAY = DEAT;
        BARY = BART;
        STFY = STFT;
        LTFY = LTFT;

        if (EMASY == 0) {
            EMAST = price;
        } else {
            EMAST = EMASY * (sho - 1) / (sho + 1) + price * 2 / (sho + 1);
        }
        if (EMALY == 0) {
            EMALT = price;
        } else {
            EMALT = EMALY * (lon - 1) / (lon + 1) + price * 2 / (lon + 1);
        }

        DIFT = EMAST - EMALT;

        if (DEAY == 10000) {
            DEAT = DIFT * 2 / (mid + 1);
        } else {
            DEAT = DEAY * (mid - 1) / (mid + 1) + DIFT * 2 / (mid + 1);
        }

        BART = 2 * (DIFT - DEAT);
        STFT = (BART >= 0);
        LTFT = (DIFT >= 0);
    }

    public double EMASY = 0;
    public double EMAST = 0;
    public double EMALY = 0;
    public double EMALT = 0;
    public double DIFY = 0;
    public double DIFT = 0;
    public double DEAY = 10000;
    public double DEAT = 0;
    public double BARY = 0;
    public double BART = 0;
    public boolean STFY = true;
    public boolean STFT = true;
    public boolean LTFY = true;
    public boolean LTFT = true;

    private final int sho;
    private final int lon;
    private final int mid;

}
