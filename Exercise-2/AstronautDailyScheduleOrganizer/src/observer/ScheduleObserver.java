package observer;

public interface ScheduleObserver {
    /**
     * Called to inform observers about conflicts or updates
     * message is human-friendly.
     */
    void onNotification(String message);
}
