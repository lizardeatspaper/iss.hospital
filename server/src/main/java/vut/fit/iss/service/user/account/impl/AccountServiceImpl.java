package vut.fit.iss.service.user.account.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import vut.fit.iss.domain.user.account.Account;
import vut.fit.iss.repository.user.account.AccountRepository;
import vut.fit.iss.service.user.account.AccountService;

import javax.persistence.EntityNotFoundException;
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
    public Collection<Account> getAllAccounts() {
        return repository.findAll(new Sort("userName"));
    }

    @Override
    public Account persist(Account account) {
        return repository.save(account);
    }

    @Override
    public Account getOrCreate(String login, String password) {
        Account account;
        Optional<Account> accountOptional = getUserByUserName(login);
        if (!accountOptional.isPresent()) {
            if (password != null) {
                account = new Account(login, password);
            } else {
                throw new EntityNotFoundException("Account with name: " + login + "does not exist");
            }
        } else {
            account = accountOptional.get();
        }
        return account;
    }
}
