package com.itheima.activiti;

import org.activiti.api.process.runtime.connector.Connector;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class Activitispringboot06Application {

    public static void main(String[] args) {
        SpringApplication.run(Activitispringboot06Application.class, args);
    }

//    @Bean
//    public Connector testConnector() {
//        return integra
//    }

}
