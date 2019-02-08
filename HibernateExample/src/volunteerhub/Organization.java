package volunteerhub;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity(name = "Organization")
@Table(name = "organization")
public class Organization implements Serializable{
    @Id
    @GeneratedValue
    @Column(name = "organization_id")
    private int organizationId;

    @Column(name = "name")
    private String name;

    @Column(name = "service_category")
    private String serviceCategory;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "state")
    private String state;

    @Column(name = "zip")
    private String zip;

    @Column(name = "URL")
    private String URL;

    @Column(name = "contact_name")
    private String contactName;

    @Column(name = "contact_title")
    private String contactTitle;

    @Column(name = "contact_email_address")
    private String contactEmailAddress;

    @Column(name = "contact_phone_nbr")
    private String contactPhoneNbr;

    @Column(name = "active")
    private char active;

    // @Column(name = "user_id")
    // int userId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "organization")
    private List<Opportunities> opportunities;

    public Organization() {
    }

    public Organization(String name, String serviceCategory, String description, String city, String state, String zip, String URL, String contactName, String contactTitle, String contactEmailAddress, String contactPhoneNbr, char active, int userId) {
        this.name = name;
        this.serviceCategory = serviceCategory;
        this.description = description;
        this.city = city;
        this.state = state;
        this.zip = zip;
        this.URL = URL;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.contactEmailAddress = contactEmailAddress;
        this.contactPhoneNbr = contactPhoneNbr;
        this.active = active;
        // this.user = user;
    }

    public int getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(int organizationId) {
        this.organizationId = organizationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getServiceCategory() {
        return serviceCategory;
    }

    public void setServiceCategory(String serviceCategory) {
        this.serviceCategory = serviceCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getContactEmailAddress() {
        return contactEmailAddress;
    }

    public void setContactEmailAddress(String contactEmailAddress) {
        this.contactEmailAddress = contactEmailAddress;
    }

    public String getContactPhoneNbr() {
        return contactPhoneNbr;
    }

    public void setContactPhoneNbr(String contactPhoneNbr) {
        this.contactPhoneNbr = contactPhoneNbr;
    }

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Opportunities> getOpportunities() {
        return opportunities;
    }

    public void setOpportunities(List<Opportunities> opportunities) {
        this.opportunities = opportunities;
    }

}
