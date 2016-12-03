package vut.fit.iss.service.user.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.user.account.Account;
import vut.fit.iss.repository.user.account.AccountRepository;
import vut.fit.iss.service.user.account.AccountService;

import java.util.Collection;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    AccountRepository repository;

    @Autowired
    public AccountServiceImpl(AccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Account> getUserByUserName(String userName) {
        return repository.findByUserName(userName);
    }

    @Override
    public Optional<Account> getUserByID(Long id) {
        return Optional.ofNullable(repository.findOne(id));
    }

    @Override
    public Collection<Account> getAllAccounts() {
        return repository.findAll(new Sort("userName"));
    }

    @Override
    public Account persist(Account account) {
        return repository.save(account);
    }
}
