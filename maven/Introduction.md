If you're preparing for Java/Spring interviews, Maven is one of the most important topics. Here's a structured, interview-focused guide.

# 1. Introduction to Maven

### What is Maven?

**Maven** is an open-source **Build Automation and Dependency Management Tool** developed by the Apache Software Foundation.

The word **Maven** comes from Yiddish and means **"Accumulator of Knowledge"**.

### Why Maven?

Before Maven:

- Developers manually downloaded JAR files.
- Dependency conflicts were common.
- Build process was complicated.

Maven automates everything.

### Maven Responsibilities

✔ Standard Project Structure

✔ Dependency Management

✔ Compilation

✔ Testing

✔ Packaging

✔ Deployment

✔ Documentation

✔ Reporting

✔ Plugin Management

---

# 2. Maven Architecture

```text
Developer
    |
    v
 pom.xml
    |
    v
 Maven
    |
    +---- Local Repository
    |
    +---- Central Repository
    |
    +---- Remote Repository
```

---

# 3. POM File (Project Object Model)

The heart of Maven is:

```text
pom.xml
```

POM = Project Object Model

It contains:

- Project Information
- Dependencies
- Plugins
- Build Configurations
- Packaging Details

### Sample POM

```xml
<project>

    <modelVersion>4.0.0</modelVersion>

    <groupId>com.example</groupId>

    <artifactId>student-app</artifactId>

    <version>1.0</version>

</project>
```

---

# 4. Important POM Tags

## modelVersion

```xml
<modelVersion>4.0.0</modelVersion>
```

Maven model version.

---

## groupId

```xml
<groupId>com.company</groupId>
```

Represents organization/project group.

Example:

```text
com.google
com.amazon
com.zoho
```

---

## artifactId

```xml
<artifactId>student-app</artifactId>
```

Project name.

---

## version

```xml
<version>1.0</version>
```

Project version.

---

## packaging

```xml
<packaging>jar</packaging>
```

Possible values:

```text
jar
war
ear
pom
```

---

# 5. Maven Repositories

## Central Repository

Default repository.

```text
https://repo.maven.apache.org/
```

Contains millions of JARs.

---

## Local Repository

Stored on your machine.

Windows:

```text
C:\Users\Username\.m2\repository
```

Linux/Mac:

```text
~/.m2/repository
```

---

## Remote Repository

Organization-specific repository.

Examples:

- Nexus
- Artifactory

---

# 6. Maven Directory Structure

```text
Project
│
├── src
│   ├── main
│   │   ├── java
│   │   ├── resources
│   │   └── webapp
│
│   └── test
│       ├── java
│       └── resources
│
├── pom.xml
│
└── target
```
