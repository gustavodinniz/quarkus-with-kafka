package br.com.mining.company.service;

import br.com.mining.company.dto.OpportunityDTO;

import java.io.ByteArrayInputStream;
import java.util.List;

public interface ReportService {

    ByteArrayInputStream generateCSVOpportunityReport();

    List<OpportunityDTO> getOpportunitiesData();
}
