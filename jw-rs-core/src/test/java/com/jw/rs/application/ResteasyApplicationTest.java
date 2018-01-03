package com.jw.rs.application;

import com.jw.rs.annotation.RestApi;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.internal.util.reflection.Whitebox;
import org.springframework.context.ApplicationContext;

import javax.servlet.ServletContext;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

/**
 * Junit test class for {@link ResteasyApplication}.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
public class ResteasyApplicationTest {

    @Mock
    private ServletContext servletContext;

    @Mock
    private ApplicationContext applicationContext;

    @InjectMocks
    private ResteasyApplication resteasyApplication;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        resteasyApplication = new ResteasyApplication(servletContext);
    }

    /**
     * Test method {@link ResteasyApplication#getSingletons()}.
     */
    @Test
    public void testGetSingletons() {
        Map<String, Object> map = new HashMap<>();
        map.put("AString", new Object());
        when(applicationContext.getBeansWithAnnotation(RestApi.class)).thenReturn(map);
        Whitebox.setInternalState(resteasyApplication, "context", applicationContext);

        Set<Object> singletons = resteasyApplication.getSingletons();

        assertNotNull(singletons);
        assertTrue(singletons.size() == 1);
    }

}
