package vut.fit.iss.service.other.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.dto.MedicalHistoryDTO;
import vut.fit.iss.domain.other.MedicalHistory;
import vut.fit.iss.domain.user.Patient;
import vut.fit.iss.domain.user.staff.Doctor;
import vut.fit.iss.repository.other.MedicalHistoryRepository;
import vut.fit.iss.service.other.MedicalHistoryService;
import vut.fit.iss.service.user.PatientService;
import vut.fit.iss.service.user.stuff.DoctorService;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;
import java.util.Optional;

@Service
public class MedicalHistoryServiceImpl implements MedicalHistoryService {
    private final MedicalHistoryRepository repository;
    private final PatientService patientService;
    private final DoctorService doctorService;

    @Autowired
    public MedicalHistoryServiceImpl(MedicalHistoryRepository repository, PatientService patientService, DoctorService doctorService) {
        this.repository = repository;
        this.patientService = patientService;
        this.doctorService = doctorService;
    }

    @Override
    public Optional<MedicalHistory> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Collection<MedicalHistory> getByPatientId(Long patientId) {
        return repository.findByPatientId(patientId);
    }

    @Override
    public MedicalHistory create(MedicalHistoryDTO dto) {
        Optional<Patient> patientOpt = patientService.getById(dto.getPatientId());
        Patient patient = null;
        if (!patientOpt.isPresent()) {
            throw new EntityNotFoundException("Patient with id: " + dto.getPatientId() + "does not exist");
        }
        patient = patientOpt.get();
        Optional<Doctor> doctorOpt = doctorService.getById(dto.getDoctorId());
        if (!doctorOpt.isPresent()) {
            throw new EntityNotFoundException("Doctor with id: " + dto.getDoctorId() + "does not exist");
        }

        Doctor doctor = doctorOpt.get();
        return new MedicalHistory(patient, doctor, dto.getDescription());

    }

    @Override
    public MedicalHistory persist(MedicalHistory medicalHistory) {
        return repository.save(medicalHistory);
    }

    @Override
    public void delete(MedicalHistory medicalHistory) {
        repository.delete(medicalHistory);
    }
}
