package vut.fit.iss.service.user.stuff;

import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.user.staff.Nurse;

import java.util.Collection;
import java.util.Optional;

public interface NurseService {
    @Transactional(readOnly = true)
    Optional<Nurse> getById(Long id);

    @Transactional(readOnly = true)
    Collection<Nurse> getAll();

    @Transactional
    Nurse persist(Nurse nurse);

    @Transactional
    void delete(Nurse nurse);
}
