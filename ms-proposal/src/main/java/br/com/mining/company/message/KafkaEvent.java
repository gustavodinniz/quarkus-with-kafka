package br.com.mining.company.message;

import br.com.mining.company.dto.request.ProposalEventRequest;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class KafkaEvent {

    @Channel("proposal")
    Emitter<ProposalEventRequest> quotationRequestEmitter;

    public void sendNewKafkaEvent(ProposalEventRequest proposalEventRequest) {
        log.info("Sending new kafka event: {}", proposalEventRequest);
        quotationRequestEmitter.send(proposalEventRequest).toCompletableFuture().join();
        log.info("Kafka event sent successfully!");
    }
}
