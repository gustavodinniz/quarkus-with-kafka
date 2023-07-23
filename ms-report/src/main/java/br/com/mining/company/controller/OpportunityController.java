package br.com.mining.company.controller;

import br.com.mining.company.dto.OpportunityDTO;
import br.com.mining.company.service.OpportunityService;
import io.quarkus.security.Authenticated;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.jwt.JsonWebToken;

import java.util.Date;
import java.util.List;

@Authenticated
@Path("/opportunities")
public class OpportunityController {

    @Inject
    JsonWebToken jwt;

    @Inject
    OpportunityService opportunityService;

    @GET
    @Path("/report")
    @RolesAllowed({"user", "manager"})
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response generateReport() {
        try {
            return Response.ok(opportunityService.generateCSVOpportunityReport(), MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "attachment; filename = " + new Date() + "-opportunities.csv")
                    .build();
        } catch (ServerErrorException e) {
            return Response.serverError().build();
        }

    }

    @GET
    @Path("/data")
    @RolesAllowed({"user", "manager"})
    public List<OpportunityDTO> generateData() {
        return opportunityService.generateOpportunityData();
    }
}
