package br.com.mining.company.service;

import br.com.mining.company.dto.ProposalDetailsDTO;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;

public interface ProposalService {

    ProposalDetailsDTO getProposalDetailsById(@PathParam("id") long id);

    Response createProposal(ProposalDetailsDTO proposalDetails);

    Response removeProposal(long id);
}
