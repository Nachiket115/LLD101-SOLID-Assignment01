public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public RuleResult evaluate(StudentProfile profile) {
        if (profile.disciplinaryFlag != LegacyFlags.NONE) {
            return new RuleResult(false, "disciplinary flag present");
        }
        return new RuleResult(true, null);
    }
}
