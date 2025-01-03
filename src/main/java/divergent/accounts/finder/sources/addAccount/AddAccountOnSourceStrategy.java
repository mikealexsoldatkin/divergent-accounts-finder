package divergent.accounts.finder.sources.addAccount;

import divergent.accounts.finder.model.Account;
import divergent.accounts.finder.model.Customer;

public interface AddAccountOnSourceStrategy {
    void addAccount(Customer customer, Account account);
}
