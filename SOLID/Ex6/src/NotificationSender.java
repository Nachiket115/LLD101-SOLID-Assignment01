public abstract class NotificationSender {
    protected final AuditLog audit;
    protected NotificationSender(AuditLog audit) { this.audit = audit; }
    
    /**
     * Base contract: Must accept any non-null Notification and send it without throwing.
     * Preconditions: n != null
     * Postconditions: Notification is sent (or attempted), audit entry added
     * No data loss: content must be preserved (no truncation)
     * No tightened preconditions: all fields are optional/nullable
     */
    public abstract void send(Notification n);
}
