package divergent.accounts.finder.sources.addAccount;

import divergent.accounts.finder.sources.SourceType;

public class AddAccountOnSourceFactory {
    public static AddAccountOnSourceStrategy getStrategy(SourceType sourceType) {
        return switch (sourceType) {
            case MAIN_CRM -> new AddAccountMainCrm();
            case PARTNERS_CRM -> new AddAccountPartnersCrm();
            default -> throw new IllegalArgumentException("Unknown source type");
        };
    }
}