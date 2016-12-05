package vut.fit.iss.domain.dto;

import vut.fit.iss.domain.user.UserRole;

import java.util.Date;

public class StaffDTO extends AbstractUserDTO {

    private Long departmentId;

    public StaffDTO() {
    }

    public StaffDTO(String firstName, String lastName, Date birthdate, String telephone, String address, UserRole role, String username, Long departmentId, String password) {
        super(firstName, lastName, birthdate, telephone, address, role, username, password);
        this.departmentId = departmentId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }
}
