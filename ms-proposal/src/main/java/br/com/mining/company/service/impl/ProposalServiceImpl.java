package br.com.mining.company.service.impl;

import br.com.mining.company.dto.request.ProposalEventRequest;
import br.com.mining.company.dto.ProposalDetailsDTO;
import br.com.mining.company.entity.ProposalEntity;
import br.com.mining.company.message.KafkaEvent;
import br.com.mining.company.repository.ProposalRepository;
import br.com.mining.company.service.ProposalService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Optional;

@Slf4j
@ApplicationScoped
public class ProposalServiceImpl implements ProposalService {

    @Inject
    ProposalRepository proposalRepository;

    @Inject
    KafkaEvent kafkaEvent;

    @Override
    public ProposalDetailsDTO findFullProposal(long id) {
        log.info("Finding full proposal by id: {}", id);
        ProposalEntity proposalEntity = Optional.ofNullable(proposalRepository.findById(id))
                .orElseThrow(() -> {
                    log.error("Proposal not found!");
                    return new NotFoundException();
                });
        return buildProposalDetailsDTO(proposalEntity);
    }

    @Override
    @Transactional
    public void createNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
        log.info("Creating new proposal...");
        ProposalEventRequest proposal = buildAndSaveNewProposal(proposalDetailsDTO);
        log.info("Sending new kafka event...");
        kafkaEvent.sendNewKafkaEvent(proposal);
    }


    @Override
    @Transactional
    public void removeProposal(long id) {
        log.info("Removing proposal by id: {}", id);
        proposalRepository.deleteById(id);
        log.info("Proposal removed successfully!");
    }

    private ProposalEventRequest buildAndSaveNewProposal(ProposalDetailsDTO proposalDetailsDTO) {
        try {
            log.info("Building proposal...");
            ProposalEntity proposal = ProposalEntity.builder()
                    .created(new Date())
                    .proposalValidityDays(proposalDetailsDTO.getProposalValidityDays())
                    .country(proposalDetailsDTO.getCountry())
                    .customer(proposalDetailsDTO.getCustomer())
                    .priceTonne(proposalDetailsDTO.getPriceTonne())
                    .tonnes(proposalDetailsDTO.getTonnes())
                    .build();

            log.info("Saving proposal...");
            proposalRepository.persist(proposal);

            log.info("Proposal saved successfully!");
            return ProposalEventRequest.builder()
                    .proposalId(proposal.getId())
                    .customer(proposal.getCustomer())
                    .priceTonne(proposal.getPriceTonne())
                    .build();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error to create new proposal.");
        }
    }

    private ProposalDetailsDTO buildProposalDetailsDTO(ProposalEntity proposalEntity) {
        log.info("Building proposal details with id: {}...", proposalEntity.getId());
        return ProposalDetailsDTO.builder()
                .proposalId(proposalEntity.getId())
                .customer(proposalEntity.getCustomer())
                .priceTonne(proposalEntity.getPriceTonne())
                .tonnes(proposalEntity.getTonnes())
                .country(proposalEntity.getCountry())
                .proposalValidityDays(proposalEntity.getProposalValidityDays())
                .build();
    }
}
