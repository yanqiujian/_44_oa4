package com.qianfeng.oa.dto;

import java.io.Serializable;

/**
 * 请假条
 */
public class QingjiaNote implements Serializable {

    private String taskId;
    private String owner;
    private String assignee;
    private String createTime;
    private String time;
    private String desc;

    public QingjiaNote() {
    }

    public QingjiaNote(String taskId, String owner, String assignee, String createTime, String time, String desc) {
        this.taskId = taskId;
        this.owner = owner;
        this.assignee = assignee;
        this.createTime = createTime;
        this.time = time;
        this.desc = desc;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
