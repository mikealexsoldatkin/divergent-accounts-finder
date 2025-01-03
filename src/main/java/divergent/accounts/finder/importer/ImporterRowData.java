package divergent.accounts.finder.importer;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ImporterRowData {
    private String customerUUID;
    private String customerID;
    private String firstName;
    private String lastName;
    private String ssn;
    private String enrolledDate;
    private String dateOpened;
    private String customerCreditorID;
    private String originalCreditorUUID;
    private String currentCreditorUUID;
    private String originalAccountNumber;
    private String currentAccountNumber;
    private String originalCreditorName;
    private String currentCreditorName;
    private String originalBalance;
    private String currentBalance;
}