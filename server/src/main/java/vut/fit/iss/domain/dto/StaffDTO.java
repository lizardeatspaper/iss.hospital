package vut.fit.iss.domain.dto;

import org.hibernate.validator.constraints.Length;
import vut.fit.iss.domain.user.UserRole;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class StaffDTO {
    @NotNull
    @Length(max = 100)
    private String firstName;
    @NotNull
    @Length(max = 100)
    private String lastName;
    private Date birthdate;
    @Length(max = 10)
    private String telephone;
    @Length(max = 500)
    private String address;
    @NotNull
    private UserRole role;
    @NotNull
    private String username;

    private String password;

    private Long departmentId;

    public StaffDTO() {
    }

    public StaffDTO(String firstName, String lastName, Date birthdate, String telephone, String address, UserRole role, String username, Long departmentId, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.telephone = telephone;
        this.address = address;
        this.role = role;
        this.username = username;
        this.departmentId = departmentId;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
