import java.util.*;

// Singleton class for Database connection
class Database {
    // Static variable to hold the single instance
    private static Database instance;

    // Private constructor to prevent direct instantiation
    private Database() { System.out.println("Database Connected"); }

    // Public method to provide access to the single instance
    public static Database getInstance() {
        if (instance == null) instance = new Database(); // Create instance if it doesn't exist
        return instance;
    }
}

public class Main {
    public static void main(String[] args) {
        // Get the singleton instance (first call creates the object)
        Database db1 = Database.getInstance();
        // Get the singleton instance again (returns the same object)
        Database db2 = Database.getInstance();

        // Check if both references point to the same object
        System.out.println(db1 == db2); // true
    }
}
