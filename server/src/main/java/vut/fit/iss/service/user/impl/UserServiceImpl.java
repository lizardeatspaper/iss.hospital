package vut.fit.iss.service.user.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.user.User;
import vut.fit.iss.repository.user.UserRepository;
import vut.fit.iss.service.user.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Autowired
    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<User> getByUserName(String userName) {
        return repository.findByAccountUserName(userName);
    }

    @Override
    public Optional<User> getById(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

}
