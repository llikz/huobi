package cn.grass.gate.model;

import org.litepal.crud.DataSupport;

/**
 * Created by min on 2017/7/31.
 * 表的(旧表和新表)
 */

public class Meter extends DataSupport {
    private int id;
    private String taskId;//任务ID
    private int store;//0 初始 1 暂存(提交后) 2 保存 (wifi下成功提交后)
    private String type;//0为旧表,1为新表
    private String power;//正向有功电量
    private String assetno;//资产编号
    private String sealnumber;//封印号
    private String meterpic;//电能表图片
    private String meterpicpath;//电能表图片path
    private String sealnopic;//封印号图片
    private String sealnopicpath;//封印号图片path
    private String allpic;//整体电表图片
    private String allpicpath;//整体电表图片path

    @Override
    public String toString() {
        return "Meter{" +
                "id=" + id +
                ", taskId='" + taskId + '\'' +
                ", store=" + store +
                ", type='" + type + '\'' +
                ", power='" + power + '\'' +
                ", assetno='" + assetno + '\'' +
                ", sealnumber='" + sealnumber + '\'' +
                ", meterpic='" + meterpic + '\'' +
                ", meterpicpath='" + meterpicpath + '\'' +
                ", sealnopic='" + sealnopic + '\'' +
                ", sealnopicpath='" + sealnopicpath + '\'' +
                ", allpic='" + allpic + '\'' +
                ", allpicpath='" + allpicpath + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getStore() {
        return store;
    }

    public void setStore(int store) {
        this.store = store;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getAssetno() {
        return assetno;
    }

    public void setAssetno(String assetno) {
        this.assetno = assetno;
    }

    public String getSealnumber() {
        return sealnumber;
    }

    public void setSealnumber(String sealnumber) {
        this.sealnumber = sealnumber;
    }

    public String getMeterpic() {
        return meterpic;
    }

    public void setMeterpic(String meterpic) {
        this.meterpic = meterpic;
    }

    public String getMeterpicpath() {
        return meterpicpath;
    }

    public void setMeterpicpath(String meterpicpath) {
        this.meterpicpath = meterpicpath;
    }

    public String getSealnopic() {
        return sealnopic;
    }

    public void setSealnopic(String sealnopic) {
        this.sealnopic = sealnopic;
    }

    public String getSealnopicpath() {
        return sealnopicpath;
    }

    public void setSealnopicpath(String sealnopicpath) {
        this.sealnopicpath = sealnopicpath;
    }

    public String getAllpic() {
        return allpic;
    }

    public void setAllpic(String allpic) {
        this.allpic = allpic;
    }

    public String getAllpicpath() {
        return allpicpath;
    }

    public void setAllpicpath(String allpicpath) {
        this.allpicpath = allpicpath;
    }
}
