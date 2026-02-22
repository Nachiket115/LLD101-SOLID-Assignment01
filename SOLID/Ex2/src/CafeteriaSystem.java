import java.util.*;

public class CafeteriaSystem {
    private final Map<String, MenuItem> menu = new LinkedHashMap<>();
    private final InvoiceRepository repository;
    private final InvoiceCalculator calculator;
    private final InvoiceFormatter formatter;
    private int invoiceSeq = 1000;

    public CafeteriaSystem() {
        this.repository = new FileStore();
        this.calculator = new InvoiceCalculator(new TaxCalculatorImpl(), new DiscountCalculatorImpl());
        this.formatter = new InvoiceFormatter();
    }

    public void addToMenu(MenuItem i) { menu.put(i.id, i); }

    public void checkout(String customerType, List<OrderLine> lines) {
        String invId = "INV-" + (++invoiceSeq);
        
        InvoiceData data = calculator.calculate(customerType, lines, menu);
        String printable = formatter.format(invId, data);
        
        System.out.print(printable);
        repository.save(invId, printable);
        System.out.println("Saved invoice: " + invId + " (lines=" + repository.countLines(invId) + ")");
    }
}
