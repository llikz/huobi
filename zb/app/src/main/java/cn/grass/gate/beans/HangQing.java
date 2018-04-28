package cn.grass.gate.beans;

/**
 * Created by intel on 2017/12/1.
 */

public class HangQing {


    /**
     * ticker : {"vol":"1621.7383","last":"10405.85","sell":"10405.83","buy":"10371.05","high":"11555.07","low":"9786.00"}
     * date : 1512004349529
     */

    private TickerBean ticker;
    private String date;

    public TickerBean getTicker() {
        return ticker;
    }

    public void setTicker(TickerBean ticker) {
        this.ticker = ticker;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public static class TickerBean {
        /**
         * vol : 1621.7383
         * last : 10405.85
         * sell : 10405.83
         * buy : 10371.05
         * high : 11555.07
         * low : 9786.00
         */

        private String vol;
        private String last;
        private String sell;
        private String buy;
        private String high;
        private String low;

        public String getVol() {
            return vol;
        }

        public void setVol(String vol) {
            this.vol = vol;
        }

        public String getLast() {
            return last;
        }

        public void setLast(String last) {
            this.last = last;
        }

        public String getSell() {
            return sell;
        }

        public void setSell(String sell) {
            this.sell = sell;
        }

        public String getBuy() {
            return buy;
        }

        public void setBuy(String buy) {
            this.buy = buy;
        }

        public String getHigh() {
            return high;
        }

        public void setHigh(String high) {
            this.high = high;
        }

        public String getLow() {
            return low;
        }

        public void setLow(String low) {
            this.low = low;
        }
    }
}
