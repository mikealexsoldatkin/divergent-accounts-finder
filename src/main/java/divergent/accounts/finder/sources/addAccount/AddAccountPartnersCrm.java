package divergent.accounts.finder.sources.addAccount;

import divergent.accounts.finder.model.Account;
import divergent.accounts.finder.model.Customer;

public class AddAccountPartnersCrm implements AddAccountOnSourceStrategy {
    @Override
    public void addAccount(Customer customer, Account account) {
        customer.addPartnerAccount(account);
    }
}