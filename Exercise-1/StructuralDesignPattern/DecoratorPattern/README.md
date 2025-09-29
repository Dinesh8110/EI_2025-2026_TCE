# Decorator Pattern – Coffee Example

## Problem
We want to dynamically add toppings (Milk, Sugar) to coffee without modifying the base class.

## Solution
We use **Decorator Pattern**:
- **Base** = SimpleCoffee
- **Wrappers** = MilkDecorator, SugarDecorator

## Detailed Explanation

The **Decorator Pattern** is a structural design pattern that allows behavior to be added to individual objects, dynamically, without affecting the behavior of other objects from the same class. It wraps the original object with a new object that adds the new behavior.

### How it works (Coffee Example):

#### Component (Coffee):
- Defines the interface for objects that can have responsibilities added to them (e.g., `getDescription()`, `getCost()`).

#### Concrete Component (SimpleCoffee):
- The original object to which new functionalities can be added.

#### Decorators (MilkDecorator, SugarDecorator):
- Wrap the component and add new behavior (e.g., add milk or sugar).
- Implement the same interface as the component.

### Steps in the Coffee Example:
1. Start with a `SimpleCoffee` object.
2. Wrap it with a `MilkDecorator` to add milk.
3. Wrap it again with a `SugarDecorator` to add sugar.
4. Each decorator adds its own behavior (description and cost).

### Benefits:
- **Flexible:** Add new features without changing existing code.
- **Combinable:** Multiple decorators can be combined in any order.
- **Single Responsibility:** Functionality is divided between classes.

### Typical Structure:
```
Component (Coffee)
 ├── getDescription()
 └── getCost()

ConcreteComponent (SimpleCoffee)
 ├── getDescription()
 └── getCost()

Decorator (CoffeeDecorator)
 ├── getDescription()
 └── getCost()

ConcreteDecorators (MilkDecorator, SugarDecorator)
 ├── getDescription()
 └── getCost()
```

## Output Example
```
Simple Coffee, Milk, Sugar -> $8.0
```

This pattern is widely used for adding features to objects in a flexible and reusable way, such as in UI frameworks and stream