package divergent.accounts.finder.sources.addAccount;

import divergent.accounts.finder.model.Account;
import divergent.accounts.finder.model.Customer;

public class AddAccountMainCrm implements AddAccountOnSourceStrategy {
    @Override
    public void addAccount(Customer customer, Account account) {
        customer.addMainAccount(account);
    }
}