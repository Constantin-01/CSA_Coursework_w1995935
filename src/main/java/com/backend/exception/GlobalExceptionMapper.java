package com.backend.exception;

import com.backend.model.ErrorMessage;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable> {

    @Override
    public Response toResponse(Throwable exception) {

        ErrorMessage errorMessage = new ErrorMessage(
                "Something went wrong! There was an unexpected internal server error!",
                500,
                "link/endpoint"
        );

        return Response.status(500)
                .entity(errorMessage)
                .type("application/json")
                .build();
    }
}