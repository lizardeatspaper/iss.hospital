package vut.fit.iss.service.user.stuff;

import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.user.staff.Administrator;

import java.util.Collection;
import java.util.Optional;

public interface AdministratorService {
    @Transactional(readOnly = true)
    Optional<Administrator> getById(Long id);

    @Transactional(readOnly = true)
    Collection<Administrator> getAll();

    @Transactional
    Administrator persist(Administrator administrator);
}
