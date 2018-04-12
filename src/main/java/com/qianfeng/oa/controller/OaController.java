package com.qianfeng.oa.controller;

import com.qianfeng.oa.service.IProcessService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class OaController {

    @Autowired
    private IProcessService processService;

    @RequestMapping("/login")
    public String login(String username,String password){
        try {
            UsernamePasswordToken passwordToken = new UsernamePasswordToken(username, password);
            SecurityUtils.getSubject()
                    .login(passwordToken);
        } catch (AuthenticationException e) {
            e.printStackTrace();
            return "login";
        }
        return "index";
    }

    @RequestMapping("/apply_history")
    public String queryApplyUserTask(String userId, Model model){
        List<Map<Object, Object>> maps = processService.queryApplyUserHistoryTask(userId);
        model.addAttribute("history_list",maps);
        return "qingjia_list";
    }

    @RequestMapping("/mytask")
    public String queryMytask(String userId, Model model){
        List<Map<Object, Object>> maps = processService.queryTaskListByAssignee(userId);
        model.addAttribute("tasklist",maps);
        return "approve_list";
    }

    @RequestMapping("/approve")
    @ResponseBody
    public String approve(String taskId){
        try {
            //Curry是人事主管（？？人事主管可以提前加载到Redis缓存中，方便此时使用）
            processService.approve(taskId,"Curry");
        } catch (Exception e) {
            e.printStackTrace();
            return "fail";
        }
        return "success";
    }
}
