import java.util.*;

// Abstract Product: Pizza
abstract class Pizza {
    // Abstract method to prepare pizza
    abstract void prepare();
}

// Concrete Product: VegPizza
class VegPizza extends Pizza {
    // Implementation of prepare for VegPizza
    void prepare() { System.out.println("Preparing Veg Pizza"); }
}

// Concrete Product: CheesePizza
class CheesePizza extends Pizza {
    // Implementation of prepare for CheesePizza
    void prepare() { System.out.println("Preparing Cheese Pizza"); }
}

// Factory class to create Pizza objects
class PizzaFactory {
    // Static factory method to get the desired Pizza type
    static Pizza getPizza(String type) {
        if (type.equals("veg")) return new VegPizza();
        else return new CheesePizza();
    }
}

// Client code to demonstrate Factory Pattern
public class Main {
    public static void main(String[] args) {
        // Request a VegPizza from the factory
        Pizza p1 = PizzaFactory.getPizza("veg");
        p1.prepare();

        // Request a CheesePizza from the factory
        Pizza p2 = PizzaFactory.getPizza("cheese");
        p2.prepare();
    }
}
