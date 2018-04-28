package cn.grass.gate.beans;

import java.io.Serializable;

/**
 * Created by min on 2017/7/28.
 * 台区(作废)
 */

public class Area implements Serializable{
    private String areaName;//台区名称
    private String areaId;//台区id
    private String userName;//用户名称
    private String userPhone;//用户电话
    private String userNo;//用户编号
    private String assetNo;//资产编号
    private String userAddr;//用电地址
    private int workType;//工作类型,表,箱,整体
    private String time;//时间
    private String userId;//用户ID,进入用户详情时用

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getWorkType() {
        return workType;
    }

    public void setWorkType(int workType) {
        this.workType = workType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public String getUserAddr() {
        return userAddr;
    }

    public void setUserAddr(String userAddr) {
        this.userAddr = userAddr;
    }

    @Override
    public String toString() {
        return "Area{" +
                "areaName='" + areaName + '\'' +
                ", areaId='" + areaId + '\'' +
                ", userName='" + userName + '\'' +
                ", userPhone='" + userPhone + '\'' +
                ", userNo='" + userNo + '\'' +
                ", assetNo='" + assetNo + '\'' +
                ", userAddr='" + userAddr + '\'' +
                ", workType=" + workType +
                ", time='" + time + '\'' +
                '}';
    }
}
