package br.com.mining.company.service.impl;

import br.com.mining.company.client.ReportRestClient;
import br.com.mining.company.dto.OpportunityDTO;
import br.com.mining.company.service.ReportService;
import br.com.mining.company.utils.CSVHelper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.io.ByteArrayInputStream;
import java.util.List;

@Slf4j
@ApplicationScoped
public class ReportServiceImpl implements ReportService {

    @Inject
    @RestClient
    ReportRestClient reportRestClient;

    @Override
    public ByteArrayInputStream generateCSVOpportunityReport() {
        log.info("Retrieving opportunities data...");
        try {
            List<OpportunityDTO> opportunityData = reportRestClient.retrieveOpportunitiesData();
            return CSVHelper.opportunitiesToCSV(opportunityData);
        } catch (Exception e) {
            log.error("Error retrieving opportunities data: {}", e.getMessage());
            throw new BadRequestException();
        }
    }

    @Override
    public List<OpportunityDTO> getOpportunitiesData() {
        log.info("Retrieving opportunities data...");
        try {
            return reportRestClient.retrieveOpportunitiesData();
        } catch (Exception e) {
            log.error("Error retrieving opportunities data: {}", e.getMessage());
            throw new BadRequestException();
        }
    }
}
