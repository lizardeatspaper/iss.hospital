package vut.fit.iss.domain.basic;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

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

    /**
     * @return {@link #id}
     */
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
