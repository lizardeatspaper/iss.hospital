package vut.fit.iss.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import vut.fit.iss.domain.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByAccountUserName(String userName);
}
