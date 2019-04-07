package com.jumia.jpay.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
@EnableEurekaClient
public class JpayAuditServiceApplication {

    public static void main(String[] args) {
        // SpringApplication.run(JpayAuditServiceApplication.class, args);


        SpringApplication app = new SpringApplication(JpayAuditServiceApplication.class);
        ApplicationContext ctx = app.run(args);
        AuditLogSaver logSaver = ctx.getBean(AuditLogSaver.class);
        logSaver.indexSampleAudit();

    }
}
