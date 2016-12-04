package vut.fit.iss.service.user.stuff;

import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.user.staff.Staff;

import java.util.Collection;
import java.util.Optional;

public interface StaffService {
    @Transactional(readOnly = true)
    Optional<Staff> getById(Long id);

    @Transactional(readOnly = true)
    Collection<Staff> getAll();

    @Transactional
    Staff persist(Staff staff);

    @Transactional
    boolean isStaffExist(String username);

}
