public class RuleResult {
    public final boolean isEligible;
    public final String reason;

    public RuleResult(boolean isEligible, String reason) {
        this.isEligible = isEligible;
        this.reason = reason;
    }
}
