
import factory.TaskFactory;
import manager.ScheduleManager;
import model.Task;
import model.Priority;
import observer.ConsoleObserver;
import util.LoggerConfig;
import exceptions.InvalidTimeFormatException;
import exceptions.TaskConflictException;
import exceptions.TaskNotFoundException;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

/**
 * Console-based application for Astronaut Daily Schedule Organizer.
 * Uses ScheduleManager Singleton, TaskFactory, and Observer.
 */
public class App {
    private static final Logger logger = Logger.getLogger(App.class.getName());
    private final ScheduleManager manager = ScheduleManager.getInstance();

    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // initialize logging
        LoggerConfig.class.getName(); // triggers static init
        App app = new App();
        app.start();
    }

    public App() {
        // register a simple console observer
        manager.registerObserver(new ConsoleObserver("Commander"));
    }

    private void start() {
        printHeader();
        boolean running = true;
        while (running) {
            printMenu();
            String choice = readLine("Enter choice").trim();
            switch (choice) {
                case "1":
                    handleAddTask();
                    break;
                case "2":
                    handleRemoveTask();
                    break;
                case "3":
                    handleViewTasks();
                    break;
                case "4":
                    handleEditTask();
                    break;
                case "5":
                    handleMarkCompleted();
                    break;
                case "6":
                    handleViewByPriority();
                    break;
                case "0":
                    running = false;
                    break;
                default:
                    println("Unknown choice. Try again.");
            }
        }
        println("Exiting. Goodbye!");
    }

    private void printHeader() {
        println("=== Astronaut Daily Schedule Organizer ===");
    }

    private void printMenu() {
        println("");
        println("1) Add Task");
        println("2) Remove Task");
        println("3) View Tasks (sorted by start time)");
        println("4) Edit Task (optional)");
        println("5) Mark Task Complete (optional)");
        println("6) View Tasks by Priority (optional)");
        println("0) Exit");
    }

    private void handleAddTask() {
        try {
            String desc = readLine("Description");
            String start = readLine("Start time (HH:mm)");
            String end = readLine("End time (HH:mm)");
            String pr = readLine("Priority (High/Medium/Low)");
            Task t = TaskFactory.createTask(desc, start, end, pr);
            manager.addTask(t);
            println("Task added successfully. No conflicts.");
        } catch (InvalidTimeFormatException e) {
            println("Error: " + e.getMessage());
            logger.warning(e.getMessage());
        } catch (TaskConflictException e) {
            println("Error: " + e.getMessage());
            logger.warning(e.getMessage());
        } catch (Exception e) {
            println("Unexpected error: " + e.getMessage());
            logger.severe("Add task failed: " + e);
        }
    }

    private void handleRemoveTask() {
        try {
            String desc = readLine("Enter exact description of task to remove");
            manager.removeTaskByDescription(desc);
            println("Task removed successfully.");
        } catch (TaskNotFoundException e) {
            println("Error: " + e.getMessage());
            logger.warning(e.getMessage());
        } catch (Exception e) {
            println("Unexpected error: " + e.getMessage());
            logger.severe("Remove task failed: " + e);
        }
    }

    private void handleViewTasks() {
        try {
            if (manager.isEmpty()) {
                println("No tasks scheduled for the day.");
                return;
            }
            List<Task> tasks = manager.viewAllTasks();
            println("Scheduled tasks:");
            for (Task t : tasks) {
                println(t.toString());
            }
        } catch (Exception e) {
            println("Unexpected error: " + e.getMessage());
            logger.severe("View tasks failed: " + e);
        }
    }

    private void handleEditTask() {
        try {
            String desc = readLine("Enter exact description of task to edit");
            String start = readLine("New start time (HH:mm)");
            String end = readLine("New end time (HH:mm)");
            String pr = readLine("New priority (High/Medium/Low)");
            LocalTime s = LocalTime.parse(start.trim());
            LocalTime e = LocalTime.parse(end.trim());
            Priority p = Priority.fromString(pr);
            manager.editTask(desc, s, e, p);
            println("Task edited successfully.");
        } catch (TaskNotFoundException | TaskConflictException ex) {
            println("Error: " + ex.getMessage());
            logger.warning(ex.getMessage());
        } catch (Exception e) {
            println("Invalid input or unexpected error: " + e.getMessage());
            logger.severe("Edit failed: " + e);
        }
    }

    private void handleMarkCompleted() {
        try {
            String desc = readLine("Enter exact description of task to mark completed");
            manager.markCompleted(desc);
            println("Task marked as completed.");
        } catch (TaskNotFoundException ex) {
            println("Error: " + ex.getMessage());
            logger.warning(ex.getMessage());
        } catch (Exception e) {
            println("Unexpected error: " + e.getMessage());
            logger.severe("Mark completed failed: " + e);
        }
    }

    private void handleViewByPriority() {
        try {
            String pr = readLine("Priority to filter (High/Medium/Low)");
            Priority p = Priority.fromString(pr);
            List<Task> list = manager.getTasksByPriority(p);
            if (list.isEmpty()) {
                println("No tasks with priority: " + p);
            } else {
                println("Tasks with priority " + p + ":");
                for (Task t : list) {
                    println(t.toString());
                }
            }
        } catch (Exception e) {
            println("Unexpected error: " + e.getMessage());
            logger.severe("Filter by priority failed: " + e);
        }
    }

    // Helper I/O wrappers
    private String readLine(String prompt) {
        System.out.print(prompt + ": ");
        return scanner.nextLine();
    }

    private void println(String s) {
        System.out.println(s);
    }
}
