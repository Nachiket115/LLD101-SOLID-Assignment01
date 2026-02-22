public class CreditsRule implements EligibilityRule {
    private static final int MIN_CREDITS = 20;

    @Override
    public RuleResult evaluate(StudentProfile profile) {
        if (profile.earnedCredits < MIN_CREDITS) {
            return new RuleResult(false, "credits below 20");
        }
        return new RuleResult(true, null);
    }
}
