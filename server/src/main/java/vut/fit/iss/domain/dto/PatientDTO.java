package vut.fit.iss.domain.dto;

import org.hibernate.validator.constraints.Length;
import vut.fit.iss.domain.user.PatientStatus;
import vut.fit.iss.domain.user.UserRole;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class PatientDTO extends AbstractUserDTO {
    @NotNull
    private PatientStatus status;
    @NotNull
    @Length(max = 50)
    private String insuranceNumber;

    public PatientDTO(String firstName, String lastName,
                      Date birthdate, String telephone,
                      String address, UserRole role, String username,
                      String password, PatientStatus status, String insuranceNumber) {
        super(firstName, lastName, birthdate, telephone, address, role, username, password);
        this.status = status;
        this.insuranceNumber = insuranceNumber;
    }

    public PatientDTO() {
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
