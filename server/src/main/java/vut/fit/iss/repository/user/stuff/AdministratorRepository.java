package vut.fit.iss.repository.user.stuff;

import org.springframework.data.jpa.repository.JpaRepository;
import vut.fit.iss.domain.user.staff.Administrator;

public interface AdministratorRepository extends JpaRepository<Administrator, Long> {
}
