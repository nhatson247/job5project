package com.fpt.job5project.config.mail;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class AsyncConfig {

    @Bean(name = "taskExecutor")
    public Executor taskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5); // Số lượng thread cơ bản
        executor.setMaxPoolSize(10); // Số lượng thread tối đa
        executor.setQueueCapacity(25); // Số lượng task tối đa trong hàng đợi
        executor.setThreadNamePrefix("MailSender-");
        executor.initialize();
        return executor;
    }
}