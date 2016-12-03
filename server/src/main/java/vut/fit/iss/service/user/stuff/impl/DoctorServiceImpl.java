package vut.fit.iss.service.user.stuff.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.user.staff.Doctor;
import vut.fit.iss.repository.user.stuff.DoctorRepository;
import vut.fit.iss.service.user.stuff.DoctorService;

import java.util.Collection;
import java.util.Optional;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorRepository repository;

    @Autowired
    public DoctorServiceImpl(DoctorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Doctor> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Collection<Doctor> getAll() {
        return repository.findAll();
    }

    @Override
    public Doctor persist(Doctor doctor) {
        return repository.save(doctor);
    }

    @Override
    public void delete(Doctor doctor) {
        repository.delete(doctor);
    }
}
