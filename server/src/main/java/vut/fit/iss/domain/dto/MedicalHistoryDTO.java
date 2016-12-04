package vut.fit.iss.domain.dto;

public class MedicalHistoryDTO {
    private Long patientId;
    private Long doctorId;
    private String title;
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
