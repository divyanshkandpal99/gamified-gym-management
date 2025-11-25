# Setup Guide - Gamified Gym Management System

## Quick Start Guide

### Step 1: Install Prerequisites

#### Java Development Kit (JDK)
- Download JDK 8 or higher from [Oracle](https://www.oracle.com/java/technologies/downloads/) or [OpenJDK](https://openjdk.org/)
- Verify installation:
  ```bash
  java -version
  javac -version
  ```

#### MySQL Server
- Download from [MySQL Downloads](https://dev.mysql.com/downloads/mysql/)
- Install and remember your root password
- Verify installation:
  ```bash
  mysql --version
  ```

#### MySQL JDBC Driver
- Download from [MySQL Connector/J](https://dev.mysql.com/downloads/connector/j/)
- Extract and place the JAR file in the `lib/` folder
- Example: `lib/mysql-connector-java-8.0.33.jar`

### Step 2: Database Setup

1. **Start MySQL Server**
   ```bash
   # Windows
   net start MySQL80
   
   # Linux/Mac
   sudo systemctl start mysql
   ```

2. **Create Database**
   ```bash
   mysql -u root -p < database/schema.sql
   ```
   
   Or manually:
   ```bash
   mysql -u root -p
   ```
   Then paste the contents of `database/schema.sql`

3. **Verify Database**
   ```sql
   USE gym_management;
   SHOW TABLES;
   SELECT * FROM members;
   ```

### Step 3: Configure Database Connection

Edit `src/database/DatabaseConnection.java`:

```java
private static final String URL = "jdbc:mysql://localhost:3306/gym_management";
private static final String USERNAME = "root";
private static final String PASSWORD = "your_mysql_password";
```

### Step 4: Compile and Run

#### Using Command Line

**Windows:**
```cmd
# Compile
javac -d bin -cp "lib/mysql-connector-java-8.0.33.jar" src/**/*.java src/*.java

# Run
java -cp "bin;lib/mysql-connector-java-8.0.33.jar" Main
```

**Linux/Mac:**
```bash
# Compile
javac -d bin -cp "lib/mysql-connector-java-8.0.33.jar" src/**/*.java src/*.java

# Run
java -cp "bin:lib/mysql-connector-java-8.0.33.jar" Main
```

#### Using Eclipse

1. **Import Project**
   - File → Import → Existing Projects into Workspace
   - Select project folder

2. **Add MySQL Driver**
   - Right-click project → Build Path → Configure Build Path
   - Libraries → Add External JARs
   - Select `lib/mysql-connector-java-8.0.33.jar`

3. **Run**
   - Right-click `Main.java` → Run As → Java Application

#### Using IntelliJ IDEA

1. **Open Project**
   - File → Open → Select project folder

2. **Add MySQL Driver**
   - File → Project Structure → Libraries
   - Click + → Java → Select `lib/mysql-connector-java-8.0.33.jar`

3. **Run**
   - Right-click `Main.java` → Run 'Main.main()'

### Step 5: Test the Application

#### Login with Sample Data
- **Username:** `john_doe`
- **Password:** `password123`
- **User Type:** MEMBER

#### Or Register New Member
- Click "Register" button
- Fill in details
- Login with new credentials

## Troubleshooting

### Database Connection Failed
- Check MySQL is running: `mysql -u root -p`
- Verify credentials in `DatabaseConnection.java`
- Ensure database exists: `SHOW DATABASES;`

### ClassNotFoundException: com.mysql.cj.jdbc.Driver
- MySQL JDBC driver not in classpath
- Download and place in `lib/` folder
- Add to project build path

### Port 3306 Already in Use
- Another MySQL instance is running
- Change port in `DatabaseConnection.java` URL
- Or stop other MySQL instance

### SQLException: Access Denied
- Wrong username/password
- Update credentials in `DatabaseConnection.java`
- Grant privileges: `GRANT ALL PRIVILEGES ON gym_management.* TO 'root'@'localhost';`

## Features to Test

1. **Member Registration**
   - Register new member
   - Login with credentials

2. **Workout Logging**
   - Click "Log Workout"
   - Select workout type, duration, calories
   - Verify points earned

3. **Challenges**
   - View active challenges
   - Check reward points

4. **Rewards**
   - View available rewards
   - Redeem with points

5. **Leaderboard**
   - View member rankings
   - Check your position

## Project Structure Verification

Ensure all files exist:
```
✓ src/models/ (6 files)
✓ src/interfaces/ (3 files)
✓ src/dao/ (5 files)
✓ src/database/ (1 file)
✓ src/services/ (2 files)
✓ src/exceptions/ (4 files)
✓ src/gui/ (6 files)
✓ src/Main.java
✓ database/schema.sql
✓ lib/ (MySQL driver)
```

## Next Steps

- Customize database credentials
- Add more sample data
- Explore gamification features
- Test multithreading with multiple users
- Review code for marking rubric compliance

## Support

For issues:
1. Check error messages in console
2. Verify database connection
3. Ensure all dependencies are installed
4. Review setup steps

Good luck with your project! 🎯
