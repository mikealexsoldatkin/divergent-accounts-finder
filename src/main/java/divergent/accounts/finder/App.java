package divergent.accounts.finder;
import divergent.accounts.finder.exporter.Exporter;
import divergent.accounts.finder.sources.SourceType;
import divergent.accounts.finder.importer.ImporterMain;
import divergent.accounts.finder.importer.ImporterPartners;
import divergent.accounts.finder.services.AccountsProcessing;

public class App {
    public static void main( String[] args ) {
        AccountsProcessing accountsProcessing = new AccountsProcessing();
        accountsProcessing.runImport(new ImporterMain(), "ext/main_crm_prod.csv", SourceType.MAIN_CRM);
        accountsProcessing.runImport(new ImporterPartners(), "ext/customer_creditors_pathway_prod.csv", SourceType.PARTNERS_CRM);
        accountsProcessing.calculateConvergenceLevels();
        accountsProcessing.printStat();
        accountsProcessing.runExport(new Exporter(), "ext/output_prod.csv");
    }
}
