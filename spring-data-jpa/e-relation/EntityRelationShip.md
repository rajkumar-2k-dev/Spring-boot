Below is the ER/Class Diagram in **Markdown (Mermaid ER Diagram)** format based on the image:

````md
```mermaid
erDiagram

    COURSE {
        Long id PK
        String title
        String description
    }

    AUTHOR {
        Long id PK
        String first_name
        String last_name
        String email UK
    }

    SECTION {
        Long id PK
        String name
        Integer order
    }

    LECTURE {
        Long id PK
        String name
    }

    RESOURCE {
        Long id PK
        String name
        Long size
        String url
    }

    VIDEO {
        Integer length
    }

    FILE {
        String type
    }

    TEXT {
        String content
    }

```
````

### Spring JPA Mapping

- `Course   ↔  Author` → `@ManyToMany`
- `Course   →  Section` → `@OneToMany`
- `Section  →  Lecture` → `@OneToMany`
- `Lecture  →  Resource` → `@OneToOne`
- `Video`, `File`, `Text` inherit from `Resource` → JPA Inheritance (`@Inheritance(strategy = InheritanceType.JOINED)` recommended)

This diagram represents a typical E-Learning platform structure:

- A course can have multiple authors.
- A course contains multiple sections.
- A section contains multiple lectures.
- A lecture has one resource.
- Resource can be a Video, File, or Text.
