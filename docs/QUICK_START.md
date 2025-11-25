# Quick Start Guide

## 🚀 Run in 3 Steps

### 1. Clone the Repository
```bash
git clone https://github.com/yourusername/gamified-gym-management.git
cd gamified-gym-management
```

### 2. Compile
```bash
mkdir bin
javac -d bin -encoding UTF-8 src/**/*.java src/*.java
```

### 3. Run
```bash
java -cp bin Main
```

## 🎮 Demo Login

**Username:** `john_doe`  
**Password:** `password123`  
**User Type:** MEMBER

## ✨ Try These Features

1. **Log a Workout** - Earn points instantly
2. **View Challenges** - See available challenges
3. **Check Leaderboard** - Compare with others
4. **Redeem Rewards** - Spend your points

## 📝 Register New Account

Click "Register" button and create your own account!

## 🔧 Using MySQL (Optional)

1. Install MySQL
2. Run `database/schema.sql`
3. Edit `src/database/DatabaseConnection.java`
4. Set `USE_MOCK = false`
5. Add MySQL JDBC driver to `lib/`

## 📚 More Documentation

- [Full Setup Guide](../SETUP_GUIDE.md)
- [Architecture](ARCHITECTURE.md)
- [API Documentation](API.md)
- [Rubric Compliance](RUBRIC_COMPLIANCE.md)
