package br.com.mining.company.controller;

import br.com.mining.company.dto.ProposalDetailsDTO;
import br.com.mining.company.service.ProposalService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/proposals")
public class ProposalController {

    @Inject
    ProposalService proposalService;

    @GET
    @Path("/{id}")
    @RolesAllowed({"user", "manager"})
    public Response getProposalDetailsById(@PathParam("id") long proposalId) {
        return Response.ok(proposalService.getProposalDetailsById(proposalId), MediaType.APPLICATION_JSON).build();
    }

    @POST
    @RolesAllowed("proposal-customer")
    public Response createProposal(ProposalDetailsDTO proposalDetailsDTO) {
        return Response.ok(proposalService.createProposal(proposalDetailsDTO).getStatus()).build();
    }

    @DELETE
    @Path("/remove/{id}")
    @RolesAllowed("manager")
    public Response removeProposal(@PathParam("id") long id) {
        return Response.ok(proposalService.removeProposal(id).getStatus()).build();
    }
}
