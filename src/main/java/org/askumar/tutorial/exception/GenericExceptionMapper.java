package org.askumar.tutorial.exception;

import org.askumar.tutorial.model.ErrorMessage;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable>{
  @Override
  public Response toResponse(Throwable throwable) {
    ErrorMessage errorMessage = new ErrorMessage("Oops Some Error Happenend. Check URL or, Retry After Sometime", 500, "http://www.ashtutorial.com");
    return Response.status(Status.INTERNAL_SERVER_ERROR)
        .entity(errorMessage)
        .build();
  }
}
