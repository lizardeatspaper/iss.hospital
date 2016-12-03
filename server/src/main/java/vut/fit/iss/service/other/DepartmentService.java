package vut.fit.iss.service.other;

import org.springframework.transaction.annotation.Transactional;
import vut.fit.iss.domain.other.Department;

import java.util.Collection;
import java.util.Optional;

public interface DepartmentService {
    @Transactional(readOnly = true)
    Optional<Department> getDepartmentById(Long id);

    @Transactional(readOnly = true)
    Optional<Department> getDepartmentByName(String name);

    @Transactional(readOnly = true)
    Collection<Department> getAllDepartments();

    @Transactional(readOnly = false)
    Department persist(Department department);
}
