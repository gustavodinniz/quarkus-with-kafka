package br.com.mining.company.service;

import br.com.mining.company.client.CurrencyPriceClient;
import br.com.mining.company.dto.request.QuotationEventRequest;
import br.com.mining.company.dto.response.CurrencyPriceResponse;
import br.com.mining.company.entity.QuotationEntity;
import br.com.mining.company.message.KafkaEvents;
import br.com.mining.company.repository.QuotationRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@ApplicationScoped
public class QuotationService {

    @Inject
    @RestClient
    CurrencyPriceClient currencyPriceClient;

    @Inject
    QuotationRepository quotationRepository;

    @Inject
    KafkaEvents kafkaEvents;

    private static final String PAIR = "USD-BRL";

    public void getCurrencyPrice() {
        log.info("Getting currency price...");
        CurrencyPriceResponse currencyPriceResponse = currencyPriceClient.getPriceByPair(PAIR);
        log.info("Currency price: {}", currencyPriceResponse.getUsdbrl());
        if (updateCurrentInfoPrice(currencyPriceResponse)) {
            log.info("Sending new kafka event...");
            kafkaEvents.sendNewKafkaEvent(buildQuotationEventRequest(currencyPriceResponse));
        }
    }

    private boolean updateCurrentInfoPrice(CurrencyPriceResponse currencyPriceResponse) {
        BigDecimal currentPrice = new BigDecimal(currencyPriceResponse.getUsdbrl().getBid());
        boolean updatePrice = false;

        List<QuotationEntity> quotationEntities = quotationRepository.findAll().list();
        if (quotationEntities.isEmpty()) {
            saveQuotation(currencyPriceResponse);
            updatePrice = true;
        } else {
            QuotationEntity lastDollarPrice = quotationEntities.get(quotationEntities.size() - 1);
            if (currentPrice.floatValue() > lastDollarPrice.getCurrencyPrice().floatValue()) {
                updatePrice = true;
                saveQuotation(currencyPriceResponse);
            }
        }
        log.info("Update price: {}", updatePrice);
        return updatePrice;
    }

    private void saveQuotation(CurrencyPriceResponse currencyPriceResponse) {
        log.info("Saving new quotation...");
        quotationRepository.persist(buildQuotationEntity(currencyPriceResponse));
        log.info("Quotation saved successfully!");
    }

    private QuotationEntity buildQuotationEntity(CurrencyPriceResponse currencyPriceResponse) {
        log.info("Building quotation entity...");
        return QuotationEntity.builder()
                .date(new Date())
                .currencyPrice(new BigDecimal(currencyPriceResponse.getUsdbrl().getBid()))
                .pctChange(currencyPriceResponse.getUsdbrl().getPctChange())
                .pair(PAIR)
                .build();
    }

    private QuotationEventRequest buildQuotationEventRequest(CurrencyPriceResponse currencyPriceResponse) {
        log.info("Building quotation event request...");
        return QuotationEventRequest.builder()
                .currencyPrice(new BigDecimal(currencyPriceResponse.getUsdbrl().getBid()))
                .date(new Date())
                .build();
    }
}
