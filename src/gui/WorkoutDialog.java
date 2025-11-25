package gui;

import dao.MemberDAO;
import exceptions.DatabaseException;
import models.Member;
import models.Workout;
import services.GamificationService;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Future;

public class WorkoutDialog extends JDialog {
    private Member member;
    private JComboBox<String> workoutTypeCombo;
    private JSpinner durationSpinner;
    private JSpinner caloriesSpinner;
    private JButton submitButton;
    private GamificationService gamificationService;
    private MainDashboard parentDashboard;
    
    public WorkoutDialog(MainDashboard parent, Member member) {
        super(parent, "Log Workout", true);
        this.parentDashboard = parent;
        this.member = member;
        
        try {
            gamificationService = new GamificationService();
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Service initialization failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        setSize(400, 300);
        setLocationRelativeTo(parent);
        initComponents();
    }
    
    private void initComponents() {
        JPanel mainPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Workout Type
        gbc.gridx = 0;
        gbc.gridy = 0;
        mainPanel.add(new JLabel("Workout Type:"), gbc);
        
        gbc.gridx = 1;
        workoutTypeCombo = new JComboBox<>(new String[]{"Cardio", "Strength", "Yoga", "CrossFit", "Swimming", "Cycling"});
        mainPanel.add(workoutTypeCombo, gbc);
        
        // Duration
        gbc.gridx = 0;
        gbc.gridy = 1;
        mainPanel.add(new JLabel("Duration (minutes):"), gbc);
        
        gbc.gridx = 1;
        durationSpinner = new JSpinner(new SpinnerNumberModel(30, 5, 300, 5));
        mainPanel.add(durationSpinner, gbc);
        
        // Calories
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(new JLabel("Calories Burned:"), gbc);
        
        gbc.gridx = 1;
        caloriesSpinner = new JSpinner(new SpinnerNumberModel(200, 50, 2000, 50));
        mainPanel.add(caloriesSpinner, gbc);
        
        // Submit Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        submitButton = new JButton("Log Workout");
        submitButton.addActionListener(e -> handleWorkoutSubmission());
        mainPanel.add(submitButton, gbc);
        
        add(mainPanel);
    }
    
    private void handleWorkoutSubmission() {
        String workoutType = (String) workoutTypeCombo.getSelectedItem();
        int duration = (Integer) durationSpinner.getValue();
        int calories = (Integer) caloriesSpinner.getValue();
        
        Workout workout = new Workout(0, member.getId(), workoutType, duration, calories);
        
        try {
            Future<Boolean> result = gamificationService.processWorkoutAsync(member, workout);
            
            if (result.get()) {
                // Update member object
                MemberDAO memberDAO = new MemberDAO();
                Member updatedMember = memberDAO.getMemberById(member.getId());
                member.setPoints(updatedMember.getPoints());
                member.setLevel(updatedMember.getLevel());
                member.setWorkoutsCompleted(updatedMember.getWorkoutsCompleted());
                
                JOptionPane.showMessageDialog(this, 
                    "Workout logged successfully!\nPoints earned: " + workout.getPointsEarned(), 
                    "Success", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                parentDashboard.refreshUserInfo();
                dispose();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error logging workout: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
