public class AttendanceRule implements EligibilityRule {
    private static final int MIN_ATTENDANCE = 75;

    @Override
    public RuleResult evaluate(StudentProfile profile) {
        if (profile.attendancePct < MIN_ATTENDANCE) {
            return new RuleResult(false, "attendance below 75");
        }
        return new RuleResult(true, null);
    }
}
