package com.thc.adcpressureinapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.thc")
public class AdcpressureinApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdcpressureinApiApplication.class, args);
    }

}
