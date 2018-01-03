package com.jw.rs.config;

import com.jw.rs.constant.RestCoreConstant;
import org.jboss.resteasy.plugins.server.servlet.HttpServletDispatcher;
import org.jboss.resteasy.plugins.server.servlet.ResteasyBootstrap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import javax.servlet.ServletContextListener;

/**
 * Spring bean configuration to config Resteasy components.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
@Configuration
@ComponentScan(basePackages = { "com.jw.rs.error.mapper" })
public class RestCoreConfig {

    @Value("${API_URL_MAPPING_PREFIX:/api}")
    private String apiUrlMappingPrefix;

    /**
     * Creates a singleton ServletContextInitializer bean.
     *
     * @return ServletContextInitializer bean
     */
    @Bean
    public ServletContextInitializer initializer() {
        return servletContext -> {
            // RestEasy configuration
            servletContext.setInitParameter(RestCoreConstant.RESTEASY_SCAN.getValue(),
                    RestCoreConstant.RESTEASY_SCAN_VALUE.getValue());
            servletContext.setInitParameter(RestCoreConstant.RESTEASY_SERVLET_MAPPING_PREFIX.getValue(),
                    apiUrlMappingPrefix);
        };
    }

    /**
     * Creates a singleton ServletContextListener bean.
     *
     * @return ServletContextListener bean
     */
    @Bean
    public ServletContextListener resteasyBootstrap() {
        return new ResteasyBootstrap();
    }

    /**
     * Creates a singleton ServletRegistrationBean bean.
     *
     * @return ServletRegistrationBean bean
     */
    @Bean
    public ServletRegistrationBean resteasyServlet() {
        final ServletRegistrationBean registrationBean = new ServletRegistrationBean();
        registrationBean.setServlet(new HttpServletDispatcher());
        registrationBean.setName(RestCoreConstant.RESTEASY_NAME.getValue());
        registrationBean.addUrlMappings(apiUrlMappingPrefix + "/*");
        registrationBean.addInitParameter(RestCoreConstant.RESTEASY_APPLICATION.getValue(),
                RestCoreConstant.RESTEASY_APPLICATION_VALUE.getValue());
        return registrationBean;
    }

}
