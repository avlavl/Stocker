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
public class Livermore {

    public Livermore() {

    }

    protected String arithmetic(double price) {
        String msg = "";
        switch (Status) {
            case "mainRiseStatus":
                if (price > mainRiseVal) {
                    mainRiseVal = price;
                } else if (price < (mainRiseVal * (100 - tpointValue1) / 100)) {
                    Status = "normalFallUStatus";
                    normalFallUVal = price;
                    riseKeyHead = mainRiseVal;
                    msg = "↗ 进入自然回撤";
                }
                break;
            case "normalFallUStatus":
                if (price < fallKeyFoot) {
                    Status = "mainFallStatus";
                    resetTrendValue();
                    mainFallVal = price;
                    msg = "↘↘↘↘ 恢复下降趋势";
                } else if (price < riseKeyFoot * (100 - tpointValue2) / 100) {
                    Status = "mainFallStatus";
                    resetTrendValue();
                    mainFallVal = price;
                    msg = "↘↘↘↘↘↘ 进入下降趋势";
                } else if (price < normalFallUVal) {
                    if ((vpointEnable) && (price < riseKeyHead * (100 - vpointValue) / 100)) {
                        Status = "mainFallStatus";
                        resetTrendValue();
                        mainFallVal = price;
                        msg = "↘↘↘↘ 进入下降趋势V";
                    } else {
                        normalFallUVal = price;
                    }
                } else if (price > (normalFallUVal * (100 + tpointValue1) / 100)) {
                    Status = "normalRiseUStatus";
                    normalRiseUVal = price;
                    riseKeyFoot = normalFallUVal;
                    msg = "↗ 进入自然回升";
                }
                break;
            case "normalRiseUStatus":
                if (price > mainRiseVal) {
                    Status = "mainRiseStatus";
                    mainRiseVal = price;
                    msg = "↗↗↗ 恢复上升趋势";
                } else if (price > normalRiseUVal) {
                    normalRiseUVal = price;
                } else if ((price >= (normalRiseUVal * (100 - tpointValue1) / 100)) && (price < (normalRiseUVal * (100 - tpointValue2) / 100))) {
                    msg = "↗ 上升趋势可能改变";
                } else if (price < (normalRiseUVal * (100 - tpointValue1) / 100)) {
                    if (price < riseKeyFoot * (100 - tpointValue2) / 100) {
                        Status = "mainFallStatus";
                        resetTrendValue();
                        mainFallVal = price;
                        msg = "↘↘↘↘↘↘ 进入下降趋势";
                    } else if (price < normalFallUVal) {
                        Status = "normalFallUStatus";
                        normalFallUVal = price;
                        msg = "↗ 进入自然回撤";
                    } else {
                        Status = "minorFallUStatus";
                        minorFallUVal = price;
                        msg = "↗ 进入次级回撤";
                    }
                }
                break;
            case "minorFallUStatus":
                if (price < riseKeyFoot * (100 - tpointValue2) / 100) {
                    Status = "mainFallStatus";
                    resetTrendValue();
                    mainFallVal = price;
                    msg = "↘↘↘↘↘↘ 进入下降趋势";
                } else if (price < normalFallUVal) {
                    Status = "normalFallUStatus";
                    normalFallUVal = price;
                    msg = "↗ 进入自然回撤";
                } else if (price < minorFallUVal) {
                    minorFallUVal = price;
                } else if (price > minorFallUVal * (100 + tpointValue1) / 100) {
                    if (price > mainRiseVal) {
                        Status = "mainRiseStatus";
                        mainRiseVal = price;
                        msg = "↗↗↗ 恢复上升趋势";
                    } else if (price > normalRiseUVal) {
                        Status = "normalRiseUStatus";
                        normalRiseUVal = price;
                        msg = "↗ 进入自然回升";
                    } else {
                        Status = "minorRiseUStatus";
                        minorRiseUVal = price;
                        msg = "↗ 进入次级回升";
                    }
                }
                break;
            case "minorRiseUStatus":
                if (price > mainRiseVal) {
                    Status = "mainRiseStatus";
                    mainRiseVal = price;
                    msg = "↗↗↗ 恢复上升趋势";
                } else if (price > normalRiseUVal) {
                    Status = "normalRiseUStatus";
                    normalRiseUVal = price;
                    msg = "↗ 进入自然回升";
                } else if (price > minorRiseUVal) {
                    minorRiseUVal = price;
                } else if (price < minorRiseUVal * (100 - tpointValue1) / 100) {
                    if (price < riseKeyFoot * (100 - tpointValue2) / 100) {
                        Status = "mainFallStatus";
                        resetTrendValue();
                        mainFallVal = price;
                        msg = "↘↘↘↘↘↘ 进入下降趋势";
                    } else if (price < normalFallUVal) {
                        Status = "normalFallUStatus";
                        normalFallUVal = price;
                        msg = "↗ 进入自然回撤";
                    } else {
                        Status = "minorFallUStatus";
                        minorFallUVal = price;
                        msg = "↗ 进入次级回撤";
                    }
                }
                break;

            case "mainFallStatus":
                if ((price < mainFallVal) || (mainFallVal == 0)) {
                    mainFallVal = price;
                } else if (price > (mainFallVal * (100 + tpointValue1) / 100)) {
                    Status = "normalRiseDStatus";
                    normalRiseDVal = price;
                    fallKeyFoot = mainFallVal;
                    msg = "↘ 进入自然回升";
                }
                break;
            case "normalRiseDStatus":
                if ((riseKeyHead != 0) && (price > riseKeyHead)) {
                    Status = "mainRiseStatus";
                    resetTrendValue();
                    mainRiseVal = price;
                    msg = "↗↗↗↗ 恢复上升趋势";
                } else if ((fallKeyHead != 0) && (price > fallKeyHead * (100 + tpointValue2) / 100)) {
                    Status = "mainRiseStatus";
                    resetTrendValue();
                    mainRiseVal = price;
                    msg = "↗↗↗↗↗↗ 进入上升趋势";
                } else if (price > normalRiseDVal) {
                    if ((vpointEnable) && (price > fallKeyFoot * (100 + vpointValue) / 100)) {
                        Status = "mainRiseStatus";
                        resetTrendValue();
                        mainRiseVal = price;
                        msg = "↗↗↗↗ 进入上升趋势V";
                    } else {
                        normalRiseDVal = price;
                    }
                } else if (price < (normalRiseDVal * (100 - tpointValue1) / 100)) {
                    Status = "normalFallDStatus";
                    normalFallDVal = price;
                    fallKeyHead = normalRiseDVal;
                    msg = "↘ 进入自然回撤";
                }
                break;
            case "normalFallDStatus":
                if (price < mainFallVal) {
                    Status = "mainFallStatus";
                    mainFallVal = price;
                    msg = "↘↘↘ 恢复下降趋势";
                } else if (price < normalFallDVal) {
                    normalFallDVal = price;
                } else if ((price <= (normalFallDVal * (100 + tpointValue1) / 100)) && (price > (normalFallDVal * (100 + tpointValue2) / 100))) {
                    msg = "↘ 下降趋势可能改变";
                } else if (price > (normalFallDVal * (100 + tpointValue1) / 100)) {
                    if (price > fallKeyHead * (100 + tpointValue2) / 100) {
                        Status = "mainRiseStatus";
                        resetTrendValue();
                        mainRiseVal = price;
                        msg = "↗↗↗↗↗↗ 进入上升趋势";
                    } else if (price > normalRiseDVal) {
                        Status = "normalRiseDStatus";
                        normalRiseDVal = price;
                        msg = "↘ 进入自然回升";
                    } else {
                        Status = "minorRiseDStatus";
                        minorRiseDVal = price;
                        msg = "↘ 进入次级回升";
                    }
                }
                break;
            case "minorRiseDStatus":
                if (price > fallKeyHead * (100 + tpointValue2) / 100) {
                    Status = "mainRiseStatus";
                    resetTrendValue();
                    mainRiseVal = price;
                    msg = "↗↗↗↗↗↗ 进入上升趋势";
                } else if (price > normalRiseDVal) {
                    Status = "normalRiseDStatus";
                    normalRiseDVal = price;
                    msg = "↘ 进入自然回升";
                } else if (price > minorRiseDVal) {
                    minorRiseDVal = price;
                } else if (price < minorRiseDVal * (100 - tpointValue1) / 100) {
                    if (price < mainFallVal) {
                        Status = "mainFallStatus";
                        mainFallVal = price;
                        msg = "↘↘↘ 恢复下降趋势";
                    } else if (price < normalFallDVal) {
                        Status = "normalFallDStatus";
                        normalFallDVal = price;
                        msg = "↘ 进入自然回撤";
                    } else {
                        Status = "minorFallDStatus";
                        minorFallDVal = price;
                        msg = "↘ 进入次级回撤";
                    }
                }
                break;
            case "minorFallDStatus":
                if (price < mainFallVal) {
                    Status = "mainFallStatus";
                    mainFallVal = price;
                    msg = "↘↘↘ 恢复下降趋势";
                } else if (price < normalFallDVal) {
                    Status = "normalFallDStatus";
                    normalFallDVal = price;
                    msg = "↘ 进入自然回撤";
                } else if (price < minorFallDVal) {
                    minorFallDVal = price;
                } else if (price > minorFallDVal * (100 + tpointValue1) / 100) {
                    if (price > fallKeyHead * (100 + tpointValue2) / 100) {
                        Status = "mainRiseStatus";
                        resetTrendValue();
                        mainRiseVal = price;
                        msg = "↗↗↗↗↗↗ 进入上升趋势";
                    } else if (price > normalRiseDVal) {
                        Status = "normalRiseDStatus";
                        normalRiseDVal = price;
                        msg = "↘ 进入自然回升";
                    } else {
                        Status = "minorRiseDStatus";
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
        switch (Status) {
            case "mainRiseStatus":
                riseKeyHead = 0;
                riseKeyFoot = 0;
                mainRiseVal = 0;
                normalRiseUVal = 0;
                normalFallUVal = 0;
                minorRiseUVal = 0;
                minorFallUVal = 0;
                break;
            case "mainFallStatus":
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

    public String Status = "mainRiseStatus";
    public String formerStatus = "";

    boolean vpointEnable = false;
    int vpointValue = 20;
    int tpointValue1 = 10;
    int tpointValue2 = 5;

}
