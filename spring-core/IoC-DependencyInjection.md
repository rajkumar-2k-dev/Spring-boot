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
