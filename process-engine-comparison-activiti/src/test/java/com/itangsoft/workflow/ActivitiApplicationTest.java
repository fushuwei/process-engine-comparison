package com.itangsoft.workflow;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ActivitiApplicationTest {

    @Autowired
    RepositoryService repositoryService;

    @Test
    void contextLoads() {
    }

    /**
     * 部署流程
     */
    @Test
    void deployProcess() {
        Deployment deployment = repositoryService.createDeployment()
                .name("测试流程")
                .addClasspathResource("测试流程.bpmn20.xml")
                .deploy();

        System.out.println("流程部署 id = " + deployment.getId());
    }
}
