import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.ArrayList;
import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Updates now create new instances.
        IncidentTicket assigned = service.assign(t, "agent@example.com");
        IncidentTicket escalated = service.escalateToCritical(assigned);
        System.out.println("\nOriginal still unchanged: " + t);
        System.out.println("Updated copy: " + escalated);

        // External list mutation after build should not affect the ticket.
        List<String> externalTags = new ArrayList<>();
        externalTags.add("BILLING");
        IncidentTicket fromExternalTags = IncidentTicket.builder()
                .id("TCK-1002")
                .reporterEmail("reporter@example.com")
                .title("Refund not processed")
                .tags(externalTags)
                .build();
        externalTags.add("HACKED_FROM_OUTSIDE");
        System.out.println("\nTicket built from external tags: " + fromExternalTags);

        // Returned tags list is immutable.
        try {
            fromExternalTags.getTags().add("SHOULD_FAIL");
        } catch (UnsupportedOperationException ex) {
            System.out.println("Tags are immutable from outside (expected).");
        }
    }
}
