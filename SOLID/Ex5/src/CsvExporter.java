import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    public ExportResult export(ExportRequest req) {
        // Keep current demo behavior: lossy single-line body conversion.
        String body = req.body == null ? "" : req.body.replace("\n", " ").replace(",", " ");
        if (body.endsWith(" ")) {
            body = body.substring(0, body.length() - 1);
        }
        String csv = req.title + "," + body + "\n";
        return new ExportResult("text/csv", csv.getBytes(StandardCharsets.UTF_8));
    }
}
