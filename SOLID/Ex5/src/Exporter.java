public abstract class Exporter {
    /**
     * Base contract: Must accept any non-null ExportRequest and return a valid ExportResult.
     * Preconditions: req != null
     * Postconditions: result != null, result.bytes != null
     * No data corruption: content must be preserved (no lossy conversions)
     */
    public abstract ExportResult export(ExportRequest req);
}
