# Query Methods in Spring Data JPA

**Query Methods (Derived Queries)** allow Spring Data JPA to generate SQL automatically based on method names.

No `@Query` is needed.

---

## Example Entity

```java
@Entity
public class Employee {

    @Id
    private Long id;

    private String name;
    private String email;
    private Double salary;
}
```

---

## 1. Find By Field

```java
List<Employee> findByName(String name);
```

Generated SQL:

```sql
SELECT * FROM employee WHERE name = ?
```

---

## 2. Find By Multiple Fields

```java
List<Employee> findByNameAndEmail(
        String name,
        String email);
```

SQL:

```sql
SELECT *
FROM employee
WHERE name = ?
AND email = ?
```

---

## 3. OR Condition

```java
List<Employee> findByNameOrEmail(
        String name,
        String email);
```

---

## 4. Greater Than

```java
List<Employee> findBySalaryGreaterThan(
        Double salary);
```

SQL:

```sql
SELECT *
FROM employee
WHERE salary > ?
```

---

## 5. Less Than

```java
List<Employee> findBySalaryLessThan(
        Double salary);
```

---

## 6. Between

```java
List<Employee> findBySalaryBetween(
        Double min,
        Double max);
```

---

## 7. Like / Contains

```java
List<Employee> findByNameContaining(
        String keyword);
```

Example:

```java
findByNameContaining("Raj")
```

Matches:

- Raj
- Raj Kumar
- Rajesh

---

## 8. Starts With

```java
List<Employee> findByNameStartingWith(
        String prefix);
```

Example:

```java
findByNameStartingWith("Ra")
```

---

## 9. Ends With

```java
List<Employee> findByNameEndingWith(
        String suffix);
```

---

## 10. Ignore Case

```java
List<Employee> findByNameIgnoreCase(
        String name);
```

Matches:

- raj
- Raj
- RAJ

---

## 11. Order By

```java
List<Employee>
findByNameOrderBySalaryDesc(
        String name);
```

---

## 12. Find First Record

```java
Employee findFirstByOrderBySalaryDesc();
```

Highest salary employee.

---

## 13. Top N Records

```java
List<Employee> findTop5ByOrderBySalaryDesc();
```

---

## 14. Check Existence

```java
boolean existsByEmail(
        String email);
```

---

## 15. Count Records

```java
long countByName(
        String name);
```

---

## 16. Not Equal

```java
List<Employee> findByNameNot(
        String name);
```

---

## 17. IN Clause

```java
List<Employee> findByNameIn(
        List<String> names);
```

---

## 18. NULL Check

```java
List<Employee> findByEmailIsNull();
```

```java
List<Employee> findByEmailIsNotNull();
```

---

## 19. Boolean Fields

```java
List<User> findByActiveTrue();

List<User> findByActiveFalse();
```

---

## 20. Pagination

```java
Page<Employee> findByName(
        String name,
        Pageable pageable);
```

Usage:

```java
Page<Employee> page =
repository.findByName(
    "Raj",
    PageRequest.of(0, 10)
);
```

---

# Commonly Asked Interview Query Methods

```java
findByName()

findByNameAndEmail()

findBySalaryGreaterThan()

findBySalaryBetween()

findByNameContaining()

findByNameIgnoreCase()

existsByEmail()

countByName()

findTop5ByOrderBySalaryDesc()

findByEmailIsNull()
```

### When to use Query Methods vs `@Query`

✅ Use **Query Methods** for simple conditions:

```java
findByNameAndEmail()
```

✅ Use **`@Query`** for:

- Joins
- Complex conditions
- Aggregations
- DTO projections
- Update/Delete queries

For interviews, expect questions like:

> "How does `findByNameAndEmail()` work internally?"

Answer: Spring Data JPA parses the method name, creates a query from it, and executes it through Hibernate automatically.
