package vut.fit.iss.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.user.Patient;
import vut.fit.iss.domain.user.User;
import vut.fit.iss.repository.user.PatientRepository;
import vut.fit.iss.repository.user.UserRepository;
import vut.fit.iss.service.user.PatientService;

import java.util.Collection;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public PatientServiceImpl(PatientRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
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

    @Override
    public boolean isPatientExist(Patient patient) {
        Optional<User> currentStaff = userRepository.findByAccountUserName(patient.getAccount().getUserName());
        return currentStaff.isPresent();
    }

    @Override
    public void delete(Patient patient) {
        repository.delete(patient);
    }
}
