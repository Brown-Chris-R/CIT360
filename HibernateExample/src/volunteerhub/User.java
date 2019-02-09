package volunteerhub;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "User")
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int userId;

    @Column(name = "user")
    private String user;

    @Column(name = "password")
    private String password;

    @Column(name = "name")
    private String name;

    @Column(name = "is_admin")
    private byte isAdmin;

    @Column(name = "active")
    private char active;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", fetch = FetchType.EAGER)
    private List<Organization> organizations;

    // Default Constructor
    public User() {}

    public User(String user, String password, String name, byte isAdmin, char active) {
        this.user = user;
        this.password = password;
        this.name = name;
        this.isAdmin = isAdmin;
        this.active = active;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(byte isAdmin) {
        this.isAdmin = isAdmin;
    }

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }

    public List<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganization(List<Organization> organizations) {
        this.organizations = organizations;
    }
}

