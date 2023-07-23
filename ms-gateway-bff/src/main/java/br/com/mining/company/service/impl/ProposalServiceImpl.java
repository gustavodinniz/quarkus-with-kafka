package br.com.mining.company.service.impl;

import br.com.mining.company.client.ProposalRestClient;
import br.com.mining.company.dto.ProposalDetailsDTO;
import br.com.mining.company.service.ProposalService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
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
        return proposalRestClient.getProposalDetailsById(proposalId);
    }

    @Override
    public Response createProposal(ProposalDetailsDTO proposalDetails) {
        log.info("Creating new proposal for customer: {}", proposalDetails.getCustomer());
        return proposalRestClient.createProposal(proposalDetails);
    }

    @Override
    public Response removeProposal(long id) {
        log.info("Removing proposal by id: {}", id);
        return proposalRestClient.removeProposal(id);
    }
}
