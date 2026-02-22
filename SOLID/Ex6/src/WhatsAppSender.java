public class WhatsAppSender extends NotificationSender {
    public WhatsAppSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        // Honor base contract: don't tighten preconditions, handle gracefully
        String phone = n.phone == null ? "" : n.phone;
        String body = n.body == null ? "" : n.body;
        
        // Validate but don't throw - log issue instead
        if (phone.isEmpty() || !phone.startsWith("+")) {
            System.out.println("WA ERROR: phone must start with + and country code");
            audit.add("WA failed");
            return;
        }
        
        System.out.println("WA -> to=" + phone + " body=" + body);
        audit.add("wa sent");
    }
}
