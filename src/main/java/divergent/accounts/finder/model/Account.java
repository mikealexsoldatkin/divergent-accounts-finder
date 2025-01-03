package divergent.accounts.finder.model;
import lombok.*;
import java.util.Objects;
@Getter
@Setter
@ToString
@EqualsAndHashCode
public class Account {
    @EqualsAndHashCode.Exclude
    private final int dateSize = 10;
    @EqualsAndHashCode.Exclude
    private String dateOpened;
    @EqualsAndHashCode.Exclude
    private String customerCreditorID;
    @EqualsAndHashCode.Exclude
    private String originalCreditorUUID;
    @EqualsAndHashCode.Exclude
    private String currentCreditorUUID;
    @EqualsAndHashCode.Include
    private String originalAccountNumber;
    @EqualsAndHashCode.Exclude
    private String currentAccountNumber;
    @EqualsAndHashCode.Exclude
    private String originalCreditorName;
    @EqualsAndHashCode.Exclude
    private String currentCreditorName;
    @EqualsAndHashCode.Exclude
    private String originalBalance;
    @EqualsAndHashCode.Exclude
    private String currentBalance;
    @EqualsAndHashCode.Exclude
    private ConvergenceLevel convergenceLevel;
    @EqualsAndHashCode.Exclude
    private Account linkedAccount;

    public Account() {
        this.convergenceLevel = ConvergenceLevel.NO_MATCH;
    }

    public boolean isSimilar(Account account) {
        if (this.dateOpened.length() >= dateSize &&
                account.dateOpened.length() >= dateSize &&
                this.dateOpened.substring(0, dateSize).equals(account.dateOpened.substring(0, dateSize)))
        {
            if (this.getOriginalBalance() == null || account.getOriginalBalance() == null) {
                return true;
            }
            return this.getOriginalBalance().equals(account.getOriginalBalance());
        } else {
            return false;
        }
    }
}