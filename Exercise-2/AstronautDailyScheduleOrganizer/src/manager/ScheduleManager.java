package manager;

import model.Task;
import observer.ScheduleObserver;
import exceptions.TaskConflictException;
import exceptions.TaskNotFoundException;

import java.time.LocalTime;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Singleton manager that stores tasks in a TreeSet ordered by start time.
 * Implements add, remove, view, edit, markCompleted, filterByPriority.
 * Uses Observer pattern to notify on conflicts/updates.
 */
public class ScheduleManager {
    private static final Logger logger = Logger.getLogger(ScheduleManager.class.getName());

    private static ScheduleManager instance;

    // TreeSet keeps tasks sorted by start time
    private final NavigableSet<Task> tasks = new TreeSet<>();
    private final List<ScheduleObserver> observers = new ArrayList<>();

    // private constructor
    private ScheduleManager() {
        logger.info("ScheduleManager started.");
    }

    // Thread-safe lazy initialization
    public static synchronized ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    public synchronized void registerObserver(ScheduleObserver observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public synchronized void unregisterObserver(ScheduleObserver observer) {
        observers.remove(observer);
    }

    private void notifyObservers(String message) {
        for (ScheduleObserver o : observers) {
            try {
                o.onNotification(message);
            } catch (Exception ex) {
                logger.log(Level.WARNING, "Observer failed: " + o, ex);
            }
        }
    }

    /**
     * Add a task if it does not conflict, else throw TaskConflictException.
     * Efficiently checks only neighbor tasks from TreeSet.
     */
    public synchronized void addTask(Task t) throws TaskConflictException {
        Objects.requireNonNull(t, "Task cannot be null");
        // Check overlap with floor (previous) and ceiling (next)
        Task lower = tasks.floor(t); // floor: greatest <= t
        Task higher = tasks.ceiling(t); // least >= t

        if (conflictsWith(lower, t)) {
            String msg = String.format("Task conflicts with existing task \"%s\"", lower.getDescription());
            notifyObservers("Conflict detected when adding \"" + t.getDescription() + "\": conflicts with \"" + lower.getDescription()+"\".");
            logger.warning(msg);
            throw new TaskConflictException(msg);
        }
        if (conflictsWith(t, higher)) {
            String msg = String.format("Task conflicts with existing task \"%s\"", higher.getDescription());
            notifyObservers("Conflict detected when adding \"" + t.getDescription() + "\": conflicts with \"" + higher.getDescription()+"\".");
            logger.warning(msg);
            throw new TaskConflictException(msg);
        }
        tasks.add(t);
        logger.info("Task added: " + t);
        notifyObservers("Task added: " + t.getDescription() + " [" + t.getStart() + "-" + t.getEnd() + "]");
    }

    private boolean conflictsWith(Task a, Task b) {
        if (a == null || b == null) return false;
        // Overlap if a.start < b.end && a.end > b.start
        return a.getStart().isBefore(b.getEnd()) && a.getEnd().isAfter(b.getStart());
    }

    public synchronized void removeTaskByDescription(String description) throws TaskNotFoundException {
        Objects.requireNonNull(description, "description cannot be null");
        Optional<Task> found = tasks.stream()
                .filter(t -> t.getDescription().equalsIgnoreCase(description.trim()))
                .findFirst();
        if (!found.isPresent()) {
            logger.warning("Attempted to remove non-existent task: " + description);
            throw new TaskNotFoundException("Task not found: " + description);
        }
        Task t = found.get();
        tasks.remove(t);
        logger.info("Task removed: " + t);
        notifyObservers("Task removed: " + t.getDescription());
    }

    public synchronized List<Task> viewAllTasks() {
        return new ArrayList<>(tasks);
    }

    public synchronized List<Task> getTasksByPriority(model.Priority priority) {
        List<Task> list = new ArrayList<>();
        for (Task t : tasks) {
            if (t.getPriority() == priority) list.add(t);
        }
        return list;
    }

    public synchronized void editTask(String description, LocalTime newStart, LocalTime newEnd, model.Priority newPriority)
            throws TaskNotFoundException, TaskConflictException {
        Optional<Task> found = tasks.stream()
                .filter(t -> t.getDescription().equalsIgnoreCase(description.trim()))
                .findFirst();
        if (!found.isPresent()) {
            throw new TaskNotFoundException("Task not found: " + description);
        }
        Task existing = found.get();
        // Remove temporarily
        tasks.remove(existing);

        Task temp = new Task(existing.getDescription(), newStart, newEnd, newPriority);

        // check conflicts with neighbors
        Task lower = tasks.floor(temp);
        Task higher = tasks.ceiling(temp);
        if (conflictsWith(lower, temp) || conflictsWith(temp, higher)) {
            // restore original
            tasks.add(existing);
            throw new TaskConflictException("Edited task conflicts with existing tasks.");
        }

        // apply edits
        existing.setStart(newStart);
        existing.setEnd(newEnd);
        existing.setPriority(newPriority);
        tasks.add(existing);

        logger.info("Task edited: " + existing);
        notifyObservers("Task edited: " + existing.getDescription());
    }

    public synchronized void markCompleted(String description) throws TaskNotFoundException {
        Optional<Task> found = tasks.stream()
                .filter(t -> t.getDescription().equalsIgnoreCase(description.trim()))
                .findFirst();
        if (!found.isPresent()) {
            throw new TaskNotFoundException("Task not found: " + description);
        }
        Task t = found.get();
        t.setCompleted(true);
        logger.info("Task marked completed: " + t);
        notifyObservers("Task completed: " + t.getDescription());
    }

    public synchronized boolean isEmpty() {
        return tasks.isEmpty();
    }
}
