package com.jw.rs.error.mapper;

import com.jw.rs.model.ErrorResponse;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * Junit test class for {@link UnknownErrorExceptionMapper}.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
public class UnknownErrorExceptionMapperTest {

    private UnknownErrorExceptionMapper mapper;

    @Before
    public void setup() {
        mapper = new UnknownErrorExceptionMapper();
    }

    /**
     * Test method {@link UnknownErrorExceptionMapper#toResponse(Throwable)}.
     */
    @Test
    public void testToResponse() {
        Exception exception = new Exception("fatal error!");
        Response response = mapper.toResponse(exception);

        assertNotNull(response);
        Object entity = response.getEntity();
        assertTrue(entity instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) entity;
        assertEquals(Integer.valueOf(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode()),
                errorResponse.getStatus());
        assertEquals("fatal error!", errorResponse.getReason());
    }

}
