package vut.fit.iss.domain.dto;

import vut.fit.iss.domain.other.MedicalHistory;

import java.util.Date;

public class MedicalHistoryResponseDTO {
    private Long id;
    private String title;
    private Date date;

    public MedicalHistoryResponseDTO(Long id, String title, Date date) {
        this.id = id;
        this.title = title;
        this.date = date;
    }

    public MedicalHistoryResponseDTO(MedicalHistory history) {
        this.id = history.getId();
        this.title = history.getTitle();
        this.date = history.getCreatedDate();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
