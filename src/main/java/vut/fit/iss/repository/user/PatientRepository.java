package vut.fit.iss.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import vut.fit.iss.domain.user.Patient;

public interface PatientRepository extends JpaRepository<Patient,Long> {
}
