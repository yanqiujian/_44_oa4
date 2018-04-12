package com.qianfeng.oa.service;

import com.qianfeng.oa.activiti.param.SystemParam;
import com.qianfeng.oa.cache.IOACache;
import com.qianfeng.oa.dto.QingjiaNote;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 进行流程管理
 */
@Service
public class ProcessService implements IProcessService{


    @Autowired
    private RuntimeService runtimeService;
    @Autowired
    private TaskService taskService;
    @Autowired
    private HistoryService historyService;

    @Autowired
    private IOACache cache;

    public String apply(QingjiaNote note) {

        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(SystemParam.QINGJIA.ASSIGNEE,note.getAssignee());//设置审批人
        map.put(SystemParam.QINGJIA.DAYS,note.getTime());
//        map.put(SystemParam.QINGJIA.APPLY_USER_ID,applyUserId);

        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(SystemParam.PROCESS.PROCESS_QINGJIA, map);
        String startUserId = processInstance.getStartUserId();
        System.out.println(startUserId);
        return processInstance.getId();
    }

    public List<Task> queryTaskByAssignee(String userId) {
        List<Task> taskList = taskService.createTaskQuery()
                .taskAssignee(userId)
                .list();
        return taskList;
    }

    public void approve(String taskId, String assignee) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put(SystemParam.QINGJIA.ASSIGNEE,assignee);
        taskService.complete(taskId,map);
    }

    public List<HistoricTaskInstance> queryHistroyTaskByUser(String userId, Date date) {
        List<HistoricTaskInstance> instanceList = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(userId)
                .orderByHistoricTaskInstanceStartTime()
                .desc()
                .taskCompletedBefore(date)
                .list();
        return instanceList;
    }

    /**
     * 查询请假人的请假信息
     * @param userId
     * @return
     */
    public List<Map<Object, Object>> queryApplyUserHistoryTask(String userId) {
        ArrayList<Map<Object, Object>> maps = new ArrayList<Map<Object, Object>>();

        List<String> processIdList = cache.getAllProcessIdByKey(SystemParam.QINGJIA.CAHCE_KEY_YFB + userId, 0, 10);
        for (String processId:processIdList) {
            Map<Object, Object> objectObjectMap = cache.queryHashByKey(SystemParam.QINGJIA.CAHCE_KEY_YFB + processId);
            maps.add(objectObjectMap);
        }
        return maps;
    }

    public List<Map<Object, Object>> queryTaskListByAssignee(String assignee) {
        List<Task> taskList = taskService.createTaskQuery()
                .taskAssignee(assignee)
                .orderByTaskCreateTime()
                .desc()
                .list();
        ArrayList<Map<Object, Object>> maps = new ArrayList<Map<Object, Object>>();
        for (Task task:taskList) {
            String processId = task.getProcessInstanceId();
            Map<Object, Object> objectObjectMap = cache.queryHashByKey(SystemParam.QINGJIA.CAHCE_KEY_YFB + processId);
            objectObjectMap.put("taskId",task.getId());
            maps.add(objectObjectMap);
        }
        return maps;
    }


}
