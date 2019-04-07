package com.jumia.jpay.audit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
//@EnableDiscoveryClient
public class JpayAuditServiceApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(JpayAuditServiceApplication.class);
        ApplicationContext ctx = app.run(args);
        DatabaseSeederUtil seederUtil = ctx.getBean(DatabaseSeederUtil.class);
        seederUtil.doApplicationInitialisation();
    }
}
