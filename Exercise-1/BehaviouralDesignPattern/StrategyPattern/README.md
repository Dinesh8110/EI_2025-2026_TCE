# Strategy Pattern – Payment Example

## Problem
We want to support multiple payment methods (CreditCard, PayPal) without hardcoding logic.

## Solution
We use **Strategy Pattern**:
- **Strategy** = Payment method (e.g., CreditCard, PayPal)
- **Context** = Executes the chosen payment strategy

## Detailed Explanation

The **Strategy Pattern** is a behavioral design pattern that enables selecting an algorithm's behavior at runtime. It defines a family of algorithms, encapsulates each one, and makes them interchangeable. The context object uses a strategy to execute the desired behavior.

### How it works (Payment Example):

#### Strategy (Payment Method):
- Defines a common interface for all supported payment methods (e.g., `pay(amount)`).
- Concrete strategies implement this interface (e.g., `CreditCardPayment`, `PayPalPayment`).

#### Context (Payment Processor):
- Maintains a reference to a strategy object.
- Allows changing the strategy at runtime.
- Delegates the payment operation to the selected strategy.

### Steps in the Payment Example:
1. The user selects a payment method (CreditCard or PayPal).
2. The context (Payment Processor) is configured with the chosen payment strategy.
3. When a payment is made, the context delegates the operation to the selected strategy.

### Benefits:
- **Open/Closed Principle:** Easily add new payment methods without modifying existing code.
- **Separation of concerns:** Payment logic is separated from the context.
- **Flexibility:** Change payment methods at runtime.

### Typical Structure:
```
Strategy
 └── pay(amount)

ConcreteStrategyA (CreditCardPayment)
 └── pay(amount)

ConcreteStrategyB (PayPalPayment)
 └── pay(amount)

Context (PaymentProcessor)
 ├── setStrategy(strategy)
 └── pay(amount)
```

## Output Example
```
Paid 500 using Credit Card
Paid 300 using PayPal
```

This pattern is widely used for algorithms that can be swapped at runtime, such as sorting, payment