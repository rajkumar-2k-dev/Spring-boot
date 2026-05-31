# Beans in Spring

## What is a Bean?

A **Spring Bean** is simply a **Java object that is created, configured, and managed by the Spring IoC (Inversion of Control) container**.

In normal Java:

```java
Car car = new Car();
```

You create objects manually.

In Spring:

> Spring creates the object for you
> Spring manages its lifecycle
> Spring injects its dependencies

So, a bean is just:

> ✔ A managed object inside the Spring container

---

## 🧠 Why Beans are Important

Beans are the foundation of Spring because they enable:

- 🔗 Dependency Injection (DI)
- 🧩 Loose coupling between components
- ⚙️ Automatic object creation and wiring
- 🔄 Lifecycle management (init → use → destroy)

---

## 🏗️ Spring Container Role

Spring uses a container called:

### IoC Container (ApplicationContext)

It is responsible for:

- Creating beans
- Wiring dependencies
- Managing configuration
- Destroying beans when application stops

So you don’t manually control objects anymore.

---

## ⚙️ Ways to Define Beans in Spring

Spring provides 3 main ways:

---

# 1. XML Configuration (Legacy Approach)

This is the oldest method.

### Example:

```xml
<bean id="car" class="com.example.Car"/>
```

### Usage:

```java
Car car = context.getBean("car", Car.class);
```

### Disadvantages:

- Verbose
- Hard to maintain
- Not type-safe

### Status:

Used only in older enterprise applications.

---

# 2. Annotation-Based Configuration (Most Common)

Spring automatically detects classes as beans using annotations.

### Example:

```java
@Component
public class Car {
}
```

Spring automatically creates a bean for this class.

---

## Common Stereotype Annotations:

| Annotation    | Purpose                      |
| ------------- | ---------------------------- |
| `@Component`  | Generic Spring bean          |
| `@Service`    | Service/business logic layer |
| `@Repository` | Database layer (DAO)         |
| `@Controller` | Web layer (MVC)              |

All of these are internally treated as beans.

---

## How it works:

Spring scans packages using **component scanning**:

```java
@ComponentScan("com.example")
```

Then automatically registers beans.

---

# 3. Java Configuration (Modern & Preferred in Spring Boot)

Instead of XML, you define beans using Java code.

### Example:

```java
@Configuration
public class AppConfig {

    @Bean
    public Car car() {
        return new Car();
    }
}
```

---

## 🧠 What happens here?

- `@Configuration` = configuration class
- `@Bean` = method return object becomes a Spring bean
- Spring calls this method internally and stores the object

---

## Advantages:

- Type-safe
- Easy to manage
- Fully Java-based (no XML)

---

## Summary of Bean Creation Methods

| Method      | Usage              | Status            |
| ----------- | ------------------ | ----------------- |
| XML         | `<bean>` tags      | Old               |
| Annotations | `@Component`, etc. | Most common       |
| Java Config | `@Bean` methods    | Modern & flexible |

---

## Key Takeaway

A Spring Bean is:

✔ A Java object
✔ Created by Spring container
✔ Managed automatically
✔ Configured via XML, annotations, or Java config
