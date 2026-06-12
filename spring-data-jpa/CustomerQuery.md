Yes. These 14 topics form the core of JPA/Hibernate knowledge for Spring Boot interviews. Here's a concise but complete guide.

---

# 1. JPA vs Hibernate

| JPA                | Hibernate        |
| ------------------ | ---------------- |
| Specification      | Implementation   |
| Defines rules      | Implements rules |
| Part of Jakarta EE | ORM Framework    |

```text
Application
    ↓
JPA
    ↓
Hibernate
    ↓
Database
```

Example:

```java
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}
```

You use JPA APIs, Hibernate executes them.

**Interview:** Can we use Hibernate without JPA?

- Yes.
- But in Spring Boot, JPA + Hibernate is the standard approach.

---

# 2. Persistence Context

Persistence Context is the area where Hibernate manages entity objects.

```java
Employee emp = repository.findById(1L).get();
```

Hibernate stores `emp` in memory.

If you modify it:

```java
emp.setName("Raj");
```

Hibernate tracks the change.

When transaction commits:

```sql
UPDATE employee SET name='Raj'
```

is executed automatically.

This feature is called **Dirty Checking**.

---

# 3. Entity Lifecycle

### Transient

Not attached to Hibernate.

```java
Employee emp = new Employee();
```

### Persistent

Managed by Hibernate.

```java
entityManager.persist(emp);
```

### Detached

No longer managed.

```java
entityManager.detach(emp);
```

### Removed

Marked for deletion.

```java
entityManager.remove(emp);
```

---

# 4. save() vs saveAndFlush()

### save()

```java
repository.save(emp);
```

Data may remain in persistence context until flush/commit.

### saveAndFlush()

```java
repository.saveAndFlush(emp);
```

Immediately synchronizes with database.

Use when subsequent code needs the record in DB immediately.

---

# 5. LAZY vs EAGER Fetching

### LAZY

```java
@OneToMany(fetch = FetchType.LAZY)
```

Child data loaded only when accessed.

```java
department.getEmployees();
```

Then SQL runs.

### EAGER

```java
@ManyToOne(fetch = FetchType.EAGER)
```

Loaded immediately.

**Preferred:** LAZY

Because EAGER can load unnecessary data.

---

# 6. Relationships

## One-To-One

```java
@OneToOne
@JoinColumn(name="passport_id")
private Passport passport;
```

One person → One passport.

---

## One-To-Many

```java
@OneToMany(mappedBy="department")
private List<Employee> employees;
```

One department → Many employees.

---

## Many-To-One

```java
@ManyToOne
@JoinColumn(name="department_id")
private Department department;
```

Many employees → One department.

---

## Many-To-Many

```java
@ManyToMany
@JoinTable(
    name="author_book",
    joinColumns=@JoinColumn(name="author_id"),
    inverseJoinColumns=@JoinColumn(name="book_id")
)
private List<Book> books;
```

Many authors ↔ Many books.

---

# 7. mappedBy

Defines the owner of the relationship.

```java
@OneToMany(mappedBy="department")
private List<Employee> employees;
```

```java
@ManyToOne
@JoinColumn(name="department_id")
private Department department;
```

Employee owns relationship because it contains `@JoinColumn`.

Without `mappedBy`, Hibernate creates extra tables.

---

# 8. Cascade Types

Without cascade:

```java
departmentRepository.save(department);
```

Employees are not saved automatically.

With:

```java
@OneToMany(cascade = CascadeType.ALL)
```

Saving department saves employees.

Types:

```java
PERSIST
MERGE
REMOVE
REFRESH
DETACH
ALL
```

Most common:

```java
CascadeType.ALL
```

---

# 9. orphanRemoval

```java
@OneToMany(
    mappedBy="department",
    orphanRemoval=true
)
```

Example:

```java
department.getEmployees().remove(employee);
```

Hibernate automatically deletes employee.

Without orphanRemoval:

Employee remains in DB.

---

# 10. JPQL

Java Persistence Query Language.

Works on entities.

```java
@Query("""
SELECT e
FROM Employee e
WHERE e.name=:name
""")
```

Notice:

```java
Employee
name
```

Entity names, not table names.

---

# 11. Native Query

Uses actual SQL.

```java
@Query(
value="SELECT * FROM employee WHERE name=:name",
nativeQuery=true
)
```

Use when:

- Database-specific SQL
- Complex queries
- Stored procedures

---

# 12. First-Level Cache vs Second-Level Cache

## First-Level Cache

Enabled by default.

```java
session.get(Employee.class,1);
session.get(Employee.class,1);
```

Second call doesn't hit database.

Scope:

```text
Session / EntityManager
```

---

## Second-Level Cache

Shared across sessions.

```text
Session 1
Session 2
Session 3
     ↓
Second-Level Cache
```

Usually implemented with:

- Ehcache
- Infinispan

---

# 13. N+1 Query Problem

Suppose:

```java
List<Department> departments =
repository.findAll();
```

Query 1:

```sql
SELECT * FROM department
```

Then:

```java
department.getEmployees()
```

For each department:

```sql
SELECT * FROM employee WHERE department_id=?
```

Result:

```text
1 query
+ N queries
```

Called N+1 problem.

### Solution

```java
@Query("""
SELECT d
FROM Department d
JOIN FETCH d.employees
""")
```

or EntityGraph.

---

# 14. Custom Queries

### JPQL

```java
@Query("""
SELECT e
FROM Employee e
WHERE e.name=:name
""")
```

---

### DTO Projection

```java
@Query("""
SELECT new com.demo.EmployeeDto(
e.id,
e.name
)
FROM Employee e
""")
```

---

### Update Query

```java
@Modifying
@Transactional
@Query("""
UPDATE Employee e
SET e.name=:name
WHERE e.id=:id
""")
```

---

### Delete Query

```java
@Modifying
@Transactional
@Query("""
DELETE FROM Employee e
WHERE e.id=:id
""")
```

---

# Most Important Interview Topics (Priority)

⭐⭐⭐⭐⭐

1. Relationships
2. Fetch Types (LAZY/EAGER)
3. Cascading
4. JPQL
5. Custom Queries
6. N+1 Problem

⭐⭐⭐⭐ 7. Persistence Context 8. Entity Lifecycle 9. mappedBy 10. orphanRemoval

⭐⭐⭐ 11. JPA vs Hibernate 12. Native Queries 13. First-Level Cache 14. Second-Level Cache
