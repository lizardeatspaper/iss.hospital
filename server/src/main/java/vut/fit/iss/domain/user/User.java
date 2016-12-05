package vut.fit.iss.domain.user;

import org.hibernate.validator.constraints.Length;
import vut.fit.iss.domain.basic.BaseObject;
import vut.fit.iss.domain.user.account.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "role")
public abstract class User extends BaseObject {
    @NotNull
    @Length(max = 100)
    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;
    @NotNull
    @Length(max = 100)
    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;
    @Column(name = "birthdate")
    private Date birthdate;
    @Length(max = 10)
    @Column(name = "telephone", length = 30)
    private String telephone;
    @Length(max = 500)
    @Column(name = "address", length = 500)
    private String address;
    @NotNull
    @Column(name = "role", nullable = false, insertable = false, updatable = false)
    @Enumerated(EnumType.STRING)
    private UserRole role;
    @NotNull
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "account_id", unique = true, nullable = false, updatable = false)
    private Account account;

    public User() {
        // User
    }

    public User(String firstName, String lastName, Date birthdate, String telephone, String address, UserRole role, Account account) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.address = address;
        this.role = role;
        this.account = account;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
