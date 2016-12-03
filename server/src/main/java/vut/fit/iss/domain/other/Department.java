package vut.fit.iss.domain.other;

import vut.fit.iss.domain.basic.BaseObject;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "department")
public class Department extends BaseObject {
    @Column(name = "name", nullable = false, unique = true, length = 10)
    private String name;

    public Department() {
        //JPA
    }

    public Department(String name) {
        this();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
