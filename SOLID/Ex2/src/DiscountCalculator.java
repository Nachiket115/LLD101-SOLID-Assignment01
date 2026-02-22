public interface DiscountCalculator {
    double getDiscount(String customerType, double subtotal, int distinctLines);
}
