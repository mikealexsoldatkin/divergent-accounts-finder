package divergent.accounts.finder.services;

import divergent.accounts.finder.exporter.Exporter;
import divergent.accounts.finder.exporter.ExporterRowData;
import divergent.accounts.finder.importer.Importer;
import divergent.accounts.finder.model.Account;
import divergent.accounts.finder.model.ConvergenceLevel;
import divergent.accounts.finder.model.Customer;
import divergent.accounts.finder.sources.SourceType;
import divergent.accounts.finder.sources.addAccount.AddAccountOnSourceFactory;
import lombok.Getter;
import divergent.accounts.finder.importer.ImporterRowData;

import java.util.*;

@Getter
public class AccountsProcessing {
    private final Map<String, Customer> customers;
    private List<ExporterRowData> exportedData;

    public AccountsProcessing() {
        this.customers = new HashMap<String, Customer>();
        this.exportedData = new LinkedList<ExporterRowData>();
    }

    public void runImport(Importer importer, String path, SourceType sourceType) {
        importer.importFromCsv(path);
        this.appendRowData(importer.getImportedData(), sourceType);
    }

    public void appendRowData(List<ImporterRowData> rowDataList, SourceType sourceType) {
        for (ImporterRowData rowData : rowDataList) {
            Customer customer = Objects.requireNonNullElse(
                    customers.get(rowData.getCustomerUUID()),
                    Mappers.getCustomerFromRowData(rowData)
            );

            AddAccountOnSourceFactory.getStrategy(sourceType).addAccount(
                    customer,
                    Mappers.getAccountFromRowData(rowData)
            );

            this.customers.put(customer.getCustomerUUID(), customer);
        }
    }

    public void calculateConvergenceLevels() {
        for (String uuid : this.customers.keySet()) {
            Customer customer = this.customers.get(uuid);
            for (Account mainAccount : customer.getMainAccounts()) {
                for (Account partnerAccount : customer.getPartnerAccounts()) {
                    if (mainAccount.equals(partnerAccount)) {
                        mainAccount.setConvergenceLevel(ConvergenceLevel.HAS_EQUAL);
                        mainAccount.setLinkedAccount(partnerAccount);
                        partnerAccount.setConvergenceLevel(ConvergenceLevel.HAS_EQUAL);
                        partnerAccount.setLinkedAccount(mainAccount);
                    }

                    if (mainAccount.getConvergenceLevel()!=ConvergenceLevel.HAS_EQUAL &&
                            partnerAccount.getConvergenceLevel()!=ConvergenceLevel.HAS_EQUAL &&
                            mainAccount.isSimilar(partnerAccount))
                    {
                        mainAccount.setConvergenceLevel(ConvergenceLevel.HAS_SIMILAR);
                        mainAccount.setLinkedAccount(partnerAccount);
                        partnerAccount.setConvergenceLevel(ConvergenceLevel.HAS_SIMILAR);
                        partnerAccount.setLinkedAccount(mainAccount);
                    }
                }
            }
        }
    }

    public void printStat() {
        long numberOfCustomers = this.customers.size();
        long numberOfPairedCustomers = 0;
        long numberOfUnpairedCustomers = 0;
        long numberOfEqualAccounts = 0;
        long numberOfSimilarAccounts = 0;
        long numberOfNonMatchedAccounts = 0;

        for (String uuid : this.customers.keySet()) {
            Customer customer = this.customers.get(uuid);

            if (customer.getMainAccounts().isEmpty() || customer.getPartnerAccounts().isEmpty()) {
                numberOfUnpairedCustomers++;
                continue;
            } else {
                numberOfPairedCustomers++;
            }

            for (Account mainAccount : customer.getMainAccounts()) {
                if (mainAccount.getConvergenceLevel()==ConvergenceLevel.HAS_EQUAL) {
                    numberOfEqualAccounts++;
                }
                if (mainAccount.getConvergenceLevel()==ConvergenceLevel.HAS_SIMILAR) {
                    numberOfSimilarAccounts++;
                }
                if (mainAccount.getConvergenceLevel()==ConvergenceLevel.NO_MATCH) {
                    numberOfNonMatchedAccounts++;
                }
            }

            for (Account partnerAccount : customer.getPartnerAccounts()) {
                if (partnerAccount.getConvergenceLevel()==ConvergenceLevel.HAS_EQUAL) {
                    numberOfEqualAccounts++;
                }
                if (partnerAccount.getConvergenceLevel()==ConvergenceLevel.HAS_SIMILAR) {
                    numberOfSimilarAccounts++;
                }
                if (partnerAccount.getConvergenceLevel()==ConvergenceLevel.NO_MATCH) {
                    numberOfNonMatchedAccounts++;
                }
            }
        }

        System.out.println("numberOfCustomers: " + numberOfCustomers);
        System.out.println("numberOfPairedCustomers: " + numberOfPairedCustomers);
        System.out.println("numberOfUnpairedCustomers: " + numberOfUnpairedCustomers);
        System.out.println("numberOfEqualAccounts: " + numberOfEqualAccounts);
        System.out.println("numberOfSimilarAccounts: " + numberOfSimilarAccounts);
        System.out.println("numberOfNonMatchedAccounts: " + numberOfNonMatchedAccounts);
    }

    public void prepareForExport() {
        this.exportedData = new LinkedList<ExporterRowData>();

        for (String uuid : this.customers.keySet()) {
            Customer customer = this.customers.get(uuid);

            if (customer.getMainAccounts().isEmpty() || customer.getPartnerAccounts().isEmpty()) {
                continue;
            }

            for (Account mainAccount : customer.getMainAccounts()) {
                if (mainAccount.getConvergenceLevel()==ConvergenceLevel.HAS_EQUAL) {
                    continue;
                }

                if (mainAccount.getLinkedAccount()==null &&
                        mainAccount.getOriginalAccountNumber()!=null &&
                        !mainAccount.getOriginalAccountNumber().equals("0") &&
                        !mainAccount.getOriginalAccountNumber().equals("")
                ) {
                    continue;
                }

                exportedData.add(Mappers.getRowDataFromCustomerAccounts(customer, mainAccount, mainAccount.getLinkedAccount()));
            }
        }
    }

    public void runExport(Exporter exporter, String path) {
        this.prepareForExport();
        exporter.setExportedData(this.exportedData);
        exporter.exportToCsv(path);
    }
}