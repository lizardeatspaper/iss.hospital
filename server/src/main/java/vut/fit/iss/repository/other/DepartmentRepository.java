package vut.fit.iss.repository.other;

import org.springframework.data.jpa.repository.JpaRepository;
import vut.fit.iss.domain.other.Department;

import java.util.Optional;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
    Optional<Department> findByName(String name);
}
