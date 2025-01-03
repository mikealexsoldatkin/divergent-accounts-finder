package divergent.accounts.finder.exporter;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountRowData {
    private String dateOpened;
    private String originalCreditorUUID;
    private String currentCreditorUUID;
    private String originalAccountNumber;
    private String currentAccountNumber;
    private String originalCreditorName;
    private String currentCreditorName;
    private String customerCreditorID;
    private String originalBalance;
    private String currentBalance;
}