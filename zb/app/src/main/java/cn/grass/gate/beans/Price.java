package cn.grass.gate.beans;

/**
 * Created by intel on 2018/4/26.
 */

public class Price {


    /**
     * createdAt : 2018-04-26 12:53:22
     * rate_24h_ago : 6.35
     * max_rate : 7.29
     * userModifyId : null
     * id : 14bfdb10-fb40-474d-b5e0-14e0b7ba4c73
     * sell_rate : 6.44
     * min_rate : 6.31
     * userAddId : null
     * updatedAt : null
     * buy_rate : 6.44
     */

    private String createdAt;
    private double rate_24h_ago;
    private double max_rate;
    private Object userModifyId;
    private String id;
    private double sell_rate;
    private double min_rate;
    private Object userAddId;
    private Object updatedAt;
    private double buy_rate;

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public double getRate_24h_ago() {
        return rate_24h_ago;
    }

    public void setRate_24h_ago(double rate_24h_ago) {
        this.rate_24h_ago = rate_24h_ago;
    }

    public double getMax_rate() {
        return max_rate;
    }

    public void setMax_rate(double max_rate) {
        this.max_rate = max_rate;
    }

    public Object getUserModifyId() {
        return userModifyId;
    }

    public void setUserModifyId(Object userModifyId) {
        this.userModifyId = userModifyId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getSell_rate() {
        return sell_rate;
    }

    public void setSell_rate(double sell_rate) {
        this.sell_rate = sell_rate;
    }

    public double getMin_rate() {
        return min_rate;
    }

    public void setMin_rate(double min_rate) {
        this.min_rate = min_rate;
    }

    public Object getUserAddId() {
        return userAddId;
    }

    public void setUserAddId(Object userAddId) {
        this.userAddId = userAddId;
    }

    public Object getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Object updatedAt) {
        this.updatedAt = updatedAt;
    }

    public double getBuy_rate() {
        return buy_rate;
    }

    public void setBuy_rate(double buy_rate) {
        this.buy_rate = buy_rate;
    }
}
