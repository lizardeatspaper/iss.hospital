package vut.fit.iss.service.user.stuff.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.user.User;
import vut.fit.iss.domain.user.staff.Staff;
import vut.fit.iss.repository.user.UserRepository;
import vut.fit.iss.repository.user.stuff.StaffRepository;
import vut.fit.iss.service.user.stuff.StaffService;

import java.util.Collection;
import java.util.Optional;

@Service
public class StaffServiceImpl implements StaffService {
    private final StaffRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public StaffServiceImpl(StaffRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
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

    @Override
    public boolean isStaffExist(String username) {
        Optional<User> currentStaff = userRepository.findByAccountUserName(username);
        return currentStaff.isPresent();

    }
}
