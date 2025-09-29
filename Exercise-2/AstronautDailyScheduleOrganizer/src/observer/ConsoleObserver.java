package observer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Simple observer that prints to console. In future this could be replaced
 * with email/mobile push etc.
 */
public class ConsoleObserver implements ScheduleObserver {
    private final String subscriberName;

    public ConsoleObserver(String subscriberName) {
        this.subscriberName = subscriberName;
    }

    @Override
    public void onNotification(String message) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_TIME);
        System.out.println("[NOTIFY - " + subscriberName + " @ " + time + "] " + message);
    }
}
