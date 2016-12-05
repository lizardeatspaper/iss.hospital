package vut.fit.iss.repository.other;

import org.springframework.data.jpa.repository.JpaRepository;
import vut.fit.iss.domain.other.MedicalHistory;

import java.util.Collection;

public interface MedicalHistoryRepository extends JpaRepository<MedicalHistory, Long> {
    Collection<MedicalHistory> findByPatientId(Long id);
}
