package divergent.accounts.finder.model;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.LinkedList;
import java.util.List;

@Getter
@ToString
public class Customer {
    @Setter
    private String customerUUID;
    @Setter
    private String customerID;
    @Setter
    private String firstName;
    @Setter
    private String lastName;
    @Setter
    private String ssn;
    private final List<Account> partnerAccounts;
    private final List<Account> mainAccounts;

    public Customer() {
        this.partnerAccounts = new LinkedList<Account>();
        this.mainAccounts = new LinkedList<Account>();
    }

    public void addPartnerAccount(Account account) {
        this.partnerAccounts.add(account);
    }

    public void addMainAccount(Account account) {
        this.mainAccounts.add(account);
    }
}
