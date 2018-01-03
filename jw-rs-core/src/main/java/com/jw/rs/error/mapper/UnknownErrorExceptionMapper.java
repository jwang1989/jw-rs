package com.jw.rs.error.mapper;

import com.jw.rs.model.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/**
 * REST general error handler which is used to handle unexpected errors.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
@Provider
@Component
public class UnknownErrorExceptionMapper implements ExceptionMapper<Throwable> {

    private static final Logger LOG = LoggerFactory.getLogger(UnknownErrorExceptionMapper.class);

    @Override
    public Response toResponse(Throwable exception) {
        ErrorResponse response = new ErrorResponse(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode(),
                exception.getMessage());
        LOG.error(String.format("Error %s", response));
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(response).build();
    }

}
