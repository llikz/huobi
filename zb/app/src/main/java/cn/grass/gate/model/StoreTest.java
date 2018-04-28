package cn.grass.gate.model;

import org.litepal.crud.DataSupport;

/**
 * Created by min on 2017/7/29.
 * 数据库相关的bean
 */

public class StoreTest  extends DataSupport {
    private int id;
    private String name;
    private String pic;
    private String taskId;

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

    @Override
    public String toString() {
        return "StoreTest{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pic='" + pic + '\'' +
                ", taskId='" + taskId + '\'' +
                '}';
    }
}
