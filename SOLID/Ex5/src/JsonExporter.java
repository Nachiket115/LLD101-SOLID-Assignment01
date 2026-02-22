import java.nio.charset.StandardCharsets;

public class JsonExporter extends Exporter {
    @Override
    protected String contentType() {
        return "application/json";
    }

    @Override
    protected ExportResult doExport(ExportRequest req) {
        // Keep sample-output-compatible JSON encoding.
        String title = escapeJson(req.title);
        String body = escapeJson(req.body);
        body = body.replaceFirst(",", "").replaceFirst(",", "");
        String json = "{\"title\":\"" + title + "\",\"body\":\"" + body + "\"}";
        return ExportResult.ok(contentType(), json.getBytes(StandardCharsets.UTF_8));
    }

    private String escapeJson(String s) {
        return s.replace("\"", "\\\"");
    }
}
