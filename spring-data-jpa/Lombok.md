**Lombok** is a Java library that reduces boilerplate code in Spring Boot applications.

### Dependency

For Maven:

```xml
<dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
</dependency>
```

---

### Common Lombok Annotations

#### 1. `@Getter` and `@Setter`

```java
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Employee {
    private Long id;
    private String name;
}
```

Lombok automatically generates getters and setters.

---

#### 2. `@NoArgsConstructor`

```java
@NoArgsConstructor
public class Employee {
    private Long id;
    private String name;
}
```

Generates:

```java
public Employee() {}
```

---

#### 3. `@AllArgsConstructor`

```java
@AllArgsConstructor
public class Employee {
    private Long id;
    private String name;
}
```

Generates:

```java
public Employee(Long id, String name) {
    this.id = id;
    this.name = name;
}
```

---

#### 4. `@RequiredArgsConstructor`

Generates constructor only for `final` and `@NonNull` fields.

```java
@RequiredArgsConstructor
public class Employee {
    private final String name;
}
```

---

#### 5. `@ToString`

```java
@ToString
public class Employee {
    private Long id;
    private String name;
}
```

---

#### 6. `@EqualsAndHashCode`

Generates `equals()` and `hashCode()` methods.

```java
@EqualsAndHashCode
public class Employee {
    private Long id;
}
```

---

#### 7. `@Data`

Most commonly used.

```java
@Data
public class Employee {
    private Long id;
    private String name;
}
```

Includes:

- `@Getter`
- `@Setter`
- `@ToString`
- `@EqualsAndHashCode`
- `@RequiredArgsConstructor`

---

#### 8. `@Builder`

Useful for creating objects.

```java
@Builder
@Data
public class Employee {
    private Long id;
    private String name;
}
```

Usage:

```java
Employee emp = Employee.builder()
        .id(1L)
        .name("Raj")
        .build();
```

---

### Spring Boot Entity Example

```java
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
}
```

### Interview Question

**Why avoid `@Data` on JPA Entities?**

Because `@Data` generates `equals()`, `hashCode()`, and `toString()`, which can cause:

- Lazy loading issues
- Circular reference problems
- Performance issues

For JPA entities, many teams prefer:

```java
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
```

instead of `@Data`.
