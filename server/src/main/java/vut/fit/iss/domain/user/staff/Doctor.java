package vut.fit.iss.domain.user.staff;

import vut.fit.iss.domain.other.Department;
import vut.fit.iss.domain.user.UserRole;
import vut.fit.iss.domain.user.account.Account;

import javax.persistence.*;
import java.util.Date;

@Entity
@DiscriminatorValue("DOCTOR")
public class Doctor extends Staff {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "department_id")
    private Department department;

    public Doctor() {
        // JPA
    }

    public Doctor(String firstName, String lastName, Date birthdate, String telephone, String address, UserRole role, Account account, Department department) {
        super(firstName, lastName, birthdate, telephone, address, role, account);
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
