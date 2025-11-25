package gui;

import dao.ChallengeDAO;
import exceptions.DatabaseException;
import models.Challenge;
import models.Member;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ChallengesDialog extends JDialog {
    private Member member;
    private JTable challengesTable;
    private DefaultTableModel tableModel;
    private ChallengeDAO challengeDAO;
    
    public ChallengesDialog(MainDashboard parent, Member member) {
        super(parent, "Active Challenges", true);
        this.member = member;
        
        try {
            challengeDAO = new ChallengeDAO();
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        setSize(700, 400);
        setLocationRelativeTo(parent);
        initComponents();
        loadChallenges();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Title
        JLabel titleLabel = new JLabel("Available Challenges");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titleLabel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"Name", "Description", "Difficulty", "Target", "Reward Points"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        challengesTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(challengesTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // Close Button
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose());
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadChallenges() {
        try {
            List<Challenge> challenges = challengeDAO.getActiveChallenges();
            
            for (Challenge challenge : challenges) {
                Object[] row = {
                    challenge.getName(),
                    challenge.getDescription(),
                    challenge.getDifficulty(),
                    challenge.getTargetValue(),
                    challenge.getRewardPoints()
                };
                tableModel.addRow(row);
            }
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Error loading challenges: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
