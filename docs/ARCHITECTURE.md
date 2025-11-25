# Architecture Documentation

## System Architecture

### Layer Structure

```
┌─────────────────────────────────────┐
│         GUI Layer (Swing)           │
│  LoginFrame, Dashboard, Dialogs     │
└─────────────────────────────────────┘
                 ↓
┌─────────────────────────────────────┐
│        Service Layer                │
│  AuthenticationService              │
│  GamificationService (Threading)    │
└─────────────────────────────────────┘
                 ↓
┌─────────────────────────────────────┐
│         DAO Layer                   │
│  MemberDAO, WorkoutDAO, etc.        │
└─────────────────────────────────────┘
                 ↓
┌─────────────────────────────────────┐
│      Database Layer                 │
│  DatabaseConnection (JDBC)          │
│  MockDatabase (Demo Mode)           │
└─────────────────────────────────────┘
```

## Design Patterns

### 1. Singleton Pattern
- `DatabaseConnection`: Ensures single database connection
- `MockDatabase`: Single instance for demo data

### 2. DAO Pattern
- Separates business logic from data access
- Each entity has its own DAO class

### 3. MVC Pattern
- Models: User, Member, Trainer, Workout, etc.
- Views: GUI components (Swing)
- Controllers: Service layer

## Key Components

### Models
- **User** (Abstract): Base class for all users
- **Member**: Extends User, implements Gamifiable
- **Trainer**: Extends User
- **Workout**: Workout session data
- **Challenge**: Gamification challenges
- **Reward**: Redeemable rewards

### Interfaces
- **Gamifiable**: Points and level management
- **Achievable**: Challenge completion
- **Rewardable**: Reward redemption

### Services
- **AuthenticationService**: Login/registration
- **GamificationService**: Async operations with ExecutorService

### DAOs
- **MemberDAO**: CRUD for members
- **WorkoutDAO**: Workout logging
- **ChallengeDAO**: Challenge management
- **RewardDAO**: Reward catalog

## Threading Model

- ExecutorService with fixed thread pool (5 threads)
- Async workout processing
- Synchronized database operations
- CountDownLatch for batch operations

## Database Schema

See [database/schema.sql](../database/schema.sql) for complete schema.

## Exception Handling

Custom exceptions:
- `DatabaseException`: Database errors
- `InvalidCredentialsException`: Authentication failures
- `UserNotFoundException`: User lookup failures
- `InsufficientPointsException`: Reward redemption failures
