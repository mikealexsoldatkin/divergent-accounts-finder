package divergent.accounts.finder.exporter;

import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@NoArgsConstructor
public class Exporter {
    @Setter
    private List<ExporterRowData> exportedData;
    public void exportToCsv(String path) {
        try {
            FileWriter writer = new FileWriter(path);
            CSVPrinter csvPrinter = new CSVPrinter(writer,
                    CSVFormat.Builder.create(CSVFormat.DEFAULT)
                            .setHeader(
                                "Customer UUID",
                                "Customer ID",
                                "First Name",
                                "Last Name",
                                "SSN",
                                "Enrolled Date",
                                "ACCOUNTS MATCHED?",
                                "MAIN CRM: Date Opened",
                                "PARTNERS CRM: Date Opened",
                                "MAIN CRM: Original Balance",
                                "PARTNERS CRM: Original Balance",
                                "MAIN CRM: Original Account Number",
                                "PARTNERS CRM: Original Account Number",
                                "MAIN CRM: Original Creditor Name",
                                "PARTNERS CRM: Original Creditor Name",
                                "MAIN CRM: Original Creditor UUID",
                                "PARTNERS CRM: Original Creditor UUID",
                                "MAIN CRM: Current Account Number",
                                "PARTNERS CRM: Current Account Number",
                                "MAIN CRM: Current Creditor Name",
                                "PARTNERS CRM: Current Creditor Name",
                                "MAIN CRM: Current Creditor UUID",
                                "PARTNERS CRM: Current Creditor UUID",
                                "MAIN CRM: Customer Creditor ID",
                                "PARTNERS CRM: Customer Creditor ID",
                                "MAIN CRM: Current Balance",
                                "PARTNERS CRM: Current Balance"
                            )
                            .build());

            for (ExporterRowData exporterRowData : exportedData) {
                csvPrinter.printRecord(
                        exporterRowData.getCustomerUUID(),
                        exporterRowData.getCustomerID(),
                        exporterRowData.getFirstName(),
                        exporterRowData.getLastName(),
                        exporterRowData.getSsn(),
                        exporterRowData.getEnrolledDate(),
                        exporterRowData.getIsAccountsMatched(),
                        exporterRowData.getMainAccount().getDateOpened(),
                        exporterRowData.getPartnersAccount().getDateOpened(),
                        exporterRowData.getMainAccount().getOriginalBalance(),
                        exporterRowData.getPartnersAccount().getOriginalBalance(),
                        exporterRowData.getMainAccount().getOriginalAccountNumber(),
                        exporterRowData.getPartnersAccount().getOriginalAccountNumber(),
                        exporterRowData.getMainAccount().getOriginalCreditorName(),
                        exporterRowData.getPartnersAccount().getOriginalCreditorName(),
                        exporterRowData.getMainAccount().getOriginalCreditorUUID(),
                        exporterRowData.getPartnersAccount().getOriginalCreditorUUID(),
                        exporterRowData.getMainAccount().getCurrentAccountNumber(),
                        exporterRowData.getPartnersAccount().getCurrentAccountNumber(),
                        exporterRowData.getMainAccount().getCurrentCreditorName(),
                        exporterRowData.getPartnersAccount().getCurrentCreditorName(),
                        exporterRowData.getMainAccount().getCurrentCreditorUUID(),
                        exporterRowData.getPartnersAccount().getCurrentCreditorUUID(),
                        exporterRowData.getMainAccount().getCustomerCreditorID(),
                        exporterRowData.getPartnersAccount().getCustomerCreditorID(),
                        exporterRowData.getMainAccount().getCurrentBalance(),
                        exporterRowData.getPartnersAccount().getCurrentBalance()
                );
            }
            csvPrinter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}