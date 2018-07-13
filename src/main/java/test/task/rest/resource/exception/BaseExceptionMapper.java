package test.task.rest.resource.exception;

import org.apache.log4j.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class BaseExceptionMapper implements ExceptionMapper<Exception> {

    private Logger logger = Logger.getLogger(BaseExceptionMapper.class);

    @Override
    public Response toResponse(Exception exception) {
        if (exception instanceof IllegalArgumentException) {
            logger.error("Exception occurred: " + exception.getMessage()
                    + "; mapped to " + Response.Status.BAD_REQUEST, exception);
            return Response
                    .status(Response.Status.BAD_REQUEST)
                    .entity(exception.getMessage()).build();
        } else {
            logger.error("Exception occurred: " + exception.getMessage()
                    + "; mapped to " + Response.Status.INTERNAL_SERVER_ERROR, exception);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("Unexpected response")
                    .build();
        }
    }
}
