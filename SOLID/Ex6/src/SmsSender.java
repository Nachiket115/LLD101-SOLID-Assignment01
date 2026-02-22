public class SmsSender extends NotificationSender {
    public SmsSender(AuditLog audit) { super(audit); }

    @Override
    public void send(Notification n) {
        // Honor base contract: handle nulls gracefully, preserve content
        String phone = n.phone == null ? "" : n.phone;
        String body = n.body == null ? "" : n.body;
        System.out.println("SMS -> to=" + phone + " body=" + body);
        audit.add("sms sent");
    }
}
