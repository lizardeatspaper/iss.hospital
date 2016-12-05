package vut.fit.iss.domain.basic;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Базовый класс в котором будут находится общие атрибуты всех классов
 *
 * @since 0.1
 */
@MappedSuperclass
public abstract class BaseObject {
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    @Column(name = "id", nullable = false, updatable = false)
    protected Long id;

    @CreatedDate
    @NotNull
    @Column(name = "created_date", nullable = false, updatable = false)
    private Date createdDate = new Date();

    @LastModifiedDate
    @Column(name = "last_modified_date")
    private Date lastModifiedDate = new Date();

    /**
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }
}
