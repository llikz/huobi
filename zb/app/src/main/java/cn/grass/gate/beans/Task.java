package cn.grass.gate.beans;

/**
 * Created by min on 2017/7/28.
 * 代办任务的bean
 */

public class Task {
    private String areaName;//台区名称
    private String areaId;//台区id//进入台区列表时用

    private int meterNum;//电表数
    private int meterDoNum;//电表完成数
    private int meterUndoNum;//电表未完成数

    private int meterBoxNum;//电箱数
    private int meterBoxDoNum;//电箱完成数
    private int meterBoxUndoNum;//电箱未完成数

    private int meterAllNum;//整体数
    private int meterAllDoNum;//整体完成数
    private int meterAllUndoNum;//整体未完成数

    private String taskTime;//任务时间

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getMeterNum() {
        return meterNum;
    }

    public void setMeterNum(int meterNum) {
        this.meterNum = meterNum;
    }

    public int getMeterDoNum() {
        return meterDoNum;
    }

    public void setMeterDoNum(int meterDoNum) {
        this.meterDoNum = meterDoNum;
    }

    public int getMeterUndoNum() {
        return meterUndoNum;
    }

    public void setMeterUndoNum(int meterUndoNum) {
        this.meterUndoNum = meterUndoNum;
    }

    public int getMeterBoxNum() {
        return meterBoxNum;
    }

    public void setMeterBoxNum(int meterBoxNum) {
        this.meterBoxNum = meterBoxNum;
    }

    public int getMeterBoxDoNum() {
        return meterBoxDoNum;
    }

    public void setMeterBoxDoNum(int meterBoxDoNum) {
        this.meterBoxDoNum = meterBoxDoNum;
    }

    public int getMeterBoxUndoNum() {
        return meterBoxUndoNum;
    }

    public void setMeterBoxUndoNum(int meterBoxUndoNum) {
        this.meterBoxUndoNum = meterBoxUndoNum;
    }

    public int getMeterAllNum() {
        return meterAllNum;
    }

    public void setMeterAllNum(int meterAllNum) {
        this.meterAllNum = meterAllNum;
    }

    public int getMeterAllDoNum() {
        return meterAllDoNum;
    }

    public void setMeterAllDoNum(int meterAllDoNum) {
        this.meterAllDoNum = meterAllDoNum;
    }

    public int getMeterAllUndoNum() {
        return meterAllUndoNum;
    }

    public void setMeterAllUndoNum(int meterAllUndoNum) {
        this.meterAllUndoNum = meterAllUndoNum;
    }

    public String getTaskTime() {
        return taskTime;
    }

    public void setTaskTime(String taskTime) {
        this.taskTime = taskTime;
    }

    @Override
    public String toString() {
        return "Task{" +
                "areaName='" + areaName + '\'' +
                ", meterNum=" + meterNum +
                ", meterDoNum=" + meterDoNum +
                ", meterUndoNum=" + meterUndoNum +
                ", meterBoxNum=" + meterBoxNum +
                ", meterBoxDoNum=" + meterBoxDoNum +
                ", meterBoxUndoNum=" + meterBoxUndoNum +
                ", meterAllNum=" + meterAllNum +
                ", meterAllDoNum=" + meterAllDoNum +
                ", meterAllUndoNum=" + meterAllUndoNum +
                ", taskTime='" + taskTime + '\'' +
                '}';
    }
}
