public class CgrRule implements EligibilityRule {
    private static final double MIN_CGR = 8.0;

    @Override
    public RuleResult evaluate(StudentProfile profile) {
        if (profile.cgr < MIN_CGR) {
            return new RuleResult(false, "CGR below 8.0");
        }
        return new RuleResult(true, null);
    }
}
