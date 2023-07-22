package br.com.mining.company.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;
import java.util.Date;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class QuotationEventRequest {

    private Date date;

    private BigDecimal currencyPrice;
}
