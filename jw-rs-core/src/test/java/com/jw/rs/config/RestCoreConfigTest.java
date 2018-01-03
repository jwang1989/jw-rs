package com.jw.rs.config;

import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;

import javax.servlet.ServletContextListener;

import static org.junit.Assert.assertNotNull;

/**
 * Junit test class for {@link RestCoreConfig}.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
public class RestCoreConfigTest {

    private RestCoreConfig restCoreConfig;

    @Before
    public void setup() {
        restCoreConfig = new RestCoreConfig();
    }

    /**
     * Test method {@link RestCoreConfig#initializer()}.
     */
    @Test
    public void testBuildServletContextInitializer() {
        ServletContextInitializer initializer = restCoreConfig.initializer();
        assertNotNull(initializer);
    }

    /**
     * Test method {@link RestCoreConfig#resteasyBootstrap()}.
     */
    @Test
    public void testBuildServletContextListener() {
        ServletContextListener listener = restCoreConfig.resteasyBootstrap();
        assertNotNull(listener);
    }

    /**
     * Test method {@link RestCoreConfig#resteasyServlet()}.
     */
    @Test
    public void testBuildServletRegistrationBean() {
        ServletRegistrationBean registrationBean = restCoreConfig.resteasyServlet();
        assertNotNull(registrationBean);
    }

}
