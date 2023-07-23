package br.com.mining.company.controller;

import br.com.mining.company.service.OpportunityService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Date;

@Path("/opportunities")
public class OpportunityController {

    @Inject
    OpportunityService opportunityService;

    @GET
    @Path("/report")
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
}
