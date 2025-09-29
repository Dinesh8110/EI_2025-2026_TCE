# Observer Pattern – WhatsApp Group Example

## Problem
We need a system where sending one message notifies multiple users automatically.

## Solution
We use **Observer Pattern**:
- **Subject** = WhatsAppGroup
- **Observers** = Users
- Any message triggers updates for all observers.

## Detailed Explanation

The **Observer Pattern** is a behavioral design pattern that creates a one-to-many dependency between objects. When the state of one object (the **Subject**) changes, all its dependents (**Observers**) are notified and updated automatically.

### How it works (WhatsApp Group Example):

#### Subject (WhatsAppGroup):
- Maintains a list of observers (users).
- Provides methods to add or remove observers.
- When a message is sent, it notifies all registered observers.

#### Observers (Users):
- Register themselves to the subject to receive updates.
- Implement an update method that is called when the subject changes (e.g., when a new message is sent).

### Steps in the WhatsApp Group Example:
1. Users (Alice, Bob) join the WhatsApp group (register as observers).
2. When someone sends a message to the group (subject), the group notifies all registered users.
3. Each user receives the message and can react to it.

### Benefits:
- **Loose coupling:** The subject doesn't need to know details about the observers.
- **Easy to add/remove observers at runtime.**

### Typical Structure:
```
Subject
 ├── addObserver(observer)
 ├── removeObserver(observer)
 └── notifyObservers()

Observer
 └── update(message)
```

## Output Example
```
Alice received: Meeting at 7 PM!
Bob received: Meeting at 7 PM!
```

This pattern is widely used in event handling systems, GUIs, and real-time notifications.