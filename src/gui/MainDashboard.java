package gui;

import models.User;
import models.Member;

import javax.swing.*;
import java.awt.*;

public class MainDashboard extends JFrame {
    private User currentUser;
    private JLabel welcomeLabel;
    private JLabel levelLabel;
    private JLabel pointsLabel;
    private JButton workoutButton;
    private JButton challengesButton;
    private JButton rewardsButton;
    private JButton leaderboardButton;
    
    public MainDashboard(User user) {
        this.currentUser = user;
        
        setTitle("Gym Dashboard - " + user.getUsername());
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Top Panel - User Info
        JPanel topPanel = new JPanel(new GridLayout(3, 1));
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        welcomeLabel = new JLabel("Welcome, " + currentUser.getUsername() + "!");
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        
        levelLabel = new JLabel("Level: " + currentUser.getLevel());
        levelLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        
        pointsLabel = new JLabel("Points: " + currentUser.getPoints());
        pointsLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        
        topPanel.add(welcomeLabel);
        topPanel.add(levelLabel);
        topPanel.add(pointsLabel);
        
        // Center Panel - Action Buttons
        JPanel centerPanel = new JPanel(new GridLayout(2, 2, 20, 20));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        workoutButton = createStyledButton("Log Workout", new Color(52, 152, 219));
        challengesButton = createStyledButton("View Challenges", new Color(46, 204, 113));
        rewardsButton = createStyledButton("Redeem Rewards", new Color(241, 196, 15));
        leaderboardButton = createStyledButton("Leaderboard", new Color(155, 89, 182));
        
        workoutButton.addActionListener(e -> openWorkoutDialog());
        challengesButton.addActionListener(e -> openChallengesDialog());
        rewardsButton.addActionListener(e -> openRewardsDialog());
        leaderboardButton.addActionListener(e -> openLeaderboardDialog());
        
        centerPanel.add(workoutButton);
        centerPanel.add(challengesButton);
        centerPanel.add(rewardsButton);
        centerPanel.add(leaderboardButton);
        
        add(topPanel, BorderLayout.NORTH);
        add(centerPanel, BorderLayout.CENTER);
    }
    
    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(200, 100));
        return button;
    }
    
    private void openWorkoutDialog() {
        if (currentUser instanceof Member) {
            new WorkoutDialog(this, (Member) currentUser).setVisible(true);
        }
    }
    
    private void openChallengesDialog() {
        if (currentUser instanceof Member) {
            new ChallengesDialog(this, (Member) currentUser).setVisible(true);
        }
    }
    
    private void openRewardsDialog() {
        if (currentUser instanceof Member) {
            new RewardsDialog(this, (Member) currentUser).setVisible(true);
        }
    }
    
    private void openLeaderboardDialog() {
        new LeaderboardDialog(this).setVisible(true);
    }
    
    public void refreshUserInfo() {
        levelLabel.setText("Level: " + currentUser.getLevel());
        pointsLabel.setText("Points: " + currentUser.getPoints());
    }
}
