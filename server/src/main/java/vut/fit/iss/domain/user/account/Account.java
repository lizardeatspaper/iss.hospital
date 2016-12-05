package vut.fit.iss.domain.user.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vut.fit.iss.domain.basic.BaseObject;

import javax.persistence.*;

/**
 * Класс отвечающий за вход в систему и информацию о аккаунте
 */
@Entity
@Table(name = "account")
public class Account extends BaseObject {
    /**
     * Логин
     */
    @Column(name = "username", nullable = false, updatable = false, unique = true)
    private String userName;
    /**
     * Пароль
     */
    @Column(name = "password", nullable = false)
    @JsonIgnore
    private String password;

    public Account() {
        // JPA
    }

    public Account(String userName, String password) {
        this();
        this.userName = userName;
        this.password = new BCryptPasswordEncoder().encode(password);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
