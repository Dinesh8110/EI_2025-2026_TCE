package factory;


import model.Task;
import model.Priority;
import exceptions.InvalidTimeFormatException;

import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Create Task objects with validation.
 */
public class TaskFactory {

    /**
     * Create a Task from raw inputs (times as strings "HH:mm").
     * Throws InvalidTimeFormatException on invalid time or start >= end.
     */
    public static Task createTask(String description, String startTime, String endTime, String priorityStr)
            throws InvalidTimeFormatException {
        Objects.requireNonNull(description, "description cannot be null");
        try {
            LocalTime start = parseTime(startTime);
            LocalTime end = parseTime(endTime);
            if (!start.isBefore(end)) {
                throw new InvalidTimeFormatException("Start time must be before end time.");
            }
            Priority priority = Priority.fromString(priorityStr);
            return new Task(description.trim(), start, end, priority);
        } catch (DateTimeParseException e) {
            throw new InvalidTimeFormatException("Invalid time format. Expected HH:mm (e.g., 07:00).");
        }
    }

    private static LocalTime parseTime(String t) {
        return LocalTime.parse(t.trim());
    }
}
