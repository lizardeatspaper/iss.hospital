package vut.fit.iss.service.other;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.dto.DepartmentDTO;
import vut.fit.iss.domain.other.Department;

import java.util.Collection;
import java.util.Optional;

public interface DepartmentService {

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    Optional<Department> getDepartmentById(Long id);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    Collection<Department> getAllDepartments();

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Department persist(Department department);

    @Transactional
    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    boolean isDepartmentExist(String name);

    @Transactional(readOnly = true)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    Department create(DepartmentDTO dto);

    @Transactional
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    void delete(Department department);

}
