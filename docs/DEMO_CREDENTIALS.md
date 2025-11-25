# 🔐 Demo Credentials

## Test Accounts

### Member Accounts

#### Account 1 (High Level)
- **Username:** `john_doe`
- **Password:** `password123`
- **Level:** 5
- **Points:** 450
- **Workouts:** 23
- **Challenges:** 3

#### Account 2 (Medium Level)
- **Username:** `jane_smith`
- **Password:** `password123`
- **Level:** 3
- **Points:** 280
- **Workouts:** 14
- **Challenges:** 2

#### Account 3 (Expert Level)
- **Username:** `mike_wilson`
- **Password:** `password123`
- **Level:** 7
- **Points:** 680
- **Workouts:** 34
- **Challenges:** 5

### Trainer Accounts

#### Trainer 1
- **Username:** `trainer_alex`
- **Password:** `trainer123`
- **Specialization:** Strength Training
- **Experience:** 5 years
- **Rating:** 4.8/5.0

#### Trainer 2
- **Username:** `trainer_sarah`
- **Password:** `trainer123`
- **Specialization:** Yoga & Flexibility
- **Experience:** 3 years
- **Rating:** 4.9/5.0

---

## Available Challenges

1. **30-Day Cardio Challenge** (HARD)
   - Target: 30 workouts
   - Reward: 500 points

2. **Beginner Strength** (EASY)
   - Target: 10 sessions
   - Reward: 150 points

3. **Calorie Burner** (MEDIUM)
   - Target: 5000 calories
   - Reward: 300 points

---

## Rewards Catalog

| Reward | Points | Category |
|--------|--------|----------|
| Free Protein Shake | 50 | FOOD |
| Personal Training Session | 200 | SERVICE |
| Gym Merchandise | 150 | MERCHANDISE |
| Premium Locker | 300 | SERVICE |
| Massage Session | 400 | SERVICE |

---

## Quick Test Scenarios

### Scenario 1: New Member Journey
1. Click "Register"
2. Create account
3. Login
4. Log first workout
5. Check points earned

### Scenario 2: Challenge Completion
1. Login as `john_doe`
2. View challenges
3. Log workouts to progress
4. Complete challenge
5. Earn reward points

### Scenario 3: Reward Redemption
1. Login as `mike_wilson` (680 points)
2. Go to Rewards Store
3. Select "Massage Session" (400 points)
4. Redeem
5. Check updated points

### Scenario 4: Leaderboard Competition
1. Login as any member
2. View leaderboard
3. See your ranking
4. Log workouts to climb ranks

---

## Testing Multithreading

Log multiple workouts quickly to see async processing in action!

## Testing Exception Handling

- Try wrong password → `InvalidCredentialsException`
- Try non-existent user → `UserNotFoundException`
- Try redeeming without points → `InsufficientPointsException`
