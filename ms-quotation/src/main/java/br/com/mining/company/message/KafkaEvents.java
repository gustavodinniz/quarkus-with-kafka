package br.com.mining.company.message;

import br.com.mining.company.dto.request.QuotationEventRequest;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;

@Slf4j
@ApplicationScoped
public class KafkaEvents {

    @Channel("quotation-channel")
    Emitter<QuotationEventRequest> quotationRequestEmitter;

    public void sendNewKafkaEvent(QuotationEventRequest quotation) {
        log.info("Sending new kafka event: {}", quotation);
        quotationRequestEmitter.send(quotation).toCompletableFuture().join();
    }
}
