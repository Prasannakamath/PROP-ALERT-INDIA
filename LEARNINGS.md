# Prop-Alert India: Technical Learnings

This document catalogs advanced topics, design patterns, and framework features encountered during the project to serve as a personal study guide for achieving 5-year senior developer level expertise.

## Table of Contents
- [1. Software Architecture](#1-software-architecture)
- [2. Software Engineering Methodology](#2-software-engineering-methodology)
- [3. Advanced Testing Strategies](#3-advanced-testing-strategies)
- [4. API & Data Integrity](#4-api--data-integrity)

---

### 1. Software Architecture
- **Hexagonal Architecture (Ports and Adapters)**: Designing the `Domain` to have zero external dependencies, forcing the `Infrastructure` and `Application` layers to adapt inward instead.
- **Domain-Driven Design (DDD) Boundaries**: Architecting the system so that Root Tenants (`Brokers`) dictate the boundaries of downstream Activities (`Leads`, `Tenants`).

### 2. Software Engineering Methodology
- **Vertical Slicing (Feature-Driven Development)**: The architectural practice of building a system one full feature at a time (from API down to Database) rather than building all databases, then all services, then all APIs. This ensures rapid feedback and complete end-to-end understanding of how layers connect.
- **Strategic Deconstruction**: Transitioning from a non-CS background directly to Senior level by avoiding "vibe coding" (blind copy-pasting API logic) in favor of understanding the core 'Why' behind data flows, isolation borders, and module decoupling.

### 3. Advanced Testing Strategies
- **Pure Unit Testing**: Avoiding `@SpringBootTest` for pure business logic to ensure blazing fast test times.
- **Testcontainers & PostgreSQL**: Utilizing Docker from inside the Java test lifecycle to replicate production environments locally, circumventing the flaws of in-memory H2 databases.
- **Test State Pollution Handling**: Using `@AfterEach` to truncate tables instead of relying on `@Transactional`, removing the risk of masking ORM lazy-loading bugs.

### 4. API & Data Integrity
- **RFC 7807 (Problem Details)**: Universal networking standard implemented natively in Spring Boot via `ProblemDetail` for standardized error outputs.
- **Deterministic Identifiers**: Generating composite database keys instead of raw UUIDs for precise lookup logic.
- **Spring Data JPA Save Semantics**: Understanding how `.save()` triggers a `SELECT` -> `UPDATE` cycle on custom IDs, dictating manual `.existsById()` checks.
