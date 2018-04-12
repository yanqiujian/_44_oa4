package com.qianfeng.oa.service;

import com.qianfeng.oa.dto.QingjiaNote;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface IProcessService {

    /**
     * 启动流程（申请某一个业务开始）
     */
    String apply(QingjiaNote note);

    /**
     * 查询当前用户下的待审批任务
     * @return
     */
    List<Task> queryTaskByAssignee(String userId);

    /**
     * 审批
     */
    void approve(String taskId,String assignee);

    /**
     * 查询当前用户下的所有历史审批信息
     * @param userId
     * @return
     */
    List<HistoricTaskInstance> queryHistroyTaskByUser(String userId, Date date);

    /**
     * 查询申请人的请假信息
     * @param userId
     * @return
     */
    List<Map<Object,Object>> queryApplyUserHistoryTask(String userId);

    /**
     * 查询未审批的任务
     * @param assignee
     * @return
     */
    List<Map<Object, Object>> queryTaskListByAssignee(String assignee);
}
