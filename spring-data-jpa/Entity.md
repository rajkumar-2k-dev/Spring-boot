# JPA Entity - Complete Guide

## What is an Entity?

An Entity is a Java class that represents a table in a relational database.

Each object of the entity class represents a row in the table, and each field represents a column.

For example:

Database Table: `employee`

| id  | name  | email                                     |
| --- | ----- | ----------------------------------------- |
| 1   | John  | [john@gmail.com](mailto:john@gmail.com)   |
| 2   | Alice | [alice@gmail.com](mailto:alice@gmail.com) |

Entity Class:

```java
@Entity
public class Employee {

    @Id
    private Integer id;

    private String name;

    private String email;
}
```

---

# @Entity Annotation

The `@Entity` annotation marks a class as a JPA entity.

```java
@Entity
public class Employee {
}
```

Without `@Entity`, Hibernate/JPA will not treat the class as a database table.

---

# Entity Requirements

A JPA Entity should:

### 1. Be annotated with `@Entity`

```java
@Entity
public class Employee {
}
```

### 2. Have a Primary Key

Every entity must contain a field annotated with `@Id`.

```java
@Id
private Integer id;
```

### 3. Have a No-Argument Constructor

```java
public Employee() {
}
```

Hibernate uses reflection to create objects.

### 4. Not be Final

```java
public class Employee {
}
```

Avoid:

```java
public final class Employee {
}
```

---

# Primary Key

## @Id

Defines the primary key.

```java
@Id
private Integer id;
```

The primary key uniquely identifies each row.

Example:

```java
Employee e1 = repository.findById(1).get();
```

Hibernate uses the ID to locate records.

---

# Auto Generated IDs

## @GeneratedValue

Automatically generates primary key values.

```java
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Integer id;
```

Database generates:

```text
1
2
3
4
```

### Strategies

#### IDENTITY

Uses database auto increment.

```java
@GeneratedValue(strategy = GenerationType.IDENTITY)
```

#### SEQUENCE

Uses database sequence.

```java
@GeneratedValue(strategy = GenerationType.SEQUENCE)
```

#### AUTO

JPA chooses the strategy.

```java
@GeneratedValue(strategy = GenerationType.AUTO)
```

---

# Why Integer Instead of int?

Preferred:

```java
private Integer id;
```

Not:

```java
private int id;
```

Reason:

Before saving:

```java
Employee emp = new Employee();
```

ID is unknown.

```java
emp.getId(); // null
```

With int:

```java
emp.getId(); // 0
```

`null` clearly indicates "not yet persisted".

---

# @Table Annotation

Maps entity to a specific table.

```java
@Entity
@Table(name = "employee")
public class Employee {
}
```

Without `@Table`, Hibernate uses the class name.

---

# @Column Annotation

Maps a field to a column.

```java
@Column(name = "employee_name")
private String name;
```

Table:

```sql
employee_name VARCHAR(255)
```

---

# Column Constraints

## Nullable

```java
@Column(nullable = false)
private String name;
```

SQL:

```sql
name VARCHAR(255) NOT NULL
```

---

## Unique

```java
@Column(unique = true)
private String email;
```

SQL:

```sql
UNIQUE(email)
```

---

## Length

```java
@Column(length = 100)
private String name;
```

SQL:

```sql
VARCHAR(100)
```

---

# Transient Fields

Fields not stored in database.

```java
@Transient
private String temporaryData;
```

Hibernate ignores this field.

---

# Date and Time Mapping

```java
private LocalDate dateOfBirth;

private LocalDateTime createdAt;
```

Example:

```java
@Column(name = "created_at")
private LocalDateTime createdAt;
```

---

# Large Objects

## @Lob

Store large text or files.

```java
@Lob
private String description;
```

or

```java
@Lob
private byte[] image;
```

---

# Entity Relationships

## One-To-One

```java
@OneToOne
@JoinColumn(name = "passport_id")
private Passport passport;
```

One employee has one passport.

---

## One-To-Many

```java
@OneToMany(mappedBy = "department")
private List<Employee> employees;
```

One department has many employees.

---

## Many-To-One

```java
@ManyToOne
@JoinColumn(name = "department_id")
private Department department;
```

Many employees belong to one department.

---

## Many-To-Many

```java
@ManyToMany
@JoinTable(
    name = "student_course",
    joinColumns = @JoinColumn(name = "student_id"),
    inverseJoinColumns = @JoinColumn(name = "course_id")
)
private List<Course> courses;
```

Many students can enroll in many courses.

---

# Entity Lifecycle

States of an Entity:

### Transient

Object created but not saved.

```java
Employee emp = new Employee();
```

### Persistent

Managed by Hibernate.

```java
repository.save(emp);
```

### Detached

No longer managed by Hibernate.

### Removed

Marked for deletion.

```java
repository.delete(emp);
```

---

# Common Entity Example

```java
@Entity
@Table(name = "employees")
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true)
    private String email;

    private Double salary;

    private LocalDate joiningDate;

    public Employee() {
    }

    // Getters and Setters
}
```

---

# Interview Questions

### What is an Entity?

A Java class that represents a database table.

### Why is @Id mandatory?

JPA requires a unique identifier to manage entity instances.

### Why use Integer instead of int?

Integer can be null before persistence, whereas int defaults to 0.

### What is @GeneratedValue?

Used to automatically generate primary key values.

### What is @Transient?

Marks a field that should not be persisted in the database.

### What is @Table?

Maps an entity to a specific database table.

### What is @Column?

Customizes column mapping and constraints.

---

# Summary

- `@Entity` → Represents a database table.
- `@Id` → Primary key.
- `@GeneratedValue` → Auto-generated IDs.
- `@Table` → Table mapping.
- `@Column` → Column mapping.
- `@Transient` → Ignore field.
- `@Lob` → Large objects.
- Relationships are handled using `@OneToOne`, `@OneToMany`, `@ManyToOne`, and `@ManyToMany`.
- Every JPA entity must have a primary key and a no-argument constructor.
