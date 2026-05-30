**Maven Dependencies** are external libraries (JARs) that your project needs to compile, test, or run. Maven automatically downloads them from repositories and manages their versions.

---

## What is a Dependency?

Instead of manually downloading JAR files and adding them to your project, you declare them in `pom.xml`:

```xml
<dependencies>
    <dependency>
        <groupId>org.springframework</groupId>
        <artifactId>spring-context</artifactId>
        <version>6.1.0</version>
    </dependency>
</dependencies>
```

### Dependency Coordinates

Each dependency is identified by:

| Element      | Description                  |
| ------------ | ---------------------------- |
| `groupId`    | Organization or project name |
| `artifactId` | Library name                 |
| `version`    | Library version              |

Example:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
</dependency>
```

---

## Dependency Scopes

Scopes determine when a dependency is available.

| Scope               | Compile | Test | Runtime | Packaged |
| ------------------- | ------- | ---- | ------- | -------- |
| compile (default)   | ✓       | ✓    | ✓       | ✓        |
| provided            | ✓       | ✓    | ✗       | ✗        |
| runtime             | ✗       | ✓    | ✓       | ✓        |
| test                | ✗       | ✓    | ✗       | ✗        |
| system (deprecated) | ✓       | ✓    | ✓       | ✓        |

### Compile Scope

Default scope.

```xml
<dependency>
    <groupId>org.apache.commons</groupId>
    <artifactId>commons-lang3</artifactId>
    <version>3.14.0</version>
</dependency>
```

Available everywhere.

---

### Provided Scope

Dependency is supplied by the runtime environment.

Example: Servlet API in a web server.

```xml
<dependency>
    <groupId>jakarta.servlet</groupId>
    <artifactId>jakarta.servlet-api</artifactId>
    <version>6.0.0</version>
    <scope>provided</scope>
</dependency>
```

---

### Runtime Scope

Needed only when the application runs.

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.3.0</version>
    <scope>runtime</scope>
</dependency>
```

---

### Test Scope

Used only during testing.

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.10.0</version>
    <scope>test</scope>
</dependency>
```

---

## Transitive Dependencies

When you add a dependency, Maven automatically downloads its required dependencies.

Example:

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>6.1.0</version>
</dependency>
```

Maven also downloads supporting libraries required by Spring.

This is called **transitive dependency management**.

---

## Excluding Transitive Dependencies

Sometimes you don't want a transitive dependency.

```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-web</artifactId>
    <version>6.1.0</version>

    <exclusions>
        <exclusion>
            <groupId>commons-logging</groupId>
            <artifactId>commons-logging</artifactId>
        </exclusion>
    </exclusions>
</dependency>
```

---

## Dependency Management

Used mainly in multi-module projects to define versions in one place.

```xml
<dependencyManagement>
    <dependencies>
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter</artifactId>
            <version>5.10.0</version>
        </dependency>
    </dependencies>
</dependencyManagement>
```

Child modules can then omit the version:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
</dependency>
```

---

## Useful Maven Commands

### Show dependency tree

```bash
mvn dependency:tree
```

Example output:

```text
my-project
+- spring-web
|  +- spring-core
|  \- spring-beans
\- junit-jupiter
```

### List dependencies

```bash
mvn dependency:list
```

### Analyze unused dependencies

```bash
mvn dependency:analyze
```

---

## Best Practices

- Use the latest stable versions when appropriate.
- Prefer `dependencyManagement` for large projects.
- Keep test libraries in `test` scope.
- Avoid unnecessary dependencies.
- Review transitive dependencies regularly using:

```bash
mvn dependency:tree
```

### Example Complete `pom.xml`

```xml
<dependencies>

    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>

    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>

    <dependency>
        <groupId>org.junit.jupiter</groupId>
        <artifactId>junit-jupiter</artifactId>
        <scope>test</scope>
    </dependency>

</dependencies>
```

This setup provides:

- Web application support
- MySQL database connectivity
- JUnit testing support
- Automatic transitive dependency resolution by Maven.
