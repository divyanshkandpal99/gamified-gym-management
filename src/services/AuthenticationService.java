package services;

import dao.MemberDAO;
import dao.TrainerDAO;
import exceptions.DatabaseException;
import exceptions.InvalidCredentialsException;
import exceptions.UserNotFoundException;
import models.Member;
import models.Trainer;
import models.User;

// Service for authentication with exception handling
public class AuthenticationService {
    private MemberDAO memberDAO;
    private TrainerDAO trainerDAO;
    
    public AuthenticationService() throws DatabaseException {
        this.memberDAO = new MemberDAO();
        this.trainerDAO = new TrainerDAO();
    }
    
    public User login(String username, String password, String userType) throws InvalidCredentialsException, DatabaseException {
        try {
            if (userType.equalsIgnoreCase("MEMBER")) {
                Member member = memberDAO.getMemberByUsername(username);
                if (member.getPassword().equals(password)) {
                    return member;
                } else {
                    throw new InvalidCredentialsException("Invalid password for member: " + username);
                }
            } else if (userType.equalsIgnoreCase("TRAINER")) {
                // For trainers, we'd need to implement similar logic
                throw new InvalidCredentialsException("Trainer login not fully implemented");
            } else {
                throw new InvalidCredentialsException("Invalid user type: " + userType);
            }
        } catch (UserNotFoundException e) {
            throw new InvalidCredentialsException("User not found: " + username);
        }
    }
    
    public Member registerMember(String username, String email, String password, String membershipType) throws DatabaseException {
        Member member = new Member(0, username, email, password, membershipType);
        memberDAO.addMember(member);
        return member;
    }
    
    public Trainer registerTrainer(String username, String email, String password, String specialization, int experience) throws DatabaseException {
        Trainer trainer = new Trainer(0, username, email, password, specialization, experience);
        trainerDAO.addTrainer(trainer);
        return trainer;
    }
}
