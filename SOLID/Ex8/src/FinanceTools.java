public interface FinanceTools extends ClubAdminTools {
    void addIncome(double amt, String note);
    void addExpense(double amt, String note);
}
