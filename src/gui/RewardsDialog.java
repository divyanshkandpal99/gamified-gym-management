package gui;

import dao.MemberDAO;
import dao.RewardDAO;
import exceptions.DatabaseException;
import exceptions.InsufficientPointsException;
import models.Member;
import models.Reward;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class RewardsDialog extends JDialog {
    private Member member;
    private JTable rewardsTable;
    private DefaultTableModel tableModel;
    private RewardDAO rewardDAO;
    private MemberDAO memberDAO;
    private JLabel pointsLabel;
    private MainDashboard parentDashboard;
    
    public RewardsDialog(MainDashboard parent, Member member) {
        super(parent, "Rewards Store", true);
        this.parentDashboard = parent;
        this.member = member;
        
        try {
            rewardDAO = new RewardDAO();
            memberDAO = new MemberDAO();
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Database connection failed", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        setSize(700, 450);
        setLocationRelativeTo(parent);
        initComponents();
        loadRewards();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Top Panel
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Rewards Store");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        pointsLabel = new JLabel("Your Points: " + member.getPoints());
        pointsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        
        topPanel.add(titleLabel, BorderLayout.WEST);
        topPanel.add(pointsLabel, BorderLayout.EAST);
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(topPanel, BorderLayout.NORTH);
        
        // Table
        String[] columns = {"Name", "Description", "Category", "Points Cost"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        rewardsTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(rewardsTable);
        add(scrollPane, BorderLayout.CENTER);
        
        // Buttons
        JPanel buttonPanel = new JPanel();
        JButton redeemButton = new JButton("Redeem Selected");
        JButton closeButton = new JButton("Close");
        
        redeemButton.addActionListener(e -> handleRedemption());
        closeButton.addActionListener(e -> dispose());
        
        buttonPanel.add(redeemButton);
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    
    private void loadRewards() {
        try {
            List<Reward> rewards = rewardDAO.getAvailableRewards();
            
            for (Reward reward : rewards) {
                Object[] row = {
                    reward.getName(),
                    reward.getDescription(),
                    reward.getCategory(),
                    reward.getPointsCost()
                };
                tableModel.addRow(row);
            }
        } catch (DatabaseException e) {
            JOptionPane.showMessageDialog(this, "Error loading rewards: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void handleRedemption() {
        int selectedRow = rewardsTable.getSelectedRow();
        
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a reward to redeem", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        try {
            List<Reward> rewards = rewardDAO.getAvailableRewards();
            Reward selectedReward = rewards.get(selectedRow);
            
            if (!selectedReward.canRedeem(member.getPoints())) {
                throw new InsufficientPointsException("You need " + selectedReward.getPointsCost() + " points. You have " + member.getPoints());
            }
            
            selectedReward.redeem(member);
            memberDAO.updateMember(member);
            
            pointsLabel.setText("Your Points: " + member.getPoints());
            parentDashboard.refreshUserInfo();
            
            JOptionPane.showMessageDialog(this, "Reward redeemed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
        } catch (InsufficientPointsException | DatabaseException e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Redemption Failed", JOptionPane.ERROR_MESSAGE);
        }
    }
}
