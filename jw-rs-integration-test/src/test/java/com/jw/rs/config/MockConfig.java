package com.jw.rs.config;

import com.jw.rs.ws.MockResource;
import com.jw.rs.ws.impl.MockResourceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Spring bean config for mock resource.
 */
@Configuration
public class MockConfig {

    /**
     * Creates a singleton MockResource bean.
     *
     * @return MockResource bean
     */
    @Bean
    public MockResource mockResource() {
        return new MockResourceImpl();
    }

}
