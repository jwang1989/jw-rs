package com.jw.rs.error.mapper;

import com.jw.rs.model.ErrorResponse;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Junit test class for {@link ConstraintViolationExceptionMapper}.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
public class ConstraintViolationExceptionMapperTest {

    private ConstraintViolationExceptionMapper mapper;

    @Before
    public void setup() {
        mapper = new ConstraintViolationExceptionMapper();
    }

    /**
     * Test method {@link ConstraintViolationExceptionMapper#toResponse(ConstraintViolationException)}.
     */
    @Test
    public void testToResponse() {
        ConstraintViolation<?> constraintViolation = mock(ConstraintViolation.class);
        when(constraintViolation.getMessage()).thenReturn("validation error!");
        Set<ConstraintViolation<?>> constraintViolations = new HashSet<>();
        constraintViolations.add(constraintViolation);
        ConstraintViolationException exception = mock(ConstraintViolationException.class);
        when(exception.getConstraintViolations()).thenReturn(constraintViolations);
        Response response = mapper.toResponse(exception);

        assertNotNull(response);
        Object entity = response.getEntity();
        assertTrue(entity instanceof ErrorResponse);
        ErrorResponse errorResponse = (ErrorResponse) entity;
        assertEquals(Integer.valueOf(Response.Status.BAD_REQUEST.getStatusCode()),
                errorResponse.getStatus());
        assertEquals("validation error!", errorResponse.getReason());
    }

}
