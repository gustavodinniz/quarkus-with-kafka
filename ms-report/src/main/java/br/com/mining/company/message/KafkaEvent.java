package br.com.mining.company.message;

import br.com.mining.company.dto.ProposalDTO;
import br.com.mining.company.dto.QuotationDTO;
import br.com.mining.company.service.OpportunityService;
import io.smallrye.reactive.messaging.annotations.Blocking;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Incoming;

@Slf4j
@ApplicationScoped
public class KafkaEvent {

    @Inject
    OpportunityService opportunityService;

    @Transactional
    @Incoming("proposal")
    public void receiveProposal(ProposalDTO proposal) {
        log.info("Received proposal: {}", proposal);
        opportunityService.saveOpportunity(proposal);
    }

    @Blocking
    @Incoming("quotation")
    public void receiveQuotation(QuotationDTO quotation) {
        log.info("Received quotation: {}", quotation);
        opportunityService.saveQuotation(quotation);
    }
}
