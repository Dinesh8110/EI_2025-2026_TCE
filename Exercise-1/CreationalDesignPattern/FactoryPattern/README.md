# Factory Pattern – Pizza Shop Example

## Problem
We want to create different types of pizzas without exposing creation logic.

## Solution
We use **Factory Pattern**:
- **Factory** = PizzaFactory
- **Products** = VegPizza, CheesePizza

## Detailed Explanation

The **Factory Pattern** is a creational design pattern that provides an interface for creating objects in a superclass, but allows subclasses to alter the type of objects that will be created. It helps encapsulate the object creation logic and promotes loose coupling.

### How it works (Pizza Shop Example):

#### Factory (PizzaFactory):
- Defines a method for creating pizza objects.
- Decides which type of pizza to instantiate based on input or parameters.

#### Products (VegPizza, CheesePizza):
- Concrete classes representing different types of pizzas.
- All products implement a common interface or base class (e.g., `Pizza`).

### Steps in the Pizza Shop Example:
1. The client requests a pizza from the factory (e.g., "Veg" or "Cheese").
2. The factory creates the appropriate pizza object without exposing the creation logic to the client.
3. The client receives the pizza and can use it (e.g., prepare or serve).

### Benefits:
- **Encapsulation:** Creation logic is hidden from the client.
- **Easy to extend:** Add new pizza types without changing existing code.
- **Loose coupling:** The client depends on abstractions, not concrete classes.

### Typical Structure:
```
Factory
 └── createPizza(type)

Product (Pizza)
 └── prepare()

ConcreteProductA (VegPizza)
 └── prepare()

ConcreteProductB (CheesePizza)
 └── prepare()
```

## Output Example
```
Preparing Veg Pizza
Preparing Cheese Pizza
```

This pattern is widely used when a class can't anticipate the type of objects it needs to create, or to centralize