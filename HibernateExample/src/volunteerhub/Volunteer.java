package volunteerhub;

import javax.persistence.*;
import java.io.Serializable;

@Entity(name = "Volunteer")
@Table(name = "volunteer")
public class Volunteer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "volunteer_id")
    int volunteerId;

    @Column(name = "name")
    String name;

    @Column(name = "description")
    String description;

    @Column(name = "email_address")
    String emailAddress;

    @Column(name = "phone_nbr")
    String phoneNumber;

    @Column(name = "active")
    char active;

    // @Column(name = "opportunity_id")
    // int opportunityId;
    @ManyToOne()
    @JoinColumn(name = "opportunity_id", referencedColumnName = "opportunity_id")
    private Opportunities opportunities;

    // Default Constructor
    public Volunteer() {}

    public Volunteer(String name, String description, String emailAddress, String phoneNumber, char active, Opportunities opportunity) {
        this.name = name;
        this.description = description;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.active = active;
        this.opportunities = opportunity;
    }

    public int getVolunteerId() {
        return volunteerId;
    }

    public void setVolunteerId(int volunteerId) {
        this.volunteerId = volunteerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }

//    public int getOpportunityId() {
//        return opportunityId;
//    }

//    public void setOpportunityId(int opportunityId) {
//        this.opportunityId = opportunityId;
//    }

    public Opportunities getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(Opportunities opportunities) {
        this.opportunities = opportunities;
    }

}
