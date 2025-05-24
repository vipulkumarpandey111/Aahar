# 🥗 AAHAR – Intelligent Diet Planner for India

🥗 AAHAR – Region-Aware, Budget-Friendly Diet Planner for India
AAHAR is an intelligent, backend-powered diet planning system designed specifically for the diverse and culturally rich Indian population. The app helps users transition from unhealthy food habits to healthier, regionally suitable diets — all while keeping in mind their budget, local food availability, and traditional preferences.

Unlike generic global diet apps, AAHAR understands that Indian dietary patterns are deeply rooted in geography, religion, and affordability. From rice-based diets in the South to wheat-heavy meals in the North, AAHAR ensures users receive personalized recommendations that are realistic, sustainable, and culturally comfortable.

This project also serves as a backend system design capstone for developers looking to master real-world concepts like:

Multi-layered architecture

Java Spring Boot ecosystem

Relational database design

MVC pattern, APIs, and DTOs

Clean code and REST principles

It begins as a monolithic architecture to ensure clarity and learning, and is structured in a way that allows seamless future evolution into a scalable microservices-based platform.



> This is a backend-heavy learning project focused on Spring Boot, system design patterns, and real-world HLD/LLD concepts.

---

## 🚀 Features (Phase 1 - MVP)
- User profile creation with:
    - Name
    - Region (state/area)
    - Food preference
- Health check endpoint
- PostgreSQL database integration
- Spring Boot backend setup
- REST API for creating user profiles

---

## 🛠️ Tech Stack

| Layer        | Tech/Tool                            |
|--------------|--------------------------------------|
| Language     | Java 17+                             |
| Framework    | Spring Boot                          |
| Build Tool   | Maven                                |
| Database     | PostgreSQL (via DBeaver)             |
| ORM          | Spring Data JPA + Hibernate          |
| Testing      | Postman / curl                       |
| Version Ctrl | Git + GitHub                         |
| IDE          | IntelliJ IDEA (recommended)          |

---

## 📦 Project Structure

Project Root/
├── .git/
├── .idea/
├── README.md
└── Aahar/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── org.example/
│   │   │       ├── ai/
│   │   │       │   ├── HFMealPlanGenerator
│   │   │       │   └── MealPlanGenerator
│   │   │       ├── config/
│   │   │       │   ├── AiConfig
│   │   │       │   └── RedisConfig
│   │   │       ├── controller/
│   │   │       │   ├── csvDataIngestion/
│   │   │       │   ├── HealthController
│   │   │       │   ├── UserMealPlanController
│   │   │       │   └── UserProfileController
│   │   │       ├── dto/
│   │   │       │   ├── MealPlanRequestDTO
│   │   │       │   ├── MealPlanResponseDTO
│   │   │       │   └── UserProfileRequestDTO
│   │   │       ├── entity/
│   │   │       │   ├── MealPlan
│   │   │       │   ├── UserMealPlan
│   │   │       │   └── UserProfile
│   │   │       ├── mapper/
│   │   │       │   └── UserMealPlanResponseMapper
│   │   │       ├── repository/
│   │   │       │   ├── MealPlanRepository
│   │   │       │   ├── UserMealPlanRepository
│   │   │       │   └── UserProfileRepository
│   │   │       ├── service/
│   │   │       │   ├── UserMealPlanService
│   │   │       │   ├── UserProfileService
│   │   │       │   └── UserProfileServiceImpl
│   │   │       └── AaharApplication
│   │   └── resources/
│   │       └── application.yml
│   └── test/
├── target/
├── .idea/
├── pom.xml
└── .gitignore
