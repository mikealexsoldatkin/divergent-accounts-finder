package divergent.accounts.finder.services;

import divergent.accounts.finder.exporter.AccountRowData;
import divergent.accounts.finder.exporter.ExporterRowData;
import divergent.accounts.finder.importer.ImporterRowData;
import divergent.accounts.finder.model.Account;
import divergent.accounts.finder.model.Customer;
import lombok.EqualsAndHashCode;

public class Mappers {
    public static Account getAccountFromRowData(ImporterRowData rowData) {
        Account account = new Account();
        account.setDateOpened(rowData.getDateOpened());
        account.setCustomerCreditorID(rowData.getCustomerCreditorID());
        account.setOriginalCreditorUUID(rowData.getOriginalCreditorUUID());
        account.setCurrentCreditorUUID(rowData.getCurrentCreditorUUID());
        account.setOriginalAccountNumber(rowData.getOriginalAccountNumber());
        account.setCurrentAccountNumber(rowData.getCurrentAccountNumber());
        account.setOriginalCreditorName(rowData.getOriginalCreditorName());
        account.setCurrentCreditorName(rowData.getCurrentCreditorName());
        account.setOriginalBalance(rowData.getOriginalBalance());
        account.setCurrentBalance(rowData.getCurrentBalance());

        return account;
    }

    public static Customer getCustomerFromRowData(ImporterRowData rowData) {
        Customer customer = new Customer();
        customer.setCustomerUUID(rowData.getCustomerUUID());
        customer.setCustomerID(rowData.getCustomerID());
        customer.setFirstName(rowData.getFirstName());
        customer.setLastName(rowData.getLastName());
        customer.setEnrolledDate(rowData.getEnrolledDate());
        customer.setSsn(rowData.getSsn());

        return customer;
    }

    public static ExporterRowData getRowDataFromCustomerAccounts(Customer customer, Account mainAccount, Account partnersAccount) {
        ExporterRowData exporterRowData = new ExporterRowData();
        exporterRowData.setCustomerUUID(customer.getCustomerUUID());
        exporterRowData.setCustomerID(customer.getCustomerID());
        exporterRowData.setFirstName(customer.getFirstName());
        exporterRowData.setLastName(customer.getLastName());
        exporterRowData.setSsn(customer.getSsn());
        exporterRowData.setEnrolledDate(customer.getEnrolledDate());
        exporterRowData.setIsAccountsMatched(mainAccount!=null&&partnersAccount!=null);
        exporterRowData.setMainAccount(Mappers.getAccountRowDataFromAccount(mainAccount));
        exporterRowData.setPartnersAccount(Mappers.getAccountRowDataFromAccount(partnersAccount));

        return exporterRowData;
    }

    public static AccountRowData getAccountRowDataFromAccount(Account account) {
        AccountRowData accountRowData = new AccountRowData();
        if (account==null) {
            return accountRowData;
        }
        accountRowData.setDateOpened(account.getDateOpened());
        accountRowData.setOriginalCreditorUUID(account.getOriginalCreditorUUID());
        accountRowData.setCurrentCreditorUUID(account.getCurrentCreditorUUID());
        accountRowData.setOriginalAccountNumber(account.getOriginalAccountNumber());
        accountRowData.setCurrentAccountNumber(account.getCurrentAccountNumber());
        accountRowData.setOriginalCreditorName(account.getOriginalCreditorName());
        accountRowData.setCurrentCreditorName(account.getCurrentCreditorName());
        accountRowData.setCustomerCreditorID(account.getCustomerCreditorID());
        accountRowData.setOriginalBalance(account.getOriginalBalance());
        accountRowData.setCurrentBalance(account.getCurrentBalance());

        return accountRowData;
    }
}
