### JPA (Java Persistence API)

JPA is a Java specification used to map Java objects to database tables (ORM - Object Relational Mapping).

In Spring Boot, the most common JPA implementation is Hibernate.

### Why JPA?

Without JPA:

```java
Connection con = DriverManager.getConnection(...);
PreparedStatement ps = con.prepareStatement(
    "INSERT INTO employee(name,email) VALUES(?,?)");
```

With JPA:

```java
employeeRepository.save(employee);
```

JPA handles the SQL generation.

---

## Basic JPA Entity

```java
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String email;
}
```

### Important Annotations

| Annotation        | Purpose                       |
| ----------------- | ----------------------------- |
| `@Entity`         | Marks class as database table |
| `@Table`          | Specifies table name          |
| `@Id`             | Primary key                   |
| `@GeneratedValue` | Auto-generate ID              |
| `@Column`         | Column configuration          |
| `@Transient`      | Ignore field in DB            |

---

## Repository

```java
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}
```

Spring Data JPA automatically provides:

```java
save()
findById()
findAll()
deleteById()
count()
existsById()
```

---

## Service

```java
@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository repository;

    public Employee save(Employee employee) {
        return repository.save(employee);
    }

    public List<Employee> getAll() {
        return repository.findAll();
    }
}
```

---

## Relationships

### One-to-One

```java
@OneToOne
@JoinColumn(name = "passport_id")
private Passport passport;
```

### One-to-Many

```java
@OneToMany(mappedBy = "department")
private List<Employee> employees;
```

### Many-to-One

```java
@ManyToOne
@JoinColumn(name = "department_id")
private Department department;
```

### Many-to-Many

```java
@ManyToMany
@JoinTable(
    name = "author_book",
    joinColumns = @JoinColumn(name = "author_id"),
    inverseJoinColumns = @JoinColumn(name = "book_id")
)
private List<Book> books;
```

---

## Derived Query Methods

```java
List<Employee> findByName(String name);

List<Employee> findByEmailContaining(String email);

Employee findByEmail(String email);
```

Spring generates SQL automatically.

---

## JPQL

```java
@Query("SELECT e FROM Employee e WHERE e.name = :name")
List<Employee> findEmployees(String name);
```

---

## Native Query

```java
@Query(
 value = "SELECT * FROM employees WHERE name = :name",
 nativeQuery = true
)
List<Employee> findByNameNative(String name);
```

---

## Fetch Types

```java
@OneToMany(fetch = FetchType.LAZY)
```

- **LAZY** → Load data only when needed.
- **EAGER** → Load related data immediately.

Interview answer:

> By default, `@ManyToOne` is EAGER and `@OneToMany` is LAZY.

---

## JPA Lifecycle

1. Transient
2. Persistent
3. Detached
4. Removed

These are common interview topics for 2–5 years of experience.

### Frequently Asked JPA Interview Questions

1. Difference between JPA and Hibernate?
2. What is the Persistence Context?
3. `save()` vs `saveAndFlush()`?
4. LAZY vs EAGER fetching?
5. `mappedBy` meaning?
6. Cascade types?
7. First-level cache and second-level cache?
8. N+1 query problem?
9. `orphanRemoval = true` usage?
10. Why avoid `@Data` on JPA entities?

For Spring Boot interviews, focus heavily on Relationships, Fetch Types, Cascading, JPQL, and the N+1 problem. These topics are asked very frequently.
