package com.itangsoft.workflow.controller;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Process Controller
 *
 * @author fushuwei
 */
@RestController
@RequestMapping("process")
public class ProcessController {

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private HistoryService historyService;

    @GetMapping("deploy")
    public String deploy() {
        repositoryService.createDeployment()
                .addClasspathResource("测试流程.bpmn20.xml")
                .name("测试流程")
                .deploy();

        return "ok";
    }

    @GetMapping("start")
    public String start() {
        // 1. 启动流程
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("Process_test");

        // 2. 主管审批
        Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId())
                .taskAssignee("user01")
                .singleResult();
        taskService.complete(task.getId());

        // 3. 分管领导审批
        task = taskService.createTaskQuery().processInstanceId(processInstance.getId())
                .taskAssignee("user02")
                .singleResult();
        taskService.complete(task.getId());

        System.out.println("模拟流程从启动到结束全过程完毕");

        return "ok";
    }
}
