package cn.grass.gate.model;

import org.litepal.crud.DataSupport;

/**
 * Created by min on 2017/7/31.
 * 表箱的(旧表箱和新表箱)
 */

public class Box extends DataSupport {
    private int id;//主键
    private String taskId;//任务ID
    private int store;//0 初始 1 暂存(提交后) 2 保存 (wifi下成功提交后)
    private String boxstamp;//表箱封印
    private String boxpic;//表箱图片
    private String boxpicpath;//表箱图片path
    private String assetno;//资产编号
    private String model;//型号
    private String factory;//生产厂家
    private String spec;//规格
    private String boxmaterial;//箱体材质
    private String productdate;//生产日期
    private String protectlevel;//防护等级
    private String remark;//备注
    private String type;//0为旧表箱,1为新表箱

    public String getBoxpicpath() {
        return boxpicpath;
    }

    public void setBoxpicpath(String boxpicpath) {
        this.boxpicpath = boxpicpath;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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

    public String getBoxstamp() {
        return boxstamp;
    }

    public void setBoxstamp(String boxstamp) {
        this.boxstamp = boxstamp;
    }

    public String getBoxpic() {
        return boxpic;
    }

    public void setBoxpic(String boxpic) {
        this.boxpic = boxpic;
    }

    public String getAssetno() {
        return assetno;
    }

    public void setAssetno(String assetno) {
        this.assetno = assetno;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getFactory() {
        return factory;
    }

    public void setFactory(String factory) {
        this.factory = factory;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getBoxmaterial() {
        return boxmaterial;
    }

    public void setBoxmaterial(String boxmaterial) {
        this.boxmaterial = boxmaterial;
    }

    public String getProductdate() {
        return productdate;
    }

    public void setProductdate(String productdate) {
        this.productdate = productdate;
    }

    public String getProtectlevel() {
        return protectlevel;
    }

    public void setProtectlevel(String protectlevel) {
        this.protectlevel = protectlevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Box{" +
                "id=" + id +
                ", taskId='" + taskId + '\'' +
                ", store=" + store +
                ", boxstamp='" + boxstamp + '\'' +
                ", boxpic='" + boxpic + '\'' +
                ", boxpicpath='" + boxpicpath + '\'' +
                ", assetno='" + assetno + '\'' +
                ", model='" + model + '\'' +
                ", factory='" + factory + '\'' +
                ", spec='" + spec + '\'' +
                ", boxmaterial='" + boxmaterial + '\'' +
                ", productdate='" + productdate + '\'' +
                ", protectlevel='" + protectlevel + '\'' +
                ", remark='" + remark + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
