import java.nio.charset.StandardCharsets;

public class CsvExporter extends Exporter {
    @Override
    protected String contentType() {
        return "text/csv";
    }

    @Override
    protected ExportResult doExport(ExportRequest req) {
        // Keep sample-output-compatible CSV encoding.
        String body = req.body.replace("\n", " ").replace(",", " ");
        if (body.endsWith(" ")) {
            body = body.substring(0, body.length() - 1);
        }
        String csv = req.title + "," + body + "\n";
        return ExportResult.ok(contentType(), csv.getBytes(StandardCharsets.UTF_8));
    }
}
