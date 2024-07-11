package com.spasov.planplus;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("classpath:secrets.properties")
public class PlanPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(PlanPlusApplication.class, args);
    }
}
