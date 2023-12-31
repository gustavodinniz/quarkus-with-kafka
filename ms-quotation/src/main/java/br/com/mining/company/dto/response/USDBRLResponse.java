package br.com.mining.company.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

@Data
@Builder
@Jacksonized
@AllArgsConstructor
public class USDBRLResponse {

    public String code;

    public String codein;

    public String name;

    public String high;

    public String low;

    public String varBid;

    public String pctChange;

    public String bid;

    public String ask;

    public String timestamp;

    public String create_date;

}