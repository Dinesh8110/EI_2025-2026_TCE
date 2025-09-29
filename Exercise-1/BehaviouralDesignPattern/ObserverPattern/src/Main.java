import java.util.*;

// Observer interface
interface Observer {
    void update(String msg);
}

// Concrete Observer
class User implements Observer {
    private String name;
    User(String name) { this.name = name; }

    public void update(String msg) {
        System.out.println(name + " received: " + msg);
    }
}

// Subject (WhatsAppGroup)
class WhatsAppGroup {
    private List<Observer> users = new ArrayList<>();
    void addUser(Observer user) { users.add(user); }
    void notifyUsers(String msg) {
        for (Observer u : users) u.update(msg);
    }
}

public class Main {
    public static void main(String[] args) {
        WhatsAppGroup group = new WhatsAppGroup();
        group.addUser(new User("Alice")); // Add Alice to group
        group.addUser(new User("Bob"));   // Add Bob to group

        group.notifyUsers("Meeting at 7 PM!");  // Notify
    }
}
