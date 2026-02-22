import java.util.*;

public class InvoiceCalculator {
    private final TaxCalculator taxCalculator;
    private final DiscountCalculator discountCalculator;

    public InvoiceCalculator(TaxCalculator taxCalculator, DiscountCalculator discountCalculator) {
        this.taxCalculator = taxCalculator;
        this.discountCalculator = discountCalculator;
    }

    public InvoiceData calculate(String customerType, List<OrderLine> lines, Map<String, MenuItem> menu) {
        double subtotal = 0.0;
        List<InvoiceLine> invoiceLines = new ArrayList<>();
        
        for (OrderLine l : lines) {
            MenuItem item = menu.get(l.itemId);
            double lineTotal = item.price * l.qty;
            subtotal += lineTotal;
            invoiceLines.add(new InvoiceLine(item.name, l.qty, lineTotal));
        }

        double taxPct = taxCalculator.getTaxPercent(customerType);
        double tax = subtotal * (taxPct / 100.0);
        double discount = discountCalculator.getDiscount(customerType, subtotal, lines.size());
        double total = subtotal + tax - discount;

        return new InvoiceData(invoiceLines, subtotal, taxPct, tax, discount, total);
    }
}

class InvoiceData {
    public final List<InvoiceLine> lines;
    public final double subtotal;
    public final double taxPct;
    public final double tax;
    public final double discount;
    public final double total;

    public InvoiceData(List<InvoiceLine> lines, double subtotal, double taxPct, double tax, double discount, double total) {
        this.lines = lines;
        this.subtotal = subtotal;
        this.taxPct = taxPct;
        this.tax = tax;
        this.discount = discount;
        this.total = total;
    }
}

class InvoiceLine {
    public final String itemName;
    public final int qty;
    public final double lineTotal;

    public InvoiceLine(String itemName, int qty, double lineTotal) {
        this.itemName = itemName;
        this.qty = qty;
        this.lineTotal = lineTotal;
    }
}
