# Trip Service Kata
Testing and Refactoring Legacy Code
  

## Business rules

We have a social networking website for travelers, which has the following rules:

- You need to be logged in to see the content
- You need to be a friend to see someone else's trips
  

## Legacy Code Rules

- **We can’t change any existing code if not covered by tests**. We can’t afford to break any existing behavior.
- The only exception is if we need to change the code to add unit tests, but in this case, **just automated refactorings (via IDE) are allowed**.
  

## Testing tips

Start testing from the shortest to the deepest branch:

- because getting to deepest requires big setup, i.e. sample data, mocks, fakes etc.
- allows us to better understand what the code does
  

## Refactoring tips

Starting from the deepest branch to the shortest (_different from testing_)

- **Feature Envy** - A method accesses the data of another object more than its own data.
  - The behavior must be next to the data.

- **Single-responsibility principle (SRP)** 
  - every class should have only one responsibility.
  - should never be more than one reason for a class to change.

- **Dependency Inversion Principle (DIP)** 
  - high level modules should not depend on low level modules; both should depend on abstractions. 
  - abstractions should not depend on details.
