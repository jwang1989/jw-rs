package com.jw.rs.it;

import com.jw.rs.config.MockConfig;
import com.jw.rs.config.RestCoreConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableAutoConfiguration
@Import(value = { MockConfig.class, RestCoreConfig.class })
@ComponentScan(basePackages = { "com.jw.rs" })
public class MockApp {

    public static void main(String[] args) {
        SpringApplication.run(MockApp.class, args);
    }

}
