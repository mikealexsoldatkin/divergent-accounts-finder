package divergent.accounts.finder.importer;

import org.apache.commons.csv.CSVRecord;

public class ImporterPartners extends Importer {
    @Override
    public ImporterRowData getRow(CSVRecord record) {
        ImporterRowData rowData = new ImporterRowData();
        rowData.setCustomerUUID(record.get("customer_uuid"));
        rowData.setCustomerID(record.get("customer_id"));
        rowData.setFirstName(record.get("first_name"));
        rowData.setLastName(record.get("last_name"));
        rowData.setSsn(record.get("ssn"));
        rowData.setCustomerCreditorID(record.get("customer_creditor_id"));
        rowData.setOriginalCreditorName(record.get("creditor_name"));
        rowData.setDateOpened(record.get("date_opened"));
        rowData.setOriginalAccountNumber(record.get("original_account_number"));
        rowData.setOriginalCreditorUUID(record.get("original_creditor_uuid"));
        rowData.setOriginalBalance(record.get("original_balance"));
        rowData.setCurrentBalance(record.get("original_creditor_uuid"));
        rowData.setCurrentCreditorUUID(record.get("current_creditor_uuid"));
        rowData.setCurrentBalance(record.get("current_balance"));

        return rowData;
    }
}
