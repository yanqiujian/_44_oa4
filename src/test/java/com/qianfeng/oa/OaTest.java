package com.qianfeng.oa;

import com.qianfeng.oa.activiti.param.SystemParam;
import com.qianfeng.oa.cache.IOACache;
import com.qianfeng.oa.dao.IUserDAO;
import com.qianfeng.oa.dto.QingjiaNote;
import com.qianfeng.oa.dto.UserDTO;
import com.qianfeng.oa.service.IProcessService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-core.xml")
public class OaTest {

    @Autowired
    private IProcessService processService;
    @Autowired
    private TaskService  taskService;
    @Autowired
    private HistoryService historyService;
    @Autowired
    private IOACache cache;

    @Autowired
    private IUserDAO userDAO;
    /**
     * 过程1：凤姐提交了请假申请给主管张三
     */
    @Test
    public void testCase1(){
        QingjiaNote qingjiaNote = new QingjiaNote();
//        String processId = processService.apply("Amy",1, "Admin");
//        HashMap<String, Object> map = new HashMap<String, Object>();
//        System.out.println(processId);
//        map.put("apply_user","Admin");
//        map.put("days","1");
//        map.put("type","事假");
//        map.put("department","IT支撑中心");
//        map.put("gangwei","高级Java工程师");
//        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String format = simpleDateFormat.format(new Date());
//        map.put("createtime",format);
//        map.put("assignee","Amy");
//        //保存的任务相关的所有参数信息
//        cache.saveHash(SystemParam.QINGJIA.CAHCE_KEY_YFB + processId,map);
//
//        //保存申请人的所有任务
//        cache.saveList(SystemParam.QINGJIA.CAHCE_KEY_YFB + "Admin",processId);
    }

    /**
     * 过程2：张三查询名下是否有凤姐的请假申请
     */
    @Test
    public void testCase2(){
        List<Task> taskList = processService.queryTaskByAssignee("zhangsan");
        for (Task task : taskList) {
            String taskId = task.getId(); //任务的ID
            String assignee = task.getAssignee();//任务审批人
            Date createTime = task.getCreateTime(); //任务创建时间
            Object applyUserId = taskService.getVariable(taskId, SystemParam.QINGJIA.APPLY_USER_ID);
            Object days= taskService.getVariable(taskId, SystemParam.QINGJIA.DAYS);
            System.out.println(taskId + "/" + assignee + "/" + createTime + "/" + applyUserId + "/" + days );
        }
    }

    /**
     * 过程3：审批
     */
    @Test
    public void testCase3(){
        processService.approve("15009","wangwu");
    }

    /**
     * 过程4：查询已经审批过的任务
     */
    @Test
    public void testCase4(){
        List<HistoricTaskInstance> list = processService.queryHistroyTaskByUser("zhangsan",new Date());
        for (HistoricTaskInstance instance:list) {
            String id = instance.getId();
            Date startTime = instance.getStartTime();
            Date endTime = instance.getEndTime();
            Long durationInMillis = instance.getDurationInMillis();
            String processInstanceId = instance.getProcessInstanceId();
            System.out.println(processInstanceId);

            Map<Object, Object> map = cache.queryHashByKey(SystemParam.QINGJIA.CAHCE_KEY_YFB + processInstanceId);
            Set<Object> keySet = map.keySet();
            for (Object obj:keySet) {
                Object o = map.get(obj);
                System.out.println(obj + "=" + o);
            }
//            Object days = taskService.getVariable(id, SystemParam.QINGJIA.DAYS);
            System.out.println(id + "/" + startTime + "/" + endTime + "/" + durationInMillis + "/"  );
        }
    }


    @Test
    public void testCase5(){
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("apply_user","fengjie");
        map.put("days","1");
        map.put("type","事假");
        cache.saveHash("sss",map);
    }

    @Test
    public void testCase6(){
        UserDTO userDTO = userDAO.checkUserByName("Amy");
        System.out.println(userDTO.getUsername());
    }

    @Test
    public void testCase7(){
        List<String> amy = userDAO.queryRoleByName("Amy");
        System.out.println(amy);
        List<String> strings = userDAO.queryPermissionByName(amy.get(0));
        System.out.println(strings);
    }

    /**
     * 查询某个用户名下的所有申请单
     */
    @Test
    public void testCase8(){
        ArrayList<Map<Object, Object>> maps = new ArrayList<Map<Object, Object>>();

        List<String> processIdList = cache.getAllProcessIdByKey(SystemParam.QINGJIA.CAHCE_KEY_YFB + "Admin", 0, 10);
        for (String processId:processIdList) {
            Map<Object, Object> objectObjectMap = cache.queryHashByKey(SystemParam.QINGJIA.CAHCE_KEY_YFB + processId);
            maps.add(objectObjectMap);
        }
    }

    @Test
    public void testCase9(){
        List<HistoricActivityInstance> list = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId("27501")
                .finished()
                .orderByHistoricActivityInstanceStartTime()
                .desc()
                .list();
        for (HistoricActivityInstance activityInstance:list) {
            System.out.println(activityInstance.getAssignee());
        }
    }

    /**
     * 查询已经完成的流程
     */
    @Test
    public void testCase10(){
        HistoricProcessInstanceQuery finished = historyService.createHistoricProcessInstanceQuery()
                .processInstanceId("27501")
                .finished();
        System.out.println(finished);
    }

    @Test
    public void testHaproxy(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://192.168.72.188:5001/java1706", "root", "123456");

//            for (int i = 0; i < 100; i++) {
                PreparedStatement pr = null;
                ResultSet res = null;
                try {
                    pr = conn.prepareStatement("select * from tb_demo");
                    res = pr.executeQuery();
                    if(res.next()) {
                        System.out.println(new Date().toLocaleString() + "->" + res.getInt(1));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    res.close();
                    pr.close();
                }

//                Thread.sleep(25000);
//            }

            conn.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
//        catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }


}
