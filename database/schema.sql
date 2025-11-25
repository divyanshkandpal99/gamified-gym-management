-- Gamified Gym Management System Database Schema

CREATE DATABASE IF NOT EXISTS gym_management;
USE gym_management;

-- Members Table
CREATE TABLE IF NOT EXISTS members (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    membership_type VARCHAR(20) NOT NULL,
    level INT DEFAULT 1,
    points INT DEFAULT 0,
    workouts_completed INT DEFAULT 0,
    challenges_completed INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Trainers Table
CREATE TABLE IF NOT EXISTS trainers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    specialization VARCHAR(100),
    years_of_experience INT DEFAULT 0,
    rating DECIMAL(3,2) DEFAULT 0.0,
    clients_handled INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Workouts Table
CREATE TABLE IF NOT EXISTS workouts (
    id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT NOT NULL,
    workout_type VARCHAR(50) NOT NULL,
    duration INT NOT NULL,
    calories_burned INT NOT NULL,
    points_earned INT NOT NULL,
    completed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE
);

-- Challenges Table
CREATE TABLE IF NOT EXISTS challenges (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    difficulty VARCHAR(20) NOT NULL,
    target_value INT NOT NULL,
    reward_points INT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    is_active BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Rewards Table
CREATE TABLE IF NOT EXISTS rewards (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    description TEXT,
    points_cost INT NOT NULL,
    category VARCHAR(50),
    is_available BOOLEAN DEFAULT TRUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Member Challenges (Junction Table)
CREATE TABLE IF NOT EXISTS member_challenges (
    id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT NOT NULL,
    challenge_id INT NOT NULL,
    progress INT DEFAULT 0,
    is_completed BOOLEAN DEFAULT FALSE,
    completed_at TIMESTAMP NULL,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (challenge_id) REFERENCES challenges(id) ON DELETE CASCADE
);

-- Member Rewards (Redemption History)
CREATE TABLE IF NOT EXISTS member_rewards (
    id INT PRIMARY KEY AUTO_INCREMENT,
    member_id INT NOT NULL,
    reward_id INT NOT NULL,
    redeemed_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (member_id) REFERENCES members(id) ON DELETE CASCADE,
    FOREIGN KEY (reward_id) REFERENCES rewards(id) ON DELETE CASCADE
);

-- Insert Sample Data

-- Sample Members
INSERT INTO members (username, email, password, membership_type, level, points, workouts_completed, challenges_completed) VALUES
('john_doe', 'john@example.com', 'password123', 'YEARLY', 5, 450, 23, 3),
('jane_smith', 'jane@example.com', 'password123', 'MONTHLY', 3, 280, 14, 2),
('mike_wilson', 'mike@example.com', 'password123', 'QUARTERLY', 7, 680, 34, 5);

-- Sample Trainers
INSERT INTO trainers (username, email, password, specialization, years_of_experience, rating, clients_handled) VALUES
('trainer_alex', 'alex@gym.com', 'trainer123', 'Strength Training', 5, 4.8, 45),
('trainer_sarah', 'sarah@gym.com', 'trainer123', 'Yoga & Flexibility', 3, 4.9, 38);

-- Sample Challenges
INSERT INTO challenges (name, description, difficulty, target_value, reward_points, start_date, end_date, is_active) VALUES
('30-Day Cardio Challenge', 'Complete 30 cardio workouts in 30 days', 'HARD', 30, 500, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), TRUE),
('Beginner Strength', 'Complete 10 strength training sessions', 'EASY', 10, 150, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), TRUE),
('Calorie Burner', 'Burn 5000 calories total', 'MEDIUM', 5000, 300, CURDATE(), DATE_ADD(CURDATE(), INTERVAL 30 DAY), TRUE);

-- Sample Rewards
INSERT INTO rewards (name, description, points_cost, category, is_available) VALUES
('Free Protein Shake', 'One free protein shake from the gym bar', 50, 'FOOD', TRUE),
('Personal Training Session', 'One free 1-hour personal training session', 200, 'SERVICE', TRUE),
('Gym Merchandise', 'Free gym t-shirt or water bottle', 150, 'MERCHANDISE', TRUE),
('Premium Locker', '1 month premium locker access', 300, 'SERVICE', TRUE),
('Massage Session', '30-minute massage therapy session', 400, 'SERVICE', TRUE);
