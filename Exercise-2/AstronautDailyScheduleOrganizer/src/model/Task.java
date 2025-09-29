package model;

import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Immutable-ish Task object. completed flag is mutable.
 */
public class Task implements Comparable<Task> {
    private final String id;
    private String description;
    private LocalTime start;
    private LocalTime end;
    private Priority priority;
    private boolean completed;

    public Task(String description, LocalTime start, LocalTime end, Priority priority) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.start = start;
        this.end = end;
        this.priority = priority;
        this.completed = false;
    }

    // Getters and setters for editable fields (edit option)
    public String getId() { return id; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public LocalTime getStart() { return start; }
    public void setStart(LocalTime start) { this.start = start; }
    public LocalTime getEnd() { return end; }
    public void setEnd(LocalTime end) { this.end = end; }
    public Priority getPriority() { return priority; }
    public void setPriority(Priority priority) { this.priority = priority; }
    public boolean isCompleted() { return completed; }
    public void setCompleted(boolean completed) { this.completed = completed; }

    @Override
    public int compareTo(Task other) {
        int cmp = this.start.compareTo(other.start);
        if (cmp != 0) return cmp;
        return this.id.compareTo(other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Task)) return false;
        Task t = (Task) o;
        return Objects.equals(id, t.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        String done = completed ? " (Completed)" : "";
        return String.format("%s - %s: %s [%s]%s",
                start, end, description, priority, done);
    }
}
