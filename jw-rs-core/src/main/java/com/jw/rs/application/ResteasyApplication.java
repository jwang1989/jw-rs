package com.jw.rs.application;

import com.jw.rs.annotation.RestApi;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;
import java.util.HashSet;
import java.util.Set;

/**
 * Resteasy application which is used to define endpoints.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
public class ResteasyApplication extends Application {

    private Set<Object> singletons = new HashSet<>();

    private ApplicationContext context;

    /**
     * Creates an instance of ResteasyApplication.
     *
     * @param servletContext ServletContext object
     */
    public ResteasyApplication(@Context ServletContext servletContext) {
        this.context = WebApplicationContextUtils.getWebApplicationContext(servletContext);
    }

    /*
     * (non-Javadoc)
     * @see javax.ws.rs.core.Application#getSingletons()
     */
    @Override
    public Set<Object> getSingletons() {
        this.singletons.addAll(context.getBeansWithAnnotation(RestApi.class).values());
        this.singletons.addAll(context.getBeansWithAnnotation(Provider.class).values());
        return this.singletons;
    }

}
