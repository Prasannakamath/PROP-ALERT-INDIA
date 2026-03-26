# Prop-Alert India: Technical Learnings

This document catalogs advanced topics, design patterns, and framework features encountered during the project to serve as a personal study guide for achieving 5-year senior developer level expertise.

### 📚 Topics To Study

#### 1. Software Architecture 
- **Hexagonal Architecture (Ports and Adapters)**: Designing the `Domain` to have zero external dependencies, forcing the `Infrastructure` and `Application` layers to adapt inward instead.
- **Domain-Driven Design (DDD) Boundaries**: Architecting the system so that Root Tenants (`Brokers`) dictate the boundaries of downstream Activities (`Leads`).

#### 2. Advanced Testing Strategies
- **Test Execution**: Utilizing Maven (`mvn -Dtest=ClassName test`) to run targeted test classes and methods for extreme fast feedback rather than running the full test suite.
- **Pure Unit Testing**: Avoiding `@SpringBootTest` for pure business logic. Employing plain unit testing with Mockito to ensure blazing fast execution times and loose coupling from the framework.
- **Testcontainers**: Utilizing Docker from inside the Java unit test lifecycle to spin up actual PostgreSQL instances during the `@DataJpaTest` phase, entirely skipping the flaws of in-memory H2 databases.

#### 3. API & Error Standardization
- **RFC 7807 (Problem Details for HTTP APIs)**: A universal networking standard for machine-readable HTTP error payloads.
- **Spring Boot 3/4 ProblemDetail**: Utilizing Spring's native `ProblemDetail` object combined with a global `@RestControllerAdvice` to eliminate custom, messy exception wrappers.

#### 4. Database Design
- **Deterministic Identifiers**: The practice of avoiding random UUID generation by creating composite keys (`City-Org-MobileTail`) to improve caching logic and drastically reduce database duplicates.
