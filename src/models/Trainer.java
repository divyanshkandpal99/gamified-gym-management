package models;

// Inheritance demonstrating Polymorphism
public class Trainer extends User {
    private String specialization;
    private int yearsOfExperience;
    private double rating;
    private int clientsHandled;
    
    public Trainer() {
        super();
    }
    
    public Trainer(int id, String username, String email, String password, String specialization, int yearsOfExperience) {
        super(id, username, email, password);
        this.specialization = specialization;
        this.yearsOfExperience = yearsOfExperience;
        this.rating = 0.0;
        this.clientsHandled = 0;
    }
    
    @Override
    public String getUserType() {
        return "TRAINER";
    }
    
    @Override
    public void displayDashboard() {
        System.out.println("=== Trainer Dashboard ===");
        System.out.println("Username: " + username);
        System.out.println("Specialization: " + specialization);
        System.out.println("Experience: " + yearsOfExperience + " years");
        System.out.println("Rating: " + rating);
        System.out.println("Clients: " + clientsHandled);
    }
    
    public void assignWorkout(Member member, Workout workout) {
        System.out.println("Trainer " + username + " assigned workout to " + member.getUsername());
    }
    
    // Getters and Setters
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    
    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }
    
    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }
    
    public int getClientsHandled() { return clientsHandled; }
    public void setClientsHandled(int clientsHandled) { this.clientsHandled = clientsHandled; }
}
