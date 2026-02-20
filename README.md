# OmniHome Architecture Assignment

In this project, I implemented the core modules for the OmniHome smart home hub. The goal was to solve specific architectural challenges using five standard GoF design patterns.

## Project Structure
The project is built as a standard Java application, using Maven to manage testing dependencies (JUnit 5). The code is strictly organized into separate packages for the main application, the pattern logic, and the unit tests.

## Implemented Patterns

### 1. Singleton Pattern (Task 1: The Core Connection)
* **Class:** `CloudConnection`
* **Purpose:** Ensures that exactly one instance of the cloud connection exists throughout the application, providing a global point of access.
* **Implementation:** I used a thread-safe approach (Initialization-on-demand holder idiom / Double-Checked Locking) so that even if multiple threads try to access the connection at the same time, only one memory instance is ever created.

### 2. Abstract Factory Pattern (Task 2: The Device Ecosystem)
* **Classes:** `DeviceFactory` (Interface), `BudgetFactory`, `LuxuryFactory`
* **Purpose:** Allows the creation of entire families of related products (Lights, Locks, Thermostats) without the client code needing to know their concrete classes.
* **Implementation:** By changing just one line of code (instantiating a `BudgetFactory` instead of a `LuxuryFactory`), the entire ecosystem swaps from basic plastic devices to premium AI-powered devices.

### 3. Adapter Pattern (Task 3: The Legacy Integration)
* **Class:** `GlorbAdapter`
* **Purpose:** Acts as a bridge to make the incompatible legacy `GlorbThermostat` work with the modern `SmartThermostat` target interface.
* **Implementation:** The adapter wraps the legacy device. When the main system calls `setTemperature(Celsius)`, the adapter translates the double into an integer in Fahrenheit and passes it to the legacy method `setHeatIndex()`.

### 4. Builder Pattern (Task 4: The Automation Builder)
* **Classes:** `AutomationRoutine` and its inner `Builder` class.
* **Purpose:** Simplifies the creation of a complex object that has multiple optional properties, avoiding a confusing constructor with too many parameters.
* **Implementation:** I created a fluent interface that allows chaining methods like `.atTime()` and `.addDevice()`. The `build()` method validates the inputs (ensuring a time is provided) before returning the final, immutable routine object.

### 5. Prototype Pattern (Task 5: The Configuration Cloner)
* **Class:** `NetworkConfig`
* **Purpose:** Allows for the rapid creation of new objects by copying an existing instance rather than building from scratch.
* **Implementation:** The class implements the `Cloneable` interface. By calling `.clone()`, we duplicate the settings from one room to another. Modifying the IP address of the cloned configuration does not affect the original object.

## How to Run the Simulation
1. Navigate to the `com.omnihome.app` package.
2. Run the `OmniHomeApp.main()` method to execute the simulation of all five patterns working together.
3. To verify the logic constraints, run the JUnit 5 tests located in the `src/test/java` directory.

