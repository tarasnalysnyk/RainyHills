package test.task.rest.resource;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import test.task.rest.resource.model.RainyHillsRequest;
import test.task.rest.resource.model.RainyHillsResponse;
import test.task.service.RainyHillsService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/rainy-hills")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@OpenAPIDefinition(servers = {
        @Server(url = "/RainyHills", description = "Using contextPath"),
        @Server(url = "/", description = "Default")}
)
public class RainyHillsResource {

    @Inject
    private RainyHillsService rainyHillsService;

    public RainyHillsResource() {
        // default constructor
    }

    @POST
    @Path("/volume")
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(summary = "This operation takes an array as an input, and calculates the volume of water which remained"
            + " after the rain, in units.",
            responses = {
                    @ApiResponse(description = "The volume of water which remained"
                            + " after the rain, in units", content = @Content(mediaType = MediaType.APPLICATION_JSON,
                            schema = @Schema(implementation = RainyHillsResponse.class))),
                    @ApiResponse(responseCode = "400", description = "Invalid input array, cannot calculate the volume")
            }
    )
    public Response volume(
            @Parameter(description = "Array of integer numbers. The array describes profile of a surface",
                    required = true) RainyHillsRequest request) {
        return Response.ok(
                RainyHillsResponse.builder()
                        .volume(rainyHillsService.calculateVolume(request.getArray()))
                        .build())
                .build();
    }
}
