package divergent.accounts.finder.importer;

import lombok.Getter;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.LinkedList;
import java.util.List;

@Getter
public abstract class Importer {
    private final List<ImporterRowData> importedData;

    public Importer() {
        this.importedData =  new LinkedList<ImporterRowData>();;
    }

    public void importFromCsv(String path) {
        try (Reader reader = new FileReader(path)) {
            Iterable<CSVRecord> records = CSVFormat.DEFAULT.withHeader().parse(reader);

            for (CSVRecord record : records) {
                this.importedData.add(getRow(record));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public abstract ImporterRowData getRow(CSVRecord record);
}
