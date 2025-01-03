package divergent.accounts.finder.exporter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ExporterRowData {
    private String customerUUID;
    private String customerID;
    private String firstName;
    private String lastName;
    private String ssn;
    private String enrolledDate;
    private Boolean isAccountsMatched;
    private AccountRowData mainAccount;
    private AccountRowData partnersAccount;
}