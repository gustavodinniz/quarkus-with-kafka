package br.com.mining.company.service.impl;

import br.com.mining.company.client.ProposalRestClient;
import br.com.mining.company.dto.ProposalDetailsDTO;
import br.com.mining.company.service.ProposalService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.core.Response;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Slf4j
@ApplicationScoped
public class ProposalServiceImpl implements ProposalService {

    @Inject
    @RestClient
    ProposalRestClient proposalRestClient;

    @Override
    public ProposalDetailsDTO getProposalDetailsById(long proposalId) {
        log.info("Getting proposal details by id: {}", proposalId);
        try {
            return proposalRestClient.getProposalDetailsById(proposalId);
        } catch (Exception e) {
            log.error("Error getting proposal: {}", e.getMessage());
            throw new BadRequestException();
        }
    }

    @Override
    public Response createProposal(ProposalDetailsDTO proposalDetails) {
        log.info("Creating new proposal for customer: {}", proposalDetails.getCustomer());
        try {
            return proposalRestClient.createProposal(proposalDetails);
        } catch (Exception e) {
            log.error("Error creating proposal: {}", e.getMessage());
            throw new BadRequestException();
        }

    }

    @Override
    public Response removeProposal(long id) {
        log.info("Removing proposal by id: {}", id);
        try {
            return proposalRestClient.removeProposal(id);
        } catch (Exception e) {
            log.error("Error removing proposal: {}", e.getMessage());
            throw new BadRequestException();
        }
    }
}
