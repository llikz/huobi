package cn.grass.gate.model;

import org.litepal.crud.DataSupport;

import java.util.Date;

public class HuoBiData extends DataSupport {


    /**
     * result : true
     * last : 6.44
     * lowestAsk : 6.43
     * highestBid : 6.44
     * percentChange : -0.31
     * baseVolume : 0
     * quoteVolume : 0
     * high24hr : 6.55
     * low24hr : 6.20
     */

    private String result;
    private String last;
    private String lowestAsk;
    private String highestBid;
    private String percentChange;
    private int baseVolume;
    private int quoteVolume;
    private String high24hr;
    private String low24hr;

    private Date createDate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getLast() {
        return last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public String getLowestAsk() {
        return lowestAsk;
    }

    public void setLowestAsk(String lowestAsk) {
        this.lowestAsk = lowestAsk;
    }

    public String getHighestBid() {
        return highestBid;
    }

    public void setHighestBid(String highestBid) {
        this.highestBid = highestBid;
    }

    public String getPercentChange() {
        return percentChange;
    }

    public void setPercentChange(String percentChange) {
        this.percentChange = percentChange;
    }

    public int getBaseVolume() {
        return baseVolume;
    }

    public void setBaseVolume(int baseVolume) {
        this.baseVolume = baseVolume;
    }

    public int getQuoteVolume() {
        return quoteVolume;
    }

    public void setQuoteVolume(int quoteVolume) {
        this.quoteVolume = quoteVolume;
    }

    public String getHigh24hr() {
        return high24hr;
    }

    public void setHigh24hr(String high24hr) {
        this.high24hr = high24hr;
    }

    public String getLow24hr() {
        return low24hr;
    }

    public void setLow24hr(String low24hr) {
        this.low24hr = low24hr;
    }

    @Override
    public String toString() {
        return "HuoBiData{" +
                "result='" + result + '\'' +
                ", last='" + last + '\'' +
                ", lowestAsk='" + lowestAsk + '\'' +
                ", highestBid='" + highestBid + '\'' +
                ", percentChange='" + percentChange + '\'' +
                ", baseVolume=" + baseVolume +
                ", quoteVolume=" + quoteVolume +
                ", high24hr='" + high24hr + '\'' +
                ", low24hr='" + low24hr + '\'' +
                ", createDate='" + createDate + '\'' +
                '}';
    }
}
