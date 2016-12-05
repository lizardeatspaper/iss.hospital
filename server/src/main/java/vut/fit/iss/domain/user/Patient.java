package vut.fit.iss.domain.user;

import org.hibernate.validator.constraints.Length;
import vut.fit.iss.domain.user.account.Account;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@DiscriminatorValue("PATIENT")
public class Patient extends User {
    @NotNull
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PatientStatus status;
    @NotNull
    @Length(max = 50)
    @Column(name = "insurance_number", length = 50)
    private String insuranceNumber;

    public Patient() {
        // JPA
    }

    public Patient(String firstName, String lastName, Date birthdate, String telephone, String address, UserRole role, Account account, PatientStatus status, String insuranceNumber) {
        super(firstName, lastName, birthdate, telephone, address, role, account);
        this.status = status;
        this.insuranceNumber = insuranceNumber;
    }

    public PatientStatus getStatus() {
        return status;
    }

    public void setStatus(PatientStatus status) {
        this.status = status;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }
}
