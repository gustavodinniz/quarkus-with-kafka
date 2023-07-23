package br.com.mining.company.utils;

import br.com.mining.company.dto.OpportunityDTO;
import br.com.mining.company.exception.CSVException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CSVHelper {

    public static ByteArrayInputStream opportunitiesToCSV(List<OpportunityDTO> opportunities) {
        log.info("Creating CSV file...");
        final CSVFormat format = CSVFormat.DEFAULT.withHeader("Proposal Id", "Customer", "Price per Ton", "Best Currency Quote");
        try {
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);
            for (OpportunityDTO opps : opportunities) {
                List<String> data = Arrays.asList(String.valueOf(opps.getProposalId()), opps.getCustomer(),
                        String.valueOf(opps.getPriceTonne()), String.valueOf(opps.getLastDollarQuotation()));
                csvPrinter.printRecord(data);
            }

            csvPrinter.flush();
            log.info("CSV file created successfully.");
            return new ByteArrayInputStream(out.toByteArray());
        } catch (IOException e) {
            throw new CSVException("Fail to import data to CSV file: " + e.getMessage());
        }
    }
}
