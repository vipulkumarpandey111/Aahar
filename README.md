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

## Features
- 🍽️ AI-powered meal plan generation
- 👤 User profile management
- 🌍 Region-specific meal recommendations
- 🥗 Dietary preference support
- 📊 CSV data ingestion capabilities
- 💾 Redis caching for improved performance
- 🔄 RESTful API endpoints
- 🏥 Health monitoring endpoints

## Technology Stack
- **Framework:** Spring Boot
- **Language:** Java 21
- **Database:** JPA/Hibernate
- **Caching:** Redis
- **AI Integration:** Hugging Face
- **Build Tool:** Maven


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

## API Documentation

### User Profile Management
- **Create User Profile**
  - `POST /api/users/profile`
  - Creates a new user profile with dietary preferences

- **Get User Profile**
  - `GET /api/users/profile/{userId}`
  - Retrieves user profile information

### Meal Plan Management
- **Generate Meal Plan**
  - `POST /api/mealplan/generate`
  - Generates personalized meal plans based on user preferences

- **Get User Meal Plan**
  - `GET /api/mealplan/user/{userId}`
  - Retrieves existing meal plans for a user

### Health Check
- **System Health**
  - `GET /health`
  - Monitors system health and dependencies

### Data Ingestion
- **CSV Import**
  - `POST /api/data/import`
  - Imports meal data through CSV files

## Caching Strategy
- Redis cache implementation with 30-minute TTL
- Caches frequently accessed meal plans and user profiles
- Configurable cache settings in RedisConfig

## AI Integration
- Utilizes Hugging Face models for meal plan generation
- Considers:
  - Regional preferences
  - Dietary restrictions
  - Nutritional requirements
  - User preferences
