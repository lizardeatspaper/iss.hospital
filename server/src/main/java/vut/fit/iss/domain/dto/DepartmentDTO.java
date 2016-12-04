package vut.fit.iss.domain.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class DepartmentDTO {
    @NotNull
    @Length(max = 10)
    private String name;

    public DepartmentDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
