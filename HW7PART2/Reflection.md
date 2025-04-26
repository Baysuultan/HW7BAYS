# Reflection: Mediator Pattern in Airport Tower Simulator

## Why Mediator is Better than Direct Communication

The Mediator pattern centralizes communication between multiple objects, reducing the complexity of many-to-many interactions. In the context of the Airport Tower Simulator, the ControlTower acts as the mediator, ensuring that all aircraft communicate through it rather than directly with each other. This approach offers several advantages:

1. **Simplified Communication**: Aircraft do not need to know about the existence or state of other aircraft. They only interact with the ControlTower, which manages all coordination.

2. **Centralized Logic**: The ControlTower encapsulates all the rules for runway allocation, emergency handling, and message broadcasting. This makes the system easier to maintain and extend.

3. **Scalability**: Adding new types of aircraft or new rules (e.g., priority for VIP planes) becomes straightforward, as changes are localized to the mediator.

4. **Improved Readability**: The code is easier to understand because the communication flow is centralized, avoiding a web of interdependent objects.

## One Disadvantage of Mediator

While the Mediator pattern simplifies communication, it can lead to a **monolithic mediator**. As the system grows, the mediator (ControlTower) may become overly complex, handling too many responsibilities. This violates the Single Responsibility Principle and can make the mediator difficult to test and maintain.

## Conclusion

The Mediator pattern is highly effective in scenarios like the Airport Tower Simulator, where centralized coordination is essential. However, care must be taken to modularize the mediator's responsibilities, possibly by combining it with other patterns like Strategy or Command to manage complexity.