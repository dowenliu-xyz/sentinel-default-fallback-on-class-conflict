package org.example.bug;

import com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SentinelDefaultFallbackOnClassConflictApplication {

    public static void main(String[] args) {
        SpringApplication.run(SentinelDefaultFallbackOnClassConflictApplication.class, args);
    }

    @Bean
    public SentinelResourceAspect sentinelResourceAspect() {
        return new SentinelResourceAspect();
    }

}
