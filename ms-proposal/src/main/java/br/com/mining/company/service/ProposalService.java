package br.com.mining.company.service;

import br.com.mining.company.dto.ProposalDetailsDTO;


public interface ProposalService {

    ProposalDetailsDTO findFullProposal(long id);

    void createNewProposal(ProposalDetailsDTO proposalDetailsDTO);

    void removeProposal(long id);
}
