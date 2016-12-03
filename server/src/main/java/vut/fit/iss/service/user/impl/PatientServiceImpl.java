package vut.fit.iss.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.user.Patient;
import vut.fit.iss.repository.user.PatientRepository;
import vut.fit.iss.service.user.PatientService;

import java.util.Collection;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repository;

    @Autowired
    public PatientServiceImpl(PatientRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Patient> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Collection<Patient> getAll() {
        return repository.findAll();
    }

    @Override
    public Patient persist(Patient patient) {
        return repository.save(patient);
    }
}
