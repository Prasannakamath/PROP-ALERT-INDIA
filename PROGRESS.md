# PROP-ALERT INDIA: PROGRESS LOG

## ✅ COMPLETED TASKS
- **[Governance]**: Established rigorous GEMINI.md rules covering Hexagonal Architecture, MapStruct mapping, and advanced testing strategies.
- **[Infra]**: Bootstrapped the empty PROGRESS.md and LEARNINGS.md templates for AI state alignment.
- **[Sprint 1.0-1.1]**: Resolved local Spring profile environments and wired multi-tenancy (Broker Isolation via Hibernate dynamic filters).
- **[Sprint 1.2 - Broker Onboarding Foundation]**: Completed the "Root Tenant". Delivered Broker Domain Model, Entity (Email/Mobile constraints), Identity Service, MapStruct Mapper, and Web Controller.
- **[Sprint 1.3 - Testing & Validation]**: Completed unit testing for [BrokerIdentityService](cci:2://file:///c:/DRIVE_D/Prop-Alert%20India/propalert/propalert/src/main/java/com/kamath/propalert/domain/service/BrokerIdentityService.java:6:0-30:1). Resolved `org.assertj` setup issues inside [BrokerIdentityServiceTest.java](cci:7://file:///c:/DRIVE_D/Prop-Alert%20India/propalert/propalert/src/test/java/com/kamath/propalert/domain/service/BrokerIdentityServiceTest.java:0:0-0:0). Implemented pure domain unit tests for [shouldGenerateId_HappyPath](cci:1://file:///c:/DRIVE_D/Prop-Alert%20India/propalert/propalert/src/test/java/com/kamath/propalert/domain/service/BrokerIdentityServiceTest.java:18:4-30:5), [shouldGenerateId_ShortOrgName](cci:1://file:///c:/DRIVE_D/Prop-Alert%20India/propalert/propalert/src/test/java/com/kamath/propalert/domain/service/BrokerIdentityServiceTest.java:32:4-46:5), and boundary exceptions ([shouldThrowException](cci:1://file:///c:/DRIVE_D/Prop-Alert%20India/propalert/propalert/src/test/java/com/kamath/propalert/domain/service/BrokerIdentityServiceTest.java:48:4-62:5)).

## ⚠️ ARCHITECTURAL PIVOTS (MISTAKES & CORRECTIONS)
- **Pivot 1**: Corrected the architectural flow from "Leads-First" to "Broker-First." A Tenant (Broker) must exist in the database before any downstream Activity (Leads) can be recorded.
- **Pivot 2**: Shifted from generating Random UUIDs to generating Deterministic IDs (using state + city + mobile suffixes) to ensure absolute business traceability and idempotency.
- **Decision (Failing Fast)**: Explictly implemented boundaries in the Domain service using `assertThatThrownBy` to prove invalid primitives are caught, mathematically guaranteeing database safety even if controller validation fails.

## 🚧 CURRENT SPRINT TASK
**Broker Integration Testing (Testcontainers)**
- **Goal**: Implement standard PostgreSQL integration testing for the [Broker](cci:1://file:///c:/DRIVE_D/Prop-Alert%20India/propalert/propalert/src/main/java/com/kamath/propalert/domain/service/BrokerIdentityService.java:9:4-29:5) onboarding API to validate the entire Hexagonal journey.
- **Status**: Domain Logic tested. Moving to Web & Database layers.

## 📅 NEXT CHUNK (Step-by-Step)
1. Setup a standard `@SpringBootTest` with `@Testcontainers` mapping to a PostgreSQL container image.
2. Formulate a test request hitting the Web Controller to register a complete Broker.
3. Validate the `CREATED (201)` response and assert the `Broker` Entity was successfully written to the Testcontainer database with the deterministic ID.
