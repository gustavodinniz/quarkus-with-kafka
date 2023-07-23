package br.com.mining.company.service;

import br.com.mining.company.dto.OpportunityDTO;
import br.com.mining.company.dto.ProposalDTO;
import br.com.mining.company.dto.QuotationDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface OpportunityService {

    void saveOpportunity(ProposalDTO proposalDTO);

    void saveQuotation(QuotationDTO quotationDTO);

    List<OpportunityDTO> generateOpportunityData();

    ByteArrayInputStream generateCSVOpportunityReport();
}
