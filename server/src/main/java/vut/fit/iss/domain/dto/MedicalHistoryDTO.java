package vut.fit.iss.domain.dto;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class MedicalHistoryDTO {
    @NotNull
    private Long patientId;
    @NotNull
    private Long doctorId;
    @NotNull
    @Length(max = 255)
    private String title;
    @NotNull
    @Length(max = 500)
    private String description;

    public MedicalHistoryDTO(Long patientId, Long doctorId, String title, String description) {
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(Long doctorId) {
        this.doctorId = doctorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
