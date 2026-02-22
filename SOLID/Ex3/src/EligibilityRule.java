public interface EligibilityRule {
    RuleResult evaluate(StudentProfile profile);
}
