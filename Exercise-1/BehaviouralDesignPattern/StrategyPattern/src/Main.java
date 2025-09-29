import java.util.*;

interface PaymentStrategy {
    void pay(int amount);
}

// Concrete strategy: Credit Card
class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using Credit Card");
    }
}

// Concrete strategy: PayPal
class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " using PayPal");
    }
}

// Context to use payment strategy
class PaymentContext {
    private PaymentStrategy strategy;
    PaymentContext(PaymentStrategy strategy) { this.strategy = strategy; }
    void executePayment(int amount) { strategy.pay(amount); }
}

public class Main {
    public static void main(String[] args) {
        PaymentContext ctx1 = new PaymentContext(new CreditCardPayment()); // Use Credit Card
        ctx1.executePayment(500);

        PaymentContext ctx2 = new PaymentContext(new PayPalPayment());     // Use PayPal
        ctx2.executePayment(300);
    }
}
