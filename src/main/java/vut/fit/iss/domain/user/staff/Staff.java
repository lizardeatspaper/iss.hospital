package vut.fit.iss.domain.user.staff;

import vut.fit.iss.domain.user.User;
import vut.fit.iss.domain.user.UserRole;
import vut.fit.iss.domain.user.account.Account;

import javax.persistence.Entity;
import java.util.Date;

@Entity
public class Staff extends User {
    public Staff() {
    }

    public Staff(String firstName, String lastName, Date birthdate, String telephone, String address, UserRole role, Account account) {
        super(firstName, lastName, birthdate, telephone, address, role, account);
    }
}
