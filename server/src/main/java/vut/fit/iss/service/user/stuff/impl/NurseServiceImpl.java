package vut.fit.iss.service.user.stuff.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.user.staff.Nurse;
import vut.fit.iss.repository.user.stuff.NurseRepository;
import vut.fit.iss.service.user.stuff.NurseService;

import java.util.Collection;
import java.util.Optional;

@Service
public class NurseServiceImpl implements NurseService {
    private final NurseRepository repository;

    @Autowired
    public NurseServiceImpl(NurseRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Nurse> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Collection<Nurse> getAll() {
        return repository.findAll();
    }

    @Override
    public Nurse persist(Nurse nurse) {
        return repository.save(nurse);
    }

    @Override
    public void delete(Nurse nurse) {
        repository.delete(nurse);
    }
}
