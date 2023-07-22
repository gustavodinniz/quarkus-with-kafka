package br.com.mining.company.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class CurrencyPriceResponse {

    @JsonProperty("USDBRL")
    public USDBRLResponse usdbrl;

}
