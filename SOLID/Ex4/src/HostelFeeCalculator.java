import java.util.*;

public class HostelFeeCalculator {
    private final FakeBookingRepo repo;
    private final AddOnPricing addOnPricing;

    public HostelFeeCalculator(FakeBookingRepo repo) {
        this.repo = repo;
        this.addOnPricing = new AddOnPricingImpl();
    }

    public void process(BookingRequest req) {
        Money monthly = calculateMonthly(req);
        Money deposit = new Money(5000.00);

        ReceiptPrinter.print(req, monthly, deposit);

        String bookingId = "H-" + (7000 + new Random(1).nextInt(1000)); // deterministic-ish
        repo.save(bookingId, req, monthly, deposit);
    }

    private Money calculateMonthly(BookingRequest req) {
        RoomPricing roomPricing = RoomPricingFactory.create(req.roomType);
        Money total = roomPricing.getMonthlyPrice();

        for (AddOn addOn : req.addOns) {
            total = total.plus(addOnPricing.getPrice(addOn));
        }

        return total;
    }
}
