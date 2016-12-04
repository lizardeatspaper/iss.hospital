package vut.fit.iss.domain.other;

import vut.fit.iss.domain.basic.BaseObject;
import vut.fit.iss.domain.user.Patient;
import vut.fit.iss.domain.user.staff.Doctor;

import javax.persistence.*;

@Entity
@Table(name = "medicalHistory")
public class MedicalHistory extends BaseObject {
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id", nullable = false, updatable = false)
    Patient patient;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "doctor_id", nullable = false, updatable = false)
    Doctor doctor;
    @Column(name = "title", nullable = false)
    String title;
    @Column(name = "description", nullable = false)
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
