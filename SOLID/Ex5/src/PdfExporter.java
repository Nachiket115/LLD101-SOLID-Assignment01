import java.nio.charset.StandardCharsets;

public class PdfExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        // Keep current demo behavior: PDF rejects large content.
        if (req.body != null && req.body.length() > 20) {
            throw new IllegalArgumentException("PDF cannot handle content > 20 chars");
        }
        String fakePdf = "PDF(" + req.title + "):" + req.body;
        return new ExportResult("application/pdf", fakePdf.getBytes(StandardCharsets.UTF_8));
    }
}
