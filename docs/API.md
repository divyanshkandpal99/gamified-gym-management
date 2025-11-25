# API Documentation

## Service Layer APIs

### AuthenticationService

#### `login(username, password, userType)`
Authenticates a user and returns User object.

**Parameters:**
- `username` (String): User's username
- `password` (String): User's password
- `userType` (String): "MEMBER" or "TRAINER"

**Returns:** `User` object

**Throws:** 
- `InvalidCredentialsException`
- `DatabaseException`

#### `registerMember(username, email, password, membershipType)`
Registers a new member.

**Parameters:**
- `username` (String): Unique username
- `email` (String): Email address
- `password` (String): Password
- `membershipType` (String): "MONTHLY", "QUARTERLY", or "YEARLY"

**Returns:** `Member` object

**Throws:** `DatabaseException`

---

### GamificationService

#### `processWorkoutAsync(member, workout)`
Processes workout asynchronously and awards points.

**Parameters:**
- `member` (Member): Member object
- `workout` (Workout): Workout data

**Returns:** `Future<Boolean>`

**Throws:** `DatabaseException`

#### `completeChallenge(member, challenge, currentProgress)`
Marks challenge as complete and awards points.

**Parameters:**
- `member` (Member): Member object
- `challenge` (Challenge): Challenge object
- `currentProgress` (int): Current progress value

**Throws:** `DatabaseException`

#### `calculateLeaderboardAsync()`
Calculates member rankings asynchronously.

**Returns:** `Future<List<Member>>`

---

## DAO Layer APIs

### MemberDAO

#### `addMember(member)`
Adds new member to database.

#### `getMemberById(id)`
Retrieves member by ID.

#### `getMemberByUsername(username)`
Retrieves member by username.

#### `getAllMembers()`
Returns all members.

#### `updateMember(member)`
Updates member data.

---

### WorkoutDAO

#### `addWorkout(workout)`
Logs a new workout.

#### `getWorkoutsByMemberId(memberId)`
Gets all workouts for a member.

---

### ChallengeDAO

#### `getActiveChallenges()`
Returns all active challenges.

---

### RewardDAO

#### `getAvailableRewards()`
Returns all available rewards.

---

## Model APIs

### Member (implements Gamifiable)

#### `earnPoints(points)`
Adds points to member account.

#### `completeChallenge(challengeName)`
Increments challenge counter and awards points.

#### `completeWorkout()`
Increments workout counter and awards points.

---

### Reward (implements Rewardable)

#### `canRedeem(userPoints)`
Checks if user has enough points.

#### `redeem(member)`
Redeems reward and deducts points.

---

### Challenge (implements Achievable)

#### `isCompleted(currentProgress)`
Checks if challenge is completed.

#### `getRewardPoints()`
Returns reward points for challenge.
