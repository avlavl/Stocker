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
public class Livermore {

    public Livermore(boolean status, int t1, int t2) {
        STATUST = status ? 1 : -1;
        TP1 = t1;
        TP2 = t2;
    }

    protected String arithmetic(double price) {
        String msg = "";
        STATUSY = STATUST;
        switch (STATUST) {
            case 1: // 主上升
                if (price > mainRiseVal) {
                    mainRiseVal = price;
                } else if (price < (mainRiseVal * (100 - TP1) / 100)) {
                    STATUST = 2;
                    normalFallUVal = price;
                    riseKeyHead = mainRiseVal;
                    msg = "↗ 进入自然回撤";
                }
                break;
            case 2: // 自然回撤
                if (price < fallKeyFoot) {
                    STATUST = -1;
                    resetTrendValue();
                    mainFallVal = price;
                    msg = "↘↘↘↘ 恢复下降趋势";
                } else if (price < riseKeyFoot * (100 - TP2) / 100) {
                    STATUST = -1;
                    resetTrendValue();
                    mainFallVal = price;
                    msg = "↘↘↘↘↘↘ 进入下降趋势";
                } else if (price < normalFallUVal) {
                    normalFallUVal = price;
                } else if (price > (normalFallUVal * (100 + TP1) / 100)) {
                    STATUST = 3;
                    normalRiseUVal = price;
                    riseKeyFoot = normalFallUVal;
                    msg = "↗ 进入自然回升";
                }
                break;
            case 3: // 自然回升
                if (price > mainRiseVal) {
                    STATUST = 1;
                    mainRiseVal = price;
                    msg = "↗↗↗ 恢复上升趋势";
                } else if (price > normalRiseUVal) {
                    normalRiseUVal = price;
                } else if ((price >= (normalRiseUVal * (100 - TP1) / 100)) && (price < (normalRiseUVal * (100 - TP2) / 100))) {
                    msg = "↗ 上升趋势可能改变";
                } else if (price < (normalRiseUVal * (100 - TP1) / 100)) {
                    if (price < riseKeyFoot * (100 - TP2) / 100) {
                        STATUST = -1;
                        resetTrendValue();
                        mainFallVal = price;
                        msg = "↘↘↘↘↘↘ 进入下降趋势";
                    } else if (price < normalFallUVal) {
                        STATUST = 2;
                        normalFallUVal = price;
                        msg = "↗ 进入自然回撤";
                    } else {
                        STATUST = 4;
                        minorFallUVal = price;
                        msg = "↗ 进入次级回撤";
                    }
                }
                break;
            case 4: // 次级回撤
                if (price < riseKeyFoot * (100 - TP2) / 100) {
                    STATUST = -1;
                    resetTrendValue();
                    mainFallVal = price;
                    msg = "↘↘↘↘↘↘ 进入下降趋势";
                } else if (price < normalFallUVal) {
                    STATUST = 2;
                    normalFallUVal = price;
                    msg = "↗ 进入自然回撤";
                } else if (price < minorFallUVal) {
                    minorFallUVal = price;
                } else if (price > minorFallUVal * (100 + TP1) / 100) {
                    if (price > mainRiseVal) {
                        STATUST = 1;
                        mainRiseVal = price;
                        msg = "↗↗↗ 恢复上升趋势";
                    } else if (price > normalRiseUVal) {
                        STATUST = 3;
                        normalRiseUVal = price;
                        msg = "↗ 进入自然回升";
                    } else {
                        STATUST = 5;
                        minorRiseUVal = price;
                        msg = "↗ 进入次级回升";
                    }
                }
                break;
            case 5: // 次级回升
                if (price > mainRiseVal) {
                    STATUST = 1;
                    mainRiseVal = price;
                    msg = "↗↗↗ 恢复上升趋势";
                } else if (price > normalRiseUVal) {
                    STATUST = 3;
                    normalRiseUVal = price;
                    msg = "↗ 进入自然回升";
                } else if (price > minorRiseUVal) {
                    minorRiseUVal = price;
                } else if (price < minorRiseUVal * (100 - TP1) / 100) {
                    if (price < riseKeyFoot * (100 - TP2) / 100) {
                        STATUST = -1;
                        resetTrendValue();
                        mainFallVal = price;
                        msg = "↘↘↘↘↘↘ 进入下降趋势";
                    } else if (price < normalFallUVal) {
                        STATUST = 2;
                        normalFallUVal = price;
                        msg = "↗ 进入自然回撤";
                    } else {
                        STATUST = 4;
                        minorFallUVal = price;
                        msg = "↗ 进入次级回撤";
                    }
                }
                break;

            case -1:    // 主下降
                if ((price < mainFallVal) || (mainFallVal == 0)) {
                    mainFallVal = price;
                } else if (price > (mainFallVal * (100 + TP1) / 100)) {
                    STATUST = -2;
                    normalRiseDVal = price;
                    fallKeyFoot = mainFallVal;
                    msg = "↘ 进入自然回升";
                }
                break;
            case -2:    // 自然回升
                if ((riseKeyHead != 0) && (price > riseKeyHead)) {
                    STATUST = 1;
                    resetTrendValue();
                    mainRiseVal = price;
                    msg = "↗↗↗↗ 恢复上升趋势";
                } else if ((fallKeyHead != 0) && (price > fallKeyHead * (100 + TP2) / 100)) {
                    STATUST = 1;
                    resetTrendValue();
                    mainRiseVal = price;
                    msg = "↗↗↗↗↗↗ 进入上升趋势";
                } else if (price > normalRiseDVal) {
                    normalRiseDVal = price;
                } else if (price < (normalRiseDVal * (100 - TP1) / 100)) {
                    STATUST = -3;
                    normalFallDVal = price;
                    fallKeyHead = normalRiseDVal;
                    msg = "↘ 进入自然回撤";
                }
                break;
            case -3:    // 自然回撤
                if (price < mainFallVal) {
                    STATUST = -1;
                    mainFallVal = price;
                    msg = "↘↘↘ 恢复下降趋势";
                } else if (price < normalFallDVal) {
                    normalFallDVal = price;
                } else if ((price <= (normalFallDVal * (100 + TP1) / 100)) && (price > (normalFallDVal * (100 + TP2) / 100))) {
                    msg = "↘ 下降趋势可能改变";
                } else if (price > (normalFallDVal * (100 + TP1) / 100)) {
                    if (price > fallKeyHead * (100 + TP2) / 100) {
                        STATUST = 1;
                        resetTrendValue();
                        mainRiseVal = price;
                        msg = "↗↗↗↗↗↗ 进入上升趋势";
                    } else if (price > normalRiseDVal) {
                        STATUST = -2;
                        normalRiseDVal = price;
                        msg = "↘ 进入自然回升";
                    } else {
                        STATUST = -4;
                        minorRiseDVal = price;
                        msg = "↘ 进入次级回升";
                    }
                }
                break;
            case -4:    // 次级回升
                if (price > fallKeyHead * (100 + TP2) / 100) {
                    STATUST = 1;
                    resetTrendValue();
                    mainRiseVal = price;
                    msg = "↗↗↗↗↗↗ 进入上升趋势";
                } else if (price > normalRiseDVal) {
                    STATUST = -2;
                    normalRiseDVal = price;
                    msg = "↘ 进入自然回升";
                } else if (price > minorRiseDVal) {
                    minorRiseDVal = price;
                } else if (price < minorRiseDVal * (100 - TP1) / 100) {
                    if (price < mainFallVal) {
                        STATUST = -1;
                        mainFallVal = price;
                        msg = "↘↘↘ 恢复下降趋势";
                    } else if (price < normalFallDVal) {
                        STATUST = -3;
                        normalFallDVal = price;
                        msg = "↘ 进入自然回撤";
                    } else {
                        STATUST = -5;
                        minorFallDVal = price;
                        msg = "↘ 进入次级回撤";
                    }
                }
                break;
            case -5:    // 次级回撤
                if (price < mainFallVal) {
                    STATUST = -1;
                    mainFallVal = price;
                    msg = "↘↘↘ 恢复下降趋势";
                } else if (price < normalFallDVal) {
                    STATUST = -3;
                    normalFallDVal = price;
                    msg = "↘ 进入自然回撤";
                } else if (price < minorFallDVal) {
                    minorFallDVal = price;
                } else if (price > minorFallDVal * (100 + TP1) / 100) {
                    if (price > fallKeyHead * (100 + TP2) / 100) {
                        STATUST = 1;
                        resetTrendValue();
                        mainRiseVal = price;
                        msg = "↗↗↗↗↗↗ 进入上升趋势";
                    } else if (price > normalRiseDVal) {
                        STATUST = -2;
                        normalRiseDVal = price;
                        msg = "↘ 进入自然回升";
                    } else {
                        STATUST = -4;
                        minorRiseDVal = price;
                        msg = "↘ 进入次级回升";
                    }
                }
                break;
            default:
                break;
        }
        return msg;
    }

    protected void resetTrendValue() {
        switch (STATUST) {
            case 1:
                riseKeyHead = 0;
                riseKeyFoot = 0;
                mainRiseVal = 0;
                normalRiseUVal = 0;
                normalFallUVal = 0;
                minorRiseUVal = 0;
                minorFallUVal = 0;
                break;
            case -1:
                fallKeyHead = 0;
                fallKeyFoot = 0;
                mainFallVal = 0;
                normalRiseDVal = 0;
                normalFallDVal = 0;
                minorRiseDVal = 0;
                minorFallDVal = 0;
                break;
            default:
                break;
        }
    }

    public boolean enterRiseStatus() {
        return (STATUSY < 0) && (STATUST > 0);
    }

    public boolean enterFallStatus() {
        return (STATUSY > 0) && (STATUST < 0);
    }

    public double riseKeyHead = 0;
    public double riseKeyFoot = 0;
    public double fallKeyHead = 0;
    public double fallKeyFoot = 0;
    public double mainRiseVal = 0;
    public double mainFallVal = 0;
    public double normalRiseUVal = 0;
    public double normalFallUVal = 0;
    public double normalRiseDVal = 0;
    public double normalFallDVal = 0;
    public double minorRiseUVal = 0;
    public double minorFallUVal = 0;
    public double minorRiseDVal = 0;
    public double minorFallDVal = 0;

    public int STATUST = 1;
    public int STATUSY = -1;
    public int TP1 = 10;
    public int TP2 = 5;

}
