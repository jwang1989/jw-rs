package com.jw.rs.error.mapper;

import com.jw.rs.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * REST general error handler which is used to handle constraint violation errors.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
@Provider
@Component
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    private static final Logger LOG = LoggerFactory.getLogger(ConstraintViolationExceptionMapper.class);

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        ErrorResponse response = new ErrorResponse(Response.Status.BAD_REQUEST.getStatusCode(),
                exception.getMessage());
        LOG.info(String.format("Client validation error %s", response));
        return Response.status(Response.Status.BAD_REQUEST).entity(response).build();
    }

}
