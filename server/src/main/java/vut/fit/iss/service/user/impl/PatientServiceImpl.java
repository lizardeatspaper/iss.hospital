package vut.fit.iss.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.dto.PatientDTO;
import vut.fit.iss.domain.user.Patient;
import vut.fit.iss.domain.user.User;
import vut.fit.iss.domain.user.account.Account;
import vut.fit.iss.repository.user.PatientRepository;
import vut.fit.iss.repository.user.UserRepository;
import vut.fit.iss.service.user.PatientService;
import vut.fit.iss.service.user.account.AccountService;

import java.util.Collection;
import java.util.Optional;

@Service
public class PatientServiceImpl implements PatientService {
    private final PatientRepository repository;
    private final UserRepository userRepository;
    private final AccountService accountService;

    @Autowired
    public PatientServiceImpl(PatientRepository repository, UserRepository userRepository, AccountService accountService) {
        this.repository = repository;
        this.userRepository = userRepository;
        this.accountService = accountService;
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

        if(patient.getAccount().getId() == null) {
            patient.setAccount(accountService.persist(patient.getAccount()));
        }

        return repository.save(patient);
    }

    @Override
    public boolean isPatientExist(String username) {
        Optional<User> currentStaff = userRepository.findByAccountUserName(username);
        return currentStaff.isPresent();
    }

    @Override
    public void delete(Patient patient) {
        repository.delete(patient);
    }

    @Override
    public Patient create(PatientDTO dto) {
        Account account = accountService.getOrCreate(dto.getUsername(), dto.getPassword());
        return new Patient(dto.getFirstName(),
                dto.getLastName(), dto.getBirthdate(),
                dto.getTelephone(), dto.getAddress(),
                dto.getRole(), account, dto.getStatus(), dto.getInsuranceNumber());
    }
}
