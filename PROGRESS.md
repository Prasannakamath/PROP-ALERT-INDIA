# PROP-ALERT INDIA: PROGRESS LOG

## ✅ COMPLETED TASKS
- **[Governance]**: Established rigorous GEMINI.md rules covering Hexagonal Architecture, MapStruct mapping, and advanced testing strategies.
- **[Infra]**: Bootstrapped the empty PROGRESS.md and LEARNINGS.md templates for AI state alignment.
- **[Sprint 1.0-1.1]**: Resolved local Spring profile environments and wired multi-tenancy (Broker Isolation via Hibernate dynamic filters).
- **[Sprint 1.2 - Broker Onboarding Foundation]**: Completed the "Root Tenant". Delivered Broker Domain Model, Entity (Email/Mobile constraints), Identity Service, MapStruct Mapper, and Web Controller.
- **[Sprint 1.3 - Unit Testing]**: Completed pure domain unit testing for `BrokerIdentityService` with AssertJ, validating happy paths and boundary exceptions.
- **[Sprint 1.4 - Integration Testing]**: Completed end-to-end testing with Testcontainers (PostgreSQL). Validated 201 Created (Happy Path), 409 Conflict (Duplicate Tenant), and 400 Bad Request (Bean Validation).
- **[Sprint 1.5 - Exception Handling]**: Implemented a `@RestControllerAdvice` Global Exception Handler using Spring Boot's RFC 7807 `ProblemDetail`.

## ⚠️ ARCHITECTURAL PIVOTS (MISTAKES & CORRECTIONS)
- **Pivot 1**: Corrected the architectural flow from "Leads-First" to "Broker-First." A Tenant (Broker) must exist in the database before any downstream Activity (Leads) can be recorded.
- **Pivot 2**: Shifted from generating Random UUIDs to generating Deterministic IDs (using state + city + mobile suffixes) to ensure absolute business traceability and idempotency.
- **Decision (Silent Updates)**: Explicitly added `.existsById()` checks in the Application Service before saving entities with deterministic IDs to prevent Spring Data JPA from performing silent `UPDATE`s instead of throwing constraint violations.
- **Decision (Test Isolation)**: Enforced manual database cleanup (`@AfterEach`) in Integration Tests instead of `@Transactional` to prevent test state pollution without masking lazy-initialization bugs.

## 🚧 CURRENT SPRINT TASK
**Sprint 2.0 - Leads Domain Foundation**
- **Goal**: Begin the hexagonal journey for our downstream activity: Property Leads.
- **Status**: Not started.

## 📅 NEXT CHUNK (Step-by-Step)
1. Review the `openapi.yaml` contract for the `/leads` endpoint.
2. Generate DTOs and create the pure Domain Model for `Lead`.