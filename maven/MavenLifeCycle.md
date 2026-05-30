The Maven build lifecycle is a well-defined sequence of phases used to build and manage a Java project in Apache Maven.

## Maven has 3 built-in lifecycles

1. **default** → handles project deployment/building
2. **clean** → removes generated files
3. **site** → generates project documentation

---

# 1. Default Lifecycle (Most Important)

This is the main lifecycle used during development.

## Common Phases

| Phase    | Description                                  |
| -------- | -------------------------------------------- |
| validate | Checks if project is correct                 |
| compile  | Compiles source code                         |
| test     | Runs unit tests                              |
| package  | Creates JAR/WAR file                         |
| verify   | Runs quality checks                          |
| install  | Installs package into local Maven repository |
| deploy   | Copies package to remote repository          |

---

## Flow Diagram

```text
validate
   ↓
compile
   ↓
test
   ↓
package
   ↓
verify
   ↓
install
   ↓
deploy
```

---

## Important Concept

When you execute a phase, Maven executes **all previous phases automatically**.

Example:

```bash
mvn package
```

Runs:

```text
validate → compile → test → package
```

Example:

```bash
mvn install
```

Runs:

```text
validate → compile → test → package → install
```

---

# 2. Clean Lifecycle

Used to remove generated files.

| Phase      | Description              |
| ---------- | ------------------------ |
| pre-clean  | Tasks before cleaning    |
| clean      | Deletes target directory |
| post-clean | Tasks after cleaning     |

Example:

```bash
mvn clean
```

Deletes:

```text
target/
```

---

# 3. Site Lifecycle

Used for generating project documentation.

| Phase  | Description                  |
| ------ | ---------------------------- |
| site   | Generates site documentation |
| deploy | Publishes documentation      |

Example:

```bash
mvn site
```

---

# Most Frequently Used Commands

| Command       | Purpose               |
| ------------- | --------------------- |
| `mvn compile` | Compile code          |
| `mvn test`    | Run tests             |
| `mvn package` | Create JAR/WAR        |
| `mvn clean`   | Remove target folder  |
| `mvn install` | Install to local repo |
| `mvn deploy`  | Deploy to remote repo |

---

# Example

Suppose you have a Maven Java project:

```text
MyProject/
 ├── src/
 ├── pom.xml
```

Run:

```bash
mvn package
```

Maven will:

1. Validate project
2. Compile Java files
3. Run tests
4. Generate `.jar` file inside `target/`

---

# Interview Point

### Difference between `install` and `deploy`

| install                                   | deploy                                |
| ----------------------------------------- | ------------------------------------- |
| Copies artifact to local `.m2` repository | Uploads artifact to remote repository |
| Used for local development                | Used for team sharing/CI-CD           |

---

# Maven Lifecycle vs Phase vs Goal

| Term      | Meaning                          |
| --------- | -------------------------------- |
| Lifecycle | Complete build process           |
| Phase     | Step in lifecycle                |
| Goal      | Specific task executed by plugin |

Example:

```bash
mvn clean install
```

- `clean` → lifecycle phase
- `install` → lifecycle phase
- Compiler/Test/JAR creation are plugin goals

---

# Key Folder

Generated output goes into:

```text
target/
```

Local repository location:

```text
~/.m2/repository
```

(on Windows)

```text
C:\Users\<user>\.m2\repository
```
