package br.com.mining.company.service.impl;

import br.com.mining.company.dto.OpportunityDTO;
import br.com.mining.company.dto.ProposalDTO;
import br.com.mining.company.dto.QuotationDTO;
import br.com.mining.company.entity.OpportunityEntity;
import br.com.mining.company.entity.QuotationEntity;
import br.com.mining.company.repository.OpportunityRepository;
import br.com.mining.company.repository.QuotationRepository;
import br.com.mining.company.service.OpportunityService;
import br.com.mining.company.utils.CSVHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Slf4j
@ApplicationScoped
public class OpportunityServiceImpl implements OpportunityService {

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    OpportunityRepository opportunityRepository;

    public void saveOpportunity(ProposalDTO proposal) {
        log.info("Saving opportunity...");
        List<QuotationEntity> quotationEntities = quotationRepository.findAll().list();
        Collections.reverse(quotationEntities);
        opportunityRepository.persist(buildOpportunity(proposal, quotationEntities));
        log.info("Opportunity saved successfully!");
    }


    @Override
    @Transactional
    public void saveQuotation(QuotationDTO quotation) {
        log.info("Saving quotation...");
        quotationRepository.persist(buildQuotation(quotation));
        log.info("Quotation saved successfully!");
    }

    private static QuotationEntity buildQuotation(QuotationDTO quotation) {
        log.info("Building quotation...");
        return QuotationEntity.builder()
                .date(new Date())
                .currencyPrice(quotation.getCurrencyPrice())
                .build();
    }


    @Override
    public ByteArrayInputStream generateCSVOpportunityReport() {
        log.info("Generating CSV opportunity report...");
        List<OpportunityDTO> opportunities = new ArrayList<>();
        opportunityRepository.findAll().list()
                .forEach(opportunity -> opportunities.add(buildOpportunityDTO(opportunity)));

        log.info("CSV opportunity report generated successfully!");
        return CSVHelper.opportunitiesToCSV(opportunities);
    }

    @Override
    public List<OpportunityDTO> generateOpportunityData() {
        log.info("Generating opportunity data...");
        List<OpportunityDTO> opportunities = new ArrayList<>();
        opportunityRepository.findAll()
                .stream()
                .forEach(opportunity -> opportunities.add(buildOpportunityDTO(opportunity)));

        log.info("Opportunity data generated successfully!");
        return opportunities;
    }

    private OpportunityDTO buildOpportunityDTO(OpportunityEntity opportunity) {
        log.info("Building opportunity with id: {}", opportunity.getId());
        return OpportunityDTO.builder()
                .proposalId(opportunity.getProposalId())
                .customer(opportunity.getCustomer())
                .priceTonne(opportunity.getPriceTonne())
                .lastDollarQuotation(opportunity.getLastDollarQuotation())
                .build();
    }

    private OpportunityEntity buildOpportunity(ProposalDTO proposal, List<QuotationEntity> quotationEntities) {
        log.info("Building opportunity...");
        return OpportunityEntity.builder()
                .date(new Date())
                .proposalId(proposal.getProposalId())
                .customer(proposal.getCustomer())
                .priceTonne(proposal.getPriceTonne())
                .lastDollarQuotation(quotationEntities.get(0).getCurrencyPrice())
                .build();
    }
}
