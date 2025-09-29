# Singleton Pattern – Database Example

## Problem
We need only one database connection shared across the system.

## Solution
We use **Singleton Pattern**:
- Private constructor to prevent direct instantiation.
- Static instance variable to hold the single instance.
- Public static method to provide global access to the instance.

## Detailed Explanation

The **Singleton Pattern** is a creational design pattern that ensures a class has only one instance and provides a global point of access to it. This is useful when exactly one object is needed to coordinate actions across the system, such as a database connection.

### How it works (Database Example):

#### Singleton (DatabaseConnection):
- The constructor is private, so no other class can instantiate it directly.
- A static variable holds the single instance of the class.
- A public static method (`getInstance()`) returns the single instance, creating it if it doesn't exist.

### Steps in the Database Example:
1. The first call to `getInstance()` creates the database connection.
2. Subsequent calls to `getInstance()` return the same instance.
3. All parts of the system use the same database connection object.

### Benefits:
- **Controlled access:** Only one instance exists.
- **Reduced resource usage:** Prevents unnecessary creation of multiple connections.
- **Global access:** The instance is accessible from anywhere in the application.

### Typical Structure:
```
Singleton
 ├── private static instance
 ├── private constructor()
 └── public static getInstance()
```

## Output Example
```
Database Connected
true
```

This pattern is widely used for logging, configuration, caching, and database