public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final String errorMessage;

    private ExportResult(String contentType, byte[] bytes, String errorMessage) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.errorMessage = errorMessage;
    }

    public static ExportResult ok(String contentType, byte[] bytes) {
        return new ExportResult(contentType, bytes, null);
    }

    public static ExportResult error(String contentType, String errorMessage) {
        return new ExportResult(contentType, new byte[0], errorMessage);
    }

    public boolean isSuccess() {
        return errorMessage == null;
    }
}
