package dao;

import database.DatabaseConnection;
import database.MockDatabase;
import exceptions.DatabaseException;
import exceptions.UserNotFoundException;
import models.Member;

import java.util.List;

// Database operations class for Members
public class MemberDAO {
    private MockDatabase db;
    
    public MemberDAO() throws DatabaseException {
        DatabaseConnection.getInstance(); // Initialize connection
        this.db = MockDatabase.getInstance();
    }
    
    // Create
    public synchronized void addMember(Member member) throws DatabaseException {
        db.addMember(member);
    }
    
    // Read
    public Member getMemberById(int id) throws DatabaseException, UserNotFoundException {
        Member member = db.getMemberById(id);
        if (member == null) {
            throw new UserNotFoundException("Member with ID " + id + " not found");
        }
        return member;
    }
    
    public Member getMemberByUsername(String username) throws DatabaseException, UserNotFoundException {
        Member member = db.getMemberByUsername(username);
        if (member == null) {
            throw new UserNotFoundException("Member " + username + " not found");
        }
        return member;
    }
    
    public List<Member> getAllMembers() throws DatabaseException {
        return db.getAllMembers();
    }
    
    // Update
    public synchronized void updateMember(Member member) throws DatabaseException {
        db.updateMember(member);
    }
    
    // Delete
    public synchronized void deleteMember(int id) throws DatabaseException {
        // Not implemented in mock
    }
}
