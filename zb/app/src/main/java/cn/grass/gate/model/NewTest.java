package cn.grass.gate.model;

import org.litepal.crud.DataSupport;

/**
 * Created by min on 2017/7/29.
 */

public class NewTest extends DataSupport {
    private int id;
    private String taskId;
    private String newName;

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

    public String getNewName() {
        return newName;
    }

    public void setNewName(String newName) {
        this.newName = newName;
    }
}
