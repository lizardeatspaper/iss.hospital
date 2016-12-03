package vut.fit.iss.service.user;

import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.user.User;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    @Transactional(readOnly = true)
    Optional<User> getByUserName(String userName);

    @Transactional(readOnly = true)
    Optional<User> getById(Long id);

    @Transactional(readOnly = true)
    Collection<User> getAll();

    @Transactional(readOnly = false)
    User persist(User user);
}
