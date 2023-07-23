package br.com.mining.company.controller;

import br.com.mining.company.service.ReportService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.ServerErrorException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Date;

@Path("/opportunities")
public class ReportController {

    @Inject
    ReportService reportService;

    @GET
    @Path("/report")
    @RolesAllowed({"user", "manager"})
    @Produces(MediaType.APPLICATION_JSON)
    public Response generateReport() {
        try {
            return Response.ok(reportService.generateCSVOpportunityReport(), MediaType.APPLICATION_OCTET_STREAM)
                    .header("content-disposition", "attachment; filename = " + new Date() + "opportunities.csv")
                    .build();
        } catch (ServerErrorException e) {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/data")
    @RolesAllowed({"user", "manager"})
    public Response generateOpportunitiesData() {
        try {
            return Response.ok(reportService.getOpportunitiesData(), MediaType.APPLICATION_JSON).build();
        } catch (ServerErrorException e) {
            return Response.serverError().build();
        }
    }
}
