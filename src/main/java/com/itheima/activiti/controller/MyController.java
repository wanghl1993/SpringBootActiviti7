package com.itheima.activiti.controller;

import com.itheima.activiti.SecurityUtil;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *  控制器
 *      任务执行
 */
@RestController
public class MyController {

    @Autowired
    private ProcessRuntime processRuntime;

    @Autowired
    private TaskRuntime taskRuntime;

    @Autowired
    private SecurityUtil securityUtil;

    // 查询任务,执行任务
    @RequestMapping("/hello")
    public void testHello(){
        // 1.认证
//        securityUtil.logInAs("erdemedeiros"); //指定用户认证信息
        Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10)); //分页查询任务列表
        if(taskPage.getTotalItems() > 0){
            // 说明有任务
            for (Task task : taskPage.getContent()) {
                // 遍历任务列表
                System.out.println("任务1:" + task);

                // 拾取任务 candidateGroups="activitiTeam"
                taskRuntime.claim(
                        TaskPayloadBuilder.claim().withTaskId(task.getId()).build()
                );

                // 执行任务
                taskRuntime.complete(
                        TaskPayloadBuilder.complete().withTaskId(task.getId()).build()
                );
            }
        }

        // 再次查询新任务
        taskPage = taskRuntime.tasks(Pageable.of(0, 10));// 分页查询任务列表

        if(taskPage.getTotalItems() > 0){
            // 说明有任务
            for (Task task : taskPage.getContent()) {
                // 遍历任务列表
                System.out.println("任务2:" + task);
            }
        }

    }
}
