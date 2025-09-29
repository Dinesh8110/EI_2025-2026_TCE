# Adapter Pattern – USB Example

## Problem
We want to connect an old USB-A device to a new USB-C port.

## Solution
We use **Adapter Pattern**:
- **Adapter** bridges the old device (USB-A) to the new interface (USB-C).

## Detailed Explanation

The **Adapter Pattern** is a structural design pattern that allows objects with incompatible interfaces to work together. It acts as a bridge between two incompatible interfaces by wrapping an existing class with a new interface.

### How it works (USB Example):

#### Adaptee (USB-A Device):
- The existing device with an old interface (USB-A).

#### Target (USB-C Port):
- The new interface that the client expects (USB-C).

#### Adapter (USB-A to USB-C Adapter):
- Implements the target interface (USB-C).
- Internally uses/adapts the adaptee (USB-A device) to make it compatible with the target.

### Steps in the USB Example:
1. The client wants to connect a USB-A device to a USB-C port.
2. The adapter is plugged into the USB-C port.
3. The USB-A device is connected to the adapter.
4. The adapter translates the USB-C requests to USB-A, enabling compatibility.

### Benefits:
- **Reusability:** Allows reuse of existing devices with new interfaces.
- **Flexibility:** Integrates incompatible interfaces without modifying their code.

### Typical Structure:
```
Target (USB-C)
 └── connect()

Adaptee (USB-A)
 └── connectA()

Adapter (USB-A to USB-C)
 └── connect() // calls connectA()
```

## Output Example
```
Connected using USB-A
```

This pattern is widely used for integrating legacy code, device compatibility, and working with third