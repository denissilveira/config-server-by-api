package com.poc.configserver.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories("com.poc.configserver.domain.repository")
@EnableTransactionManagement
public class DatabaseConfiguration {
}
