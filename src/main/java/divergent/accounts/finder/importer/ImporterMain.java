package divergent.accounts.finder.importer;

import org.apache.commons.csv.CSVRecord;

public class ImporterMain extends Importer {
    @Override
    public ImporterRowData getRow(CSVRecord record) {
        ImporterRowData rowData = new ImporterRowData();
        rowData.setCustomerUUID(record.get("customer_uuid"));
        rowData.setFirstName(record.get("first_name"));
        rowData.setLastName(record.get("last_name"));
        rowData.setSsn(record.get("ssn"));
        rowData.setCustomerCreditorID(record.get("customer_creditor_id"));
        rowData.setDateOpened(record.get("date_opened"));
        rowData.setOriginalAccountNumber(record.get("original_account_number"));
        rowData.setOriginalCreditorName(record.get("original_creditor_name"));
        rowData.setOriginalCreditorUUID(record.get("original_creditor_uuid"));
        rowData.setOriginalBalance(record.get("original_balance"));
        rowData.setCurrentAccountNumber(record.get("current_account_number"));
        rowData.setCurrentCreditorName(record.get("current_creditor_name"));
        rowData.setCurrentCreditorUUID(record.get("current_creditor_uuid"));
        rowData.setCurrentBalance(record.get("current_balance"));
        return rowData;
    }
}
