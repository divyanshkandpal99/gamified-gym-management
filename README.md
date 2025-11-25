# 🏋️ Gamified Gym Management System

[![Java](https://img.shields.io/badge/Java-17+-orange.svg)](https://www.oracle.com/java/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](CONTRIBUTING.md)

A comprehensive Java-based gym management system with gamification features including points, levels, challenges, and rewards to make fitness more engaging and motivating.

![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/MySQL-005C84?style=for-the-badge&logo=mysql&logoColor=white)
![Swing](https://img.shields.io/badge/Swing-GUI-red?style=for-the-badge)

## Features

### Core Functionality
- **Member Management**: Registration, login, profile management
- **Trainer Management**: Trainer profiles and specializations
- **Workout Tracking**: Log workouts with duration and calories burned
- **Gamification System**: Points, levels, challenges, and rewards
- **Leaderboard**: Competitive ranking system

### Technical Implementation

#### OOP Concepts (10 marks)
- **Inheritance**: `User` base class extended by `Member` and `Trainer`
- **Polymorphism**: Abstract methods `getUserType()` and `displayDashboard()` overridden in subclasses
- **Interfaces**: `Gamifiable`, `Achievable`, `Rewardable` interfaces implemented
- **Exception Handling**: Custom exceptions (`DatabaseException`, `InvalidCredentialsException`, `UserNotFoundException`, `InsufficientPointsException`)

#### Collections & Generics (6 marks)
- `List<Member>`, `List<Trainer>`, `List<Workout>`, `List<Challenge>`, `List<Reward>`
- Generic DAO pattern for database operations
- `ArrayList<>` for dynamic data storage

#### Multithreading & Synchronization (4 marks)
- `GamificationService` with `ExecutorService` for async operations
- `processWorkoutAsync()` - asynchronous workout processing
- `calculateLeaderboardAsync()` - threaded leaderboard calculation
- `synchronized` methods for thread-safe database operations
- `CountDownLatch` for coordinating multiple threads

#### Database Operations (7 marks)
- **MemberDAO**: CRUD operations for members
- **TrainerDAO**: CRUD operations for trainers
- **WorkoutDAO**: Workout logging and retrieval
- **ChallengeDAO**: Challenge management
- **RewardDAO**: Reward catalog management

#### JDBC Connectivity (3 marks)
- `DatabaseConnection` class with Singleton pattern
- MySQL JDBC driver integration
- Connection pooling and management
- PreparedStatement for SQL injection prevention

## Project Structure

```
src/
├── models/          # User, Member, Trainer, Workout, Challenge, Reward
├── interfaces/      # Gamifiable, Achievable, Rewardable
├── dao/             # MemberDAO, TrainerDAO, WorkoutDAO, ChallengeDAO, RewardDAO
├── database/        # DatabaseConnection (JDBC)
├── services/        # GamificationService, AuthenticationService
├── exceptions/      # Custom exception classes
├── gui/             # Swing GUI components
└── Main.java        # Application entry point

database/
└── schema.sql       # Database schema and sample data
```

