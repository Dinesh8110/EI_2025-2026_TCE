# Astronaut Daily Schedule Organizer 🚀

## Overview

Welcome to the Astronaut Daily Schedule Organizer! This Java-based console application helps astronauts efficiently manage their daily tasks. The app supports adding, removing, viewing, editing, and marking tasks as complete, all while enforcing a no-overlap rule for scheduled activities. The project demonstrates clean Object-Oriented Programming (OOP), robust exception handling, and the use of key design patterns.

---

## Features

- **Task Management:** Add, remove, edit, and mark tasks as completed.
- **Priority Support:** Assign and filter tasks by priority (HIGH, MEDIUM, LOW).
- **No-Overlap Rule:** Prevents scheduling conflicts by disallowing overlapping tasks.
- **Task Viewing:** View all tasks sorted by start time.
- **Logging:** Actions and errors are logged to both the console and `app.log`.
- **Robust Exceptions:** Custom exceptions for time format, conflicts, and not-found cases.
- **Design Patterns:** Implements Singleton, Factory, and Observer patterns for maintainability.

---

## Key Components

### App

The main entry point. Handles user interaction and coordinates actions with the schedule manager.

### model

- **Task:** Represents a scheduled task with description, time, priority, and completion status.
- **Priority:** Enum for task priority levels.

### manager

- **ScheduleManager:** Core logic for managing tasks, enforcing rules, and notifying observers.

### factory

- **TaskFactory:** Responsible for creating Task objects.

### observer

- **ScheduleObserver:** Interface for observers notified of schedule changes.
- **ConsoleObserver:** Prints notifications to the console.

### exceptions

- **InvalidTimeFormatException:** Thrown for invalid time inputs.
- **TaskConflictException:** Thrown when a new or edited task overlaps with an existing one.
- **TaskNotFoundException:** Thrown when a task is not found for removal or editing.

### util

- **LoggerConfig:** Configures logging for the application.

---

## Design Patterns

- **Singleton Pattern:** Ensures only one instance of `ScheduleManager`.
- **Factory Pattern:** Used for creating `Task` objects.
- **Observer Pattern:** Notifies observers (like `ConsoleObserver`) of schedule changes.
- **OOP Principles:** Applies encapsulation, abstraction, and modularity throughout.

---

## Example Usage

1. **Add a Task:**
   - Enter task description, start time, end time, and priority.
2. **Remove a Task:**
   - Enter the description of the task to remove.
3. **View Tasks:**
   - Displays all tasks sorted by start time.
4. **Edit a Task:**
   - Modify details of an existing task.
5. **Mark as Completed:**
   - Mark a task as completed by description.
6. **Filter by Priority:**
   - View only tasks of a specific priority.

**Sample Output:**
```
[NOTIFY - Console @ 12:30:01] Task 'Check Oxygen Levels' added successfully.
[NOTIFY - Console @ 12:31:15] Task 'Check Oxygen Levels' marked as completed.
```

---

## Code Structure

```
src/
├─ App.java
├─ model/
│   ├─ Task.java
│   └─ Priority.java
├─ factory/
│   └─ TaskFactory.java
├─ manager/
│   └─ ScheduleManager.java
├─ observer/
│   ├─ ScheduleObserver.java
│   └─ ConsoleObserver.java
├─ exceptions/
│   ├─ InvalidTimeFormatException.java
│   ├─ TaskConflictException.java
│   └─ TaskNotFoundException.java
└─ util/
    └─ LoggerConfig.java
```

---

## How to Compile and Run

1. **Navigate to the project root:**
   ```sh
   cd d:\Placement_Materials\EI_coding_exercise_018\Exercise-2\AstronautDailyScheduleOrganizer
   ```

2. **Compile all source files:**
   - **Windows (PowerShell):**
     ```sh
     javac -d out (Get-ChildItem -Recurse -Path src -Filter *.java | ForEach-Object { $_.FullName })
     ```
   - **Linux/macOS:**
     ```sh
     javac -d out $(find src -name "*.java")
     ```

3. **Run the application:**
   ```sh
   java -cp out App
   ```

---

## Contact

For any queries or further information, please reach out to [dineshsankar8110@gmail.com](mailto:dineshsankar8110@gmail.com).

---
