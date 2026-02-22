public class InvoiceFormatter {
    public String format(String invoiceId, InvoiceData data) {
        StringBuilder out = new StringBuilder();
        out.append("Invoice# ").append(invoiceId).append("\n");

        for (InvoiceLine line : data.lines) {
            out.append(String.format("- %s x%d = %.2f\n", line.itemName, line.qty, line.lineTotal));
        }

        out.append(String.format("Subtotal: %.2f\n", data.subtotal));
        out.append(String.format("Tax(%.0f%%): %.2f\n", data.taxPct, data.tax));
        out.append(String.format("Discount: -%.2f\n", data.discount));
        out.append(String.format("TOTAL: %.2f\n", data.total));

        return InvoiceFormatter.identityFormat(out.toString());
    }

    private static String identityFormat(String s) { return s; }
}
