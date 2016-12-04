package vut.fit.iss.domain.other;

import org.hibernate.validator.constraints.Length;
import vut.fit.iss.domain.basic.BaseObject;
import vut.fit.iss.domain.user.Patient;
import vut.fit.iss.domain.user.staff.Doctor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "medicalHistory")
public class MedicalHistory extends BaseObject {
    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false, updatable = false)
    Patient patient;

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false, updatable = false)
    Doctor doctor;

    @NotNull
    @Length(max = 255)
    @Column(name = "title", nullable = false)
    String title;

    @NotNull
    @Length(max = 500)
    @Column(name = "description", nullable = false, length = 500)
    String description;

    public MedicalHistory() {
        //JPA
    }

    public MedicalHistory(Patient patient, Doctor doctor, String title, String description) {
        this.patient = patient;
        this.doctor = doctor;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
