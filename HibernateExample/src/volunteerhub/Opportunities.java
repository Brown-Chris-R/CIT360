package volunteerhub;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.util.List;

@Entity(name = "Opportunities")
@Table(name = "opportunities")
public class Opportunities implements Serializable {
    @Id
    @GeneratedValue
    @Column(name = "opportunity_id")
    int opportunityId;

    @Column(name = "description")
    String description;

    @Column(name = "nbr_of_volunteers")
    byte numberOfVolunteers;

    @Column(name = "event_start_date")
    Date eventStartDate;

    @Column(name = "event_start_time")
    Time eventStartTime;

    @Column(name = "event_end_date")
    Date eventEndDate;

    @Column(name = "event_end_time")
    Time eventEndTime;

    @Column(name = "active")
    char active;

    @Column(name = "user_id")
    int userId;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "opportunities")
    private List<Volunteer> volunteers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id", referencedColumnName = "organization_id")
    private Organization organization;

    public Opportunities() {
    }

    public Opportunities(String description, byte numberOfVolunteers, Date eventStartDate, Time eventStartTime, Date eventEndDate, Time eventEndTime, char active, int userId) {
        this.description = description;
        this.numberOfVolunteers = numberOfVolunteers;
        this.eventStartDate = eventStartDate;
        this.eventStartTime = eventStartTime;
        this.eventEndDate = eventEndDate;
        this.eventEndTime = eventEndTime;
        this.active = active;
        this.userId = userId;
    }

    public int getOpportunityId() {
        return opportunityId;
    }

    public void setOpportunityId(int opportunityId) {
        this.opportunityId = opportunityId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getNumberOfVolunteers() {
        return numberOfVolunteers;
    }

    public void setNumberOfVolunteers(byte numberOfVolunteers) {
        this.numberOfVolunteers = numberOfVolunteers;
    }

    public Date getEventStartDate() {
        return eventStartDate;
    }

    public void setEventStartDate(Date eventStartDate) {
        this.eventStartDate = eventStartDate;
    }

    public Time getEventStartTime() {
        return eventStartTime;
    }

    public void setEventStartTime(Time eventStartTime) {
        this.eventStartTime = eventStartTime;
    }

    public Date getEventEndDate() {
        return eventEndDate;
    }

    public void setEventEndDate(Date eventEndDate) {
        this.eventEndDate = eventEndDate;
    }

    public Time getEventEndTime() {
        return eventEndTime;
    }

    public void setEventEndTime(Time eventEndTime) {
        this.eventEndTime = eventEndTime;
    }

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public List<Volunteer> getVolunteers() {
        return volunteers;
    }

    public void setVolunteers(List<Volunteer> volunteers) {
        this.volunteers = volunteers;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganizations(Organization organization) {
        this.organization = organization;
    }

}
