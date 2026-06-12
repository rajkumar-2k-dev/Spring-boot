# Repository in Spring Data JPA

A **Repository** is the layer that communicates with the database.

```text
Controller
    ↓
Service
    ↓
Repository
    ↓
Database
```

---

## Repository Interface

```java
@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}
```

- `Employee` → Entity class
- `Long` → Primary key type

---

## What Methods Do We Get?

By extending `JpaRepository`, Spring provides methods automatically.

```java
save()
findById()
findAll()
deleteById()
existsById()
count()
saveAll()
delete()
```

Example:

```java
Employee emp = new Employee();

repository.save(emp);

List<Employee> employees =
        repository.findAll();
```

---

# Repository Hierarchy

```text
Repository
    ↑
CrudRepository
    ↑
PagingAndSortingRepository
    ↑
JpaRepository
```

---

## 1. Repository

Marker interface.

```java
public interface EmployeeRepository
        extends Repository<Employee, Long> {
}
```

Rarely used directly.

---

## 2. CrudRepository

Provides basic CRUD operations.

```java
public interface EmployeeRepository
        extends CrudRepository<Employee, Long> {
}
```

Methods:

```java
save()
findById()
findAll()
deleteById()
```

---

## 3. PagingAndSortingRepository

Adds pagination and sorting.

```java
findAll(Pageable pageable)

findAll(Sort sort)
```

---

## 4. JpaRepository

Most commonly used.

```java
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}
```

Additional methods:

```java
flush()

saveAndFlush()

deleteAllInBatch()

getReferenceById()
```

---

# Query Methods

Spring generates queries automatically.

```java
List<Employee> findByName(String name);

List<Employee> findByNameAndEmail(
        String name,
        String email);

boolean existsByEmail(String email);

long countByName(String name);
```

No implementation required.

---

# Custom Query

```java
@Query("""
SELECT e
FROM Employee e
WHERE e.name = :name
""")
List<Employee> findEmployee(
        @Param("name") String name);
```

---

# Native Query

```java
@Query(
 value = "SELECT * FROM employee WHERE name=:name",
 nativeQuery = true
)
List<Employee> findNative(
        String name);
```

---

# Pagination

```java
Page<Employee> findAll(
        Pageable pageable);
```

Usage:

```java
Page<Employee> page =
repository.findAll(
    PageRequest.of(0, 10)
);
```

---

# Sorting

```java
List<Employee> employees =
repository.findAll(
    Sort.by("name")
);
```

Descending:

```java
Sort.by(
    Sort.Direction.DESC,
    "salary"
)
```

---

# Repository vs Service

### Repository

```java
repository.save(employee);
```

Only database operations.

---

### Service

```java
@Transactional
public void transferMoney() {
    ...
}
```

Contains business logic.

---

# Common Interview Questions

### Why use Repository?

To separate database logic from business logic.

---

### Difference between CrudRepository and JpaRepository?

| CrudRepository  | JpaRepository       |
| --------------- | ------------------- |
| Basic CRUD      | CRUD + JPA features |
| Limited methods | More methods        |
| Less used       | Most used           |

---

### What class implements JpaRepository?

Internally, Spring Data JPA uses:

```java
org.springframework.data.jpa.repository.support.SimpleJpaRepository
```

Spring creates a proxy implementation at runtime.

---

### Most Common Repository Declaration

```java
@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}
```

This is the repository structure you'll see in most Spring Boot projects.

---

Yes. The complete hierarchy is:

```text
Repository
    ↑
CrudRepository
    ↑
ListCrudRepository   (Spring Data 3+)
    ↑
ListPagingAndSortingRepository
    ↑
JpaRepository
```

Or simplified (commonly explained in interviews):

```text
Repository
    ↑
CrudRepository
    ↑
PagingAndSortingRepository
    ↑
JpaRepository
```

### Purpose of Each

#### 1. Repository

Marker interface.

```java
public interface EmployeeRepository
        extends Repository<Employee, Long> {
}
```

No methods provided.

---

#### 2. CrudRepository

Basic CRUD operations.

```java
save()
findById()
findAll()
deleteById()
count()
existsById()
```

---

#### 3. PagingAndSortingRepository

Adds pagination and sorting.

```java
findAll(Pageable pageable)

findAll(Sort sort)
```

---

#### 4. JpaRepository

Adds JPA-specific features.

```java
saveAndFlush()

flush()

deleteAllInBatch()

getReferenceById()
```

---

### What We Actually Use

```java
@Repository
public interface EmployeeRepository
        extends JpaRepository<Employee, Long> {
}
```

Since `JpaRepository` already inherits all parent interfaces, you automatically get:

- Repository capabilities
- CRUD operations
- Pagination
- Sorting
- JPA-specific operations

### Interview Answer

**Q: What is the hierarchy of JpaRepository?**

```text
Repository
    ↑
CrudRepository
    ↑
PagingAndSortingRepository
    ↑
JpaRepository
```

And in modern Spring Data versions, `ListCrudRepository` and `ListPagingAndSortingRepository` exist in between, but the above hierarchy is the standard interview answer.
