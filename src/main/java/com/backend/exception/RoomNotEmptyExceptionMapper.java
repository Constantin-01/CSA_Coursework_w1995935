package com.backend.exception;

import com.backend.model.ErrorMessage;
import com.backend.exception.RoomNotEmptyException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider //This annotiation registers the mapper with Jax-RS
public class RoomNotEmptyExceptionMapper implements ExceptionMapper<RoomNotEmptyException> {

    @Override
    public Response toResponse(RoomNotEmptyException exception) {
        ErrorMessage errorMessage = new ErrorMessage(exception.getMessage(), 409, "link/endpoint");
        return Response.status(Status.CONFLICT)
                .type("application/json")
                .entity(errorMessage)
                .build();
    }

}
