public class AddOnPricingImpl implements AddOnPricing {
    @Override
    public Money getPrice(AddOn addOn) {
        return switch (addOn) {
            case MESS -> new Money(1000.0);
            case LAUNDRY -> new Money(500.0);
            case GYM -> new Money(300.0);
        };
    }
}
