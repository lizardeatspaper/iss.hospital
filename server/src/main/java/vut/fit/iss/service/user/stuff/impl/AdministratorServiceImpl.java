package vut.fit.iss.service.user.stuff.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.user.staff.Administrator;
import vut.fit.iss.repository.user.stuff.AdministratorRepository;
import vut.fit.iss.service.user.stuff.AdministratorService;

import java.util.Collection;
import java.util.Optional;

@Service
public class AdministratorServiceImpl implements AdministratorService {
    private final AdministratorRepository repository;

    @Autowired
    public AdministratorServiceImpl(AdministratorRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Administrator> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Collection<Administrator> getAll() {
        return repository.findAll();
    }

    @Override
    public Administrator persist(Administrator administrator) {
        return repository.save(administrator);
    }
}
