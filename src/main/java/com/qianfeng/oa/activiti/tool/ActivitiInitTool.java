package com.qianfeng.oa.activiti.tool;


import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.test.ActivitiRule;

/**
 * 进行Activiti的初始化工作
 */
public class ActivitiInitTool {


    /**
     * 完成Activiti初始化
     */
    public static void init(){
        ProcessEngineConfiguration configuration =  ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("spring-activiti.xml");
        configuration.buildProcessEngine();
    }

    /**
     * 部署流程
     */
    public static void publishProcess(){
        ProcessEngines.getDefaultProcessEngine()
                .getRepositoryService()
                .createDeployment()
                .name("请假流程")
                .addClasspathResource("process/process_qingjia.bpmn")
                .deploy();
    }

    public static void main(String[] args) {
//        init();
        publishProcess();
    }


}
