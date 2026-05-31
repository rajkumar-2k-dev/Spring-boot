# Introduction to Spring Framework Core

Spring Core is the fundamental module of the Spring Framework. It provides the basic features required to build Java applications, especially:

- Dependency Injection (DI)
- Inversion of Control (IoC)
- Bean management
- Application context

Spring Core is considered the “heart” of the Spring Framework because all other Spring modules depend on it.

---

# What is Spring Core?

Spring Core is a lightweight framework that helps developers build loosely coupled, maintainable, and scalable Java applications.

Main purpose:

- Manage Java objects automatically
- Reduce tight coupling between classes
- Simplify enterprise application development

---

# Why Spring Core is Important

Before Spring:

- Objects were manually created using `new`
- Classes became tightly coupled
- Testing and maintenance were difficult

Spring Core solves this using:

- IoC Container
- Dependency Injection

---

## Key Concepts in Spring Core

### 1. Inversion of Control (IoC)

Instead of your code controlling object creation, Spring takes over that responsibility.

- You define _what_ objects are needed
- Spring decides _when and how_ to create them

This reduces tight coupling between classes.

---

### 2. Dependency Injection (DI)

DI is the mechanism used by Spring to implement IoC.

There are 3 main types:

- **Constructor Injection** (recommended)
- **Setter Injection**
- **Field Injection** (less preferred)

Example idea:

```java
class Car {
    Engine engine;

    Car(Engine engine) {
        this.engine = engine;
    }
}
```

Spring automatically provides the `Engine` object.

---

### 3. Spring Container

The Spring container is responsible for:

- Creating objects (beans)
- Managing their lifecycle
- Injecting dependencies

Main container types:

- `BeanFactory` (basic)
- `ApplicationContext` (advanced, widely used)

---

### 4. Beans

A **bean** is simply an object managed by Spring.

You define beans using:

- XML configuration (older style)
- Java annotations (modern style)
- Java configuration classes

---

### 5. Configuration Styles

Modern Spring heavily uses annotations:

- `@Component`
- `@Service`
- `@Repository`
- `@Configuration`
- `@Bean`
- `@Autowired`

---

## Why Spring Core is Important

- Reduces boilerplate code
- Improves testability
- Encourages clean architecture
- Supports loose coupling
- Forms the base for Spring Boot and other Spring modules
