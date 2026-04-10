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
- **Domain-Driven Design (DDD) Boundaries**: Architecting the system so that Root Tenants (`Brokers`) dictate the boundaries of downstream Activities.

### 2. Software Engineering Methodology
- **Vertical Slicing (Feature-Driven Development)**: The architectural practice of building a system one full feature at a time (from API down to Database).
- **OpenAPI Modularization (`$ref`)**: Avoiding monolithic YAML files by injecting decoupled endpoint paths and component schemas using relative `$ref` anchors to ensure merge-conflict-free team development.

### 3. Advanced Testing Strategies
- **Pure Unit Testing**: Avoiding `@SpringBootTest` for pure business logic to ensure blazing fast test times.
- **Testcontainers & PostgreSQL**: Utilizing Docker from inside the Java test lifecycle to replicate production environments locally.

### 4. API & Data Integrity
- **ThreadLocal Context Extraction**: Securing API endpoints by dropping trust in frontend payloads (e.g. ignoring `brokerId`) and extracting identity directly from a thread-bound Security Filter (`BrokerContext`).
- **MapStruct Bypass & Immutability**: Deliberately using `@Mapping(target="id", ignore=true)` to block API payload attacks from injecting arbitrary database keys.
