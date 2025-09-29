interface Coffee {
    String getDescription();
    double cost();
}

// Base coffee implementation
class SimpleCoffee implements Coffee {
    public String getDescription() { return "Simple Coffee"; }
    public double cost() { return 5.0; }
}

// Decorator to add milk
class MilkDecorator implements Coffee {
    private Coffee coffee;
    MilkDecorator(Coffee coffee) { this.coffee = coffee; }
    public String getDescription() { return coffee.getDescription() + ", Milk"; }
    public double cost() { return coffee.cost() + 2.0; }
}

// Decorator to add sugar
class SugarDecorator implements Coffee {
    private Coffee coffee;
    SugarDecorator(Coffee coffee) { this.coffee = coffee; }
    public String getDescription() { return coffee.getDescription() + ", Sugar"; }
    public double cost() { return coffee.cost() + 1.0; }
}

public class Main {
    public static void main(String[] args) {
        Coffee coffee = new SimpleCoffee();         // Start with simple coffee
        coffee = new MilkDecorator(coffee);         // Add milk
        coffee = new SugarDecorator(coffee);        // Add sugar

        System.out.println(coffee.getDescription() + " -> $" + coffee.cost());
    }
}
