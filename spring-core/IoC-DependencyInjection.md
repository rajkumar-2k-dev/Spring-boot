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

# Key Concepts in Spring Core

## 1. Inversion of Control (IoC)

IoC means:
Instead of the programmer creating objects manually, the Spring container creates and manages objects.

Traditional Java:

```java id="9m63hn"
UserService service = new UserService();
```

Spring:

- Spring creates the object
- Spring injects dependencies automatically

This shifts control from the programmer to the framework.

---

# IoC Container

The IoC Container is responsible for:

- Creating objects
- Managing objects
- Injecting dependencies
- Configuring beans
- Managing bean lifecycle

Two main containers:

1. BeanFactory
2. ApplicationContext

---

## BeanFactory

Basic IoC container.

Features:

- Lightweight
- Lazy loading
- Basic dependency injection

---

## ApplicationContext

Advanced IoC container.

Features:

- Bean management
- Event handling
- Internationalization
- Annotation support

Most commonly used container.

---

# Dependency Injection (DI)

Dependency Injection means:
Objects receive dependencies from Spring instead of creating them manually.

Example:

Without DI:

```java id="9n8ryg"
public class Car {
    Engine engine = new Engine();
}
```

With DI:

```java id="tw0zdo"
public class Car {

    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

Spring injects `Engine` automatically.

---

# Types of Dependency Injection

## Constructor Injection

Recommended approach.

```java id="ukb23w"
@Component
public class Car {

    private final Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

Advantages:

- Immutable objects
- Easier testing
- Better design

---

## Setter Injection

```java id="1vllxq"
@Component
public class Car {

    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }
}
```

---

## Field Injection

```java id="8lpk18"
@Autowired
private Engine engine;
```

Not recommended for large applications because:

- Harder to test
- Less maintainable

---

# Spring Beans

A Bean is an object managed by the Spring IoC container.

Example:

```java id="h43b0q"
@Component
public class Engine {
}
```

Spring creates and manages this object.

---

# Bean Lifecycle

Lifecycle steps:

```text id="msm4j6"
Bean Created
    ↓
Dependencies Injected
    ↓
Initialization Method
    ↓
Bean Ready for Use
    ↓
Destroy Method
```

---

# Bean Scopes

Spring supports multiple scopes.

| Scope     | Description                 |
| --------- | --------------------------- |
| singleton | Single object per container |
| prototype | New object every request    |
| request   | One bean per HTTP request   |
| session   | One bean per HTTP session   |

Default scope:

```text id="6i7bq9"
singleton
```

---

# Spring Core Modules

Core Spring Framework modules include:

| Module            | Purpose             |
| ----------------- | ------------------- |
| spring-core       | Core utilities      |
| spring-beans      | Bean management     |
| spring-context    | Application context |
| spring-expression | Expression language |

---

# Important Annotations

## @Component

Marks class as Spring Bean.

```java id="0j6o2q"
@Component
public class Engine {
}
```

---

## @Autowired

Automatically injects dependencies.

```java id="q8k2el"
@Autowired
private Engine engine;
```

---

## @Configuration

Defines configuration class.

```java id="8slyvu"
@Configuration
public class AppConfig {
}
```

---

## @Bean

Creates bean manually.

```java id="qecm24"
@Bean
public Engine engine() {
    return new Engine();
}
```

---

# Configuration Types

## XML Configuration

Old approach.

```xml id="5nxjpk"
<bean id="engine" class="com.example.Engine"/>
```

---

## Java-Based Configuration

Modern approach.

```java id="mrn7tq"
@Configuration
public class AppConfig {

    @Bean
    public Engine engine() {
        return new Engine();
    }
}
```

---

## Annotation-Based Configuration

Most popular approach.

```java id="tzr0sr"
@Component
public class Engine {
}
```

---

# ApplicationContext Example

```java id="c4mnj7"
ApplicationContext context =
    new AnnotationConfigApplicationContext(AppConfig.class);

Engine engine = context.getBean(Engine.class);
```

---

# Advantages of Spring Core

✅ Loose coupling
✅ Easy testing
✅ Better maintainability
✅ Reusable components
✅ Simplified object management
✅ Scalable architecture

---

# Spring Core Architecture

```text id="0q8x84"
Application
     ↓
Spring Container
     ↓
Beans / Objects
     ↓
Dependencies Injected
```

---

# Real-World Example

Example:
Car depends on Engine.

Instead of:

```java id="6lk8kq"
Engine engine = new Engine();
```

Spring handles:

- Engine creation
- Injection into Car
- Lifecycle management

This improves:

- Flexibility
- Testing
- Maintenance

---

# Spring Core vs Traditional Java

| Traditional Java       | Spring Core               |
| ---------------------- | ------------------------- |
| Tight coupling         | Loose coupling            |
| Manual object creation | Automatic bean management |
| Hard testing           | Easier testing            |
| Complex maintenance    | Easy maintenance          |

---

# Relationship Between Spring Core and Spring Boot

| Spring Core          | Spring Boot          |
| -------------------- | -------------------- |
| Foundation framework | Built on Spring Core |
| Manual configuration | Auto configuration   |
| More setup           | Simplified setup     |

Spring Boot internally uses Spring Core concepts like:

- IoC
- DI
- Beans
- ApplicationContext

---

# Commonly Used Spring Core Interfaces

| Interface          | Purpose                 |
| ------------------ | ----------------------- |
| BeanFactory        | Basic container         |
| ApplicationContext | Advanced container      |
| InitializingBean   | Initialization callback |
| DisposableBean     | Destroy callback        |

---

# Bean Lifecycle Methods

Example:

```java id="d7pnz5"
@PostConstruct
public void init() {
    System.out.println("Bean Initialized");
}

@PreDestroy
public void destroy() {
    System.out.println("Bean Destroyed");
}
```

---

# Example Complete Flow

```java id="8n1r1x"
@Component
class Engine {
}

@Component
class Car {

    private final Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }
}
```

Spring:

1. Creates Engine bean
2. Creates Car bean
3. Injects Engine into Car

---
