package br.com.mining.company.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.math.BigDecimal;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class ProposalEventRequest {

    private Long proposalId;

    private String customer;

    private BigDecimal priceTonne;

}
