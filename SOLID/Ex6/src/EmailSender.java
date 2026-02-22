public class EmailSender extends NotificationSender {
    public EmailSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        // Honor base contract: preserve full content, handle nulls gracefully
        String email = n.email == null ? "" : n.email;
        String subject = n.subject == null ? "" : n.subject;
        String body = n.body == null ? "" : n.body;
        System.out.println("EMAIL -> to=" + email + " subject=" + subject + " body=" + body);
        audit.add("email sent");
    }
}
