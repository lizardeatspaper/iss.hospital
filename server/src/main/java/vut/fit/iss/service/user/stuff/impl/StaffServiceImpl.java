package vut.fit.iss.service.user.stuff.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.user.staff.Staff;
import vut.fit.iss.repository.user.stuff.StaffRepository;
import vut.fit.iss.service.user.stuff.StaffService;

import java.util.Collection;
import java.util.Optional;

@Service

public class StaffServiceImpl implements StaffService {
    private final StaffRepository repository;

    @Autowired
    public StaffServiceImpl(StaffRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Staff> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Collection<Staff> getAll() {
        return repository.findAll();
    }

    @Override
    public Staff persist(Staff staff) {
        return repository.save(staff);
    }
}
