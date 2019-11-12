package com.poc.configserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EntityScan("com.poc.configserver*")
@EnableFeignClients
@EnableConfigServer
@EnableDiscoveryClient
public class ConfigServerByApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServerByApiApplication.class, args);
    }

}
