package com.jw.rs.ws;

import com.jw.rs.annotation.RestApi;

import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Mock endpoint for integration test.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
@RestApi
@Path("/mock")
@Consumes(MediaType.APPLICATION_JSON)
public interface MockResource {

    /**
     * Retrieves greetings string.
     *
     * @return REST response
     */
    @GET
    @Path("/greetings/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response greetings(@NotNull String name);

}
