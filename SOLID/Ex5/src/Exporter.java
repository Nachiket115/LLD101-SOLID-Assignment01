public abstract class Exporter {
    /**
     * Base contract:
     * - Must accept any ExportRequest.
     * - Must not throw for regular validation failures.
     * - Must return non-null ExportResult with non-null bytes.
     */
    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            return ExportResult.error(contentType(), "request must not be null");
        }

        ExportRequest normalized = new ExportRequest(
                req.title == null ? "" : req.title,
                req.body == null ? "" : req.body
        );

        ExportResult result = doExport(normalized);
        if (result == null || result.bytes == null) {
            return ExportResult.error(contentType(), "exporter returned invalid result");
        }
        return result;
    }

    protected abstract String contentType();

    protected abstract ExportResult doExport(ExportRequest req);
}
