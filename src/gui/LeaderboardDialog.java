package gui;

import exceptions.DatabaseException;
import models.Member;
import services.GamificationService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.concurrent.Future;

public class LeaderboardDialog extends JDialog {
    private JTable leaderboardTable;
    private DefaultTableModel tableModel;
    private GamificationService gamificationService;
    
    public LeaderboardDialog(MainDashboard parent) {
        super(parent, "Leaderboard", true);
        
        try {
            gamificationService = new GamificationService();
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Service initialization failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        setSize(600, 400);
        setLocationRelativeTo(parent);
        initComponents();
        loadLeaderboard();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Title
        JLabel titleLabel = new JLabel("Top Members Leaderboard");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"Rank", "Username", "Level", "Points", "Workouts"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        leaderboardTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(leaderboardTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // Close Button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadLeaderboard() {
        try {
            Future<List<Member>> future = gamificationService.calculateLeaderboardAsync();
            List<Member> members = future.get();
            
            int rank = 1;
            for (Member member : members) {
                Object[] row = {
                    rank++,
                    member.getUsername(),
                    member.getLevel(),
                    member.getPoints(),
                    member.getWorkoutsCompleted()
                };
                tableModel.addRow(row);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error loading leaderboard: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
