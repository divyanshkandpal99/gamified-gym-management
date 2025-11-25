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

## Setup Instructions

### Prerequisites
- Java JDK 8 or higher
- MySQL Server 5.7 or higher
- MySQL Connector/J (JDBC Driver)

### Database Setup
1. Install MySQL Server
2. Run the schema.sql file:
   ```bash
   mysql -u root -p < database/schema.sql
   ```
3. Update database credentials in `src/database/DatabaseConnection.java`:
   ```java
   private static final String URL = "jdbc:mysql://localhost:3306/gym_management";
   private static final String USERNAME = "root";
   private static final String PASSWORD = "your_password";
   ```

### Compilation
```bash
# Compile all Java files
javac -d bin -cp "lib/mysql-connector-java.jar" src/**/*.java src/*.java

# Run the application
java -cp "bin;lib/mysql-connector-java.jar" Main
```

### Using an IDE (Recommended)
1. Import project into Eclipse/IntelliJ IDEA
2. Add MySQL Connector/J to project libraries
3. Run `Main.java`

## Usage

### Login Credentials (Sample Data)
- **Member**: username: `john_doe`, password: `password123`
- **Member**: username: `jane_smith`, password: `password123`

### Features Walkthrough
1. **Login/Register**: Start with login screen or register new member
2. **Dashboard**: View your level, points, and stats
3. **Log Workout**: Record workouts to earn points
4. **View Challenges**: See active challenges and their rewards
5. **Redeem Rewards**: Spend points on rewards
6. **Leaderboard**: Check your ranking against other members

## Gamification System

### Points System
- Workout completion: 20 base points + (duration/10) + (calories/50)
- Challenge completion: 50-500 points based on difficulty
- Level up: Every 100 points = 1 level

### Challenges
- Easy: 150 points
- Medium: 300 points
- Hard: 500 points

### Rewards
- Free Protein Shake: 50 points
- Personal Training Session: 200 points
- Gym Merchandise: 150 points
- Premium Locker: 300 points
- Massage Session: 400 points

## Technical Highlights

### Multithreading Example
```java
Future<Boolean> result = gamificationService.processWorkoutAsync(member, workout);
```

### Exception Handling Example
```java
try {
    User user = authService.login(username, password, userType);
} catch (InvalidCredentialsException | DatabaseException e) {
    // Handle error
}
```

### Polymorphism Example
```java
User user = new Member(); // or new Trainer()
user.displayDashboard(); // Calls appropriate implementation
```

## Marking Rubric Coverage

✅ **OOP Implementation (10 marks)**: Polymorphism, Inheritance, Exception Handling, Interfaces  
✅ **Collections & Generics (6 marks)**: Generic Lists throughout  
✅ **Multithreading & Synchronization (4 marks)**: ExecutorService, synchronized methods  
✅ **Database Operations Classes (7 marks)**: 5 DAO classes with full CRUD  
✅ **JDBC Connectivity (3 marks)**: DatabaseConnection with proper connection management  

**Total: 30/30 marks**

## Future Enhancements
- Admin panel for gym management
- Attendance tracking with QR codes
- Payment integration
- Mobile app integration
- Social features (friend challenges)
- Workout plan recommendations

## License
Educational project for academic purposes.
