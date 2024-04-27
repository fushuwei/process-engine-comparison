package com.itangsoft.workflow;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.engine.repository.Deployment;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CamundaApplicationTests {

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
                .addClasspathResource("测试流程.bpmn")
                .deploy();

        System.out.println("流程部署 id = " + deployment.getId());
    }
}
