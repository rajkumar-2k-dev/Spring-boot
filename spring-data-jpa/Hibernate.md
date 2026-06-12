# Hibernate

**Hibernate** is an ORM (Object Relational Mapping) framework and the most popular implementation of the JPA specification.

Relationship:

```text
JPA (Specification)
       ↑
   Hibernate
(Implementation)
```

Think of it this way:

- **JPA** = Rules/Interface
- **Hibernate** = Actual implementation of those rules

---

## Why Hibernate?

Instead of writing SQL manually:

```java
INSERT INTO employee(name,email) VALUES('Raj','raj@gmail.com');
```

You can write:

```java
employeeRepository.save(employee);
```

Hibernate generates the SQL automatically.

---

## Hibernate Architecture

```text
Application
    ↓
Hibernate
    ↓
JDBC
    ↓
Database
```

---

## Entity Example

```java
@Entity
@Table(name = "employee")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;
}
```

---

## Hibernate Configuration (Spring Boot)

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/testdb
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

### `ddl-auto` Values

| Value       | Meaning                             |
| ----------- | ----------------------------------- |
| none        | No action                           |
| validate    | Validate schema                     |
| update      | Update tables                       |
| create      | Recreate tables                     |
| create-drop | Create on startup, drop on shutdown |

---

## Hibernate Primary Key Generation

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

Strategies:

- IDENTITY
- SEQUENCE
- TABLE
- AUTO

---

## Hibernate Caching

### First-Level Cache

Enabled by default.

```java
Employee e1 = session.get(Employee.class, 1);
Employee e2 = session.get(Employee.class, 1);
```

Only one database query is executed within the same session.

### Second-Level Cache

Works across sessions and requires configuration.

Common providers:

- Ehcache
- Infinispan

---

## Fetch Types

```java
@OneToMany(fetch = FetchType.LAZY)
```

### LAZY

Loads child data only when accessed.

### EAGER

Loads child data immediately.

Interview question:

> Which is better?

Generally **LAZY** is preferred for performance.

---

## Cascade Types

```java
@OneToMany(cascade = CascadeType.ALL)
```

Types:

- PERSIST
- MERGE
- REMOVE
- REFRESH
- DETACH
- ALL

Example:

```java
departmentRepository.save(department);
```

Employees are also saved automatically.

---

## N+1 Query Problem

Example:

```java
List<Department> departments = repository.findAll();
```

For each department, Hibernate executes another query to load employees.

Solution:

```java
@Query("""
SELECT d
FROM Department d
JOIN FETCH d.employees
""")
```

or use `EntityGraph`.

---

## Hibernate Session

Equivalent of JPA's `EntityManager`.

```java
Session session =
entityManager.unwrap(Session.class);
```

Common methods:

```java
save()
update()
delete()
get()
load()
```

---

## `get()` vs `load()`

### get()

```java
Employee emp = session.get(Employee.class, 1L);
```

- Immediately hits database.
- Returns `null` if record not found.

### load()

```java
Employee emp = session.load(Employee.class, 1L);
```

- Returns proxy object.
- Database access happens later.
- Throws exception if record doesn't exist.

---

## Hibernate Lifecycle States

```text
Transient
   ↓
Persistent
   ↓
Detached
   ↓
Removed
```

Example:

```java
Employee emp = new Employee(); // Transient

session.save(emp);             // Persistent

session.detach(emp);           // Detached

session.delete(emp);           // Removed
```
