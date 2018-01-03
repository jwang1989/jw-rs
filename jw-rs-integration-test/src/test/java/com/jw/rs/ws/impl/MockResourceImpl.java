package com.jw.rs.ws.impl;

import com.jw.rs.ws.MockResource;

import javax.ws.rs.core.Response;

/**
 * Implementation of MockResource.
 *
 * @author JWang
 * @since Jan 02, 2018
 */
public class MockResourceImpl implements MockResource {

    @Override
    public Response greetings(String name) {
        return Response.status(Response.Status.OK)
                .entity(String.format("{\"greetings\": \"Hello, %s.\"}", name)).build();
    }

}
